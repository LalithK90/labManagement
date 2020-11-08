package lk.lab_management.asset.payment.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentStatus {

    PAID("Payment Done"),
    NOTPAID("Not Paid");

    private final String paymentStatus;

}
