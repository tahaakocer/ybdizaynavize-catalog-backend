package com.tahaakocer.ybdizaynavize.mapper.product;


import com.tahaakocer.ybdizaynavize.dto.product.ProductDto;
import com.tahaakocer.ybdizaynavize.dto.product.ProductVariantDto;
import com.tahaakocer.ybdizaynavize.dto.product.request.ProductRequest;
import com.tahaakocer.ybdizaynavize.dto.product.response.ProductResponse;
import com.tahaakocer.ybdizaynavize.model.product.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {VariantMapper.class})
public interface ProductMapper {
    Product dtoToEntity(ProductDto productDto);

    ProductDto entityToDto(Product saved);

    ProductDto requestToDto(ProductRequest productRequest);

    ProductResponse dtoToResponse(ProductDto saved);

    List<ProductDto> entityListToDtoList(List<Product> products);

    List<ProductResponse> dtoListToResponseList(List<ProductDto> all);

    ProductVariantDto dtoToVariantDto(ProductDto productDto);
}
