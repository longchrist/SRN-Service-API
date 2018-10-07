package com.srn.api.model.entity;

import com.srn.api.model.dto.SrnEmailDto;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "srn_user_email")
@NamedQueries({@NamedQuery(name = "srn_user_email.findAll", query = "SELECT l FROM SrnEmail l")})
public class SrnEmail extends BaseModel<SrnEmailDto> implements Serializable {

    @Basic(optional = false)
    @Column(name = "email", nullable = false)
    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "srn_user_email_seq")
    @SequenceGenerator(name = "srn_user_email_seq", sequenceName = "srn_user_email_seq", initialValue = 10000000, allocationSize = 1)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private long id;

    @Basic(optional = false)
    @Column(name = "login_type", nullable = false)
    private String loginType;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    @Override
    public SrnEmailDto toDto() {
        SrnEmailDto dto = new SrnEmailDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}