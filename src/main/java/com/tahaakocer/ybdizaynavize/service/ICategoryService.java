package com.tahaakocer.ybdizaynavize.service;

import com.tahaakocer.ybdizaynavize.dto.CategoryDto;

import java.util.List;

public interface ICategoryService {
    CategoryDto create(String name);
    CategoryDto getById(Long id);
    String delete(Long id);
    List<CategoryDto> getAll();
}
