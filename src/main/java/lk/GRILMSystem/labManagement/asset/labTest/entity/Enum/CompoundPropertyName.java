package lk.GRILMSystem.labManagement.asset.labTest.entity.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CompoundPropertyName {
    PHEO("RHEOMETRIC PROPERTIES (MDR02)"),
    PYSIC("PHYSICAL PROPERTIES"),
    VIS("VISCOSITY TEST");

    private final String compoundPropertyName;

}
