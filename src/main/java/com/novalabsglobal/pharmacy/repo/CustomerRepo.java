package com.novalabsglobal.pharmacy.repo;

import com.novalabsglobal.pharmacy.entity.Customer;
import com.novalabsglobal.pharmacy.enums.CustomerStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    @Query(value = "SELECT c.status FROM Customer c WHERE c.id=:id")
    CustomerStatus getStatus(Integer id);

    @Modifying
    @Query(value = "UPDATE Customer c SET c.status='DELETED' WHERE c.id=:id")
    int deleteCustomer(Integer id);

    @Query(value = "SELECT c.id,c.name,c.contact,c.address,c.email,c.nic,c.status,c.createdBy,c.updatedBy,c.lastUpdate " +
            "FROM Customer c WHERE c.status!='DELETED'")
    Page<Object> getAllCustomers(PageRequest pageRequest);

    @Query(value = "SELECT COUNT(c.id) FROM Customer c WHERE c.status!='DELETED'")
    int getCustomersCount();
}
