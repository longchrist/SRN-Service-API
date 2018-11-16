package com.srn.api.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "srn_campaign_detail")
@NamedQueries({@NamedQuery(name = "srn_campaign_detail.findAll", query = "SELECT p FROM SrnCampaignDetail p")})
public class SrnCampaignDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "srn_campaign_detail_seq")
    @SequenceGenerator(name = "srn_campaign_detail_seq", sequenceName = "srn_campaign_detail_seq", initialValue = 10000000, allocationSize = 1)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private long id;

    @Basic(optional = false)
    @Column(name = "campaign_id", nullable = false, insertable = false, updatable = false)
    private long campaignId;

    @Basic(optional = false)
    @Column(name = "store_id", nullable = false, insertable = false, updatable = false)
    private String storeId;

    @Basic(optional = false)
    @Column(name = "voucher_campaign_id", nullable = false, insertable = false, updatable = false)
    private long voucherCampaignId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(long campaignId) {
        this.campaignId = campaignId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public long getVoucherCampaignId() {
        return voucherCampaignId;
    }

    public void setVoucherCampaignId(long voucherCampaignId) {
        this.voucherCampaignId = voucherCampaignId;
    }
}