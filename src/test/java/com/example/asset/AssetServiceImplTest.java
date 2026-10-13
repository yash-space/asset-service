package com.example.asset;

import com.example.asset.entity.Asset;
import com.example.asset.exception.AssetNotFoundException;
import com.example.asset.repository.AssetRepository;
import com.example.asset.service.AssetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssetServiceImplTest {

    @Mock
    private AssetRepository assetRepository;

    private AssetServiceImpl assetService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        assetService = new AssetServiceImpl(assetRepository);
    }

    @Test
    void getAssetById_whenMissing_throwsException() {
        when(assetRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(AssetNotFoundException.class, () -> assetService.getAssetById(99L));
    }

    @Test
    void updateHealthScore_validValue_savesAsset() {
        Asset asset = new Asset();
        asset.setId(1L);
        when(assetRepository.findById(1L)).thenReturn(Optional.of(asset));
        when(assetRepository.save(any(Asset.class))).thenReturn(asset);

        Asset result = assetService.updateHealthScore(1L, 87.5);

        assertEquals(87.5, result.getHealthScore());
    }

    @Test
    void updateHealthScore_outOfRange_throwsAndNeverSaves() {
        assertThrows(IllegalArgumentException.class, () -> assetService.updateHealthScore(1L, 150.0));
        verify(assetRepository, never()).save(any());
    }

    @Test
    void deleteAsset_whenMissing_throwsException() {
        when(assetRepository.existsById(99L)).thenReturn(false);
        assertThrows(AssetNotFoundException.class, () -> assetService.deleteAsset(99L));
    }
}