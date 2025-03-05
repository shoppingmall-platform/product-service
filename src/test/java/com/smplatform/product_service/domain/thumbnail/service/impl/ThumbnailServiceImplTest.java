package com.smplatform.product_service.domain.thumbnail.service.impl;

import com.smplatform.product_service.domain.product.service.impl.ThumbnailServiceImpl;
import com.smplatform.product_service.domain.product.dto.ThumbnailRequestDto;
import com.smplatform.product_service.domain.product.repository.ThumbnailRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ThumbnailServiceImplTest {
    @Mock
    ThumbnailRepository thumbnailRepository;

    @InjectMocks
    ThumbnailServiceImpl thumbnailService;

    @Test
    @DisplayName("thumbnail 저장")
    void saveThumbnail() {
        ThumbnailRequestDto.SaveThumbnail saveThumbnail = new ThumbnailRequestDto.SaveThumbnail(
                1,
                List.of(
                        "/images.img-1",
                        "/images.img-1"
                )
        );

    }
}