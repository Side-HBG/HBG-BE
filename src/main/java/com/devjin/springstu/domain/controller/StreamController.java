package com.devjin.springstu.domain.controller;


import com.devjin.springstu.domain.dto.response.ResStream;
import com.devjin.springstu.domain.service.StreamService;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/price")
@RequiredArgsConstructor
public class StreamController {
    private final StreamService streamService;
    @GetMapping
    @ResponseBody
    public ResStream getprice(@RequestParam String item_id){
        var result=  streamService.getStreamIDName(item_id);
        return new ResStream(result.getName(),result.getInitial(),result.getDiscount_percent(),result.getPrice());
    }
}
