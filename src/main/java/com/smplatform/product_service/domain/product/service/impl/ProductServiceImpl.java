package com.smplatform.product_service.domain.product.service.impl;

import com.smplatform.product_service.domain.category.repository.CategoryRepository;
import com.smplatform.product_service.domain.discount.repository.DiscountRepository;
import com.smplatform.product_service.domain.product.dto.ThumbnailRequestDto;
import com.smplatform.product_service.domain.product.dto.ThumbnailResponseDto;
import com.smplatform.product_service.domain.product.entity.ProductOption;
import com.smplatform.product_service.domain.product.entity.ProductOptionDetail;
import com.smplatform.product_service.domain.product.repository.ProductOptionDetailRepository;
import com.smplatform.product_service.domain.product.repository.ProductOptionRepository;
import com.smplatform.product_service.domain.product.dto.ProductRequestDto;
import com.smplatform.product_service.domain.product.dto.ProductResponseDto;
import com.smplatform.product_service.domain.product.entity.Product;
import com.smplatform.product_service.domain.product.exception.ProductNotFoundException;
import com.smplatform.product_service.domain.product.repository.ProductRepository;
import com.smplatform.product_service.domain.product.repository.ThumbnailRepository;
import com.smplatform.product_service.domain.product.service.ProductService;
import com.smplatform.product_service.domain.product.service.ThumbnailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final DiscountRepository discountRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ProductOptionDetailRepository productOptionDetailRepository;
    private final ThumbnailService thumbnailService;

    /**
     * 단일 제품 조회
     *
     * @param productId
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public ProductResponseDto.GetProduct getProduct(long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(String.format("product { %d } not found", productId)));

        List<ProductOption> productOptions = productOptionRepository.findAllByProduct_Id(productId);

        List<ProductResponseDto.GetProductOption> productOptionDtos = productOptions.stream()
                .map(productOption -> {
                    List<ProductOptionDetail> productOptionDetails = productOptionDetailRepository
                            .findAllByProductOption_ProductOptionId(productOption.getProductOptionId());
                    List<ProductResponseDto.GetProductOptionDetail> productOptionDetailDtos = productOptionDetails.stream()
                            .map(ProductResponseDto.GetProductOptionDetail::of)
                            .toList();
                    ProductResponseDto.GetProductOption productOptionDto = ProductResponseDto.GetProductOption.of(productOption);
                    productOptionDto.setProductOptionDetails(productOptionDetailDtos);
                    return productOptionDto;
                }).toList();

        ProductResponseDto.GetProduct productDto = ProductResponseDto.GetProduct.of(product);
        productDto.setProductOptions(productOptionDtos);
        return productDto;
    }

    /**
     * 단일 제품 등록
     *
     * @param productDto
     * @return
     */
    @Override
    @Transactional
    public String saveProduct(ProductRequestDto.SaveProduct productDto) {
        // 상품 저장
        Product product = productDto.toEntity();
        product.setCategory(
                categoryRepository.findById(productDto.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("category not found"))
        );
        productRepository.save(product);

        // 상품옵션 저장
        List<ProductRequestDto.SaveProductOption> productOptionDtos = productDto.getProductOptions();
        List<ProductOption> productOptions = productOptionDtos.stream()
                .map(productOptionDto -> {
                    ProductOption productOption = productOptionDto.toEntity();
                    productOption.setProduct(product);
                    return productOption;
                }).toList();
        List<ProductOption> savedProductOptions = productOptionRepository.saveAll(productOptions);

        // 옵션구성 저장
        List<ProductOptionDetail> productOptionDetails = IntStream.range(0, savedProductOptions.size()).boxed()
                .flatMap(index -> {
                    ProductOption savedProductOption = savedProductOptions.get(index);
                    return productOptionDtos.get(index).getProductOptionDetails().stream()
                            .map(dto -> {
                                ProductOptionDetail productOptionDetail = dto.toEntity();
                                productOptionDetail.setProductOption(savedProductOption);
                                return productOptionDetail;
                            });
                }).toList();
        productOptionDetailRepository.saveAll(productOptionDetails);

        // 썸네일 저장
        thumbnailService.saveThumbnails(
                product.getId(),
                productDto.getThumbnails().getPaths());

        // 상품 태그 저장


        return String.valueOf(product.getId());
    }

    /**
     * 단일 제품 수정
     *
     * @param productDto
     * @return
     */
    @Override
    public String updateProduct(ProductRequestDto.UpdateProduct productDto) {
        Product product = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new ProductNotFoundException(String.format("product id : %d not found", productDto.getId())));

        // 카테고리 수정
        product.setCategory(
                categoryRepository.findById(productDto.getCategoryId())
                        .orElseThrow(() -> new RuntimeException(String.format("category id : %d not found", productDto.getCategoryId())))
        );

        // 할인 수정
        if (!Objects.isNull(productDto.getDiscountId())) {
            product.setDiscount(
                    discountRepository.findById(productDto.getDiscountId())
                            .orElseThrow(() -> new RuntimeException(String.format("discount id : %d not found", productDto.getDiscountId())))
            );
        }
        BeanUtils.copyProperties(productDto, product, "id");

        // 썸네일 수정
        updateThumbnail(
                product,
                productDto.getThumbnails().getPaths()
        );

        return String.valueOf(productRepository.save(product).getId());
    }

    private void updateThumbnail(Product product, List<String> paths) {
        List<ThumbnailResponseDto.ThumbnailInfo> originalThumbnailInfoList = thumbnailService.getProductThumbnailList(product.getId());

        // 삭제된 항목 제거
        List<Long> toDeleteIds = originalThumbnailInfoList.stream()
                .filter(original -> !paths.contains(original.getPath()))
                .map(ThumbnailResponseDto.ThumbnailInfo::getThumbnailId)
                .collect(Collectors.toList());

        if (!toDeleteIds.isEmpty()) {
            thumbnailService.deleteThumbnail(toDeleteIds);
        }

        // 추가된 항목 저장
        List<String> originalPaths = originalThumbnailInfoList.stream()
                .map(ThumbnailResponseDto.ThumbnailInfo::getPath)
                .collect(Collectors.toList());

        List<String> toAdd = paths.stream()
                .filter(path -> !originalPaths.contains(path))
                .collect(Collectors.toList());

        if (!toAdd.isEmpty()) {
            thumbnailService.saveThumbnails(product.getId(), toAdd);
        }
    }

    /**
     * 전체 제품을 조회
     *
     * @param pageable
     * @return
     */
    @Override
    public List<ProductResponseDto.GetProduct> getProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        List<ProductResponseDto.GetProduct> resultProducts = new ArrayList<>();
        // N+1 entitygraph 사용
        for (Product product : products) {
            ProductResponseDto.GetProduct productDto = ProductResponseDto.GetProduct.of(product);
            productDto.setThumbnails(thumbnailService.getProductThumbnailList(product.getId()));
            resultProducts.add(productDto);
        }
        return resultProducts;
    }
}
