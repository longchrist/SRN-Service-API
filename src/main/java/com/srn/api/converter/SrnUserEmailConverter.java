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
    public SrnUserEmail DTOtoSrnUserEmail(SrnUserEmailDTO SUEDTO) {
        SrnUserEmail SUE = new SrnUserEmail();
        SUE.setId(SUEDTO.getId());
        SUE.setEmail(SUEDTO.getEmail());
        SUE.setLogin_type(SUEDTO.getLogin_type());
        SUE.setCreated(SUEDTO.getCreated());
        SUE.setLast_updated(SUEDTO.getLast_updated());
        return SUE;
    }

    public SrnUserEmailDTO SrnUserEmailToDTO(SrnUserEmail SUE) {
        SrnUserEmailDTO SUEDTO = new SrnUserEmailDTO();
        SUEDTO.setId(SUE.getId());
        SUEDTO.setEmail(SUE.getEmail());
        SUEDTO.setLogin_type(SUE.getLogin_type());
        SUEDTO.setCreated(SUE.getCreated());
        SUEDTO.setLast_updated(SUE.getLast_updated());
        return SUEDTO;
    }
}
