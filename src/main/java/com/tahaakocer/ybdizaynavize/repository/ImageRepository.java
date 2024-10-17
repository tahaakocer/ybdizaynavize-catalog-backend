package com.tahaakocer.ybdizaynavize.repository;

import com.tahaakocer.ybdizaynavize.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
