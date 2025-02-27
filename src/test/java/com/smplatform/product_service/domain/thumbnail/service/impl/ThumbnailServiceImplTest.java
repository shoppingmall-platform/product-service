package com.smplatform.product_service.domain.thumbnail.service.impl;

import com.smplatform.product_service.domain.thumbnail.dto.ThumbnailRequestDto;
import com.smplatform.product_service.domain.thumbnail.entity.Thumbnail;
import com.smplatform.product_service.domain.thumbnail.repository.ThumbnailRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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