package lk.GRILMSystem.labManagement.asset.sampleReceiving.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.GRILMSystem.labManagement.asset.compound.entity.Specification;
import lk.GRILMSystem.labManagement.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("SampleReceivingLabTestResult")
public class SampleReceivingLabTestResult extends AuditEntity {

    private float result;

    @ManyToOne
    private SampleReceivingLabTest sampleReceivingLabTest;

    @ManyToOne(fetch = FetchType.EAGER)
    private Specification specification;


}
