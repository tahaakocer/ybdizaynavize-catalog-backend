package com.tahaakocer.ybdizaynavize.mapper;


import com.tahaakocer.ybdizaynavize.dto.AttributeValueDto;
import com.tahaakocer.ybdizaynavize.dto.response.AttributeValueResponse;
import com.tahaakocer.ybdizaynavize.model.AttributeValue;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttributeValueMapper {
    public AttributeValue dtoToEntity(AttributeValueDto attributeValueDto);

    AttributeValueDto entityToDto(AttributeValue save);

    AttributeValueResponse dtoToResponse(AttributeValueDto attributeValueDto);

    List<AttributeValueResponse> dtoListToResponseList(List<AttributeValueDto> attributeValueDtos);

    List<AttributeValueDto> entityListToDtoList(List<AttributeValue> attributeValues);
}
