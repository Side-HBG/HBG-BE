package com.devjin.springstu.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = Product.TABLE_NAME)
public class Product {

    public static final String TABLE_NAME = "PRODUCT";
    public static final String COL_APPID = "APPID";
    public static final String COL_NAME = "NAME";

    public Product()
    {

    }

    public Product(int _appid,String _name)
    {
        this.appid = _appid;
        this.name = _name;
    }

    @Id
    @Column(name = "NUM")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;

    @Column(name = COL_APPID)
    private int appid;

    @Column(name = COL_NAME)
    private String name = "";

}
