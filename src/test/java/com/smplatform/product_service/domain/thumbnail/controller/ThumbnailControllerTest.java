package com.smplatform.product_service.domain.thumbnail.controller;

import com.smplatform.product_service.domain.thumbnail.dto.ThumbnailResponseDto;
import com.smplatform.product_service.domain.thumbnail.service.ThumbnailService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ThumbnailController.class)
class ThumbnailControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ThumbnailService thumbnailService;



    @Test
    @DisplayName("썸네일 리스트 조회")
    void getProductThumbnailList() throws Exception {
        List<ThumbnailResponseDto.ThumbnailInfo> thumbnails = List.of(
                new ThumbnailResponseDto.ThumbnailInfo(
                        1,
                        "http://localhost:1111"
                ),
                new ThumbnailResponseDto.ThumbnailInfo(
                        2,
                        "http://localhost:2222"
                )
        );
        when(thumbnailService.getProductThumbnailList(any())).thenReturn(thumbnails);
        mockMvc.perform(get("/v1/thumbnails").param("productId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].thumbnailId").value(1))
                .andExpect(jsonPath("$.[0].path").value("http://localhost:1111"))
                .andDo(print());
    }

    @Test
    @DisplayName("상품 썸네일 저장")
    void saveThumbnail() {

    }

    @Test
    @DisplayName("상품 썸네일 저장 위치 url 수정")
    void updateThumbnail() {
    }

    @Test
    @DisplayName("상품 썸네일 삭제")
    void deleteThumbnail() {
    }
}