package com.example.operational.service.serviceinterface;


import com.example.operational.service.dto.common.AdaptorResponse;
import com.example.operational.service.dto.salesorders.createOrder.SalesOrderCreationRequest;
import com.example.operational.service.dto.salesorders.createOrder.SalesOrderCreationResponse;
import com.example.operational.service.util.exception.BaseException;

public interface SalesOrderInterface {
    AdaptorResponse<SalesOrderCreationResponse> createOrder(SalesOrderCreationRequest salesOrderCreationRequest) throws BaseException;
}
