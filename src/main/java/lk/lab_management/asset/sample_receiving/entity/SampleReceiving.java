package lk.lab_management.asset.sample_receiving.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.lab_management.asset.compound.entity.Compound;
import lk.lab_management.asset.customer.entity.Customer;
import lk.lab_management.asset.discount_ratio.entity.DiscountRatio;
import lk.lab_management.asset.sample_receiving.entity.enums.SampleReceivingStatus;
import lk.lab_management.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("SampleReceiving")
public class SampleReceiving extends AuditEntity {

    private String number;

    private String sampleCode;

    private String batchNo;

    @Column( nullable = false, precision = 10, scale = 2 )
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private SampleReceivingStatus sampleReceivingStatus;

    @ManyToOne
    private Compound compound;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "sampleReceiving",cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<SampleReceivingLabTest> sampleReceivingLabTests;

    @ManyToOne
    private DiscountRatio discountRatio;



}
