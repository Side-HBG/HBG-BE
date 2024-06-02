package com.devjin.springstu.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name= ProductPriceInfo.TABLE_NAME)
@NoArgsConstructor

public class ProductPriceInfo {


    public static final String TABLE_NAME = "PRODUCTPRICEINFO";
    public static final String COL_APPID = "APPID";
    public static final String COL_TYPE= "TYPE";
    public static final String COL_ISFREE ="ISFREE" ;
    public static final String COL_INITIAL= "INITIAL";
    public static final String COL_DISCOUNT_PERCENT ="DISCOUNTPERCENT";
    public static final String COL_PRICE = "PRICE";
    public static final String COL_UPDATETIME = "UPDATETIME";

    public ProductPriceInfo(final String type, final boolean isfree
            , final String initial, final String discountpersent, final String price,final LocalDateTime updatetime, final Product prod, final Integer num)
    {
        this.type = type;
        this.isfree = isfree;
        this.initial = initial;
        this.discountpersent = discountpersent;
        this.price = price;
        this.product = prod;
        this.updatetime=updatetime;
        if(num !=null) this.num = num;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "num")
    private int num;


    @Column(name = COL_TYPE)
    private String type;
    @Column(name = COL_ISFREE)
    private Boolean isfree;
    @Column(name= COL_INITIAL)
    private String initial;
    @Column(name = COL_DISCOUNT_PERCENT)
    private String discountpersent;
    @Column(name = COL_PRICE)
    private String price;
    @Column(name = COL_UPDATETIME)
    private LocalDateTime updatetime;



    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = Product.TABLE_NAME)
    private Product product;


}
