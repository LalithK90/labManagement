package lk.lab_management.asset.compound.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.lab_management.asset.compound.entity.Enum.LabTestName;
import lk.lab_management.asset.compound.entity.Enum.SpecificationName;
import lk.lab_management.asset.sample_receiving.entity.SampleReceivingLabTestResult;
import lk.lab_management.util.audit.AuditEntity;
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
