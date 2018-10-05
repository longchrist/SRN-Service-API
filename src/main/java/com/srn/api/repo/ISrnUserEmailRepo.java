package com.srn.api.repo;

import com.srn.api.model.entity.SrnEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISrnUserEmailRepo extends JpaRepository<SrnEmail, String> {
    SrnEmail findByEmail(String email);
}