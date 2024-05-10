package com.simsasookbak.email.domain;

import java.util.function.Function;
import lombok.Getter;

public enum MailType {
    CANCEL_USER_RESERVATION(
            "[심사숙박] 예약 취소",
            msg -> msg + "님의 예약이 취소 되었습니다."
    ),
    CANCEL_BUSINESS_PERSON_RESERVATION(
            "[심사숙박] 예약 취소",
            msg -> "요청하신 숙소의 예약이 취소되었습니다"
    ),
    RESERVATION_APPROVAL(
            "[심사숙박] 예약 확정",
            msg -> msg + "님의 예약이 확정되었습니다."
    );


    @Getter
    private final String subject;
    private final Function<String, String> message;

    MailType(String subject, Function<String, String> message) {
        this.subject = subject;
        this.message = message;
    }

    public String getMessage(String userName) {
        return this.message.apply(userName);
    }
}
