/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srn.api.repository;

import com.srn.api.model.srnUserProfile;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author user
 */
public interface srnUserProfileRepository extends JpaRepository<srnUserProfile, Integer>{
    srnUserProfile findByUsername(String username);
}
