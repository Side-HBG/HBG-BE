package com.devjin.springstu.domain.dto.response.ResStream;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@RequiredArgsConstructor
@Getter
@Setter
public class Price {
    private final String name;
    private final String initial;
    private final String discount_percent;
    private final String price;
}
