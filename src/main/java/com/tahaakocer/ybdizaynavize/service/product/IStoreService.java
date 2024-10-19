package com.tahaakocer.ybdizaynavize.service.product;

import com.tahaakocer.ybdizaynavize.dto.product.StoreDto;

import java.util.List;

public interface IStoreService {
    StoreDto create(String name);
    StoreDto update(Long id, String name);
    String delete(Long id);
    StoreDto get(Long id);

    List<StoreDto> getAll();
    }
