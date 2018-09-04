/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srn.api.dto;

import java.sql.Timestamp;

/**
 *
 * @author long
 */

public class SrnUserEmailDTO {

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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the login_type
     */
    public String getLogin_type() {
        return login_type;
    }

    /**
     * @param login_type the login_type to set
     */
    public void setLogin_type(String login_type) {
        this.login_type = login_type;
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
    private String email;
    private String login_type;
    private Timestamp created;
    private Timestamp last_updated;
}
