package com.srn.api.model.dto;

import com.srn.api.model.entity.SrnStore;

import java.sql.Timestamp;
import java.util.List;

public class SrnBrandDto {

    String brandId;
    String brandName;
    String BrandUrl;
    private Timestamp created;
    private Timestamp lastUpdated;
    private List<SrnStore> stores;

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandUrl() {
        return BrandUrl;
    }

    public void setBrandUrl(String brandUrl) {
        BrandUrl = brandUrl;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<SrnStore> getStores() {
        return stores;
    }

    public void setStores(List<SrnStore> stores) {
        this.stores = stores;
    }
}