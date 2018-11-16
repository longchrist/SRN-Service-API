package com.srn.api.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "srn_voucher_campaign")
@NamedQueries({@NamedQuery(name = "srn_voucher_campaign.findAll", query = "SELECT p FROM SrnVoucherCampaign p")})
public class SrnVoucherCampaign {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "srn_voucher_campaign_seq")
    @SequenceGenerator(name = "srn_voucher_campaign_seq", sequenceName = "srn_voucher_campaign_seq", initialValue = 10000000, allocationSize = 1)
    @Column(name = "voucher_campaign_id", nullable = false, insertable = true, updatable = true)
    private long id;

    @Basic(optional = false)
    @Column(name = "voucher_campaign_name", nullable = false, insertable = false, updatable = false)
    private String voucherCampaignName;

    @Basic(optional = false)
    @Column(name = "voucher_expired", nullable = false, insertable = false, updatable = false)
    private Timestamp voucherExpired;

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

    public String getVoucherCampaignName() {
        return voucherCampaignName;
    }

    public void setVoucherCampaignName(String voucherCampaignName) {
        this.voucherCampaignName = voucherCampaignName;
    }

    public Timestamp getVoucherExpired() {
        return voucherExpired;
    }

    public void setVoucherExpired(Timestamp voucherExpired) {
        this.voucherExpired = voucherExpired;
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