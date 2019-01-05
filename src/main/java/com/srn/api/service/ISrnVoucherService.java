package com.srn.api.service;/*
 Author: vikraa
 created: 1/5/19 - 2:15 PM
*/

import com.srn.api.model.dto.SrnVoucherDto;

import java.util.List;

public interface ISrnVoucherService {
    List<SrnVoucherDto> findUserVoucher(String session);

}