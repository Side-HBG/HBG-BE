package com.devjin.springstu.domain.VO;

import com.devjin.springstu.domain.entity.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class Productinfo {
    private final String name;
    private final String type;
    private final boolean is_free;
    private final String initial;
    private final String discount_percent;
    private final String price;
    private final Product product;

    public Productinfo(String name, String type, boolean is_free,String initial,String discount_percent
    ,String price,Product product)
    {
        this.name = name; this.type = type;
        this.is_free = is_free;
        this.initial = initial;
        this.discount_percent = discount_percent;
        this.price = price;
        this.product =product;
    }
    public Productinfo(String name, String type, boolean is_free,String initial,String discount_percent
            ,String price)
    {
        this.name = name; this.type = type;
        this.is_free = is_free;
        this.initial = initial;
        this.discount_percent = discount_percent;
        this.price = price;
        this.product = null;
    }
}
