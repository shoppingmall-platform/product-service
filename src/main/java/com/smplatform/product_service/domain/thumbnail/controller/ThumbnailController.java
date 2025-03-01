package com.smplatform.product_service.domain.thumbnail.controller;

import com.smplatform.product_service.domain.thumbnail.dto.ThumbnailRequestDto;
import com.smplatform.product_service.domain.thumbnail.dto.ThumbnailResponseDto;
import com.smplatform.product_service.domain.thumbnail.service.ThumbnailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/thumbnails")
@RequiredArgsConstructor
@Tag(name = "Thumbnail", description = "Thumbnail management APIs")
public class ThumbnailController {
    private final ThumbnailService thumbnailService;

    @GetMapping
    @Operation(summary = "상품 썸네일 목록 조회", description = "전체 썸네일 저장 url 목록 조회")
    public ResponseEntity<List<ThumbnailResponseDto.ThumbnailInfo>> getProductThumbnailList(@RequestParam(required = false) Integer productId) {
        return ResponseEntity.ok(thumbnailService.getProductThumbnailList(productId));
    }

    @PostMapping
    @Operation(summary = "상품 썸네일 목록 저장", description = "상품에 등록된 썸네일 url 목록 저장")
    public ResponseEntity<String> saveThumbnail(@Valid @RequestBody ThumbnailRequestDto.SaveThumbnail requestBody) {
        thumbnailService.saveThumbnail(requestBody.getProductId(), requestBody.getPaths());
        return ResponseEntity.status(201).body("save successful");
    }


    @PostMapping("/delete-thumbnail")
    @Operation(summary = "특정 썸네일 삭제", description = "하나의 썸네일 삭제")
    public ResponseEntity<String> deleteThumbnail(@Valid @RequestBody ThumbnailRequestDto.DeleteThumbnail requestBody) {
        thumbnailService.deleteThumbnail(requestBody.getThumbnailId());
        return ResponseEntity.status(204).build();
    }
}
