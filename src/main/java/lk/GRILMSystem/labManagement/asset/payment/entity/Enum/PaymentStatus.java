package lk.GRILMSystem.labManagement.asset.payment.entity.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentStatus {

    PAID("Payment Done"),
    NOTPAID("Not Paid");

    private final String paymentStatus;

}
