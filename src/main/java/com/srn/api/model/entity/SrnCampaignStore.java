package com.srn.api.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "srn_campaign_store")
@NamedQueries({@NamedQuery(name = "srn_campaign_store.findAll", query = "SELECT p FROM SrnCampaignStore p")})
public class SrnCampaignStore {

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private long id;

    @Basic(optional = false)
    @JoinColumn(name = "campaign_id", nullable = false)
    private String campaignId;

    @Basic(optional = false)
    @Column(name = "store_id", nullable = false)
    private String storeId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}