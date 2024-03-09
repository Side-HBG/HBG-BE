package com.devjin.springstu.domain.dto.response;

public class ResLogin {

    private final boolean result;
    public ResLogin(boolean _result)
    {
        this.result = _result;
    }
    public boolean getResult()
    {
        return result;
    }
}
