package com.novalabsglobal.pharmacy.repo;

import com.novalabsglobal.pharmacy.entity.Brand;
import com.novalabsglobal.pharmacy.enums.BrandStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    Page<Brand> getAllBrands(PageRequest pageRequest);

    @Query(value = "SELECT b.id as brand_id, b.address, b.contact,b.description as brand_description, b.name as brand_name, b.website, " +
            "       i.id as item_id, i.name as item_name,i.description as item_description, i.status as item_status," +
            "       i.id as category_id, c.name as category_name,c.description as category_description, " +
            "       i.id as unit_id, u.unit_name, u.unit_symbology " +
            "FROM Brand b " +
            "LEFT JOIN item i on b.id = i.brand_id " +
            "LEFT JOIN category c on i.category_id = c.id " +
            "LEFT JOIN unit u on i.unit_id = u.id " +
            "WHERE b.id=:id AND b.status!='DELETED'",nativeQuery = true)
    List<Object> getBrandById(Integer id);

    @Query(value = "SELECT COUNT(b.id) FROM Brand b WHERE b.status!='DELETED'")
    int getBrandCount();

    @Query(value = "SELECT b.id as brand_id, b.address, b.contact,b.description as brand_description, b.name as brand_name, b.website, " +
            "       i.id as item_id, i.name as item_name,i.description as item_description, i.status as item_status," +
            "       i.id as category_id, c.name as category_name,c.description as category_description, " +
            "       i.id as unit_id, u.unit_name, u.unit_symbology " +
            "FROM Brand b " +
            "LEFT JOIN item i on b.id = i.brand_id " +
            "LEFT JOIN category c on i.category_id = c.id " +
            "LEFT JOIN unit u on i.unit_id = u.id " +
            "WHERE b.id=:id AND b.status!='DELETED' AND " +
            "(:name is null or b.name LIKE %:name%)", nativeQuery = true)
    Page<Brand> searchBrands(String name, PageRequest pageRequest);
}
