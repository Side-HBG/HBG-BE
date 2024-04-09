package com.devjin.springstu.domain.service;

import com.devjin.springstu.domain.Exception.ApiException;
import com.devjin.springstu.domain.VO.Product;
import com.devjin.springstu.domain.dto.response.ResStream;
import com.devjin.springstu.domain.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamService {
    private final WebService webService;
    final String appDataReqID = "https://store.steampowered.com/api/appdetails?appids=";
    public Product getStreamIDName(final String item_id){
        var varob = webService.get(appDataReqID+item_id);
        System.out.println(varob.toString());
        var idData = varob.getJSONObject(item_id);
        System.out.println(idData.toString());
        var data = idData.getJSONObject("data");
        System.out.println(data.toString());


        try {
            // 이름
            var name = data.getString("name");
            // 가격표
            var price_overview = data.getJSONObject("price_overview");
            // 할인전가격
            var nonDisPrice  = price_overview.getString("initial_formatted");
            // 할인퍼센트
            var discountPer  = price_overview.getInt("discount_percent");
            // 최종가격
            var price  = price_overview.getString("final_formatted");

            if(discountPer==0) nonDisPrice = price;
            return new Product(name,nonDisPrice,Integer.toString(discountPer),price);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        throw  new ApiException(ErrorCode.INTER_SERVER_ERROR);

    }
}
