package com.example.operational.service.serviceinterface;

import com.example.operational.service.dto.common.AdaptorResponse;
import com.example.operational.service.repository.model.CustomerDetails;
import com.example.operational.service.util.exception.BaseException;

public interface CustomerServiceInterface {
    AdaptorResponse<CustomerDetails> saveCustomer(CustomerDetails customerDetails) throws BaseException;
}
