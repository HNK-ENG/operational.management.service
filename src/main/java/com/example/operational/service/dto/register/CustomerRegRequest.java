package com.example.operational.service.dto.register;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerRegRequest {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String identityNumber;
    private String district;
    private String city;
    private String street1;
    private String street2;
    private String houseNo;

}
