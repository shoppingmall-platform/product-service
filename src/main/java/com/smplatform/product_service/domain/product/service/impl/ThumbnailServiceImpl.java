package com.smplatform.product_service.domain.product.service.impl;

import com.smplatform.product_service.domain.product.entity.Product;
import com.smplatform.product_service.domain.product.exception.ProductNotFoundException;
import com.smplatform.product_service.domain.product.repository.ProductRepository;
import com.smplatform.product_service.domain.product.dto.ThumbnailResponseDto;
import com.smplatform.product_service.domain.product.entity.Thumbnail;
import com.smplatform.product_service.domain.product.exception.ThumbnailNotFoundException;
import com.smplatform.product_service.domain.product.repository.ThumbnailRepository;
import com.smplatform.product_service.domain.product.service.ThumbnailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ThumbnailServiceImpl implements ThumbnailService {
    private final ProductRepository productRepository;
    private final ThumbnailRepository thumbnailRepository;


    @Override
    @Transactional(readOnly = true)
    public List<ThumbnailResponseDto.ThumbnailInfo> getProductThumbnailList(Long productId) {
        List<Thumbnail> thumbnails;
        if (productId == null) {
            thumbnails = thumbnailRepository.findAll();
        } else {
            thumbnails = thumbnailRepository.findAllByProduct_Id(productId);
        }
        return thumbnails.stream()
                .map(ThumbnailResponseDto.ThumbnailInfo::from)
                .collect(Collectors.toList());
    }

    @Override
    public void saveThumbnails(long productId, List<String> paths) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("존재하지 않는 상품 Id 입니다 : " + productId));
        List<Thumbnail> thumbnails = paths.stream()
                .map(path -> Thumbnail.builder()
                        .path(path)
                        .product(product)
                        .build())
                .collect(Collectors.toList());
        thumbnailRepository.saveAll(thumbnails);
    }

    @Override
    public void deleteThumbnail(List<Long> thumbnailIds) {
        thumbnailRepository.deleteAllById(thumbnailIds);
    }
}
