package com.novalabsglobal.pharmacy.repo;

import com.novalabsglobal.pharmacy.entity.Orders;
import com.novalabsglobal.pharmacy.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepo extends JpaRepository<Orders,Integer> {
    @Query(value = "SELECT o.status FROM Orders o WHERE o.id=:id")
    OrderStatus getStatus(Integer id);
}
