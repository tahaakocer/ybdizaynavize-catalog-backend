package com.tahaakocer.ybdizaynavize.mapper.product;

import com.tahaakocer.ybdizaynavize.dto.product.StoreUrlsDto;
import com.tahaakocer.ybdizaynavize.dto.product.request.StoreUrlsRequest;
import com.tahaakocer.ybdizaynavize.dto.product.response.StoreUrlsResponse;
import com.tahaakocer.ybdizaynavize.model.product.StoreUrls;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StoreUrlsMapper
{
    StoreUrls dtoToEntity(StoreUrlsDto storeUrlsDto);

    StoreUrlsDto entityToDto(StoreUrls saved);

    List<StoreUrlsDto> entityListToDtoList(List<StoreUrls> attributeValues);

    StoreUrlsResponse dtoToResponse(StoreUrlsDto storeUrlsDto);

    List<StoreUrlsResponse> dtoListToResponseList(List<StoreUrlsDto> all);

    StoreUrlsDto requestToDto(StoreUrlsRequest storeUrlsRequest);
}
