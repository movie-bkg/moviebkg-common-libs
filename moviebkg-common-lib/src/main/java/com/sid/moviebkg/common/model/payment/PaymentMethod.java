package com.sid.moviebkg.common.model.payment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.STRING)
@Getter
public enum PaymentMethod {
    CREDIT("Credit Card", 0),
    DEBIT("Debit Card", 1),
    UPI("UPI", 2);

    private final String displayName;
    private final Integer order;

    PaymentMethod(String displayName, Integer order) {
        this.displayName = displayName;
        this.order = order;
    }
}
