package com.srn.api.service.impl;

import com.srn.api.model.entity.SrnDevice;
import com.srn.api.model.response.Session;
import com.srn.api.repo.ISrnDeviceRepo;
import com.srn.api.service.ISrnDeviceService;
import com.srn.api.utils.FormatterUtils;
import com.srn.api.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service("srnDeviceService")
public class SrnDeviceServiceImpl implements ISrnDeviceService {

    @Autowired
    ISrnDeviceRepo srnDeviceRepo;

    @Override
    public Session registerDevice(SrnDevice device) {
        SrnDevice entity = srnDeviceRepo.findByImei(device.getImei());
        if ( entity != null) {
            entity.setLastUpdated(FormatterUtils.getCurrentTimestamp());
            srnDeviceRepo.save(entity);
        } else {
            device.setCreated(FormatterUtils.getCurrentTimestamp());
            device.setLastUpdated(FormatterUtils.getCurrentTimestamp());
            srnDeviceRepo.save(device);
        }

        Session session = new Session(device);
        session.setSessionId(SecurityUtils.getInstance().setData(device).setMethod(SecurityUtils.Method.SESSION_ENCRYPT).build());
        return session;
    }

    @Override
    public SrnDevice findDeviceById(long id) {
        return srnDeviceRepo.findById(id);
    }

    @Override
    public boolean unregisterDevice(SrnDevice device) {
        if (srnDeviceRepo.findByImei(device.getImei()) != null) {
            srnDeviceRepo.delete(device);
            return true;
        }
        return false;
    }

}