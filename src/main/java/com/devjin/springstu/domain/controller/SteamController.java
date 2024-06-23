package com.devjin.springstu.domain.controller;


import com.devjin.springstu.domain.dto.response.ResStream.Price;
import com.devjin.springstu.domain.service.SteamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/steam")
@RequiredArgsConstructor
public class SteamController {
    private final SteamService streamService;
    @GetMapping("/price")
    public List<Price> getPrice(@RequestParam(value = "item_id") String item_id){
       var result=  streamService.getStreamIDName(item_id);

       return result.stream().filter(mp-> mp !=null).map(mp-> new Price(mp.getName(),mp.getType(),mp.is_free(),mp.getInitial(),mp.getDiscount_percent(),mp.getPrice())).toList();
    }
    @GetMapping("/pricev2")
    public List<Price> getPriceV2(@RequestParam(value = "item_id") String item_id){
        var result=  streamService.getStreamIDNameV2(item_id);
        return result.stream().filter(mp-> mp !=null).map(mp-> new Price(mp.getName(),mp.getType(),mp.is_free(),mp.getInitial(),mp.getDiscount_percent(),mp.getPrice())).toList();
    }

    @GetMapping("/saveapplist")
    public boolean getAppList(){
        return streamService.saveAppList();
    }

    @GetMapping("/saveProductPriceDetail")
    public boolean saveProductPriceDetail(){
        return streamService.saveProductPriceDetail();
    }

    @GetMapping("/saveProductPriceInfo")
    public boolean saveProductPriceInfo() {
        return streamService.saveProductPriceinfo();
    }
}
