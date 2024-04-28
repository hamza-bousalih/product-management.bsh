package org.bshg.productmanagement.services.impl;
import org.bshg.productmanagement.entity.Supplier;
import org.bshg.productmanagement.dao.SupplierDao;
import org.bshg.productmanagement.services.facade.SupplierService;
import org.bshg.productmanagement.entity.Product;
import org.bshg.productmanagement.services.facade.ProductService;
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
public class SupplierServiceImpl implements SupplierService {
//--------------- FIND -------------------------------------
public Supplier findById(Long id) {
return dao.findById(id).orElse(null);
}
public List<Supplier> findAll() {
return dao.findAll();
}
public List<Supplier> findAllOptimized() {
return dao.findAllOptimized();
}
@Override
public Pagination<Supplier> findPaginated(int page, int size) {
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
public Supplier create(Supplier item) {
if (item == null) return null;
Supplier saved = dao.save(item);
createAssociatedList(saved);
return saved;
}
@Transactional(rollbackFor = Exception.class)
public List<Supplier> create(List<Supplier> items) {
List<Supplier> result = new ArrayList<>();
if (items == null || items.isEmpty()) return result;
items.forEach(it -> result.add(create(it)));
return result;
}
//--------------- UPDATE -----------------------------------
@Transactional(rollbackFor = Exception.class)
public Supplier update(Supplier item) {
if (item == null || item.getId() == null) return null;
var oldItem = findById(item.getId());
if (oldItem == null) throw new NotFoundException("Unknown Supplier To Be Updated!");
Supplier saved = dao.save(item);
updateAssociatedList(saved);
return saved;
}
@Transactional(rollbackFor = Exception.class)
public List<Supplier> update(List<Supplier> items) {
List<Supplier> result = new ArrayList<>();
if (items == null || items.isEmpty()) return result;
items.forEach(it -> result.add(update(it)));
return result;
}
//--------------- DELETE -----------------------------------
@Transactional(rollbackFor = Exception.class)
public void deleteById(Long id) {
Supplier item = findById(id);
if (item != null) delete(item);
}
@Transactional(rollbackFor = Exception.class)
public void delete(Supplier item) {
deleteAssociated(item);
dao.delete(item);
}
@Transactional(rollbackFor = Exception.class)
public void delete(List<Supplier> items) {
if (items == null || items.isEmpty()) return;
items.forEach(this::delete);
}
@Transactional(rollbackFor = Exception.class)
public void deleteByIdIn(List<Long> ids) {
ids.forEach(id -> {
Supplier item = findById(id);
if (item != null) {
deleteAssociated(item);
}
});
dao.deleteByIdIn(ids);
}
//--------------- FIND AND DELETE BYs ----------------------
//----------------------------------------------------------
public void createAssociatedList(Supplier item) {
if (item == null || item.getId() == null) return;
ServiceHelper.createList(item, Supplier::getProducts, Product::setSupplier, productService::create);
}
public void updateAssociatedList(Supplier item) {
if (item == null || item.getId() == null) return;
ServiceHelper.updateList(
item, productService.findBySupplierId(item.getId()),
item.getProducts(), Product::setSupplier,
productService::update,
productService::delete
);
}
@Transactional(rollbackFor = Exception.class)
public void deleteAssociated(Supplier item) {
deleteAssociatedList(item);
}
public void deleteAssociatedList(Supplier item) {
productService.deleteBySupplierId(item.getId());
}
//----------------------------------------------------------
@Autowired private SupplierDao dao;
@Lazy @Autowired private ProductService productService;
}