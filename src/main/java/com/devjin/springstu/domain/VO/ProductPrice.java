package com.devjin.springstu.domain.VO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.objenesis.SpringObjenesis;

@RequiredArgsConstructor
@Getter
public class ProductPrice {
    private final String name;
    private final String type;
    private final boolean is_free;
    private final String initial;
    private final String discount_percent;
    private final String price;

}
