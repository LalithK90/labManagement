package lk.GRILMSystem.labManagement.asset.compound.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.GRILMSystem.labManagement.asset.compound.entity.Enum.LabTestName;
import lk.GRILMSystem.labManagement.asset.compound.entity.Enum.SpecificationName;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.SampleReceivingLabTest;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.SampleReceivingLabTestResult;
import lk.GRILMSystem.labManagement.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("Specification")
public class Specification extends AuditEntity {

    @NotNull
    private String min;

    private String max;


    @Enumerated(EnumType.STRING)
    private LabTestName labTestName;

    @Enumerated(EnumType.STRING)
    private SpecificationName specificationName;

    @ManyToOne
    private Compound compound;

    @OneToMany(mappedBy = "specification")
    public List<SampleReceivingLabTestResult> sampleReceivingLabTestResults;

}
