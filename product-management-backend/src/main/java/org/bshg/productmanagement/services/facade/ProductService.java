package org.bshg.productmanagement.services.facade;
import org.bshg.productmanagement.entity.Product;
import org.bshg.productmanagement.entity.Customer;
import org.bshg.productmanagement.entity.Supplier;
import org.bshg.productmanagement.zutils.pagination.Pagination;
import java.util.List;
public interface ProductService {
Product findById(Long id);
List<Product> findAllOptimized();
List<Product> findAll();
Pagination<Product> findPaginated(int page, int size);
Product create(Product item);
List<Product> create(List<Product> item);
Product update(Product item);
List<Product> update(List<Product> item);
void deleteById(Long id);
void delete(Product item);
void delete(List<Product> items);
void deleteByIdIn(List<Long> ids);
int deleteByCustomerId(Long id);
List<Product> findByCustomerId(Long id);
int deleteBySupplierId(Long id);
List<Product> findBySupplierId(Long id);
}