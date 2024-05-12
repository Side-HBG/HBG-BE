package com.devjin.springstu.domain.controller;


import com.devjin.springstu.domain.dto.response.ResStream.Price;
import com.devjin.springstu.domain.service.SteamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/steam")
@RequiredArgsConstructor
public class SteamController {
    private final SteamService streamService;
    @GetMapping("/price")
    public Price getPrice(@RequestParam(value = "item_id") String item_id){
       var result=  streamService.getStreamIDName(item_id);
       return new Price(result.getName(),result.getInitial(),result.getDiscount_percent(),result.getPrice());
    }

    @GetMapping("/saveapplist")
    public boolean getAppList(){
        return streamService.saveAppList();
    }
}
