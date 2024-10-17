package com.tahaakocer.ybdizaynavize.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tahaakocer.ybdizaynavize.model.Brand;
import com.tahaakocer.ybdizaynavize.model.Category;
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
public class ProductResponse{

    private Long id;
    private String name;
    private String description;
    private Category category;
    private Brand brand;
    private List<VariantProductDto> variants;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
