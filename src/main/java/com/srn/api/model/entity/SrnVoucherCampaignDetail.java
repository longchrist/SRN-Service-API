package com.srn.api.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "srn_voucher_campaign_detail")
@NamedQueries({@NamedQuery(name = "srn_voucher_campaign_detail.findAll", query = "SELECT p FROM SrnVoucherCampaignDetail p")})
public class SrnVoucherCampaignDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "srn_voucher_campaign_detail_seq")
    @SequenceGenerator(name = "srn_voucher_campaign_detail_seq", sequenceName = "srn_voucher_campaign_detail_seq", initialValue = 10000000, allocationSize = 1)
    @Column(name = "voucher_campaign_detail_id", nullable = false, insertable = true, updatable = true)
    private long id;

    @Basic(optional = false)
    @Column(name = "voucher_campaign_id", nullable = false, insertable = false, updatable = false)
    private long voucherCampaignId;

    @Basic(optional = false)
    @Column(name = "voucher_code", nullable = false, insertable = false, updatable = false)
    private String voucherCode;

    @Basic(optional = false)
    @Column(name = "created", nullable = true)
    private Timestamp created;

    @Basic(optional = false)
    @Column(name = "last_updated", nullable = true)
    private Timestamp lastUpdated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVoucherCampaignId() {
        return voucherCampaignId;
    }

    public void setVoucherCampaignId(long voucherCampaignId) {
        this.voucherCampaignId = voucherCampaignId;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
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
}