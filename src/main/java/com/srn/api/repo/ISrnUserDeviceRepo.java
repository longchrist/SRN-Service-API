package com.srn.api.repo;

import com.srn.api.model.entity.SrnUserDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISrnUserDeviceRepo extends JpaRepository<SrnUserDevice, Long> {
    SrnUserDevice findById(long id);
    SrnUserDevice findBySessionId(String text);
}