package com.srn.api.model.entity;/*
 Author: vikraa
 created: 1/2/19 - 10:39 PM
*/

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "SrnRedeem")
@Table(name = "srn_campaign_redeem")
@NamedQueries({@NamedQuery(name = "srn_campaign_redeem.findAll", query = "SELECT r FROM SrnRedeem r")})
public class SrnRedeem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "srn_campaign_redeem_seq")
    @SequenceGenerator(name = "srn_campaign_redeem_seq", sequenceName = "srn_campaign_redeem_seq", initialValue = 10000000, allocationSize = 1)
    @Column(name = "redeem_id", nullable = false, insertable = true, updatable = true)
    private long id;

    @Basic(optional = false)
    @Column(name = "user_id", nullable = true)
    private long userId;

    @Basic(optional = false)
    @Column(name = "device_id", nullable = true)
    private long deviceId;

    @Basic(optional = false)
    @Column(name = "voucher_code", nullable = true)
    private String voucherCode;

    @Basic(optional = false)
    @Column(name = "redeem_date", nullable = true)
    private Timestamp redeemDate;

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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceid) {
        this.deviceId = deviceid;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public Timestamp getRedeemDate() {
        return redeemDate;
    }

    public void setRedeemDate(Timestamp redeemDate) {
        this.redeemDate = redeemDate;
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