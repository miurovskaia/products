package com.example.demo.service;

import com.example.demo.dto.CreateProductDto;
import com.example.demo.entity.ProductEntity;
import com.example.demo.entity.ProductEntityAud;
import com.example.demo.repository.ProductAudRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
//@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductAudRepository productAudRepository;

    public ProductService(ProductRepository productRepository,ProductAudRepository productAudRepository ) {
        this.productRepository = productRepository;
        this.productAudRepository = productAudRepository;
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



    public Set<ProductEntityAud> getPreviousProductVersionsByProductId(Integer id){

        Set<ProductEntityAud> productEntityAudSet = new HashSet<>();

        ProductEntityAud maxRevEntity = productAudRepository.findFirstByOrderByRevDesc()
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Integer maxRev = maxRevEntity.getRev();

        productEntityAudSet.addAll(productAudRepository.findByIdAndRevIsNot(id,maxRev ));

        return productEntityAudSet;
    }

    public Integer createProduct(ProductEntity productEntity) {
        return (productRepository.save(productEntity)).getId();
    }

    public void changeProduct(CreateProductDto createProductDto, Integer id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        productEntity.setName(createProductDto.getName());
        productEntity.setTariffId(createProductDto.getTariffId());
        productEntity.setTimedate(createProductDto.getTimedate());
        productRepository.save(productEntity);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
    public Set<ProductEntityAud> getVersionsForPeriodByProductId(Integer productId, Instant startTimedate, Instant endTimedate) {
        Set<ProductEntityAud> productEntityAudSet = new HashSet<>();
        productEntityAudSet.addAll(productAudRepository.findAllByIdAndTimedateBetween(productId,startTimedate, endTimedate ));
        return productEntityAudSet;
    }

    public void revertToPreviousVersion(Integer id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        ProductEntityAud prevProductEntityAud = productAudRepository.findSecondLatestBy(id)
                .orElseThrow(() -> new RuntimeException("ProductAud not found"));

        product.setName(prevProductEntityAud.getName());
        product.setTariffId(prevProductEntityAud.getTariffId());
        product.setTimedate(prevProductEntityAud.getTimedate());
        productRepository.save(product);

/*
         Integer currentVersion = product.getVersion();
        if (currentVersion>0) {
            product.setVersion(currentVersion - 1);
        }
        else {
            throw new RuntimeException("it is min version");
        }

 */
        productRepository.save(product);
    }

}
