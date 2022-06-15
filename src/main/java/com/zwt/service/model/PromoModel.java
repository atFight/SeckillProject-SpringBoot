package com.zwt.service.model;

import lombok.Data;
import org.joda.time.DateTime;

import java.math.BigDecimal;

@Data
public class PromoModel {

    private PromoModelStatus status = PromoModelStatus.NONE;

    private Integer id;

    private String promoName;

    private DateTime startTime;

    private DateTime endTime;

    private Integer itemId;

    private BigDecimal promoItemPrice;

    public void updateStatus() {
        DateTime now = new DateTime();
        if (this.startTime.isAfterNow()) {
            this.status = PromoModelStatus.WAITING;
        }else if (this.endTime.isBeforeNow()) {
            this.status = PromoModelStatus.ENDED;
        }else {
            this.status = PromoModelStatus.PROCESSING;
        }
    }

    public enum PromoModelStatus {
        NONE,
        WAITING,
        PROCESSING,
        ENDED,
    }
}
