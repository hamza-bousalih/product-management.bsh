package org.bshg.productmanagement.services.impl;

import org.bshg.productmanagement.bean.Customer;
import org.bshg.productmanagement.dao.CustomerDao;
import org.bshg.productmanagement.services.facade.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
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
        return dao.save(item);
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
        return dao.save(item);
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
        dao.delete(item);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Customer> items) {
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
    private CustomerDao dao;
}