package com.srn.api.model.dto;/*
 Author: vikraa
 created: 1/5/19 - 2:06 PM
*/

import java.util.List;

public class SrnVoucherDto extends BaseDto {

    private String voucherCode;
    private long voucherExpired;
    private long voucherCampaignId;
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
}