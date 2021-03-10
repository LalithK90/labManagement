package lk.lab_management.asset.common_asset.model;

import lk.lab_management.asset.payment.entity.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTypeAmount{
  private PaymentMethod paymentMethod;
  private BigDecimal amount;
}
