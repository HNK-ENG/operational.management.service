package com.example.operational.service.controller.customerprofilemanagement;

import com.example.operational.service.dto.common.AdaptorResponse;
import com.example.operational.service.dto.salesorders.createOrder.SalesOrderCreationRequest;
import com.example.operational.service.dto.salesorders.createOrder.SalesOrderCreationResponse;
import com.example.operational.service.repository.model.CustomerDetails;
import com.example.operational.service.serviceinterface.CustomerServiceInterface;
import com.example.operational.service.util.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/om/customer")
public class CustomerDetailsController {

    @Autowired
    CustomerServiceInterface customerServiceInterface;

    @PostMapping("/save")
    ResponseEntity<AdaptorResponse<CustomerDetails>> saveCustomer(@RequestBody CustomerDetails customerDetails, HttpServletRequest httpServletRequest) throws BaseException {
        AdaptorResponse<CustomerDetails> adaptorResponse = customerServiceInterface.saveCustomer(customerDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(adaptorResponse);
    }




}
