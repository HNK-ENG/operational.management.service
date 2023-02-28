package com.example.operational.service.mappers;


import com.example.operational.service.dto.register.CustomerRegRequest;
import com.example.operational.service.dto.salesorders.createOrder.SalesOrderCreationRequest;
import com.example.operational.service.dto.salesorders.updateOrder.OrderData;
import com.example.operational.service.repository.OrderDetailsRepository;
import com.example.operational.service.repository.model.CustomerDetails;
import com.example.operational.service.repository.model.OrderDetails;
import com.example.operational.service.repository.model.User;
import com.example.operational.service.util.constants.Constants;
import com.example.operational.service.util.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CommonMapper {
    public OrderDetails createOrderMap(SalesOrderCreationRequest salesOrderCreationRequest) throws BaseException {
        try{
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setCustomerId(salesOrderCreationRequest.getCustomerId());
            orderDetails.setItem_name(salesOrderCreationRequest.getItemName());
            orderDetails.setQuantity(salesOrderCreationRequest.getQuantity()!=null ? String.valueOf(salesOrderCreationRequest.getQuantity()) : null);
            orderDetails.setTotalCost(salesOrderCreationRequest.getTotalCost());
            orderDetails.setStatus(Constants.NEW);
            return orderDetails;

        }catch (Exception ex){
            throw new BaseException(ex.getMessage(), "Exception in Mapper layer", HttpStatus.INTERNAL_SERVER_ERROR, "500", null);
        }
    }

    public OrderDetails createOrderMap(OrderData salesOrderCreationRequest, CustomerDetails customerDetails) throws BaseException {
        try{
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setCustomerId(String.valueOf(customerDetails.getId()));
            orderDetails.setItem_name(salesOrderCreationRequest.getItemName());
            orderDetails.setQuantity(salesOrderCreationRequest.getQuantity()!=null ? String.valueOf(salesOrderCreationRequest.getQuantity()) : null);
            orderDetails.setTotalCost(salesOrderCreationRequest.getTotalCost());
            orderDetails.setStatus(Constants.NEW);
            return orderDetails;

        }catch (Exception ex){
            throw new BaseException(ex.getMessage(), "Exception in Mapper layer", HttpStatus.INTERNAL_SERVER_ERROR, "500", null);
        }
    }

    public CustomerDetails createCustomer(CustomerRegRequest customerRegRequest, User user) throws BaseException {
        try{
            CustomerDetails customerDetails = new CustomerDetails();
            customerDetails.setFirstName(customerRegRequest.getFirstName());
            customerDetails.setLastName(customerRegRequest.getLastName());
            customerDetails.setDistrict(customerRegRequest.getDistrict());
            customerDetails.setCity(customerRegRequest.getCity());
            customerDetails.setStreet1(customerRegRequest.getStreet1());
            customerDetails.setStreet2(customerRegRequest.getStreet2());
            customerDetails.setHouseNo(customerRegRequest.getHouseNo());
            customerDetails.setUserId(user);
            return customerDetails;

        }catch (Exception ex){
            throw new BaseException(ex.getMessage(), "Exception in Mapper layer", HttpStatus.INTERNAL_SERVER_ERROR, "500", null);
        }
    }

    public User createUser(CustomerRegRequest customerRegRequest) throws BaseException {
        try{
            User user = new User();
            user.setUserName(customerRegRequest.getUserName());
            user.setPassword(customerRegRequest.getPassword());
            user.setRole(Constants.USER_ROLE);
            return user;
        }catch (Exception ex){
            throw new BaseException(ex.getMessage(), "Exception in Mapper layer", HttpStatus.INTERNAL_SERVER_ERROR, "500", null);
        }
    }
}
