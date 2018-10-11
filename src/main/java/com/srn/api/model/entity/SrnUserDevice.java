package com.srn.api.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "srn_user_device_session")
@NamedQueries({@NamedQuery(name = "srn_user_device_session.findAll", query = "SELECT u FROM SrnUserDevice u")})
public class SrnUserDevice {

    @Id
    @Basic(optional = false)
    @Column(name = "device_id", nullable = false)
    private long id;

    @Basic(optional = false)
    @Column(name = "session_id", nullable = false)
    private String sessionId;

    @Basic(optional = false)
    @Column(name = "user_id", nullable = false)
    private long userId;

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

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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