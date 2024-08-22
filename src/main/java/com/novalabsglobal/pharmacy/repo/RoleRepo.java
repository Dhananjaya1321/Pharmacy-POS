package com.novalabsglobal.pharmacy.repo;

import com.novalabsglobal.pharmacy.entity.Role;
import com.novalabsglobal.pharmacy.entity.Shop;
import com.novalabsglobal.pharmacy.enums.ShopStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepo extends JpaRepository<Role,Integer> {

}
