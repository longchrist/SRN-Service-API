/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srn.api.repository;

import com.srn.api.model.SrnDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public interface SrnDeviceRepository extends JpaRepository<SrnDevice, Integer>{
    SrnDevice findByImei(String imei);
}
