/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srn.api.service;

import com.srn.api.model.SrnDevice;
import java.util.List;

/**
 *
 * @author user
 */
public interface SrnDeviceService {
    
    SrnDevice create(SrnDevice sd);

    boolean delete(int id);

    boolean deleteAll();

    SrnDevice find(int id);

    List<SrnDevice> findAll();

    SrnDevice findByImei(String imei);

    SrnDevice update(String imei, SrnDevice sd);
    
}
