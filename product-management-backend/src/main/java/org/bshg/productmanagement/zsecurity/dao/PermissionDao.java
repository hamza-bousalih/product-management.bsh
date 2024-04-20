package org.bshg.productmanagement.zsecurity.dao;

import org.bshg.productmanagement.zsecurity.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PermissionDao extends JpaRepository<Permission, Long> {
    Permission findByName(String name);
}