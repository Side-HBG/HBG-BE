package com.devjin.springstu.domain.service;

import com.devjin.springstu.domain.Exception.ApiException;
import com.devjin.springstu.domain.VO.Product;
import com.devjin.springstu.domain.common.NumbericCheck;
import com.devjin.springstu.domain.enums.ErrorCode;
import com.devjin.springstu.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.devjin.springstu.domain.common.SteamApiURL.appDataReqID;
import static com.devjin.springstu.domain.common.SteamApiURL.getAppList;

@Service
@RequiredArgsConstructor
public class StreamService {
    private final WebService webService;
    private final ProductRepository productRepository;

    public boolean saveAppList(){
        var resultJob = webService.get(getAppList);
        var applist = resultJob.getJSONObject("applist");
        var apps = applist.getJSONArray("apps");

        System.out.println("start");

        var realData = productRepository.findAll();
        var realappId = realData.stream().map(mp-> mp.getAppid()).toList();

        var appEntityList = new ArrayList<com.devjin.springstu.domain.entity.Product>();
        apps.forEach(fe-> appEntityList.add(new com.devjin.springstu.domain.entity
                .Product(
                ((JSONObject)fe).getInt("appid")
                ,((JSONObject)fe).getString("name"),
                realappId.contains(((JSONObject)fe).getInt("appid"))
                        ? realData.stream().filter(fl-> fl.getAppid() ==((JSONObject)fe).getInt("appid") )
                                .findFirst()
                                .orElseThrow(()->  new ApiException(ErrorCode.INTER_SERVER_ERROR)).getNum() : null
                )));
        var result = appEntityList.stream().filter(fl-> !fl.getName().isBlank()).toList();

        productRepository.saveAll(result);
        return true;
    }
    public Product getStreamIDName(final String item_id){

        String value;
        if(NumbericCheck.isNumberric(item_id)) value= item_id;
        else value = String.valueOf(productRepository.findByName(item_id).get().getAppid());

        var varob = webService.get(appDataReqID+value);
        var idData = varob.getJSONObject(value);
        var data = idData.getJSONObject("data");

        try {
            System.out.println(data.toString());
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
            // 할인없는 품목
            if(discountPer==0) nonDisPrice = price;

            return new Product(name,nonDisPrice,Integer.toString(discountPer),price);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        throw new ApiException(ErrorCode.INTER_SERVER_ERROR);

    }
}
