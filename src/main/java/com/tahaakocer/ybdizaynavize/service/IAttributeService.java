package com.tahaakocer.ybdizaynavize.service;

import com.tahaakocer.ybdizaynavize.dto.AttributeDto;

import java.util.List;

public interface IAttributeService {
    AttributeDto create(String name);
    String delete(Long id);
    AttributeDto getById(Long id);

    List<AttributeDto> getAll();
}
