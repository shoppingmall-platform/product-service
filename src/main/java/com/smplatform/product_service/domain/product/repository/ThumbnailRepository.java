package com.smplatform.product_service.domain.product.repository;

import com.smplatform.product_service.domain.product.entity.Thumbnail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThumbnailRepository extends JpaRepository<Thumbnail, Long> {
    List<Thumbnail> findAllByProduct_Id(long productId);
}
