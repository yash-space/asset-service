package com.example.asset.service;

import com.example.asset.entity.Asset;
import com.example.asset.exception.AssetNotFoundException;
import com.example.asset.repository.AssetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;

    public AssetServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public Asset createAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    @Override
    public Asset getAssetById(Long id) {
        return assetRepository.findById(id)
                .orElseThrow(() -> new AssetNotFoundException(id));
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public Asset updateAsset(Long id, Asset updated) {
        Asset existing = getAssetById(id);
        existing.setName(updated.getName());
        existing.setType(updated.getType());
        existing.setLocation(updated.getLocation());
        existing.setCriticality(updated.getCriticality());
        return assetRepository.save(existing);
    }

    @Override
    public Asset updateHealthScore(Long id, Double healthScore) {
        if (healthScore < 0 || healthScore > 100) {
            throw new IllegalArgumentException("healthScore must be between 0 and 100");
        }
        Asset asset = getAssetById(id);
        asset.setHealthScore(healthScore);
        return assetRepository.save(asset);
    }

    @Override
    public void deleteAsset(Long id) {
        if (!assetRepository.existsById(id)) {
            throw new AssetNotFoundException(id);
        }
        assetRepository.deleteById(id);
    }
}