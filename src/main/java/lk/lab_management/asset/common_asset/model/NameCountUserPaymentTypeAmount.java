package lk.lab_management.asset.common_asset.model;

import lk.lab_management.asset.payment.entity.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NameCountUserPaymentTypeAmount {
    private String name;
    private Integer count;
    private List<PaymentTypeAmount> paymentTypeAmounts;
}

