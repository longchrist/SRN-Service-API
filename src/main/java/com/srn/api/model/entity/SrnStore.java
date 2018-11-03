package com.srn.api.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.srn.api.model.dto.SrnStoreDto;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "SrnStore")
@Table(name = "srn_store")
@NamedQueries({@NamedQuery(name = "srn_store.findAll", query = "SELECT b FROM SrnStore b")})
public class SrnStore extends BaseModel<SrnStoreDto> implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "store_id", nullable = false)
    private String id;

    @Basic(optional = false)
    @Column(name = "brand_id", nullable = false)
    private String brandId;

    @Basic(optional = false)
    @Column(name = "store_name", nullable = false)
    private String storeName;

    @Basic(optional = false)
    @Column(name = "store_address", nullable = false)
    private String storeAddress;

    @Basic(optional = false)
    @Column(name = "store_city", nullable = false)
    private String storeCity;

    @Basic(optional = false)
    @Column(name = "store_province", nullable = false)
    private String storeProvince;

    @Basic(optional = false)
    @Column(name = "store_latitude", nullable = false)
    private String storeLatitude;

    @Basic(optional = false)
    @Column(name = "store_longitude", nullable = false)
    private String storeLongitude;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", insertable = false, updatable = false)
    private SrnBrand brandStore;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStoreProvince() {
        return storeProvince;
    }

    public void setStoreProvince(String storeProvince) {
        this.storeProvince = storeProvince;
    }

    public SrnBrand getBrandStore() {
        return brandStore;
    }

    public void setBrandStore(SrnBrand brandStore) {
        this.brandStore = brandStore;
    }

    public String getStoreLatitude() {
        return storeLatitude;
    }

    public void setStoreLatitude(String storeLatitude) {
        this.storeLatitude = storeLatitude;
    }

    public String getStoreLongitude() {
        return storeLongitude;
    }

    public void setStoreLongitude(String storeLongitude) {
        this.storeLongitude = storeLongitude;
    }

    @Override
    public SrnStoreDto toDto() {
        SrnStoreDto dto = new SrnStoreDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}