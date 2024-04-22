package org.bshg.productmanagement.dao;
import org.bshg.productmanagement.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface CustomerDao extends JpaRepository<Customer, Long> {
int deleteByIdIn(List<Long> ids);
}