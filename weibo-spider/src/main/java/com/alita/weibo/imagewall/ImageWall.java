package com.alita.weibo.imagewall;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 微博相册
 * @author: Luancx
 * @date: 2022/6/30
 */
@Slf4j
public class ImageWall {
    private static final String HEADER_KEY = "cookie";
    private static final String IMAGE_WALL_URL = "https://weibo.com/ajax/profile/getImageWall?uid=";

    public static ImageWallResponses getImageWall(String uid, String sinceid, String cookie) {
        if (StrUtil.isBlank(sinceid)) {
            sinceid = "0";
        }
        String url = IMAGE_WALL_URL + uid + "&sinceid=" + sinceid;
        HttpResponse response = HttpRequest.get(url).header(HEADER_KEY, cookie).execute();
        try {
            return JSON.parseObject(response.body(), ImageWallResponses.class);
        } catch (Exception e) {
            log.error("JSON装换异常:{}", response.body());
            return null;
        }
    }
}
