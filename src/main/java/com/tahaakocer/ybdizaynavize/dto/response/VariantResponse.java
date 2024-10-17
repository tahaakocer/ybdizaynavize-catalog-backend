package com.tahaakocer.ybdizaynavize.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tahaakocer.ybdizaynavize.dto.AttributeValueDto;
import com.tahaakocer.ybdizaynavize.dto.ImageDto;
import com.tahaakocer.ybdizaynavize.dto.ProductDto;
import com.tahaakocer.ybdizaynavize.dto.StoreUrlsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VariantResponse {
    private Long id;
    private ProductDto product;
    private List<AttributeValueDto> attributeValues;
    private String sku;
    private Double price;
    private Double discountedPrice;
    private Integer stock;
    private List<ImageDto> images;

    // Store URL'leri için DTO
    private List<StoreUrlsDto> storeUrls;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
