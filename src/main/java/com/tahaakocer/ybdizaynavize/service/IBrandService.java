package com.tahaakocer.ybdizaynavize.service;

import com.tahaakocer.ybdizaynavize.dto.BrandDto;

import java.util.List;

public interface IBrandService {
    BrandDto create(String name);
    BrandDto getById(Long id);
    String delete(Long id);
    List<BrandDto> getAll();
}
