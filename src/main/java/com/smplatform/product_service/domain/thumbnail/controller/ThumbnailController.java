package com.smplatform.product_service.domain.thumbnail.controller;

import com.smplatform.product_service.domain.thumbnail.dto.ThumbnailRequestDto;
import com.smplatform.product_service.domain.thumbnail.dto.ThumbnailResponseDto;
import com.smplatform.product_service.domain.thumbnail.service.ThumbnailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/thumbnails")
@RequiredArgsConstructor
public class ThumbnailController {
    private final ThumbnailService thumbnailService;

    @GetMapping
    public ResponseEntity<List<ThumbnailResponseDto.ThumbnailInfo>> getProductThumbnailList(@RequestParam(required = false) Integer productId) {
        return ResponseEntity.ok(thumbnailService.getProductThumbnailList(productId));
    }

    @PostMapping
    public ResponseEntity<String> saveThumbnail(@Valid @RequestBody ThumbnailRequestDto.SaveThumbnail requestBody) {
        thumbnailService.saveThumbnail(requestBody.getProductId(), requestBody.getPaths());
        return ResponseEntity.status(201).body("save successful");
    }


    @PostMapping("/delete-thumbnail")
    public ResponseEntity<String> deleteThumbnail(@Valid @RequestBody ThumbnailRequestDto.DeleteThumbnail requestBody) {
        thumbnailService.deleteThumbnail(requestBody.getThumbnailId());
        return ResponseEntity.status(204).build();
    }
}
