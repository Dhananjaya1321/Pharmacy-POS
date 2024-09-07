package com.novalabsglobal.pharmacy.repo;

import com.novalabsglobal.pharmacy.entity.Brand;
import com.novalabsglobal.pharmacy.enums.BrandStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepo extends JpaRepository<Brand,Integer> {
    @Query(value = "SELECT b.status FROM Brand b WHERE b.id=:id")
    BrandStatus getStatus(Integer id);

    @Modifying
    @Query(value = "UPDATE Brand b SET b.status='DELETED' WHERE b.id=:id")
    int deleteBrand(Integer id);

    @Query(value = "SELECT b FROM Brand b WHERE b.status!='DELETED'")
    List<Brand> getAllBrands();
}
