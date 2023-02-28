package com.example.operational.service.dto.common;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Result {
    private String resultCode;
    private String resultDescription;
}
