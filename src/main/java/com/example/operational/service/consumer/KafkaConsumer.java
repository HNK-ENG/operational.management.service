package com.example.operational.service.consumer;

import com.example.operational.service.dto.salesorders.updateOrder.KafkaOrderCommon;
import com.example.operational.service.repository.OrderDetailsRepository;
import com.example.operational.service.service.CustomerService;
import com.example.operational.service.service.SalesOrderService;
import com.example.operational.service.util.constants.Constants;
import com.example.operational.service.util.exception.BaseException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @Autowired
    SalesOrderService salesOrderService;

    @Autowired
    CustomerService customerService;

    @KafkaListener(topics = Constants.ORDER_TOPIC, groupId = "group_id")
    public void orderConsumer(String message) throws BaseException {
        try{
            System.out.println("sales order message = " + message);
            salesOrderService.consume(message);

        }catch (Exception ex){
            throw new BaseException(ex.getMessage(), Constants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR_CODE, null);
        }
    }


    @KafkaListener(topics = Constants.CUSTOMER_TOPIC, groupId = "group_id")
    public void customerConsumer(String message) throws BaseException {
        try{
            System.out.println("customer message = " + message);
            customerService.consume(message);

        }catch (Exception ex){
            throw new BaseException(ex.getMessage(), Constants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR_CODE, null);
        }
    }


}

