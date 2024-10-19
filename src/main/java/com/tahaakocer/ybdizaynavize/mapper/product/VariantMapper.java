package com.tahaakocer.ybdizaynavize.mapper.product;


import com.tahaakocer.ybdizaynavize.dto.product.ProductVariantDto;
import com.tahaakocer.ybdizaynavize.dto.product.VariantDto;
import com.tahaakocer.ybdizaynavize.dto.product.request.VariantRequest;
import com.tahaakocer.ybdizaynavize.dto.product.request.VariantUpdateRequest;
import com.tahaakocer.ybdizaynavize.dto.product.response.VariantByIdResponse;
import com.tahaakocer.ybdizaynavize.dto.product.response.VariantResponse;
import com.tahaakocer.ybdizaynavize.model.product.Variant;
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
