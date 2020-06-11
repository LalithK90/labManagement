package lk.GRILMSystem.labManagement.asset.compound.entity.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CompoundPropertyName {
    PHEO("RHEOMETRIC PROPERTIES (MDR02)"),
    PYSIC("PHYSICAL PROPERTIES"),
    OTH("Other");

    private final String compoundPropertyName;

}
