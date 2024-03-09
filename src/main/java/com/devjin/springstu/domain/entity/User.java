package com.devjin.springstu.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = User.TABLE_NAME)
public class User {

    public static final String TABLE_NAME = "User";
    public static final String COL_ID = "id";
    public static final String COL_PWD = "pwd";
    public static final String COL_EMAIL = "email";
    public  static  final String COL_Ver ="version";

    public User()
    {

    }
    public User(String _id , String _pwd , String _email,String _version)
    {
        this.id = _id;
        this.pwd = _pwd;
        this.email = _email;
        this.version = _version;
    }

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GenericGenerator(name = "USER_GENERATOR", strategy = "uuid")
    @Column(name = COL_ID)
    private String id ="";

    @Column(name = COL_PWD)
    private String pwd = "";

    @Column(name = COL_EMAIL)
    private String email = "";

    @Column(name = COL_Ver)
    private String version="";


    public String getId()
    {
        return id;
    }
    public String getPwd()
    {
        return pwd;
    }
    public  String getEmail()
    {
        return email;
    }
    public String getVersion(){return version;}

}

