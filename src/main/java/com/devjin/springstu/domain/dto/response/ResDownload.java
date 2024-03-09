package com.devjin.springstu.domain.dto.response;
import javax.servlet.http.HttpServletResponse;

public class ResDownload {

    private final HttpServletResponse response;

    public ResDownload(HttpServletResponse _response)
    {
        this.response = _response;
    }
    public HttpServletResponse getResult()
    {
        return response;
    }
}
