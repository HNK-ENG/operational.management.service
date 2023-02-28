package com.example.operational.service.service;


import com.example.operational.service.dto.common.AdaptorResponse;
import com.example.operational.service.dto.common.Result;
import com.example.operational.service.dto.salesorders.createOrder.SalesOrderCreationRequest;
import com.example.operational.service.dto.salesorders.createOrder.SalesOrderCreationResponse;
import com.example.operational.service.dto.salesorders.updateOrder.KafkaOrderCommon;
import com.example.operational.service.dto.salesorders.updateOrder.OrderData;
import com.example.operational.service.repository.CustomerDetailsRepository;
import com.example.operational.service.repository.OrderDetailsRepository;
import com.example.operational.service.repository.model.CustomerDetails;
import com.example.operational.service.repository.model.OrderDetails;
import com.example.operational.service.repository.model.User;
import com.example.operational.service.serviceinterface.SalesOrderInterface;
import com.example.operational.service.util.constants.Constants;
import com.example.operational.service.mappers.CommonMapper;
import com.example.operational.service.util.exception.BaseException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesOrderService{
    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    CommonMapper commonMapper;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CustomerDetailsRepository customerDetailsRepository;

    public void consume(String message) throws BaseException {
        try {
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            KafkaOrderCommon kafkaOrderCommon = objectMapper.readValue(message, KafkaOrderCommon.class);
            Long orderId = null;
            if(kafkaOrderCommon.getOrderId()!=null) orderId = Long.valueOf(kafkaOrderCommon.getOrderId());
            if(orderId!=null){
                Optional<OrderDetails> orderDetails = orderDetailsRepository.findById(orderId);
                switch(kafkaOrderCommon.getAction()) {
                    case Constants.CANCEL:
                        if(orderDetails.isPresent()){
                            orderDetails.get().setStatus(Constants.CANCELLED);
                            orderDetailsRepository.save(orderDetails.get());
                        }
                        break;
                    case Constants.DISPATCH:
                        if(orderDetails.isPresent()){
                            orderDetails.get().setStatus(Constants.DISPATCHED);
                            orderDetailsRepository.save(orderDetails.get());
                        }
                        break;
                    case Constants.SUCCESS:
                        if(orderDetails.isPresent()){
                            orderDetails.get().setStatus(Constants.SUCCESS_DESCRIPTION);
                            orderDetailsRepository.save(orderDetails.get());
                        }
                    default:
                        System.out.println(Constants.SUCCESS);
                }
            } else {
                createOrderConsumer(kafkaOrderCommon.getOrderData());
            }

        } catch (Exception ex) {
            throw new BaseException(ex.getMessage(), Constants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR_CODE, null);
        }
    }

    private void createOrderConsumer(OrderData salesOrderCreationRequest) throws BaseException {
        try{
            List<CustomerDetails> customerDetails = customerDetailsRepository.selectByUserId(Long.valueOf(salesOrderCreationRequest.getCustomerId()));
            orderDetailsRepository.save(commonMapper.createOrderMap(salesOrderCreationRequest, customerDetails.get(0)));
        }catch (Exception ex){
            throw new BaseException(ex.getMessage(), Constants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR_CODE, null);
        }
    }



}
