package com.example.demo.controller;

import com.example.demo.dto.CreateProductDto;
import com.example.demo.dto.ProductAudDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.entity.ProductEntity;
import com.example.demo.entity.ProductEntityAud;
//import com.example.demo.kafka.KafkaReceiver;
import com.example.demo.kafka.KafkaReceiver;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;
    private static final Logger logger = LoggerFactory.getLogger("ProductController");

    ProductController( ProductService productService1, ProductMapper productMapper1)
    {
        this.productService = productService1;
        this.productMapper = productMapper1;

    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody CreateProductDto createProductDto)  throws Exception{
        String tariffId = createProductDto.getTariffId().toString();
        System.out.println("tariffId= "+tariffId);
        System.out.println("tariffids from tariff service"+KafkaReceiver.tariffIds.toString());
        boolean resultOfCompare =false;
        for (String tarId: KafkaReceiver.tariffIds) {
            if (tariffId.equals(tarId)) {
                resultOfCompare = true;
            }
        }
        if(!resultOfCompare )
        {
            throw new RuntimeException("Tariff with this id does not exists!");
        }
        ProductEntity entity = productMapper.createProductDtoToProductEntity(createProductDto);
        Integer productId = productService.createProduct(entity);

        return ResponseEntity.ok(productId);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?>  getProductByProductId(@PathVariable Integer productId) {
        ProductDto productDto  = productMapper.productEntityToProductDto(productService.getProduct(productId));
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping("/peviousVersions/{productId}")
    public ResponseEntity<?>  getPreviousProductVersionsByProductId(@PathVariable Integer productId) {
        Set<ProductEntityAud> productAudEntitySet = productService.getPreviousProductVersionsByProductId(productId);
        Set<ProductAudDto> productDtoSet = new HashSet<>();
        for(ProductEntityAud productAud: productAudEntitySet )
        {
            productDtoSet.add(productMapper.productEntityAudToProductAudDto(productAud));
        }
        return new ResponseEntity(productDtoSet, HttpStatus.OK);
    }

    @GetMapping("/versionsForPeriod/{productId}")
    public ResponseEntity<?>  getVersionsForPeriodByProductId(@RequestParam Instant startTimeDate,@RequestParam Instant endTimeDate, @PathVariable Integer productId) {
        Set<ProductEntityAud> productAudEntitySet = productService.getVersionsForPeriodByProductId(productId, startTimeDate, endTimeDate);
        Set<ProductAudDto> productDtoSet = new HashSet<>();
        for(ProductEntityAud productAud: productAudEntitySet )
        {
            productDtoSet.add(productMapper.productEntityAudToProductAudDto(productAud));
        }
        return new ResponseEntity(productDtoSet, HttpStatus.OK);
    }

    @GetMapping("/revertToPreviousVersion/{productId}")
    public ResponseEntity<?>  revertToPreviousVersion( @PathVariable Integer productId) {
        productService.revertToPreviousVersion(productId);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(
            @RequestBody CreateProductDto createProductDto,
            @PathVariable Integer id
    ) {
        productService.changeProduct(createProductDto,  id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }


//edit
    /*
    @GetMapping("/search")
    public ResponseEntity<?> getClientBySearch(@RequestHeader(value = "SearchString", required = true) String searchString) throws Exception {
        if (searchString.isEmpty()) {
            throw new BadRequestException("search word should not be empty");
        }
        Set<ClientEntity> clientEntitySet = clientService.searchByString(searchString);
        Set<ClientDto> clientDtoSet = new HashSet<>();
        for(ClientEntity client: clientEntitySet )
        {
            clientDtoSet.add(clientMapper.clientEntityToClientDto(client));
        }
        return new ResponseEntity(clientDtoSet, HttpStatus.OK);
    }

     */
}
