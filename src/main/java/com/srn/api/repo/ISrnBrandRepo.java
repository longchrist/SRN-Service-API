package com.srn.api.repo;

import com.srn.api.model.entity.SrnBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISrnBrandRepo extends JpaRepository<SrnBrand, Long> {
    List<SrnBrand> findAll();
    SrnBrand findById(String id);
}