package com.tahaakocer.ybdizaynavize.service.impl;


import com.tahaakocer.ybdizaynavize.dto.AttributeDto;
import com.tahaakocer.ybdizaynavize.dto.AttributeValueDto;
import com.tahaakocer.ybdizaynavize.exception.EntityNotFoundException;
import com.tahaakocer.ybdizaynavize.mapper.AttributeValueMapper;
import com.tahaakocer.ybdizaynavize.model.AttributeValue;
import com.tahaakocer.ybdizaynavize.repository.AttributeValueRepository;
import com.tahaakocer.ybdizaynavize.service.IAttributeValueService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class AttributeValueService implements IAttributeValueService {
    private final AttributeValueRepository attributeValueRepository;
    private final AttributeService attributeService;
    private final AttributeValueMapper attributeValueMapper;

    public AttributeValueService(AttributeValueRepository attributeValueRepository,
                                 AttributeService attributeService,
                                 AttributeValueMapper attributeValueMapper) {
        this.attributeValueRepository = attributeValueRepository;
        this.attributeService = attributeService;
        this.attributeValueMapper = attributeValueMapper;
    }

    @Override
    public AttributeValueDto create(Long attributeId, String value) {
        AttributeDto attributeDto = this.attributeService.getById(attributeId);
        if(attributeDto != null){
            AttributeValueDto attributeValueDto = AttributeValueDto.builder()
                    .attribute(attributeDto)
                    .attributeValue(value)
                    .build();

            AttributeValue saved = this.attributeValueRepository.save(
                    this.attributeValueMapper.dtoToEntity(attributeValueDto));
            log.info("Attribute value created: {}", attributeValueDto);
            return this.attributeValueMapper.entityToDto(saved);
        }
        return null;
    }

    @Override
    public AttributeValueDto getById(Long id) {

        AttributeValue attributeValue = this.attributeValueRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Attribute value not found with id: " + id)
        );
        log.info("Attribute value found: {}", attributeValue);
        return this.attributeValueMapper.entityToDto(attributeValue);
    }

    @Override
    public String delete(Long id) {
        AttributeValue attributeValue = this.attributeValueRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Attribute value not found with id: " + id));

        this.attributeValueRepository.delete(attributeValue);
        log.info("Attribute value deleted: {}", attributeValue);
        return "Attribute value deleted by id: " + id;
    }

    @Override
    public List<AttributeValueDto> getAll() {
        List<AttributeValue> attributeValues = this.attributeValueRepository.findAll();
        log.info("Attribute values found: {}", attributeValues);
        return this.attributeValueMapper.entityListToDtoList(attributeValues);
    }
}
