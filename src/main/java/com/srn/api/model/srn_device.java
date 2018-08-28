/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srn.api.model;

import java.sql.Timestamp;

/**
 *
 * @author user
 */
public class srn_device {

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the imei
     */
    public String getImei() {
        return imei;
    }

    /**
     * @param imei the imei to set
     */
    public void setImei(String imei) {
        this.imei = imei;
    }

    /**
     * @return the manufacture
     */
    public String getManufacture() {
        return manufacture;
    }

    /**
     * @param manufacture the manufacture to set
     */
    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    /**
     * @return the osversion
     */
    public String getOsversion() {
        return osversion;
    }

    /**
     * @param osversion the osversion to set
     */
    public void setOsversion(String osversion) {
        this.osversion = osversion;
    }
    
    /**
     * @return the fcm_id
     */
    public String getFcm_id() {
        return fcm_id;
    }

    /**
     * @param fcm_id the fcm_id to set
     */
    public void setFcm_id(String fcm_id) {
        this.fcm_id = fcm_id;
    }

    /**
     * @return the created
     */
    public Timestamp getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(Timestamp created) {
        this.created = created;
    }

    /**
     * @return the last_updated
     */
    public Timestamp getLast_updated() {
        return last_updated;
    }

    /**
     * @param last_updated the last_updated to set
     */
    public void setLast_updated(Timestamp last_updated) {
        this.last_updated = last_updated;
    }
    
    private int id;
    private String model;
    private String imei;
    private String manufacture;
    private String osversion;
    private String fcm_id;
    private Timestamp created;
    private Timestamp last_updated;

}
