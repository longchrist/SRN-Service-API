package com.srn.api.model.entity;

import com.srn.api.model.dto.SrnProfileDto;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "srn_user_profile")
@NamedQueries({@NamedQuery(name = "srn_user_profile.findAll", query = "SELECT p FROM SrnProfile p")})
public class SrnProfile extends BaseModel<SrnProfileDto> implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false)
    private long userId;

    @Basic(optional = false)
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Basic(optional = false)
    @Column(name = "nickname", nullable = false)
    private String nickName;

    @Basic(optional = false)
    @Column(name = "address", nullable = false)
    private String address;

    @Basic(optional = false)
    @Column(name = "city", nullable = false)
    private String city;

    @Basic(optional = false)
    @Column(name = "province", nullable = false)
    private String province;

    @Basic(optional = false)
    @Column(name = "phone", nullable = false)
    private String phone;

    @Basic(optional = false)
    @Column(name = "alternate_email", nullable = false)
    private String alternateEmail;

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
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAlternateEmail() {
        return alternateEmail;
    }

    public void setAlternateEmail(String alternateEmail) {
        this.alternateEmail = alternateEmail;
    }


    @Override
    public SrnProfileDto toDto() {
        SrnProfileDto dto = new SrnProfileDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}