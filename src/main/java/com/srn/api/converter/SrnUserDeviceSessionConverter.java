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
 * @author long
 */

public final class SrnUserDeviceSessionConverter {
    public SrnUserDeviceSession DTOtoSrnUserDeviceSession(SrnUserDeviceSessionDTO sudsDto) {
        SrnUserDeviceSession suds = new SrnUserDeviceSession();
        suds.setDevice_id(sudsDto.getDevice_id());
        suds.setSession_id(sudsDto.getSession_id());
        suds.setUser_id(sudsDto.getUser_id());
        suds.setCreated(sudsDto.getCreated());
        suds.setLast_updated(sudsDto.getLast_updated());
        return suds;
    }

    public SrnUserDeviceSessionDTO SrnUserDeviceSessionToDTO(SrnUserDeviceSession suds) {
        SrnUserDeviceSessionDTO sudsDto = new SrnUserDeviceSessionDTO();
        sudsDto.setDevice_id(suds.getDevice_id());
        sudsDto.setSession_id(suds.getSession_id());
        sudsDto.setUser_id(suds.getUser_id());
        sudsDto.setCreated(suds.getCreated());
        sudsDto.setLast_updated(suds.getLast_updated());
        return sudsDto;
    }
}
