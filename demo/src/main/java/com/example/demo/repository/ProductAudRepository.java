package com.example.demo.repository;

import com.example.demo.entity.ProductEntity;
import com.example.demo.entity.ProductEntityAud;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

//?
public interface ProductAudRepository extends JpaRepository<ProductEntityAud, String> {

    Set<ProductEntityAud> findById(Integer id);


}