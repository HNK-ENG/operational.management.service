package com.example.operational.service.repository;


import com.example.operational.service.repository.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {


//    @Transactional
//    @Modifying
//    @Query(nativeQuery = true, value = "update order_details set status = 'Cancelled' where order_id = :orderId")
//    void updateOrderStatus(Long orderId);
}

