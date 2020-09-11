package lk.GRILMSystem.labManagement.asset.payment.entity;

import lk.GRILMSystem.labManagement.asset.payment.entity.Enum.PaymentStatus;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.SampleReceiving;
import lk.GRILMSystem.labManagement.util.audit.AuditEntity;
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

    @ManyToOne
    private SampleReceiving sampleReceiving;

    private BigDecimal amount;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private LocalDate paymentDate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
