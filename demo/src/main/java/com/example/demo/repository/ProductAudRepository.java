package com.example.demo.repository;

import com.example.demo.entity.ProductEntity;
import com.example.demo.entity.ProductEntityAud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//?
public interface ProductAudRepository extends JpaRepository<ProductEntityAud, Integer> {

    Set<ProductEntityAud> findAllById(Integer id);
    Set<ProductEntityAud> findByIdAndRevIsNot(Integer id, Integer rev);
    Optional<ProductEntityAud> findFirstByOrderByRevDesc();
    Set<ProductEntityAud> findAllByIdAndTimedateBetween(Integer id, Instant startTimedate, Instant endTimedate);
    @Query(value = "SELECT * FROM product_aud WHERE id = :id ORDER BY rev DESC LIMIT 1 OFFSET 1", nativeQuery = true)
    Optional<ProductEntityAud> findSecondLatestBy(@Param("id") Integer id);
}