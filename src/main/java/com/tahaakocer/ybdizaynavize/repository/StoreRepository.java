package com.tahaakocer.ybdizaynavize.repository;

import com.tahaakocer.ybdizaynavize.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
}
