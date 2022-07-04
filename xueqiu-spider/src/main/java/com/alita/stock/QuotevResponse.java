package com.alita.stock;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: 报价单
 * @author: Luancx
 * @date: 2022/7/1
 */
@Data
public class QuotevResponse {
    @JsonProperty("error_code")
    private Integer errorCode;
    @JsonProperty("error_description")
    private String errorDescription;
    private List<Quotev> data;

    @Data
    public static class Quotev {
        private Integer amount;
        private Double amplitude;
        @JsonProperty("avg_price")
        private Double avgPrice;
        @JsonProperty("bid_appl_seq_num")
        private Double bidApplSeqNum;
        private Double chg;
        private Double current;
        @JsonProperty("current_year_percent")
        private Double currentYearPercent;
        @JsonProperty("float_market_capital")
        private Long floatMarketCapital;
        private Double high;
        @JsonProperty("is_trade")
        private Boolean isTrade;
        @JsonProperty("last_close")
        private Double lastClose;
        private Integer level;
        private Double low;
        @JsonProperty("market_capital")
        private Long marketCapital;
        @JsonProperty("offer_appl_seq_num")
        private String offerApplSeqNum;
        private Double open;
        private Double percent;
        private Integer side;
        private String symbol;
        private Long timestamp;
    }
}
