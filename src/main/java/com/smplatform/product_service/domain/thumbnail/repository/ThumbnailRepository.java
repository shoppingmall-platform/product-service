package com.smplatform.product_service.domain.thumbnail.repository;

import com.smplatform.product_service.domain.thumbnail.entity.Thumbnail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThumbnailRepository extends JpaRepository<Thumbnail, Long> {
    List<Thumbnail> findAllByProduct_Id(int productId);
}
