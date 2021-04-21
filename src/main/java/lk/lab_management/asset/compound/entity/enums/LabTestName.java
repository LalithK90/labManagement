package lk.lab_management.asset.compound.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LabTestName {

    PYSIC("Physical Properties Test"),
    RHEO("Rheometer Test"),
    VIS("Viscosity Test");

    private final String labTestName;

}
