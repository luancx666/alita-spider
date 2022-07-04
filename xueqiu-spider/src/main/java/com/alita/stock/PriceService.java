package com.alita.stock;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;

import java.util.Collections;
import java.util.List;

/**
 * @description: 股票价格
 * @author: Luancx
 * @date: 2022/7/1
 */
public class PriceService {
    private static final String XUEQIU_URL = "https://stock.xueqiu.com/v5/stock/realtime/quotec.json?symbol=";

    public static List<QuotevResponse.Quotev> getStockPrice(List<String> code) {
        StringBuilder sb = new StringBuilder(XUEQIU_URL);
        for (int i = 0; i < code.size(); i++) {
            sb.append(code.get(i));
            if (i < code.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("&_").append(System.currentTimeMillis());
        String s = HttpUtil.get(sb.toString());
        QuotevResponse response = JSON.parseObject(s, QuotevResponse.class);
        return response.getData();
    }

    public static void main(String[] args) {
        List<QuotevResponse.Quotev> sh601288 = getStockPrice(Collections.singletonList("SH601288"));
        for (QuotevResponse.Quotev quotev : sh601288) {
            System.out.println(JSON.toJSONString(quotev));
        }
    }
}
