package lk.GRILMSystem.labManagement.asset.sampleReceiving.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.GRILMSystem.labManagement.asset.labTest.entity.LabTest;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.Enum.AcceptOrNot;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.Enum.SampleReceivingLabTestStatus;
import lk.GRILMSystem.labManagement.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("SampleReceivingLabTest")
public class SampleReceivingLabTest extends AuditEntity {

    private String remarks;

    @Enumerated(EnumType.STRING)
    private SampleReceivingLabTestStatus sampleReceivingLabTestStatus;

    @Enumerated(EnumType.STRING)
    private AcceptOrNot acceptOrNot;

    @ManyToOne
    private LabTest labTest;

    @ManyToOne
    private SampleReceiving sampleReceiving;
}
