package com.tahaakocer.ybdizaynavize.service.product.impl;

import com.tahaakocer.ybdizaynavize.dto.product.AttributeValueDto;
import com.tahaakocer.ybdizaynavize.dto.product.ProductDto;
import com.tahaakocer.ybdizaynavize.dto.product.StoreDto;
import com.tahaakocer.ybdizaynavize.dto.product.VariantDto;
import com.tahaakocer.ybdizaynavize.exception.EntityNotFoundException;
import com.tahaakocer.ybdizaynavize.mapper.product.AttributeValueMapper;
import com.tahaakocer.ybdizaynavize.mapper.product.ProductMapper;
import com.tahaakocer.ybdizaynavize.mapper.product.StoreMapper;
import com.tahaakocer.ybdizaynavize.mapper.product.VariantMapper;
import com.tahaakocer.ybdizaynavize.model.product.AttributeValue;
import com.tahaakocer.ybdizaynavize.model.product.Image;
import com.tahaakocer.ybdizaynavize.model.product.StoreUrls;
import com.tahaakocer.ybdizaynavize.model.product.Variant;
import com.tahaakocer.ybdizaynavize.repository.product.VariantRepository;
import com.tahaakocer.ybdizaynavize.repository.specifications.VariantSpecification;
import com.tahaakocer.ybdizaynavize.service.product.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class VariantService implements IVariantService {
    private final VariantRepository variantRepository;
    private final VariantMapper variantMapper;
    private final AwsS3Service awsS3Service;
    private final IProductService productService;
    private final ProductMapper productMapper;
    private final IAttributeValueService attributeValueService;
    private final AttributeValueMapper attributeValueMapper;
    private final IStoreService storeService; // IStoreService
    private final StoreMapper storeMapper;
    private final IStoreUrlsService storeUrlsService;

    public VariantService(VariantRepository variantRepository,
                          VariantMapper variantMapper,
                          AwsS3Service awsS3Service,
                          IProductService productService,
                          ProductMapper productMapper,
                          IAttributeValueService attributeValueService,
                          AttributeValueMapper attributeValueMapper, IStoreService storeService, StoreMapper storeMapper, IStoreUrlsService storeUrlsService) {
        this.variantRepository = variantRepository;
        this.variantMapper = variantMapper;
        this.awsS3Service = awsS3Service;
        this.productService = productService;
        this.productMapper = productMapper;
        this.attributeValueService = attributeValueService;
        this.attributeValueMapper = attributeValueMapper;
        this.storeService = storeService;
        this.storeMapper = storeMapper;
        this.storeUrlsService = storeUrlsService;
    }

    private Pageable createPageable(int page, int size, String sortBy, String sortDirection) {
        Sort sort = Sort.by(sortBy);
        sort = sortDirection.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
        return PageRequest.of(page, size, sort);
    }

    @Override
    @Transactional
    public VariantDto create(VariantDto variantDto) {
        // Product
        ProductDto productDto = this.productService.getById(variantDto.getProductId());
        variantDto.setProduct(this.productMapper.dtoToVariantDto(productDto));

        log.info("variantDto: " + variantDto);

        // AttributeValues
        List<AttributeValueDto> attributeValueDtoList = new ArrayList<>();
        variantDto.getAttributeValueIds().forEach(attributeValueId -> {
            AttributeValueDto attributeValue = this.attributeValueService.getById(attributeValueId);
            attributeValueDtoList.add(attributeValue);
        });
        variantDto.setAttributeValues(attributeValueDtoList);
        log.info("attributeValueDtoList: " + attributeValueDtoList);

        // Image
        Variant variant = this.variantMapper.dtoToEntity(variantDto);
        List<String> imageUrls = new ArrayList<>();
        if (variantDto.getPhotoFiles() != null) {
            imageUrls = awsS3Service.saveMultipleImages(variantDto.getPhotoFiles());
        }
        List<Image> imageEntities = imageUrls.stream()
                .map(imageUrl -> {
                    Image image = new Image();
                    image.setImageUrl(imageUrl);
                    return image;
                }).toList();

        variant.setImages(imageEntities);
        log.info("imageEntities: " + imageEntities);

        // Store URLs

        if (variantDto.getStoreUrls() != null && !variantDto.getStoreUrls().isEmpty()) {
            List<StoreUrls> storeUrlsEntities = variantDto.getStoreUrls().stream()
                    .map(storeUrlDTO -> {
                        StoreDto storeDto = this.storeService.get(storeUrlDTO.getStoreId());
                        StoreUrls storeUrl = new StoreUrls();
                        storeUrl.setStore(this.storeMapper.dtoToEntity(storeDto));
                        storeUrl.setUrl(storeUrlDTO.getUrl());
                        return storeUrl;
                    }).toList();
            variant.setStoreUrls(storeUrlsEntities);
            log.info("storeUrlsEntities: " + storeUrlsEntities);
        }

        // Save
        Variant savedVariant = this.variantRepository.save(variant);
        log.info("savedVariant: " + savedVariant);

        return this.variantMapper.entityToDto(savedVariant);
    }


    @Override
    public String delete(Long id) {
        Variant variant = this.variantRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Variant not found with id: " + id)
        );
        this.variantRepository.delete(variant);
        log.info("Variant deleted: {}", variant);
        return "Variant deleted by id: " + id;
    }

    @Override
    public List<VariantDto> getAll() {
        List<Variant> list = this.variantRepository.findAll();
        log.info("list: " + list);
        return this.variantMapper.entityListToDtoList(list);
    }

    @Override
    public VariantDto getById(Long id) {
        Variant variant = this.variantRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Variant not found with id: " + id)
        );
        log.info("variant: " + variant);
        return this.variantMapper.entityToDto(variant);
    }

    @Override
    @Transactional
    public VariantDto update(Long id, VariantDto variantDto) {
        // Variant'ı bul
        Variant variant = this.variantRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Variant not found with id: " + id)
        );

        // Fiyat, stok vb. güncelleme işlemleri
        variant.setPrice(variantDto.getPrice());
        variant.setDiscountedPrice(variantDto.getDiscountedPrice());
        variant.setStock(variantDto.getStock());

        // AttributeValue'leri güncelleme
        List<AttributeValueDto> attributeValueDtoList = new ArrayList<>();
        variantDto.getAttributeValueIds().forEach(attributeValueId -> {
            AttributeValueDto attributeValue = this.attributeValueService.getById(attributeValueId);
            attributeValueDtoList.add(attributeValue);
        });
        List<AttributeValue> updatedAttributeValues = attributeValueDtoList.stream()
                .map(this.attributeValueMapper::dtoToEntity)
                .toList();
        variant.setAttributeValues(new ArrayList<>(updatedAttributeValues));

