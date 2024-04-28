package org.bshg.productmanagement.services.impl;
import org.bshg.productmanagement.entity.Product;
import org.bshg.productmanagement.dao.ProductDao;
import org.bshg.productmanagement.services.facade.ProductService;
import org.bshg.productmanagement.entity.Customer;
import org.bshg.productmanagement.services.facade.CustomerService;
import org.bshg.productmanagement.entity.Supplier;
import org.bshg.productmanagement.services.facade.SupplierService;
import org.bshg.productmanagement.zutils.service.ServiceHelper;
import org.bshg.productmanagement.zutils.pagination.Pagination;
import org.bshg.productmanagement.exceptions.NotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.ArrayList;
@Service
public class ProductServiceImpl implements ProductService {
//--------------- FIND -------------------------------------
public Product findById(Long id) {
return dao.findById(id).orElse(null);
}
public List<Product> findAll() {
return dao.findAll();
}
public List<Product> findAllOptimized() {
return dao.findAllOptimized();
}
@Override
public Pagination<Product> findPaginated(int page, int size) {
var pageable = PageRequest.of(page, size);
var found = dao.findAll(pageable);
var items = found.stream().toList();
return new Pagination<>(
items,
found.getNumber(),
found.getSize(),
found.getTotalElements(),
found.getTotalPages(),
found.isFirst(),
found.isLast()
);
}
//--------------- CREATE -----------------------------------
@Transactional(rollbackFor = Exception.class)
public Product create(Product item) {
if (item == null) return null;
// check if customer exists
var customer = item.getCustomer();
if (customer != null) {
if(customer.getId() == null) item.setCustomer(null);
else {
var found = customerService.findById(customer.getId());
if (found == null) throw new NotFoundException("Unknown Given Customer");
item.setCustomer(found);
}
}
// check if supplier exists
var supplier = item.getSupplier();
if (supplier != null) {
if(supplier.getId() == null) item.setSupplier(null);
else {
var found = supplierService.findById(supplier.getId());
if (found == null) throw new NotFoundException("Unknown Given Supplier");
item.setSupplier(found);
}
}
return dao.save(item);
}
@Transactional(rollbackFor = Exception.class)
public List<Product> create(List<Product> items) {
List<Product> result = new ArrayList<>();
if (items == null || items.isEmpty()) return result;
items.forEach(it -> result.add(create(it)));
return result;
}
//--------------- UPDATE -----------------------------------
@Transactional(rollbackFor = Exception.class)
public Product update(Product item) {
if (item == null || item.getId() == null) return null;
var oldItem = findById(item.getId());
if (oldItem == null) throw new NotFoundException("Unknown Product To Be Updated!");
return dao.save(item);
}
@Transactional(rollbackFor = Exception.class)
public List<Product> update(List<Product> items) {
List<Product> result = new ArrayList<>();
if (items == null || items.isEmpty()) return result;
items.forEach(it -> result.add(update(it)));
return result;
}
//--------------- DELETE -----------------------------------
@Transactional(rollbackFor = Exception.class)
public void deleteById(Long id) {
Product item = findById(id);
if (item != null) delete(item);
}
@Transactional(rollbackFor = Exception.class)
public void delete(Product item) {
dao.delete(item);
}
@Transactional(rollbackFor = Exception.class)
public void delete(List<Product> items) {
if (items == null || items.isEmpty()) return;
items.forEach(this::delete);
}
@Transactional(rollbackFor = Exception.class)
public void deleteByIdIn(List<Long> ids) {
dao.deleteByIdIn(ids);
}
//--------------- FIND AND DELETE BYs ----------------------
@Override
@Transactional(rollbackFor = Exception.class)
public int deleteByCustomerId(Long id){
if (id == null) return 0;
return dao.deleteByCustomerId(id);
}
@Override
public List<Product> findByCustomerId(Long id){
return dao.findByCustomerId(id);
}
@Override
@Transactional(rollbackFor = Exception.class)
public int deleteBySupplierId(Long id){
if (id == null) return 0;
return dao.deleteBySupplierId(id);
}
@Override
public List<Product> findBySupplierId(Long id){
return dao.findBySupplierId(id);
}
//----------------------------------------------------------
//----------------------------------------------------------
@Autowired private ProductDao dao;
@Lazy @Autowired private CustomerService customerService;
@Lazy @Autowired private SupplierService supplierService;
}