package org.bshg.productmanagement.services.facade;
import org.bshg.productmanagement.entity.Supplier;
import org.bshg.productmanagement.entity.Product;
import org.bshg.productmanagement.zutils.pagination.Pagination;
import java.util.List;
public interface SupplierService {
Supplier findById(Long id);
List<Supplier> findAllOptimized();
List<Supplier> findAll();
Pagination<Supplier> findPaginated(int page, int size);
Supplier create(Supplier item);
List<Supplier> create(List<Supplier> item);
Supplier update(Supplier item);
List<Supplier> update(List<Supplier> item);
void deleteById(Long id);
void delete(Supplier item);
void delete(List<Supplier> items);
void deleteByIdIn(List<Long> ids);
}