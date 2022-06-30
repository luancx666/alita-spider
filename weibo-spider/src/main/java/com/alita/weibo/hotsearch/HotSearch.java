package com.alita.weibo.hotsearch;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;

/**
 * @description: 微博热搜
 * @author: Luancx
 * @date: 2022/6/30
 */
public class HotSearch {
    private static final String HOT_SEARCH_URL = "https://weibo.com/ajax/side/hotSearch";

    public static HotSearchResponses getHotSearch() {
        String response = HttpUtil.get(HOT_SEARCH_URL);
        return JSON.parseObject(response, HotSearchResponses.class);
    }
}
