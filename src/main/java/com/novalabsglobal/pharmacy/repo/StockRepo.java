package com.novalabsglobal.pharmacy.repo;

import com.novalabsglobal.pharmacy.entity.Stock;
import com.novalabsglobal.pharmacy.enums.StockStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockRepo extends JpaRepository<Stock,Integer> {
    @Query(value = "SELECT s.status FROM Stock s WHERE s.id=:id")
    StockStatus getStatus(Integer id);

    @Modifying
    @Query(value = "UPDATE Stock s SET s.status='DELETED' WHERE s.id=:id")
    int deleteStock(Integer id);

    @Query(value = "SELECT s.id as stock_id, s.expiry_date, " +
            "s.purchased_qty, s.purchased_amount, s.purchased_discount, s.available_qty, " +
            "s.purchase_price_per_unit, s.selling_price_per_unit, s.selling_discount_per_unit, s.total_amount, " +
            "s.description, " +
            "i.id as item_id, i.name FROM stock s " +
            "LEFT JOIN item i on i.id = s.item_id " +
            "WHERE s.status!='DELETED'",nativeQuery = true)
    Page<Object> getAllStocks(PageRequest pageRequest);

    @Query(value = "SELECT COUNT(s.id) FROM Stock s WHERE s.item.id=:id AND s.status!='DELETED'")
    int getCountStocksUnderItemByItemId(Integer id);
}
