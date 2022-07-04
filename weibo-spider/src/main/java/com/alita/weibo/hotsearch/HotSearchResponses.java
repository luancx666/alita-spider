package com.alita.weibo.hotsearch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: 热搜词响应类
 * @author: Luancx
 * @date: 2022/6/30
 */
@Data
public class HotSearchResponses {
    private Integer ok;
    private HotList data;

    @Data
    public static class HotList {
        private List<HotDetail> realtime;
    }

    @Data
    public static class HotDetail {
        private Integer expand;
        @JsonProperty("word_scheme")
        private String wordScheme;
        private Integer flag;
        @JsonProperty("subject_label")
        private String subjectLabel;
        private Integer num;
        private String emoticon;
        private String mid;
        @JsonProperty("channelType")
        private String channelType;
        @JsonProperty("label_name")
        private String labelName;
        @JsonProperty("star_word")
        private Integer starWord;
        @JsonProperty("funWord")
        private Integer funWord;
        @JsonProperty("ad_info")
        private Integer adInfo;
        @JsonProperty("subject_querys")
        private String subjectQuerys;
        @JsonProperty("star_name")
        private List<String> starName;
        @JsonProperty("onboard_time")
        private Integer onboardTime;
        @JsonProperty("raw_hot")
        private Integer rawHot;
        private String category;
        private String word;
        @JsonProperty("topic_flag")
        private Integer topicFlag;
        private String note;
        private Integer realpos;
        private Integer rank;
    }
}
