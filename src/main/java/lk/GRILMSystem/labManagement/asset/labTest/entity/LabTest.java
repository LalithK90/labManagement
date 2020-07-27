package lk.GRILMSystem.labManagement.asset.labTest.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.GRILMSystem.labManagement.asset.LabTestParameter.entity.LabTestParameter;
import lk.GRILMSystem.labManagement.asset.labTest.entity.Enum.CompoundPropertyName;
import lk.GRILMSystem.labManagement.asset.labTest.entity.Enum.LabTestStatus;
import lk.GRILMSystem.labManagement.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("LabTest")
public class LabTest extends AuditEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private CompoundPropertyName compoundPropertyName;

    @Enumerated(EnumType.STRING)
    private LabTestStatus LabTestStatus;

    @ManyToMany
    @JoinTable(name = "lab_test_lab_test_parameter",
            joinColumns = @JoinColumn(name = "lab_test_id"),
            inverseJoinColumns = @JoinColumn(name = "lab_test_parameter_id"))
    private List<LabTestParameter> labTestParameters;
}
