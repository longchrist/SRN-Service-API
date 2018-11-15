package com.srn.api.model.entity;

import com.srn.api.model.dto.SrnCampaignDto;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "srn_campaign")
@NamedQueries({@NamedQuery(name = "srn_campaign.findAll", query = "SELECT p FROM SrnCampaign p")})
public class SrnCampaign extends BaseModel<SrnCampaignDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "srn_campaign_promo_seq")
    @SequenceGenerator(name = "srn_campaign_promo_seq", sequenceName = "srn_campaign_promo_seq", initialValue = 10000000, allocationSize = 1)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private long id;

    @Basic(optional = false)
    @Column(name = "brand_id", nullable = false)
    private String brandId;

    @Basic(optional = false)
    @Column(name = "campaign_type", nullable = false)
    private int campaignType;

    @Basic(optional = false)
    @Column(name = "campaign_name", nullable = false)
    private String campaignName;

    @Basic(optional = false)
    @Column(name = "description", nullable = false)
    private String description;

    @Basic(optional = false)
    @Column(name = "tnc", nullable = false)
    private String tnc;

    @Basic(optional = false)
    @Column(name = "start_date", nullable = false)
    private Timestamp startDate;

    @Basic(optional = false)
    @Column(name = "end_date", nullable = false)
    private Timestamp endDate;

    @Basic(optional = false)
    @Column(name = "is_active", nullable = false)
    private boolean active;

    @Basic(optional = false)
    @Column(name = "required_points", nullable = false)
    private int requiredPoints;

    @Basic(optional = false)
    @Column(name = "created", nullable = true)
    private Timestamp created;

    @Basic(optional = false)
    @Column(name = "last_updated", nullable = true)
    private Timestamp lastUpdated;

    @OneToMany(mappedBy = "campaignId")
    private List<SrnCampaignStore> campaignStores = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCampaignType() {
        return campaignType;
    }

    public void setCampaignType(int campaignType) {
        this.campaignType = campaignType;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTnc() {
        return tnc;
    }

    public void setTnc(String tnc) {
        this.tnc = tnc;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setIsActive(boolean isActive) {
        this.active = isActive;
    }

    public int getRequiredPoints() {
        return requiredPoints;
    }

    public void setRequiredPoints(int requiredPoints) {
        this.requiredPoints = requiredPoints;
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

    public List<SrnCampaignStore> getCampaignStores() {
        return campaignStores;
    }

    public void setCampaignStores(List<SrnCampaignStore> campaignStores) {
        this.campaignStores = campaignStores;
    }

    @Override
    public SrnCampaignDto toDto() {
        SrnCampaignDto dto = new SrnCampaignDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}