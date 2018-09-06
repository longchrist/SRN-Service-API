/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srn.api.converter;

import com.srn.api.dto.SrnUserEmailDTO;
import com.srn.api.model.SrnUserEmail;

/**
 *
 * @author long
 */

public final class SrnUserEmailConverter {
    public SrnUserEmail DTOtoSrnUserEmail(SrnUserEmailDTO sueDto) {
        SrnUserEmail sue = new SrnUserEmail();
        sue.setId(sueDto.getId());
        sue.setEmail(sueDto.getEmail());
        sue.setLogin_type(sueDto.getLogin_type());
        sue.setCreated(sueDto.getCreated());
        sue.setLast_updated(sueDto.getLast_updated());
        return sue;
    }

    public SrnUserEmailDTO SrnUserEmailToDTO(SrnUserEmail sue) {
        SrnUserEmailDTO sueDto = new SrnUserEmailDTO();
        sueDto.setId(sue.getId());
        sueDto.setEmail(sue.getEmail());
        sueDto.setLogin_type(sue.getLogin_type());
        sueDto.setCreated(sue.getCreated());
        sueDto.setLast_updated(sue.getLast_updated());
        return sueDto;
    }
}
