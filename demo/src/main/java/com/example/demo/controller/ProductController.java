package com.example.demo.controller;

import com.example.demo.dto.CreateProductDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.entity.ProductEntity;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    ProductController( ProductService productService1, ProductMapper productMapper1)
    {
        this.productService = productService1;
        this.productMapper = productMapper1;

    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody CreateProductDto createProductDto)  throws Exception{
        ProductEntity entity = productMapper.createProductDtoToProductEntity(createProductDto);
        Integer productId = productService.createProduct(entity);
        return ResponseEntity.ok(productId);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?>  getProductByProductId(@PathVariable Integer productId) {
        ProductDto productDto  = productMapper.productEntityToProductDto(productService.getProduct(productId));
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(
            @RequestBody CreateProductDto createProductDto,
            @PathVariable String id
    ) {
        productService.changeProduct(createProductDto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") String id) {
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
