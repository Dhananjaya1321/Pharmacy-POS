package com.novalabsglobal.pharmacy.repo;

import com.novalabsglobal.pharmacy.entity.Brand;
import com.novalabsglobal.pharmacy.entity.Item;
import com.novalabsglobal.pharmacy.enums.BrandStatus;
import com.novalabsglobal.pharmacy.enums.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item,Integer> {
    @Query(value = "SELECT i.status FROM Item i WHERE i.id=:id")
    ItemStatus getStatus(Integer id);

    @Modifying
    @Query(value = "UPDATE Item i SET i.status='DELETED' WHERE i.id=:id")
    int deleteItem(Integer id);

    @Query(value = "SELECT i.id, i.name as item_name, i.reorder_level, i.description, " +
            "u.unit_name as unit_name, u.unit_symbology, " +
            "b.name as brand_name, " +
            "c.name as category_name " +
            "FROM item i " +
            "left join unit u on i.unit_id = u.id " +
            "left join brand b on i.brand_id=b.id " +
            "left join category c on i.category_id=c.id " +
            "WHERE i.status!='DELETED'",nativeQuery = true)
    List<Object> getAllItems();
}
