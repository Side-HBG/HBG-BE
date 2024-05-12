package com.devjin.springstu.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ProductInfo {


    public static final String TABLE_NAME = "PRODUCT";
    public static final String COL_APPID = "APPID";
    public static final String COL_NAME = "NAME";

    public ProductInfo()
    {

    }

    public ProductInfo(int _appid,String _name,Integer _num)
    {
        if(_num!=null) num=_num;
        this.appid = _appid;
        this.name = _name;
    }

   // @Id
   //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "num")
    private int num;


   // @Column(name = COL_APPID)
    private int appid;

  //  @Column(name = COL_NAME)
    private String name = "";



}
