package org.bshg.productmanagement.services.impl;
import org.bshg.productmanagement.entity.Admin;
import org.bshg.productmanagement.dao.AdminDao;
import org.bshg.productmanagement.services.facade.AdminService;
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
public class AdminServiceImpl implements AdminService {
//--------------- FIND -------------------------------------
public Admin findById(Long id) {
return dao.findById(id).orElse(null);
}
public List<Admin> findAll() {
return dao.findAll();
}
public List<Admin> findAllOptimized() {
return findAll();
}
@Override
public Pagination<Admin> findPaginated(int page, int size) {
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
public Admin create(Admin item) {
if (item == null) return null;
return dao.save(item);
}
@Transactional(rollbackFor = Exception.class)
public List<Admin> create(List<Admin> items) {
List<Admin> result = new ArrayList<>();
if (items == null || items.isEmpty()) return result;
items.forEach(it -> result.add(create(it)));
return result;
}
//--------------- UPDATE -----------------------------------
@Transactional(rollbackFor = Exception.class)
public Admin update(Admin item) {
if (item == null || item.getId() == null) return null;
var oldItem = findById(item.getId());
if (oldItem == null) throw new NotFoundException("Unknown Admin To Be Updated!");
return dao.save(item);
}
@Transactional(rollbackFor = Exception.class)
public List<Admin> update(List<Admin> items) {
List<Admin> result = new ArrayList<>();
if (items == null || items.isEmpty()) return result;
items.forEach(it -> result.add(update(it)));
return result;
}
//--------------- DELETE -----------------------------------
@Transactional(rollbackFor = Exception.class)
public void deleteById(Long id) {
Admin item = findById(id);
if (item != null) delete(item);
}
@Transactional(rollbackFor = Exception.class)
public void delete(Admin item) {
dao.delete(item);
}
@Transactional(rollbackFor = Exception.class)
public void delete(List<Admin> items) {
if (items == null || items.isEmpty()) return;
items.forEach(this::delete);
}
@Transactional(rollbackFor = Exception.class)
public void deleteByIdIn(List<Long> ids) {
dao.deleteByIdIn(ids);
}
//--------------- FIND AND DELETE BYs ----------------------
//----------------------------------------------------------
//----------------------------------------------------------
@Autowired private AdminDao dao;
}