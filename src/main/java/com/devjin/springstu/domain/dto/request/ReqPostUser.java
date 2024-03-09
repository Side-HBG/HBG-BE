package com.devjin.springstu.domain.dto.request;
import com.devjin.springstu.domain.entity.User;
import com.sun.istack.NotNull;

public class ReqPostUser {

    @NotNull
    private String id;
    @NotNull
    private String pwd;
    @NotNull
    private String email;
    @NotNull
    private String version;

    public String getId()
    {
        return id;
    }
    public String getPwd()
    {
        return pwd;
    }
    public String getEmail()
    {
        return email;
    }
    public String getVersion(){return version;}

    public void setId(String id)
    {
        this.id=id;
    }
    public void setPwd(String pwd)
    {
        this.pwd=pwd;
    }
    public void setEmail(String email)
    {
        this.email=email;
    }
    public void setVersion(String version){this.version=version;}

    public User toEntity()
    {
        return new User(this.id , this.pwd , this.email,this.version);
    }
}
