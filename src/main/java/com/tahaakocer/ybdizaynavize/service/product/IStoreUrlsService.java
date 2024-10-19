package com.tahaakocer.ybdizaynavize.service.product;

import com.tahaakocer.ybdizaynavize.dto.product.StoreUrlsDto;

import java.util.List;

public interface IStoreUrlsService {
    StoreUrlsDto create(Long storeId, String url);

    String delete(Long id);

    StoreUrlsDto get(Long id);

    List<StoreUrlsDto> getAll();

    StoreUrlsDto update(Long id, String url);
}
