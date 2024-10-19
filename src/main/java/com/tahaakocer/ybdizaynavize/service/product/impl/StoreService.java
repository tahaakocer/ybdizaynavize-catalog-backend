package com.tahaakocer.ybdizaynavize.service.product.impl;

import com.tahaakocer.ybdizaynavize.dto.product.StoreDto;
import com.tahaakocer.ybdizaynavize.exception.EntityNotFoundException;
import com.tahaakocer.ybdizaynavize.mapper.product.StoreMapper;
import com.tahaakocer.ybdizaynavize.model.product.Store;
import com.tahaakocer.ybdizaynavize.repository.product.StoreRepository;
import com.tahaakocer.ybdizaynavize.service.product.IStoreService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class StoreService implements IStoreService {
    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    public StoreService(StoreRepository storeRepository, StoreMapper storeMapper) {
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
    }

    @Override
    public StoreDto create(String name) {
        Store store = Store.builder().name(name).build();
        Store saved = this.storeRepository.save(store);
        log.info("Saved store: " + saved);
        return this.storeMapper.entityToDto(saved);
    }
    @Override
    public StoreDto update(Long id, String name) {
        Store store = this.storeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Store not found with id: " + id)
        );
        store.setName(name);
        Store saved = this.storeRepository.save(store);
        log.info("updated store: " + saved);
        return this.storeMapper.entityToDto(saved);
    }
    @Override
    public String delete(Long id) {
        Store store = this.storeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Store not found with id: " + id)
        );
        this.storeRepository.delete(store);
        log.info("deleted store: " + store);
        return "deleted store: " + store;
    }
    @Override
    public StoreDto get(Long id) {
        Store store = this.storeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Store not found with id: " + id)
        );
        return this.storeMapper.entityToDto(store);
    }
    @Override
    public List<StoreDto> getAll() {
        List<Store> stores = this.storeRepository.findAll();
        return this.storeMapper.entityListToDtoList(stores);
    }

}
