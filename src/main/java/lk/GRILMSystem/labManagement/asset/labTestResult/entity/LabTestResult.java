package lk.GRILMSystem.labManagement.asset.labTestResult.entity;

import lk.GRILMSystem.labManagement.asset.compound.entity.Enum.SpecificationName;
import lk.GRILMSystem.labManagement.asset.compound.entity.Specification;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.SampleReceivingLabTest;
import lk.GRILMSystem.labManagement.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LabTestResult extends AuditEntity {


    private float value;

    @ManyToOne
    private SampleReceivingLabTest sampleReceivingLabTest;

}
