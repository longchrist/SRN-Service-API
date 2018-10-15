package com.srn.api.repo;

import com.srn.api.model.entity.SrnPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISrnUserPointsRepo extends JpaRepository<SrnPoint, Long> {
    List<SrnPoint> findAll();
    List<SrnPoint> findPointsByUserId(long userId);
}