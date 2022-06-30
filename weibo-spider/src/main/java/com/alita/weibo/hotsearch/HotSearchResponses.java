package com.alita.weibo.hotsearch;

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
        private String word_scheme;
        private Integer flag;
        private String subject_label;
        private Integer num;
        private String emoticon;
        private String mid;
        private String channel_type;
        private String label_name;
        private Integer star_word;
        private Integer fun_word;
        private Integer ad_info;
        private String subject_querys;
        private List<String> star_name;
        private Integer onboard_time;
        private Integer raw_hot;
        private String category;
        private String word;
        private Integer topic_flag;
        private String note;
        private Integer realpos;
        private Integer rank;
    }
}
