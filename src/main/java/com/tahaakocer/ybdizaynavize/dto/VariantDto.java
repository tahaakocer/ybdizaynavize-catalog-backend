package com.tahaakocer.ybdizaynavize.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class VariantDto {
    private Long id;
    private Long productId;
    @JsonIgnore
    private ProductVariantDto product;
    private List<AttributeValueDto> attributeValues;
    private List<Long> attributeValueIds;
    private String sku;
    private Double price;
    private Double discountedPrice;
    private Integer stock;
    private MultipartFile [] photoFiles;
    private List<ImageDto> images;
    private List<StoreUrlsDto> storeUrls;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
