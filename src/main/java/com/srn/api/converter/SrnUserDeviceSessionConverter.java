/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srn.api.converter;

import com.srn.api.dto.SrnUserDeviceSessionDTO;
import com.srn.api.model.SrnUserDeviceSession;

/**
 *
 * @author user
 */
public final class SrnUserDeviceSessionConverter {
    public SrnUserDeviceSession DTOtoSrnUserDeviceSession(SrnUserDeviceSessionDTO SUDSDTO) {
        SrnUserDeviceSession SUDS = new SrnUserDeviceSession();
        SUDS.setDevice_id(SUDSDTO.getDevice_id());
        SUDS.setSession_id(SUDSDTO.getSession_id());
        SUDS.setUser_id(SUDSDTO.getUser_id());
        SUDS.setCreated(SUDSDTO.getCreated());
        SUDS.setLast_updated(SUDSDTO.getLast_updated());
        return SUDS;
    }

    public SrnUserDeviceSessionDTO SrnUserDeviceSessionToDTO(SrnUserDeviceSession SUDS) {
        SrnUserDeviceSessionDTO SUDSDTO = new SrnUserDeviceSessionDTO();
        SUDSDTO.setDevice_id(SUDS.getDevice_id());
        SUDSDTO.setSession_id(SUDS.getSession_id());
        SUDSDTO.setUser_id(SUDS.getUser_id());
        SUDSDTO.setCreated(SUDS.getCreated());
        SUDSDTO.setLast_updated(SUDS.getLast_updated());
        return SUDSDTO;
    }
}
