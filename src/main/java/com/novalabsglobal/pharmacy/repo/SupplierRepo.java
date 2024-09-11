
package com.novalabsglobal.pharmacy.repo;

import com.novalabsglobal.pharmacy.entity.Supplier;
import com.novalabsglobal.pharmacy.enums.SupplierStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierRepo extends JpaRepository<Supplier, Integer> {
    @Query(value = "SELECT s.status FROM Supplier s WHERE s.id=:id")
    SupplierStatus getStatus(Integer id);

    @Modifying
    @Query(value = "UPDATE Supplier s SET s.status='DELETED' WHERE s.id=:id")
    int deleteSupplier(Integer id);

    @Query(value = "SELECT s FROM Supplier s WHERE s.status!='DELETED'")
    Page<Supplier> getAllSuppliers(Pageable pageable);
}
