package com.srn.api.repo;

import com.srn.api.model.entity.SrnProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISrnUserProfileRepo extends JpaRepository<SrnProfile, Long> {
    List<SrnProfile> findAll();
    SrnProfile findProfileByUserId(long id);
}