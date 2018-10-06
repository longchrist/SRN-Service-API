package com.srn.api.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.srn.api.model.dto.SrnBrandDto;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity(name = "SrnBrand")
@Table(name = "srn_brand")
@NamedQueries({@NamedQuery(name = "srn_brand.findAll", query = "SELECT b FROM SrnBrand b")})
public class SrnBrand extends BaseModel<SrnBrandDto> implements Serializable {

    @Id
    @Column(name = "brand_id", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "srn_brand_seq")
    @SequenceGenerator(name = "srn_brand_seq", sequenceName = "srn_brand_seq", initialValue = 10000000, allocationSize = 1)
    long id;

    @Basic(optional = false)
    @Column(name = "brand_name", nullable = false)
    String brandName;

    @Basic(optional = false)
    @Column(name = "brand_image_url", nullable = false)
    String brandUrl;

    @Basic(optional = false)
    @Column(name = "created", nullable = true)
    private Timestamp created;

    @Basic(optional = false)
    @Column(name = "last_updated", nullable = true)
    private Timestamp lastUpdated;

    @JsonManagedReference
    @OneToMany(mappedBy = "brandStore", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SrnStore> stores;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<SrnStore> getStores() {
        return stores;
    }

    public void setStores(List<SrnStore> stores) {
        this.stores = stores;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandUrl() {
        return brandUrl;
    }

    public void setBrandUrl(String brandUrl) {
        this.brandUrl = brandUrl;
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

    @Override
    public SrnBrandDto toDto() {
        SrnBrandDto dto = new SrnBrandDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}