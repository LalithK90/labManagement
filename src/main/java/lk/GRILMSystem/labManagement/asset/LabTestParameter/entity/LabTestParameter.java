package lk.GRILMSystem.labManagement.asset.LabTestParameter.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.GRILMSystem.labManagement.asset.compound.entity.Enum.SpecificationName;
import lk.GRILMSystem.labManagement.asset.labTest.entity.LabTest;
import lk.GRILMSystem.labManagement.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("LabTestParameter")
public class LabTestParameter extends AuditEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private SpecificationName specificationName;

    @ManyToMany(mappedBy = "labTestParameters")
    private List<LabTest> labTests;


}
