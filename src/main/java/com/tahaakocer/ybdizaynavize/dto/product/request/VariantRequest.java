package com.tahaakocer.ybdizaynavize.dto.product.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VariantRequest {
    private Long productId;
    private List<Long> attributeValueIds;
    private Double price;
    private Double discountedPrice;
    private Integer stock;
    private MultipartFile[] photoFiles;

    // Store URL bilgileri için DTO listesi (ID ve URL)
    private List<StoreUrlsRequest> storeUrls;
}
