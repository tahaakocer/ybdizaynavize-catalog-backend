package com.tahaakocer.ybdizaynavize.service;

import com.tahaakocer.ybdizaynavize.dto.AttributeValueDto;

import java.util.List;

public interface IAttributeValueService {

    AttributeValueDto create(Long attributeId, String value);
    AttributeValueDto getById(Long id);
    String delete(Long id);
    List<AttributeValueDto> getAll();
}
