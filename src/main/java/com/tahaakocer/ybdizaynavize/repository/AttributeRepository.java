package com.tahaakocer.ybdizaynavize.repository;

import com.tahaakocer.ybdizaynavize.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {
}
