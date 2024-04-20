package org.bshg.productmanagement.services.impl.user;

import org.bshg.productmanagement.bean.user.Admin;
import org.bshg.productmanagement.dao.user.AdminDao;
import org.bshg.productmanagement.services.facade.user.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private AdminDao dao;
}