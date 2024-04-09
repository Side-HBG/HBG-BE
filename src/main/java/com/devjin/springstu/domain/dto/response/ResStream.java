package com.devjin.springstu.domain.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ResStream {

    private final String name;
    private final String initial;
    private final String discount_percent;
    private final String price;

}
