package org.bshg.productmanagement.services.impl;

import org.bshg.productmanagement.bean.Supplier;
import org.bshg.productmanagement.dao.SupplierDao;
import org.bshg.productmanagement.services.facade.SupplierService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
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

    //--------------- CREATE -----------------------------------
    @Transactional(rollbackFor = Exception.class)
    public Supplier create(Supplier item) {
        if (item == null) return null;
        return dao.save(item);
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
        return dao.save(item);
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
        dao.delete(item);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Supplier> items) {
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
    @Autowired
    private SupplierDao dao;
}