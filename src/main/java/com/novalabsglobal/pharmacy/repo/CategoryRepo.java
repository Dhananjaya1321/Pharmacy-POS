package com.novalabsglobal.pharmacy.repo;

import com.novalabsglobal.pharmacy.entity.Category;
import com.novalabsglobal.pharmacy.enums.CategoryStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
    @Query(value = "SELECT c.status FROM Category c WHERE c.id=:id")
    CategoryStatus getStatus(Integer id);

    @Modifying
    @Query(value = "UPDATE Category c SET c.status='DELETED' WHERE c.id=:id")
    int deleteCategory(Integer id);

    @Query(value = "SELECT c FROM Category c WHERE c.status!='DELETED'")
    Page<Category> getAllCategories(PageRequest pageRequest);

    @Query(value = "SELECT COUNT(c.id) FROM Category c WHERE c.status!='DELETED'")
    int getCategoryCount();

}
