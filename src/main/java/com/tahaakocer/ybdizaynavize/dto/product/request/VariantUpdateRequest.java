package com.tahaakocer.ybdizaynavize.dto.product.request;

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
public class VariantUpdateRequest {

    private List<Long> attributeValueIds;
    private Double price;
    private Double discountedPrice;
    private Integer stock;
    private String sku;

    // Store URL bilgileri için DTO listesi (ID ve URL)
    private List<StoreUrlsRequest> storeUrls;

    // Resim dosyaları (fotoğraflar)
    private MultipartFile[] photoFiles;
}
