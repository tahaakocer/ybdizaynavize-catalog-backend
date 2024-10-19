package com.tahaakocer.ybdizaynavize.dto.product.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tahaakocer.ybdizaynavize.dto.product.AttributeValueDto;
import com.tahaakocer.ybdizaynavize.dto.product.ImageDto;
import com.tahaakocer.ybdizaynavize.dto.product.StoreUrlsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VariantProductDto {
    private Long id;
    private List<AttributeValueDto> attributeValues;
    private String sku;
    private Double price;
    private Double discountedPrice;
    private Integer stock;
    private List<ImageDto> images;
    // Store URL'leri için DTO
    private List<StoreUrlsDto> storeUrls;
}
