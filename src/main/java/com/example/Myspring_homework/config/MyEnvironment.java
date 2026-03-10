package com.example.Myspring_homework.config;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class MyEnvironment {
    private String mode;
    private double rate;
}