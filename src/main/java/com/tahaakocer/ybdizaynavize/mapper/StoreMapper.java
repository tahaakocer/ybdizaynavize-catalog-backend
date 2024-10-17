package com.tahaakocer.ybdizaynavize.mapper;

import com.tahaakocer.ybdizaynavize.dto.StoreDto;
import com.tahaakocer.ybdizaynavize.dto.response.StoreResponse;
import com.tahaakocer.ybdizaynavize.model.Store;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StoreMapper {
    Store dtoToEntity(StoreDto storeDto);

    StoreDto entityToDto(Store saved);

    List<StoreDto> entityListToDtoList(List<Store> stores);

    StoreResponse dtoToResponse(StoreDto storeDto);

    List<StoreResponse> dtoListToResponseList(List<StoreDto> all);
}
