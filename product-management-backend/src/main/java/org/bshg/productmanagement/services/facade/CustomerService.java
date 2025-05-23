package org.bshg.productmanagement.services.facade;
import org.bshg.productmanagement.entity.Customer;
import org.bshg.productmanagement.entity.Product;
import org.bshg.productmanagement.zutils.pagination.Pagination;
import java.util.List;
public interface CustomerService {
Customer findById(Long id);
List<Customer> findAllOptimized();
List<Customer> findAll();
Pagination<Customer> findPaginated(int page, int size);
Customer create(Customer item);
List<Customer> create(List<Customer> item);
Customer update(Customer item);
List<Customer> update(List<Customer> item);
void deleteById(Long id);
void delete(Customer item);
void delete(List<Customer> items);
void deleteByIdIn(List<Long> ids);
}