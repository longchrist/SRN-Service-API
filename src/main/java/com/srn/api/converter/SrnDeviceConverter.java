/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srn.api.converter;

import com.srn.api.dto.SrnDeviceDTO;
import com.srn.api.model.SrnDevice;

/**
 *
 * @author long
 */

public final class SrnDeviceConverter {
    public SrnDevice DTOtoSrnUser(SrnDeviceDTO SDCDTO) {
        SrnDevice SDC = new SrnDevice();
        SDC.setId(SDCDTO.getId());
        SDC.setModel(SDCDTO.getModel());
        SDC.setImei(SDCDTO.getImei());
        SDC.setManufacture(SDCDTO.getManufacture());
        SDC.setOsversion(SDCDTO.getOsversion());
        SDC.setFcm_id(SDCDTO.getFcm_id());
        SDC.setCreated(SDCDTO.getCreated());
        SDC.setLast_updated(SDCDTO.getLast_updated());
        return SDC;
    }

    public SrnDeviceDTO SrnUserToDTO(SrnDevice SDC) {
        SrnDeviceDTO SDCDTO = new SrnDeviceDTO();
        SDCDTO.setId(SDC.getId());
        SDCDTO.setModel(SDC.getModel());
        SDCDTO.setImei(SDC.getImei());
        SDCDTO.setManufacture(SDC.getManufacture());
        SDCDTO.setOsversion(SDC.getOsversion());
        SDCDTO.setFcm_id(SDC.getFcm_id());
        SDCDTO.setCreated(SDC.getCreated());
        SDCDTO.setLast_updated(SDC.getLast_updated());
        return SDCDTO;
    }
}
