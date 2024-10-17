package com.tahaakocer.ybdizaynavize.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tahaakocer.ybdizaynavize.dto.AttributeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class AttributeValueResponse {
    private Long id;
    private AttributeDto attribute;
    private String attributeValue;
}