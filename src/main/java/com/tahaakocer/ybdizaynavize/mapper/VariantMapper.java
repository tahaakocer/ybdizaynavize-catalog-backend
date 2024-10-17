package com.tahaakocer.ybdizaynavize.mapper;


import com.tahaakocer.ybdizaynavize.dto.ProductVariantDto;
import com.tahaakocer.ybdizaynavize.dto.VariantDto;
import com.tahaakocer.ybdizaynavize.dto.request.VariantRequest;
import com.tahaakocer.ybdizaynavize.dto.request.VariantUpdateRequest;
import com.tahaakocer.ybdizaynavize.dto.response.VariantByIdResponse;
import com.tahaakocer.ybdizaynavize.dto.response.VariantResponse;
import com.tahaakocer.ybdizaynavize.model.Variant;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses =  {StoreUrlsMapper.class})
public interface VariantMapper {
     Variant dtoToEntity(VariantDto variantDto);

    VariantDto entityToDto(Variant savedVariant);

    VariantDto requestToDto(VariantRequest variantRequest);

    VariantResponse dtoToResponse(VariantDto saved);
    ProductVariantDto dtoToProductVariantDto(VariantDto variantDto);
    ProductVariantDto entityToProductVariantDto(Variant variant);

    List<VariantDto> entityListToDtoList(List<Variant> list);

    VariantByIdResponse dtoToVariantByIdResponse(VariantDto variantDto);

    VariantDto updateRequestToDto(VariantUpdateRequest variantUpdateRequest);
}
