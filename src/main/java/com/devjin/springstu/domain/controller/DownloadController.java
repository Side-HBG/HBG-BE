package com.devjin.springstu.domain.controller;

import com.devjin.springstu.domain.dto.request.ReqPostDownload;
import com.devjin.springstu.domain.dto.response.ResDownload;
import com.devjin.springstu.domain.service.DownloadService;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;


@RestController
@RequestMapping("/download")
public class DownloadController {
    private final DownloadService downloadService;
    public DownloadController(DownloadService _downloadService)
    {
        this.downloadService = _downloadService;
    }

    @PostMapping
    public ResDownload downloadfile (HttpServletResponse _response, @RequestBody @Valid ReqPostDownload _reqPostDownload)throws IOException
    {
        return downloadService.download(_response,_reqPostDownload);
    }

}
