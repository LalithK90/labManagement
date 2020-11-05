package lk.lab_management.asset.compound.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LabTestName {

    PYSIC("PHYSICAL PROPERTIES TEST"),
    RHEO("RHEOMETER TEST"),
    VIS("VISCOSITY TEST");

    private final String labTestName;

}
