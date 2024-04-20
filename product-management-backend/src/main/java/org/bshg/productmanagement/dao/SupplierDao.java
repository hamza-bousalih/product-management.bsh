package org.bshg.productmanagement.dao;

import org.bshg.productmanagement.bean.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierDao extends JpaRepository<Supplier, Long> {
    int deleteByIdIn(List<Long> ids);

    @Query("SELECT NEW Supplier(item.id,item.name) FROM Supplier item")
    List<Supplier> findAllOptimized();
}