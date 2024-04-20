package org.bshg.productmanagement.dao.user;

import org.bshg.productmanagement.bean.user.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminDao extends JpaRepository<Admin, Long> {
    int deleteByIdIn(List<Long> ids);
}