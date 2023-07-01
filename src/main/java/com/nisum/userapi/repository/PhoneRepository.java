package com.nisum.userapi.repository;

import com.nisum.userapi.domain.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity, Integer> {
}
