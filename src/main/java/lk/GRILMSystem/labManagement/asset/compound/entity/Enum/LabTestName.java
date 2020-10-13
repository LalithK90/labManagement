package lk.GRILMSystem.labManagement.asset.compound.entity.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LabTestName {
    RHEO("RHEOMETER TEST"),
    PYSIC("PHYSICAL PROPERTIES TEST"),
    VIS("VISCOSITY TEST");

    private final String labTestName;

}
