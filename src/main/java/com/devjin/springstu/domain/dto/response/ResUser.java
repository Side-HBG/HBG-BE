package com.devjin.springstu.domain.dto.response;
import com.devjin.springstu.domain.entity.User;

public class ResUser {

    private final String id;
    private final String pwd;
    private final String email;
    private final String version;

    public ResUser(User _user)
    {
        this.id = _user.getId();
        this.pwd= _user.getPwd();
        this.version =_user.getVersion();
        this.email = _user.getEmail();
    }
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
}
