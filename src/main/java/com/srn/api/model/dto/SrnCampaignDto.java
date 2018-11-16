package com.srn.api.model.dto;

public class SrnCampaignDto extends BaseDto {

    private long id;
    private String brandId;
    private long campaignType;
    private String description;
    private String tnc;
    private long startTimestamp;
    private long endTimestamp;
    private int requiredPoints;

    public SrnCampaignDto() {

    }

    public SrnCampaignDto(long id, String brandId, long campaignType, String description, String tnc,
                          long startTimestamp, long endTimestamp, int requiredPoints) {
        this.id = id;
        this.brandId = brandId;
        this.campaignType = campaignType;
        this.description = description;
        this.tnc = tnc;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.requiredPoints = requiredPoints;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public long getCampaignType() {
        return campaignType;
    }

    public void setCampaignType(long campaignType) {
        this.campaignType = campaignType;
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

    public long getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public long getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(long endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public int getRequiredPoints() {
        return requiredPoints;
    }

    public void setRequiredPoints(int requiredPoints) {
        this.requiredPoints = requiredPoints;
    }
}