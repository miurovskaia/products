package com.example.demo.repository;

import com.example.demo.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

//?
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

        Optional<ProductEntity> findById(Integer id);
        Set<ProductEntity> findAllByNameIn(List<String> searchWords);


    }


