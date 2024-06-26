package com.linked.classbridge.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class RefundPolicy {
    private static final double FULL_REFUND_RATE = 1.0;             // 100% 환불
    private static final double SEVENTY_PERCENT_REFUND_RATE = 0.7;  // 70% 환불
    private static final double FIFTY_PERCENT_REFUND_RATE = 0.5;    // 50% 환불
    private static final double NO_REFUND_RATE = 0.0;               // 환불 없음

    private static final int FULL_REFUND_DAYS = 4;                  // 4일 전 전액 환불 가능
    private static final int SEVENTY_PERCENT_REFUND_DAYS = 3;       // 3일 전 70% 환불 가능
    private static final int FIFTY_PERCENT_REFUND_DAYS = 2;         // 2일 전 50% 환불 가능
    public static double calculateRefundRate(LocalDate lessonDate, LocalTime lessonStartTime, LocalDateTime refundRequestDate) {
        LocalDateTime lessDateTime = LocalDateTime.of(lessonDate, lessonStartTime);
        long daysBetween = Duration.between(refundRequestDate, lessDateTime).toDays();

        if (daysBetween >= FULL_REFUND_DAYS) {
            return FULL_REFUND_RATE; // 100% 환불
        } else if (daysBetween == SEVENTY_PERCENT_REFUND_DAYS) {
            return SEVENTY_PERCENT_REFUND_RATE; // 70% 환불
        } else if (daysBetween == FIFTY_PERCENT_REFUND_DAYS) {
            return FIFTY_PERCENT_REFUND_RATE; // 50% 환불
        } else {
            return NO_REFUND_RATE; // 환불 없음
        }

    }

}
