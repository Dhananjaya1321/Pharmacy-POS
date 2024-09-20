package com.novalabsglobal.pharmacy.repo;

import com.novalabsglobal.pharmacy.entity.Item;
import com.novalabsglobal.pharmacy.enums.ItemStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item, Integer> {
    @Query(value = "SELECT i.status FROM Item i WHERE i.id=:id")
    ItemStatus getStatus(Integer id);

    @Modifying
    @Query(value = "UPDATE Item i SET i.status='DELETED' WHERE i.id=:id")
    int deleteItem(Integer id);

    @Query(value = "SELECT i.id, i.name as item_name, i.re_order_level, i.description, " +
            "u.unit_name as unit_name, u.unit_symbology, " +
            "b.name as brand_name, " +
            "c.name as category_name " +
            "FROM item i " +
            "left join unit u on i.unit_id = u.id " +
            "left join brand b on i.brand_id=b.id " +
            "left join category c on i.category_id=c.id " +
            "WHERE i.status!='DELETED'", nativeQuery = true)
    Page<Object> getAllItems(PageRequest pageRequest);

    @Query(value = "SELECT COUNT(i.id) FROM Item i WHERE i.brand.id=:id AND i.status!='DELETED'")
    int getCountItemsUnderBrandByBrandId(Integer id);

    @Query(value = "SELECT COUNT(i.id) FROM Item i WHERE i.category.id=:id AND i.status!='DELETED'")
    int getCountItemsUnderCategoryByCategoryId(Integer id);

    @Query(value = "SELECT COUNT(i.id) FROM Item i WHERE i.unit.id=:id AND i.status!='DELETED'")
    int getCountItemsUnderUnitByUnitId(Integer id);

    @Query("SELECT COUNT(DISTINCT s.item.id) FROM Stock s " +
            "left join Item i on i.id = s.item.id " +
            "WHERE s.availableQty > 0 AND s.expiryDate > CURRENT_DATE AND i.status='ACTIVE' AND s.status='ACTIVE'")
    int countDistinctAvailableItemsInStock();

    @Query("SELECT COUNT(s.id) FROM Stock s WHERE s.availableQty > 0 AND s.expiryDate > CURRENT_DATE AND s.status='ACTIVE' " +
            "AND s.item.id=:id")
    int getAvailableStocksCountByItem(Integer id);

    @Query("SELECT s.availableQty, s.purchasedQty FROM Stock s WHERE s.item.id=:id AND s.status='ACTIVE'")
    List<Object> getAvailableStocksAvailableQtyAndPurchasedQtyByItem(Integer id);

    @Query("SELECT i.id FROM Item i WHERE i.status!='DELETED'")
    List<Integer> getAllItemsIds();

    @Query("SELECT i.reOrderLevel FROM Item i WHERE i.id=:id")
    double getItemReorderLevelByItemId(Integer id);

    @Query("SELECT COUNT(DISTINCT s.item.id) FROM Stock s WHERE " +
            "s.availableQty > 0 AND s.expiryDate < CURRENT_DATE AND s.status='ACTIVE'")
    int getExpiredAvailableStockItemsCount();
}
