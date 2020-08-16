package lk.GRILMSystem.labManagement.asset.payment.entity;

import lk.GRILMSystem.labManagement.asset.payment.entity.Enum.PaymentStatus;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.SampleReceiving;
import lk.GRILMSystem.labManagement.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment extends AuditEntity {

    @ManyToOne
    private SampleReceiving sampleReceiving;

    private double amount;

    private PaymentStatus paymentStatus;
}
