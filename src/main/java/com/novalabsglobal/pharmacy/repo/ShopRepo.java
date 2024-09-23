package com.novalabsglobal.pharmacy.repo;

import com.novalabsglobal.pharmacy.entity.Shop;
import com.novalabsglobal.pharmacy.enums.ShopStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShopRepo extends JpaRepository<Shop,Integer> {
    @Query(value = "SELECT s.status FROM Shop s WHERE s.pharmacyId=:id")
    ShopStatus getStatus(Integer id);

    @Query("SELECT COUNT(s.pharmacyId) FROM Shop s WHERE s.status='ACTIVE'")
    int checkHasAccount();
}
