package com.tahaakocer.ybdizaynavize.repository;

import com.tahaakocer.ybdizaynavize.model.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeValueRepository extends JpaRepository<AttributeValue, Long> {
}
