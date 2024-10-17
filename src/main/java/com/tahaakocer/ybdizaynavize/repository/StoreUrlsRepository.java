package com.tahaakocer.ybdizaynavize.repository;

import com.tahaakocer.ybdizaynavize.model.StoreUrls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreUrlsRepository extends JpaRepository<StoreUrls, Long> {
}
