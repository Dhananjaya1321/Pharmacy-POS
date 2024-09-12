package com.novalabsglobal.pharmacy.repo;

import com.novalabsglobal.pharmacy.entity.Unit;
import com.novalabsglobal.pharmacy.enums.UnitStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UnitRepo extends JpaRepository<Unit,Integer> {
    @Query(value = "SELECT u.status FROM Unit u WHERE u.id=:id")
    UnitStatus getStatus(Integer id);

    @Modifying
    @Query(value = "UPDATE Unit u SET u.status='DELETED' WHERE u.id=:id")
    int deleteUnit(Integer id);

    @Query(value = "SELECT u FROM Unit u WHERE u.status!='DELETED'")
    Page<Unit> getAllUnits(PageRequest pageRequest);
}
