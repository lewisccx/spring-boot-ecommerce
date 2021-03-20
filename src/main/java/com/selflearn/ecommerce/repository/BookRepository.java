package com.selflearn.ecommerce.repository;

import com.selflearn.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor {
    
}