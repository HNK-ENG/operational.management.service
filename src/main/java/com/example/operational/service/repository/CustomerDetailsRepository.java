package com.example.operational.service.repository;

import com.example.operational.service.repository.model.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Long> {

    @Query(nativeQuery = true, value = "select * from customer_details cd where cd.user_id = :userId")
    List<CustomerDetails> selectByUserId(Long userId);
}
