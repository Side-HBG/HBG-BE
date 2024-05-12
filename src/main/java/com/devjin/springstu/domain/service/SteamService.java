package com.devjin.springstu.domain.service;

import com.devjin.springstu.domain.Exception.ApiException;
import com.devjin.springstu.domain.VO.ProductPrice;
import com.devjin.springstu.domain.common.NumbericCheck;
import com.devjin.springstu.domain.enums.ErrorCode;
import com.devjin.springstu.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.devjin.springstu.domain.common.SteamApiURL.appDataReqID;
import static com.devjin.springstu.domain.common.SteamApiURL.getAppList;

@Service
@RequiredArgsConstructor
public class SteamService {
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
                ,((JSONObject)fe).getString("name").toUpperCase(),
                realappId.contains(((JSONObject)fe).getInt("appid"))
                        ? realData.stream().filter(fl-> fl.getAppid() ==((JSONObject)fe).getInt("appid") )
                                .findFirst()
                                .orElseThrow(()->  new ApiException(ErrorCode.INTER_SERVER_ERROR)).getNum() : null
                )));
        var result = appEntityList.stream().filter(fl-> !fl.getName().isBlank()).toList();

        productRepository.saveAll(result);
        return true;
    }
    public ProductPrice getJsonToPriceProduct(final String item_id)
    {
        try {
            var varob = webService.get(appDataReqID + item_id);
            var idData = varob.getJSONObject(item_id);
            var data = idData.getJSONObject("data");
            var name = data.getString("name");
            var type = data.getString("type");
            var is_free = data.getBoolean("is_free");
            try {
                System.out.println(item_id);
                System.out.println(data.toString());
                // 이름

                // 가격표
                var price_overview = data.getJSONObject("price_overview");
                // 할인전가격
                var nonDisPrice = price_overview.getString("initial_formatted");
                // 할인퍼센트
                var discountPer = price_overview.getInt("discount_percent");
                // 최종가격
                var price = price_overview.getString("final_formatted");
                // 할인없는 품목
                if (discountPer == 0) nonDisPrice = price;

                return new ProductPrice(name,type,is_free, nonDisPrice, Integer.toString(discountPer), price);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new ProductPrice(name, type,is_free,null, null, null);
            }
        }
        catch (Exception e2) {
            return new ProductPrice(null,null,false,null,null,null);
        }
    }


    public List<ProductPrice> getStreamIDName(final String item_id){

        List<String> values = getItem_IDs(item_id);
        List<ProductPrice> result = new ArrayList<>();
        values.stream().forEach(fe-> {
            var product = getJsonToPriceProduct(fe);
            if(product.getName()==null) return;
            result.add(product);
        });
        return result;
    }

    private List<String> getItem_IDs(String item_id) {
        String target = "%"+item_id.toUpperCase()+"%";
        String value ="";
        List<String> values = new ArrayList<>();
        if(NumbericCheck.isNumberric(target)) value= target;
        else values= productRepository
                .findAllByNameLike(target)
                .orElseThrow(()-> new ApiException(ErrorCode.STEAM_NOT_FONUD_APPNAME))
                .stream()
                .map(mp->String.valueOf(mp.getAppid()))
                .toList();

        if(values.isEmpty())
            if(value.isBlank()) throw new ApiException(ErrorCode.STEAM_NOT_FONUD_APPNAME);
            else values.add(value);

        return values;
    }
}
