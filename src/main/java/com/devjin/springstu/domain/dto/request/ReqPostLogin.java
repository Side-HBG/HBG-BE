package com.devjin.springstu.domain.dto.request;
import com.sun.istack.NotNull;

public class ReqPostLogin {

    @NotNull
    private String id;
    @NotNull
    private String pwd;
    private String email;
    private String version;

    public String getID(){return id;}
    public String getPwd(){return pwd;}
    public String getEmail(){return email;}
    public String getVersion(){return version;}

    public void setID(String id){this.id = id;}
    public void setPwd(String pwd){this.pwd = pwd;}
    public void setEmail(String email){this.email = id;}
    public void setVersion(String version){this.version = version;}

}
