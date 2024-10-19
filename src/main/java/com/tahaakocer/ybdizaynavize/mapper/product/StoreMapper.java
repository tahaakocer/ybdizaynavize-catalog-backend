package com.tahaakocer.ybdizaynavize.mapper.product;

import com.tahaakocer.ybdizaynavize.dto.product.StoreDto;
import com.tahaakocer.ybdizaynavize.dto.product.response.StoreResponse;
import com.tahaakocer.ybdizaynavize.model.product.Store;
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
