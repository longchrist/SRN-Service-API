package com.srn.api.repo;

import com.srn.api.model.entity.SrnDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISrnDeviceRepo extends JpaRepository<SrnDevice, Long> {
    List<SrnDevice> findAll();
    SrnDevice findById(long id);
    SrnDevice findByImei(String imei);
}