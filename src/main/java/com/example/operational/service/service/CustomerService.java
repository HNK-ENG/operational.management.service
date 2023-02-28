package com.example.operational.service.service;


import com.example.operational.service.dto.common.AdaptorResponse;
import com.example.operational.service.dto.common.Result;
import com.example.operational.service.dto.register.CustomerRegRequest;
import com.example.operational.service.dto.salesorders.createOrder.SalesOrderCreationResponse;
import com.example.operational.service.mappers.CommonMapper;
import com.example.operational.service.repository.CustomerDetailsRepository;
import com.example.operational.service.repository.UserRepository;
import com.example.operational.service.repository.model.CustomerDetails;
import com.example.operational.service.repository.model.User;
import com.example.operational.service.serviceinterface.CustomerServiceInterface;
import com.example.operational.service.util.constants.Constants;
import com.example.operational.service.util.exception.BaseException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements CustomerServiceInterface {

    @Autowired
    CustomerDetailsRepository customerDetailsRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CommonMapper commonMapper;

    @Autowired
    UserRepository userRepository;

    @Override
    public AdaptorResponse<CustomerDetails> saveCustomer(CustomerDetails customerDetails) throws BaseException {
        try{
            CustomerDetails customerDetails_ = customerDetailsRepository.save(customerDetails);
            Result result = Result.builder().resultCode(Constants.SUCCESS_CODE).resultDescription(Constants.SUCCESS_DESCRIPTION).build();
            return AdaptorResponse.<CustomerDetails>builder().data(customerDetails_).result(result).build();

        }catch (Exception ex){
            throw new BaseException(ex.getMessage(), Constants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR_CODE, null);
        }
    }


    public void consume(String message) throws BaseException {
        try{

            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            CustomerRegRequest customerRegRequest = objectMapper.readValue(message, CustomerRegRequest.class);

            User user = commonMapper.createUser(customerRegRequest);
            userRepository.save(user);
            CustomerDetails customerDetails = commonMapper.createCustomer(customerRegRequest, user);
            customerDetailsRepository.save(customerDetails);




        }catch (Exception ex){
            throw new BaseException(ex.getMessage(), Constants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR_CODE, null);
        }
    }
}
