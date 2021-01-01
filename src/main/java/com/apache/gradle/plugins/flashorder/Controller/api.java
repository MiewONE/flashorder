package com.apache.gradle.plugins.flashorder.Controller;

import com.apache.gradle.plugins.flashorder.Service.DetectText;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class api {
    private DetectText detectText;
    @PostMapping("/api/getOrderNo")
    public Map<String,String> order(@RequestBody String url) throws IOException {
        HashMap<String,String> orderInfo = new HashMap<String, String>();
        List<String> info;
        int no=0;
        String restaurantName = "";
        info = detectText.detectTextGcs(url);
        String link = "";
        String[] elements = info.get(0).split("\n");

        for(int i=0;i<elements.length;i++)
        {
            if(elements[i].contains("교환처")) {
                no=i;
                break;
            }
        }
        for(String el: elements)
        {
            switch (el)
            {
                case "굽네치킨":
                    restaurantName = el;
                    break;
                case "도미노피자":
                    restaurantName = el;
                    break;
                case "BBQ" :
                    restaurantName = el;
                    break;
                case "피자헛":
                    restaurantName = el;
                    break;
                case "교촌치킨" :
                    restaurantName = el;
                    break;
                case "bhc":
                    restaurantName = el;
                    break;
            }
        }
        String orderNo = elements[no-1];
        switch (restaurantName)
        {
            case "굽네치킨":
                link = "https://order.goobne.co.kr:8481/login/login.aspx#";
                break;
            case "도미노피자":
                link = "https://web.dominos.co.kr/ecoupon/index";
                break;
            case "BBQ" :
                link = "https://m.bbq.co.kr/coupon_use.asp";
                break;
            case "피자헛":
                link = "https://www.pizzahut.co.kr/e-coupon";
                break;
            case "교촌치킨" :
                link = "https://order.kyochon.com/member/login";
                break;
            case "bhc" :
                link = "https://online.bhc.co.kr/order/orderSelect.do";
                break;
            default :
                link = "https://dogdrip.net";
                break;
        }
        orderNo = orderNo.replaceAll(" ","");
        orderInfo.put("link",link);
        orderInfo.put("restaurantName",restaurantName);
        orderInfo.put("orderNo",orderNo);
        return orderInfo;
    }
}
