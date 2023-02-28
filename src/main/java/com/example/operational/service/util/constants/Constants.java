package com.example.operational.service.util.constants;


import org.springframework.stereotype.Component;

@Component
public class Constants {
    public static final String NEW = "NEW";
    public static final String SUCCESS_CODE = "100";
    public static final String SUCCESS_DESCRIPTION = "Success";
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String INTERNAL_SERVER_ERROR_CODE = "500";
    public static final String ORDER_TOPIC = "Order";

    public static final String CANCEL = "CANCEL";
    public static final String DISPATCH = "DISPATCH";
    public static final String SUCCESS = "SUCCESS";
    public static final String CANCELLED = "CANCELLED";
    public static final String DISPATCHED = "DISPATCHED";

    public static final String CUSTOMER_TOPIC = "Customer";
    public static final String USER_ROLE = "0";


    private Constants() {}
}
