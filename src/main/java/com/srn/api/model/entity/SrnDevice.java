package com.srn.api.model.entity;

import com.srn.api.model.dto.SrnDeviceDto;
import com.srn.api.utils.FormatterUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "srn_device")
@NamedQueries({@NamedQuery(name = "srn_device.findAll", query = "SELECT r FROM SrnDevice r")})
public class SrnDevice extends BaseModel<SrnDeviceDto> implements Serializable {

    @Basic(optional = false)
    @Column(name = "imei", nullable = false)
    private String imei;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "srn_device_seq")
    @SequenceGenerator(name = "srn_device_seq", sequenceName = "srn_device_seq", initialValue = 10000000, allocationSize = 1)
    private long id;

    @Basic(optional = false)
    @Column(name = "model", nullable = false)
    private String model;

    @Basic(optional = false)
    @Column(name = "manufacture", nullable = false)
    private String manufacture;

    @Basic(optional = false)
    @Column(name = "osversion", nullable = false)
    private String osversion;

    @Basic(optional = false)
    @Column(name = "fcm_id", nullable = false)
    private String fcmId;

    @Basic(optional = false)
    @Column(name = "created", nullable = true)
    private Timestamp created;

    @Basic(optional = false)
    @Column(name = "last_updated", nullable = true)
    private Timestamp lastUpdated;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getOsversion() {
        return osversion;
    }

    public void setOsversion(String osversion) {
        this.osversion = osversion;
    }

    public String getFcmId() {
        return fcmId;
    }

    public void setFcmId(String fcmId) {
        this.fcmId = fcmId;
    }


    @Override
    public SrnDeviceDto toDto() {
        SrnDeviceDto dto = new SrnDeviceDto();
        dto.setId(this.id);
        dto.setModel(this.model);
        dto.setManufacture(this.manufacture);
        dto.setOsVersion(this.osversion);
        dto.setImei(this.imei);
        dto.setCreated(FormatterUtils.getCurrentTimestamp());
        dto.setLastUpdated(FormatterUtils.getCurrentTimestamp());
        return dto;
    }

}