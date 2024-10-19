package com.tahaakocer.ybdizaynavize.service.product.impl;

import com.tahaakocer.ybdizaynavize.dto.product.StoreDto;
import com.tahaakocer.ybdizaynavize.dto.product.StoreUrlsDto;
import com.tahaakocer.ybdizaynavize.exception.EntityNotFoundException;
import com.tahaakocer.ybdizaynavize.mapper.product.StoreUrlsMapper;
import com.tahaakocer.ybdizaynavize.model.product.StoreUrls;
import com.tahaakocer.ybdizaynavize.repository.product.StoreUrlsRepository;
import com.tahaakocer.ybdizaynavize.service.product.IStoreService;
import com.tahaakocer.ybdizaynavize.service.product.IStoreUrlsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class StoreUrlsService implements IStoreUrlsService {
    private final StoreUrlsRepository storeUrlsRepository;
    private final StoreUrlsMapper storeUrlsMapper;
    private final IStoreService storeService;

    public StoreUrlsService(StoreUrlsRepository storeUrlsRepository, StoreUrlsMapper storeUrlsMapper, IStoreService storeService) {
        this.storeUrlsRepository = storeUrlsRepository;
        this.storeUrlsMapper = storeUrlsMapper;
        this.storeService = storeService;
    }

    @Override
    public StoreUrlsDto create(Long storeId, String url) {
        StoreDto storeDto = this.storeService.get(storeId);
        if(storeDto != null){
            StoreUrlsDto storeUrlsDto = StoreUrlsDto.builder()
                    .store(storeDto)
                    .url(url)
                    .build();

            StoreUrls saved = this.storeUrlsRepository.save(
                    this.storeUrlsMapper.dtoToEntity(storeUrlsDto));
            log.info("StoreUrl created: {}", storeUrlsDto);
            return this.storeUrlsMapper.entityToDto(saved);
        }
        return null;
    }


    @Override
    public String delete(Long id) {
        StoreUrls storeUrls = this.storeUrlsRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Store Url not found with id: " + id));

        this.storeUrlsRepository.delete(storeUrls);
        log.info("Store url deleted: {}", storeUrls);
        return "Store url deleted by id: " + id;
    }

    @Override
    public StoreUrlsDto get(Long id) {

        StoreUrls storeUrls = this.storeUrlsRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Attribute value not found with id: " + id)
        );
        log.info("Attribute value found: {}", storeUrls);
        return this.storeUrlsMapper.entityToDto(storeUrls);
    }

    @Override
    public List<StoreUrlsDto> getAll() {
        List<StoreUrls> attributeValues = this.storeUrlsRepository.findAll();
        log.info("store urls found: {}", attributeValues);
        return this.storeUrlsMapper.entityListToDtoList(attributeValues);    }

    @Override
    public StoreUrlsDto update(Long id, String url) {

        StoreUrls storeUrls = this.storeUrlsRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Store Url not found with id: " + id)
        );
        storeUrls.setUrl(url);
        StoreUrls saved = this.storeUrlsRepository.save(storeUrls);
        log.info("store url updated: {}", saved);
        return this.storeUrlsMapper.entityToDto(saved);
    }
}
