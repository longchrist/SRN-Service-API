package com.srn.api.repo;

import com.srn.api.model.entity.SrnStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISrnStoreRepo extends JpaRepository<SrnStore, Long> {
    List<SrnStore> findAll();
    SrnStore findById(String id);
    List<SrnStore> findByBrandId(String brandId);
}