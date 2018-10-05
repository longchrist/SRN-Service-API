package com.srn.api.repo;

import com.srn.api.model.entity.SrnDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface ISrnDeviceRepo extends JpaRepository<SrnDevice, Long> {
    List<SrnDevice> findAll();
    SrnDevice findById(long id);
    SrnDevice findByImei(String imei);
}