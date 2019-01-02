package com.srn.api.model.dto;
/*
 Author: vikraa
 created: 12/31/18 - 8:57 PM
*/


import com.srn.api.model.entity.SrnDevice;

import java.sql.Timestamp;

public class SrnCampaignRedeemDto extends BaseDto {

    private String campaignId;
    private int requiredPoints;
    private Timestamp redeemTimestamp;
    private String sessionId;
    private Timestamp timestamp;
    private SrnDevice deviceData;

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public Timestamp getRedeemTimestamp() {
        return redeemTimestamp;
    }

    public void setRedeemTimestamp(Timestamp redeemTimestamp) {
        this.redeemTimestamp = redeemTimestamp;
    }

    public int getRequiredPoints() {
        return requiredPoints;
    }

    public void setRequiredPoints(int requiredPoints) {
        this.requiredPoints = requiredPoints;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public SrnDevice getDeviceData() {
        return deviceData;
    }

    public void setDeviceData(SrnDevice deviceData) {
        this.deviceData = deviceData;
    }
}