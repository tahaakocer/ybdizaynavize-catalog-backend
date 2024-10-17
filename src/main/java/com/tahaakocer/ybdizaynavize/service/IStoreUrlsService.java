package com.tahaakocer.ybdizaynavize.service;

import com.tahaakocer.ybdizaynavize.dto.StoreDto;
import com.tahaakocer.ybdizaynavize.dto.StoreUrlsDto;

import java.util.List;

public interface IStoreUrlsService {
    StoreUrlsDto create(Long storeId, String url);

    String delete(Long id);

    StoreUrlsDto get(Long id);

    List<StoreUrlsDto> getAll();
}