//        TODO Image'leri güncelleme
//        TODO veritabanında birikme oluyor
        // Store URLs'i güncelleme
        if (variantDto.getStoreUrls() != null && !variantDto.getStoreUrls().isEmpty()) {
            List<StoreUrls> storeUrlsEntities = variantDto.getStoreUrls().stream()
                    .map(storeUrlDTO -> {
                        StoreDto storeDto = this.storeService.get(storeUrlDTO.getStoreId());
                        StoreUrls storeUrl = new StoreUrls();
                        storeUrl.setStore(this.storeMapper.dtoToEntity(storeDto));
                        storeUrl.setUrl(storeUrlDTO.getUrl());
                        return storeUrl;
                    }).toList();
            variant.setStoreUrls(new ArrayList<>(storeUrlsEntities)); // Eğer immutable bir set ya da liste kullanıyorsanız bunu deneyin.

            log.info("storeUrlsEntities updated: " + storeUrlsEntities);
        }

        // Variant'ı kaydetme
        Variant saved = this.variantRepository.save(variant);
        log.info("variant updated: " + variant);

        return this.variantMapper.entityToDto(saved);
    }


    @Override
    public List<VariantDto> getByProductId(Long id) {
       List<VariantDto> list = this.variantMapper.entityListToDtoList(this.variantRepository.findAllByProductId(id));
       log.info("list: " + list);
       return list;

    }

    @Override
    public List<VariantDto> filterVariantsByAttributeValues(List<Integer> attributeValues) {
        List<Variant> variants = this.variantRepository.findAll(VariantSpecification.hasAttributeValue(attributeValues));
        return this.variantMapper.entityListToDtoList(variants);
    }

    @Override
    public Page<VariantDto> filterVariantsByAttributeValues(List<Integer> attributeValues, int page, int size, String sortBy, String sortDirection) {
        Pageable pageable = createPageable(page,size,sortBy,sortDirection);
        Page<Variant> variants = this.variantRepository.findAll(VariantSpecification.hasAttributeValue(attributeValues), pageable);
        log.info("variants: " + variants);
        return variants.map(this.variantMapper::entityToDto);
    }

    @Override
    public Page<VariantDto> getAll(int page, int size, String sortBy, String sortDirection) {
        Pageable pageable = createPageable(page, size, sortBy, sortDirection);
        Page<Variant> variants = this.variantRepository.findAll(pageable);
        log.info("variants: " + variants);
        return variants.map(this.variantMapper::entityToDto);
    }

}
