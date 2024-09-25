package com.novalabsglobal.pharmacy.repo;

import com.novalabsglobal.pharmacy.entity.User;
import com.novalabsglobal.pharmacy.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

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
            "       u.nic, u.email, u.username, u.status, " +
            "       r.id, r.name as role_name, r.description, " +
            "       u.createdBy, u.updatedBy, u.lastUpdate " +
            "FROM User u " +
            "left join Role r on u.role.id = r.id " +
            "where u.status!='DELETED'")
    List<Object> getAllUserDetails();

    @Query(value = "SELECT u.id, u.name as user_name, u.address, u.contact, " +
            "       u.nic, u.email, u.username, u.status, " +
            "       r.id, r.name as role_name, r.description, " +
            "       u.createdBy, u.updatedBy, u.lastUpdate " +
            "FROM User u " +
            "left join Role r on u.role.id = r.id " +
            "where u.status!='DELETED' AND u.id=:id")
    List<Object> getAllUserById(Integer id);

    @Query(value = "SELECT COUNT(u.id) FROM User u WHERE u.status!='DELETED'")
    int getUserCount();

    @Query(value = "SELECT u.email, u.username,u.password, r.name "+
            "FROM User u " +
            "left join Role r on u.role.id = r.id " +
            "where u.status!='DELETED' AND (u.email=:emailOrUsername OR u.username=:emailOrUsername)")
    Object searchUserByUsernameOrEmail(String emailOrUsername);

    @Modifying
    @Query("UPDATE User u SET u.password=:newPassword WHERE u.email=:email")
    int updatePassword(String email, String newPassword);
}
