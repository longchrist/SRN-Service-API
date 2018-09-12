/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srn.api.model;

import java.sql.Timestamp;
import javax.persistence.*;

/**
 *
 * @author long
 */

@Entity
public class SrnUserDeviceSession {

    /**
     * @return the device_id
     */
    public int getDevice_id() {
        return device_id;
    }

    /**
     * @param device_id the device_id to set
     */
    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    /**
     * @return the session_id
     */
    public String getSession_id() {
        return session_id;
    }

    /**
     * @param session_id the session_id to set
     */
    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    /**
     * @return the user_id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int device_id;
    private String session_id;
    private int user_id;
    private Timestamp created;
    private Timestamp last_updated;
}
