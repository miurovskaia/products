package com.example.demo.service;

import com.example.demo.dto.CreateProductDto;
import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
//@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductEntity getProduct(Integer id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return product;
    }


    //edit
    public Set<ProductEntity> searchByString(String searchString){
        String[] searchWords = searchString.split(" ");
        List<String> searchWordsList = Arrays.asList(searchWords);
        Set<ProductEntity> productEntitySet = new HashSet<>();

        productEntitySet.addAll(productRepository.findAllByNameIn(searchWordsList));

        return productEntitySet;
    }

    public Integer createProduct(ProductEntity productEntity) {
        return (productRepository.save(productEntity)).getId();
    }

    public void changeProduct(CreateProductDto createProductDto, String id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        productEntity.setName(createProductDto.getName());
        productEntity.setTariffId(createProductDto.getTariffId());
        productEntity.setTimeAndDate(createProductDto.getTimeAndDate());
        productRepository.save(productEntity);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }


}
