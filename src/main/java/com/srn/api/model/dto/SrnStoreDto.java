package com.srn.api.model.dto;

public class SrnStoreDto {

    private String storeId;
    private String brandId;
    private String storeName;
    private String storeAddress;
    private String storeCity;
    private String storePhone;
    private long storeSeating;
    private String storeOperationalHours;
    private String storeProvince;
    private long storeLatitude;
    private long storeLongitude;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreCity() {
        return storeCity;
    }

    public void setStoreCity(String storeCity) {
        this.storeCity = storeCity;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public long getStoreSeating() {
        return storeSeating;
    }

    public void setStoreSeating(long storeSeating) {
        this.storeSeating = storeSeating;
    }

    public String getStoreOperationalHours() {
        return storeOperationalHours;
    }

    public void setStoreOperationalHours(String storeOperationalHours) {
        this.storeOperationalHours = storeOperationalHours;
    }

    public String getStoreProvince() {
        return storeProvince;
    }

    public void setStoreProvince(String storeProvince) {
        this.storeProvince = storeProvince;
    }

    public long getStoreLatitude() {
        return storeLatitude;
    }

    public void setStoreLatitude(long storeLatitude) {
        this.storeLatitude = storeLatitude;
    }

    public long getStoreLongitude() {
        return storeLongitude;
    }

    public void setStoreLongitude(long storeLongitude) {
        this.storeLongitude = storeLongitude;
    }
}