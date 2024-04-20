package org.bshg.productmanagement.services.impl;

import org.bshg.productmanagement.bean.Product;
import org.bshg.productmanagement.dao.ProductDao;
import org.bshg.productmanagement.services.facade.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
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

    //--------------- CREATE -----------------------------------
    @Transactional(rollbackFor = Exception.class)
    public Product create(Product item) {
        if (item == null) return null;
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
//----------------------------------------------------------
//----------------------------------------------------------
    @Autowired
    private ProductDao dao;
}