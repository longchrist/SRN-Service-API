package com.srn.api.model.dto;/*
 Author: vikraa
 created: 1/5/19 - 2:06 PM
*/

import java.util.List;

public class SrnVoucherDto extends BaseDto {

    private String voucherCode;
    private long voucherExpired;
    private long voucherCampaignId;
    private long voucherAmount;
    private String campaignName;
    private String tnc;
    private String description;
    private List<SrnVoucherStoreDto> store;

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public long getVoucherExpired() {
        return voucherExpired;
    }

    public void setVoucherExpired(long voucherExpired) {
        this.voucherExpired = voucherExpired;
    }

    public long getVoucherCampaignId() {
        return voucherCampaignId;
    }

    public void setVoucherCampaignId(long voucherCampaignId) {
        this.voucherCampaignId = voucherCampaignId;
    }

    public List<SrnVoucherStoreDto> getStore() {
        return store;
    }

    public void setStore(List<SrnVoucherStoreDto> store) {
        this.store = store;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getTnc() {
        return tnc;
    }

    public void setTnc(String tnc) {
        this.tnc = tnc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getVoucherAmount() {
        return voucherAmount;
    }

    public void setVoucherAmount(long voucherAmount) {
        this.voucherAmount = voucherAmount;
    }
}