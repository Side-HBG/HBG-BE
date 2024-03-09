package com.devjin.springstu.domain.dto.request;
import com.sun.istack.NotNull;

public class ReqPostDownload {

    @NotNull
    private String id;
    @NotNull
    private String pwd;

    public String getID(){return id;}
    public String getPwd(){return pwd;}
    public void setID(String id){this.id = id;}
    public void setPwd(String pwd){this.pwd = pwd;}

}
