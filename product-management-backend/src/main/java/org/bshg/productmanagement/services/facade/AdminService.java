package org.bshg.productmanagement.services.facade;
import org.bshg.productmanagement.entity.Admin;
import org.bshg.productmanagement.zutils.pagination.Pagination;
import java.util.List;
public interface AdminService {
Admin findById(Long id);
List<Admin> findAllOptimized();
List<Admin> findAll();
Pagination<Admin> findPaginated(int page, int size);
Admin create(Admin item);
List<Admin> create(List<Admin> item);
Admin update(Admin item);
List<Admin> update(List<Admin> item);
void deleteById(Long id);
void delete(Admin item);
void delete(List<Admin> items);
void deleteByIdIn(List<Long> ids);
}