package com.smplatform.product_service.domain.product.service.impl;

import com.smplatform.product_service.domain.category.repository.CategoryRepository;
import com.smplatform.product_service.domain.discount.repository.DiscountRepository;
import com.smplatform.product_service.domain.product.dto.ProductImageResponseDto;
import com.smplatform.product_service.domain.product.entity.*;
import com.smplatform.product_service.domain.product.repository.*;
import com.smplatform.product_service.domain.product.dto.ProductRequestDto;
import com.smplatform.product_service.domain.product.dto.ProductResponseDto;
import com.smplatform.product_service.domain.product.exception.ProductNotFoundException;
import com.smplatform.product_service.domain.product.service.ProductService;
import com.smplatform.product_service.domain.product.service.ProductImageService;
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
import java.util.Optional;
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
    private final ProductImageService productImageService;
    private final TagRepository tagRepository;
    private final ProductTagRepository productTagRepository;

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

        // 상품이미지 저장
        productImageService.saveProductImages(
                product.getId(),
                productDto.getProductImages().getPaths());

        // 상품 태그 저장
        for (ProductRequestDto.SaveTag tagDto : productDto.getTags()) {
            Optional<Tag> optionalTag = tagRepository.findByTagName(tagDto.getTagName());

            if (optionalTag.isPresent()) {
                productTagRepository.save(new ProductTag(0L, product, optionalTag.get()));
            } else {
                Tag tag = tagRepository.save(tagDto.toEntity());
                productTagRepository.save(new ProductTag(0L, product, tag));
            }
        }

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
            product.setDiscount(discountRepository.findById(productDto.getDiscountId())
                    .orElseThrow(() -> new RuntimeException(String.format("discount id : %d not found", productDto.getDiscountId())))
            );
        }
        BeanUtils.copyProperties(productDto, product, "id");

        // 썸네일 수정
        updateProductImage(
                product,
                productDto.getProductImagePaths().getPaths()
        );

        return String.valueOf(productRepository.save(product).getId());
    }

    private void updateProductImage(Product product, List<String> paths) {
        List<ProductImageResponseDto.ProductImageInfo> originalProductImageInfoList =
                productImageService.getProductProductImageList(product.getId());

        // 삭제된 항목 제거
        List<Long> toDeleteIds = originalProductImageInfoList.stream()
                .filter(original -> !paths.contains(original.getPath()))
                .map(ProductImageResponseDto.ProductImageInfo::getProductImageId)
                .toList();

        if (!toDeleteIds.isEmpty()) {
            productImageService.deleteProductImage(toDeleteIds);
        }

        // 추가된 항목 저장
        List<String> originalPaths = originalProductImageInfoList.stream()
                .map(ProductImageResponseDto.ProductImageInfo::getPath)
                .toList();

        List<String> toAdd = paths.stream()
                .filter(path -> !originalPaths.contains(path))
                .toList();

        if (!toAdd.isEmpty()) {
            productImageService.saveProductImages(product.getId(), toAdd);
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
            productDto.setProductImagePaths(productImageService.getProductProductImageList(product.getId()));
            resultProducts.add(productDto);
        }
        return resultProducts;
    }


    /**
     * 일반 사용자 용 전체 제품 조회
     *
     * @param categoryId
     * @param pageable
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDto.GetProductForUsers> getProductsForUsers(int categoryId,
                                                                           ProductRequestDto.ProductSearchCondition condition,
                                                                           Pageable pageable) {
        if (Objects.isNull(condition) || condition.isConditionEmpty()) {
            return productRepository.findAllByCategoryCategoryId(categoryId, pageable).stream()
                    .map(ProductResponseDto.GetProductForUsers::of)
                    .toList();
        } else {
            return productRepository.searchProducts(categoryId, condition, pageable).stream()
                    .map(ProductResponseDto.GetProductForUsers::of)
                    .toList();
        }
    }

    @Override
    public List<ProductResponseDto.GetTag> getProductsTags() {
        return ProductResponseDto.GetTag.of(tagRepository.findAll());
    }
}
