package com.srn.api.model.entity;

import com.srn.api.model.dto.SrnPointDto;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "srn_user_point")
@NamedQueries({@NamedQuery(name = "srn_user_point.findAll", query = "SELECT p FROM SrnPoint p")})
public class SrnPoint extends BaseModel<SrnPointDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "srn_user_point_seq")
    @SequenceGenerator(name = "srn_user_point_seq", sequenceName = "srn_user_point_seq", initialValue = 10000000, allocationSize = 1)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private long id;

    @Basic(optional = false)
    @Column(name = "user_id", nullable = true)
    private long userId;

    @Basic(optional = false)
    @Column(name = "brand_id", nullable = true)
    private long brandId;

    @Basic(optional = false)
    @Column(name = "store_id", nullable = true)
    private long storeId;

    @Basic(optional = false)
    @Column(name = "receipt_number", nullable = true)
    private String receiptNumber;

    @Basic(optional = false)
    @Column(name = "point_value", nullable = true)
    private long pointValue;

    @Basic(optional = false)
    @Column(name = "created", nullable = true)
    private Timestamp created;

    @Basic(optional = false)
    @Column(name = "last_updated", nullable = true)
    private Timestamp lastUpdated;

    public long getPointValue() {
        return pointValue;
    }

    public void setPointValue(long pointValue) {
        this.pointValue = pointValue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
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
    public SrnPointDto toDto() {
        SrnPointDto dto = new SrnPointDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}