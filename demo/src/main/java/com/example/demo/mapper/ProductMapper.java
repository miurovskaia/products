package com.example.demo.mapper;

import com.example.demo.dto.CreateProductDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.entity.ProductEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ProductMapper {


    ProductDto productEntityToProductDto(ProductEntity productEntity);
/*
    @Mappings({
            @Mapping(target = "bankid", source = "bankid"),
            @Mapping(target = "surname", source = "surname"),
    })

 */
    ProductEntity createProductDtoToProductEntity(CreateProductDto createProductDto);
}
