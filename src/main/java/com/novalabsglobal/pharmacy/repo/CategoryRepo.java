package com.novalabsglobal.pharmacy.repo;

import com.novalabsglobal.pharmacy.entity.Category;
import com.novalabsglobal.pharmacy.enums.CategoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
    @Query(value = "SELECT c.status FROM Category c WHERE c.id=:id")
    CategoryStatus getStatus(Integer id);
}
