package com.alita.weibo.imagewall;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("bottom_tips_visible")
    private Boolean bottomTipsVisible;
    @JsonProperty("bottom_tips_text")
    private String bottomTipsText;
    private ImageWallData data;

    @Data
    public static class ImageWallData {
        @JsonProperty("since_id")
        private String sinceId;
        private List<ImageWallDetail> list;
    }

    @Data
    public static class ImageWallDetail {
        private String pid;
        private String mid;
        @JsonProperty("is_paid")
        private Boolean isPaid;
        @JsonProperty("timeline_month")
        private String timelineMonth;
        @JsonProperty("timeline_year")
        private String timelineYear;
        @JsonProperty("object_id")
        private String objectId;
        private String type;
    }
}
