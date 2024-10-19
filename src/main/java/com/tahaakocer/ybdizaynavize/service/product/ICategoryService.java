package com.tahaakocer.ybdizaynavize.service.product;

import com.tahaakocer.ybdizaynavize.dto.product.CategoryDto;

import java.util.List;

public interface ICategoryService {
    CategoryDto create(String name);
    CategoryDto getById(Long id);
    String delete(Long id);
    List<CategoryDto> getAll();
}
