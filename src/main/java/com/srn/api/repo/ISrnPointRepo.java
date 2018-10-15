package com.srn.api.repo;

import com.srn.api.model.entity.SrnPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISrnPointRepo extends JpaRepository<SrnPoints, Long> {
    List<SrnPoints> findAll();
    List<SrnPoints> findPointsByUserId(long userId);
}