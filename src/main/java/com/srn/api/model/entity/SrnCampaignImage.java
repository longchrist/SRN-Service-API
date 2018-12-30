package com.srn.api.model.entity;
/*
 Author: vikraa
 created: 12/16/18 - 12:50 PM
*/

import javax.persistence.*;

@Entity
@Table(name = "srn_campaign_image")
@NamedQueries({@NamedQuery(name = "srn_campaign_image.findAll", query = "SELECT p FROM SrnCampaignImage p")})
public class SrnCampaignImage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "srn_campaign_image_seq")
    @SequenceGenerator(name = "srn_campaign_image_seq", sequenceName = "srn_campaign_image_seq", initialValue = 10000000, allocationSize = 1)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private long id;

    @Basic(optional = false)
    @Column(name = "campaign_id", nullable = true)
    private long campaignId;


    @Basic(optional = false)
    @Column(name = "img400x200", nullable = true)
    private String img400x200;

    @Basic(optional = false)
    @Column(name = "img800x400", nullable = true)
    private String img800x400;

    @Basic(optional = false)
    @Column(name = "img1440x720", nullable = true)
    private String img1440x720;

    public long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(long campaignId) {
        this.campaignId = campaignId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImg400x200() {
        return img400x200;
    }

    public void setImg400x200(String img400x200) {
        this.img400x200 = img400x200;
    }

    public String getImg800x400() {
        return img800x400;
    }

    public void setImg800x400(String img800x400) {
        this.img800x400 = img800x400;
    }

    public String getImg1440x720() {
        return img1440x720;
    }

    public void setImg1440x720(String img1440x720) {
        this.img1440x720 = img1440x720;
    }


}