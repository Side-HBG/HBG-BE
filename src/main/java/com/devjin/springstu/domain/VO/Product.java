package com.devjin.springstu.domain.VO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Product {
    private final String name;
    private final String initial;
    private final String discount_percent;
    private final String price;
}
