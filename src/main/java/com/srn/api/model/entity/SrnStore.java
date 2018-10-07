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
    private long id;

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

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private SrnBrand brandStore;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public SrnStoreDto toDto() {
        SrnStoreDto dto = new SrnStoreDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}