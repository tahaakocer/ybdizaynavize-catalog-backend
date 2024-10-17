package com.tahaakocer.ybdizaynavize.mapper;


import com.tahaakocer.ybdizaynavize.dto.CategoryDto;
import com.tahaakocer.ybdizaynavize.dto.response.CategoryResponse;
import com.tahaakocer.ybdizaynavize.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    public CategoryDto entityToDto(Category saved);

    List<CategoryDto> entityListToDtoList(List<Category> categories);

    CategoryResponse dtoToResponse(CategoryDto categoryDto);

    List<CategoryResponse> dtoListToResponseList(List<CategoryDto> all);
}
