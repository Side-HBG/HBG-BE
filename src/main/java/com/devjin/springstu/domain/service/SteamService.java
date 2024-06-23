package com.devjin.springstu.domain.service;

import com.devjin.springstu.domain.Exception.ApiException;
import com.devjin.springstu.domain.VO.Productinfo;
import com.devjin.springstu.domain.common.NumbericCheck;
import com.devjin.springstu.domain.entity.Product;
import com.devjin.springstu.domain.entity.ProductPriceInfo;
import com.devjin.springstu.domain.enums.ErrorCode;
import com.devjin.springstu.domain.repository.ProductPriceInfoRepository;
import com.devjin.springstu.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static com.devjin.springstu.domain.common.SteamApiURL.appDataReqID;
import static com.devjin.springstu.domain.common.SteamApiURL.getAppList;

@Service
@RequiredArgsConstructor
public class SteamService {
    private final WebService webService;
    private final ProductRepository productRepository;
    private final ProductPriceInfoRepository productPriceInfoRepository;

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
                        ? getAppid((JSONObject) fe, realData) : null
                )));
        var result = appEntityList.stream().filter(fl-> !fl.getName().isBlank()).toList();

        productRepository.saveAll(result);
        System.out.println("end");
        return true;
    }

    private static int getAppid(JSONObject fe, List<Product> realData) {
        var data = realData.stream().filter(fl -> fl.getAppid() == fe.getInt("appid"))
                .findFirst()
                .orElseThrow(() -> new ApiException(ErrorCode.INTER_SERVER_ERROR)).getNum();
        return data;
    }

    public Productinfo getJsonToPriceProduct(final String item_id)
    {
        try {
            var varob = webService.get(appDataReqID + item_id+"&cc=krw&l=kr");
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

                return new Productinfo(name,type,is_free, nonDisPrice, Integer.toString(discountPer), price,null);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new Productinfo(name, type,is_free,null, null, null,null);
            }
        }
        catch (Exception e2) {
            return new Productinfo(null,null,false,null,null,null,null);
        }
    }
    public ArrayList<Productinfo> getJsonToPrice(final List<Product> products,final String strings)
    {
        var result = new ArrayList<Productinfo>();
        try {

            // 250개씩 분할가능
            var varob = webService.get(appDataReqID + strings+"&cc=krw&l=kr&filters=price_overview");

            Iterator<String> keys = varob.keys();

            while (keys.hasNext())
            {
                String key = keys.next();

                var idData = varob.getJSONObject(key);
                var name = products.stream().filter(fl-> fl.getAppid()==Integer.parseInt(key)).findFirst().orElse(new Product());
                try {
                    if(name.getName().isBlank()) continue;
                    var data = idData.getJSONObject("data");
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

                    result.add(new Productinfo(name.getName(),null,false, nonDisPrice, Integer.toString(discountPer), price,name));
                }
                catch (Exception e)
                {
                    System.out.println(e + " E1");

                    result.add(new Productinfo(name.getName(),null,true,null,null,null, name));
                }
            }
        }
        catch (Exception e2) {
            System.out.println(e2 + " E2");
            return result;
        }
        return result;
    }


    public boolean saveProductPriceDetail(){
        var datas = productRepository.findAll();
        datas.stream().forEach(mp->{
            var productPrice = getJsonToPriceProduct(String.valueOf(mp.getAppid()));
            if (productPrice.getName() == null) return;
            var priceinfo = new ProductPriceInfo(productPrice.getType(),productPrice.is_free(),productPrice.getInitial()
            ,productPrice.getDiscount_percent(),productPrice.getPrice(), LocalDateTime.now(),mp,null);
            productPriceInfoRepository.save(priceinfo);
        });
        return true;
    }

    public boolean saveProductPriceinfo(){
        var datas = productRepository.findAll();
        var datasTostringArray = (ArrayList<String>)datas.stream()
                .map(mp-> String.valueOf(mp.getAppid()))
                .collect(Collectors.toList());
        var data250SplitString =new ArrayList<String>();


        StringJoiner joiner = new StringJoiner(",");
        for(int i = 0; i< datasTostringArray.size(); i++) {
            joiner.add(datasTostringArray.get(i));
            if((i+1) % 250 ==0 || i == datasTostringArray.size() - 1){
                data250SplitString.add(joiner.toString());
                joiner = new StringJoiner(",");
            }
        }

        data250SplitString.stream().forEach(array -> {
            var priceResult = getJsonToPrice(datas,array);
            if(priceResult.stream().count() == 0) {
                return;
            }
            var productPriceInfoRepDatas = productPriceInfoRepository.findAll().stream().map(mp->mp.getProduct()).toList();

            try {
            var repDatas= priceResult.stream().map(mp-> new ProductPriceInfo(
                    mp.getType(),mp.is_free(),mp.getInitial(),mp.getDiscount_percent(),mp.getPrice(), LocalDateTime.now()
                    ,mp.getProduct()
                    ,productPriceInfoRepDatas.contains(mp.getProduct())?productPriceInfoRepository.findByProduct(mp.getProduct()).get().getNum():null
            )).toList();

            productPriceInfoRepository.saveAll(repDatas);

                Thread.sleep(1500);
                System.out.println("Sleep....");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }catch (InvalidDataAccessApiUsageException e2) {
            }
        });


        return true;
    }



    public List<Productinfo> getStreamIDName(final String item_id){

        List<String> values = getItem_IDs(item_id);
        List<Productinfo> result = new ArrayList<>();
        values.stream().forEach(fe-> {
            var product = getJsonToPriceProduct(fe);
            if(product.getName()==null) return;
            result.add(product);
        });
        return result;
    }


    public List<Productinfo> getStreamIDNameV2(final String item_id){

        List<String> values = getItem_IDs(item_id);
        List<Productinfo> result = new ArrayList<>();
        values.stream().forEach(ids-> {
            var product =  productRepository.findByAppid(Integer.parseInt(ids));
            product.stream().forEach(prds-> {
                System.out.println("in1");
                try {
                    var priceInfoRep = productPriceInfoRepository.findByProduct(prds).orElseThrow(() -> new ApiException(ErrorCode.STEAM_NOT_FONUD_APPNAME));
                    System.out.println("in2");
                    var priceInfo = new Productinfo(prds.getName(), priceInfoRep.getType(), priceInfoRep.getIsfree(), priceInfoRep.getInitial()
                            , priceInfoRep.getDiscountpersent(), priceInfoRep.getPrice());
                    result.add(priceInfo);
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
            });
        });
        return result;
    }

    private List<String> getItem_IDs(String item_id) {
        String target = item_id.toUpperCase();
        String value ="";
        List<String> values = new ArrayList<>();
        if(NumbericCheck.isNumberric(target)) value= "%"+target+"%";
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
