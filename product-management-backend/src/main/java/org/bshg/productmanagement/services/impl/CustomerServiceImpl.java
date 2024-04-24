package org.bshg.productmanagement.services.impl;
import org.bshg.productmanagement.entity.Customer;
import org.bshg.productmanagement.dao.CustomerDao;
import org.bshg.productmanagement.services.facade.CustomerService;
import org.bshg.productmanagement.entity.Product;
import org.bshg.productmanagement.services.facade.ProductService;
import org.bshg.productmanagement.zutils.service.ServiceHelper;
import org.bshg.productmanagement.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.ArrayList;
@Service
public class CustomerServiceImpl implements CustomerService {
//--------------- FIND -------------------------------------
public Customer findById(Long id) {
return dao.findById(id).orElse(null);
}
public List<Customer> findAll() {
return dao.findAll();
}
public List<Customer> findAllOptimized() {
return findAll();
}
//--------------- CREATE -----------------------------------
@Transactional(rollbackFor = Exception.class)
public Customer create(Customer item) {
if (item == null) return null;
Customer saved = dao.save(item);
createAssociatedList(saved);
return saved;
}
@Transactional(rollbackFor = Exception.class)
public List<Customer> create(List<Customer> items) {
List<Customer> result = new ArrayList<>();
if (items == null || items.isEmpty()) return result;
items.forEach(it -> result.add(create(it)));
return result;
}
//--------------- UPDATE -----------------------------------
@Transactional(rollbackFor = Exception.class)
public Customer update(Customer item) {
if (item == null || item.getId() == null) return null;
Customer saved = dao.save(item);
updateAssociatedList(saved);
return saved;
}
@Transactional(rollbackFor = Exception.class)
public List<Customer> update(List<Customer> items) {
List<Customer> result = new ArrayList<>();
if (items == null || items.isEmpty()) return result;
items.forEach(it -> result.add(update(it)));
return result;
}
//--------------- DELETE -----------------------------------
@Transactional(rollbackFor = Exception.class)
public void deleteById(Long id) {
Customer item = findById(id);
if (item != null) delete(item);
}
@Transactional(rollbackFor = Exception.class)
public void delete(Customer item) {
deleteAssociated(item);
dao.delete(item);
}
@Transactional(rollbackFor = Exception.class)
public void delete(List<Customer> items) {
if (items == null || items.isEmpty()) return;
items.forEach(this::delete);
}
@Transactional(rollbackFor = Exception.class)
public void deleteByIdIn(List<Long> ids) {
ids.forEach(id -> {
Customer item = findById(id);
if (item != null) {
deleteAssociated(item);
}
});
dao.deleteByIdIn(ids);
}
//--------------- FIND AND DELETE BYs ----------------------
//----------------------------------------------------------
public void createAssociatedList(Customer item) {
if (item == null || item.getId() == null) return;
ServiceHelper.createList(item, Customer::getProducts, Product::setCustomer, productService::create);
}
public void updateAssociatedList(Customer item) {
if (item == null || item.getId() == null) return;
ServiceHelper.updateList(
item, productService.findByCustomerId(item.getId()),
item.getProducts(), Product::setCustomer,
productService::update,
productService::delete
);
}
@Transactional(rollbackFor = Exception.class)
public void deleteAssociated(Customer item) {
deleteAssociatedList(item);
}
public void deleteAssociatedList(Customer item) {
productService.deleteByCustomerId(item.getId());
}
//----------------------------------------------------------
@Autowired private CustomerDao dao;
@Lazy @Autowired private ProductService productService;
}