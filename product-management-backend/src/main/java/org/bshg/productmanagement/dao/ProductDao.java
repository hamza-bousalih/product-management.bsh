package org.bshg.productmanagement.dao;

import org.bshg.productmanagement.bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Long> {
    int deleteByIdIn(List<Long> ids);

    @Query("SELECT NEW Product(item.id,item.name) FROM Product item")
    List<Product> findAllOptimized();
}