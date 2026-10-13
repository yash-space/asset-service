package com.example.asset.mapper;

import com.example.asset.dto.AssetRequest;
import com.example.asset.dto.AssetResponse;
import com.example.asset.entity.Asset;

public class AssetMapper {

    public static Asset toEntity(AssetRequest request) {
        Asset asset = new Asset();
        asset.setName(request.getName());
        asset.setType(request.getType());
        asset.setLocation(request.getLocation());
        asset.setCriticality(request.getCriticality());
        return asset;
    }

    public static AssetResponse toResponse(Asset asset) {
        return new AssetResponse(asset.getId(), asset.getName(), asset.getType(), asset.getLocation(),
                asset.getCriticality(), asset.getHealthScore(), asset.getCreatedDate(), asset.getUpdatedDate());
    }
}