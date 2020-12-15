package lk.lab_management.asset.payment.entity;

import lk.lab_management.asset.payment.entity.enums.PaymentStatus;
import lk.lab_management.asset.sample_receiving.entity.SampleReceiving;
import lk.lab_management.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment extends AuditEntity {

  private String number;

  @Column( nullable = false, precision = 10, scale = 2 )
  private BigDecimal amount;

  @ManyToOne( cascade = CascadeType.MERGE )
  private SampleReceiving sampleReceiving;

  @DateTimeFormat( pattern = "yyyy-MM-dd" )
  private LocalDate paymentDate;

  @Enumerated( EnumType.STRING )
  private PaymentStatus paymentStatus;
}
