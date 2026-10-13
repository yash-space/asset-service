package com.example.asset.service;

import com.example.asset.entity.Asset;
import java.util.List;

public interface AssetService {
    Asset createAsset(Asset asset);
    Asset getAssetById(Long id);
    List<Asset> getAllAssets();
    Asset updateAsset(Long id, Asset updated);
    Asset updateHealthScore(Long id, Double healthScore);
    void deleteAsset(Long id);
}