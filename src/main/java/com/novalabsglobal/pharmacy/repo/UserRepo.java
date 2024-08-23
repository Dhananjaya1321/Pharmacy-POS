package com.novalabsglobal.pharmacy.repo;

import com.novalabsglobal.pharmacy.entity.User;
import com.novalabsglobal.pharmacy.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Objects;

public interface UserRepo extends JpaRepository<User, Integer> {
    @Query(value = "SELECT u.status FROM User u WHERE u.id=:id")
    UserStatus getStatus(Integer id);

    @Query(value = "SELECT u.username FROM User u WHERE u.id=:id")
    String getUsername(Integer id);

    @Query(value = "SELECT u.password FROM User u WHERE u.id=:id")
    String getPassword(Integer id);

    @Modifying
    @Query(value = "UPDATE User u SET u.status='DELETED' WHERE u.id=:id")
    int deleteUser(Integer id);

    @Query(value = "SELECT u.id, u.name as user_name, u.address, u.contact, " +
            "       u.username, u.status, " +
            "       r.id, r.name as role_name, r.description, " +
            "       u.createdBy, u.updatedBy, u.lastUpdate " +
            "FROM User u " +
            "left join Role r on u.id = r.id " +
            "where u.status!='DELETED'")
    List<Object> getAllUserDetails();
}
