package com.example.asset.controller;

import com.example.asset.dto.AssetRequest;
import com.example.asset.dto.AssetResponse;
import com.example.asset.dto.HealthScoreRequest;
import com.example.asset.mapper.AssetMapper;
import com.example.asset.service.AssetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @PostMapping
    public ResponseEntity<AssetResponse> create(@Valid @RequestBody AssetRequest request) {
        var created = assetService.createAsset(AssetMapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(AssetMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(AssetMapper.toResponse(assetService.getAssetById(id)));
    }

    @GetMapping
    public ResponseEntity<List<AssetResponse>> getAll() {
        return ResponseEntity.ok(assetService.getAllAssets().stream()
                .map(AssetMapper::toResponse)
                .collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssetResponse> update(@PathVariable Long id,
                                                @Valid @RequestBody AssetRequest request) {
        var updated = assetService.updateAsset(id, AssetMapper.toEntity(request));
        return ResponseEntity.ok(AssetMapper.toResponse(updated));
    }

    @PatchMapping("/{id}/health-score")
    public ResponseEntity<AssetResponse> updateHealthScore(@PathVariable Long id,
                                                           @Valid @RequestBody HealthScoreRequest request) {
        var updated = assetService.updateHealthScore(id, request.getHealthScore());
        return ResponseEntity.ok(AssetMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        assetService.deleteAsset(id);
        return ResponseEntity.noContent().build();
    }
}