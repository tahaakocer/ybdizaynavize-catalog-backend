package com.tahaakocer.ybdizaynavize.mapper;

import com.tahaakocer.ybdizaynavize.dto.StoreUrlsDto;
import com.tahaakocer.ybdizaynavize.dto.response.StoreUrlsResponse;
import com.tahaakocer.ybdizaynavize.model.StoreUrls;
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
}
