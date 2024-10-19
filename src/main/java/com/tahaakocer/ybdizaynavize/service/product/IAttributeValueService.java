package com.tahaakocer.ybdizaynavize.service.product;

import com.tahaakocer.ybdizaynavize.dto.product.AttributeValueDto;

import java.util.List;

public interface IAttributeValueService {

    AttributeValueDto create(Long attributeId, String value);
    AttributeValueDto getById(Long id);
    String delete(Long id);
    List<AttributeValueDto> getAll();
}
