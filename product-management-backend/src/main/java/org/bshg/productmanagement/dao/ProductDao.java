package org.bshg.productmanagement.dao;
import org.bshg.productmanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface ProductDao extends JpaRepository<Product, Long> {
int deleteByIdIn(List<Long> ids);
int deleteByCustomerId(Long id);
List<Product> findByCustomerId(Long id);
int deleteBySupplierId(Long id);
List<Product> findBySupplierId(Long id);
@Query("SELECT NEW Product(item.id,item.name) FROM Product item")
List<Product> findAllOptimized();
}