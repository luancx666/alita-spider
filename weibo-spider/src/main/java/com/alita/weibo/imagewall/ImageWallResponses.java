package com.alita.weibo.imagewall;

import lombok.Data;

import java.util.List;

/**
 * @description: 微博相册
 * @author: Luancx
 * @date: 2022/6/30
 */
@Data
public class ImageWallResponses {
    private Integer ok;
    private Boolean bottom_tips_visible;
    private String bottom_tips_text;
    private ImageWallData data;

    @Data
    public static class ImageWallData {
        private String since_id;
        private List<ImageWallDetail> list;
    }

    @Data
    public static class ImageWallDetail {
        private String pid;
        private String mid;
        private Boolean is_paid;
        private String timeline_month;
        private String timeline_year;
        private String object_id;
        private String type;
    }
}
