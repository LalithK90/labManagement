package lk.GRILMSystem.labManagement.asset.compound.entity.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SpecificationName {

    SG("Specific Gravity"),
    HARDNESS("Hardness"),
    VISCOSITY("Viscosity"),
    ML("ML"),
    MH("MH"),
    TS2("Ts2"),
    T90("T90"),
    TENST("Tensile Strength"),
    MODULUS("Modulus"),
    EAB("E.A.B"),
    TEARST("Tear Strength"),
    ABRASION("Abrasion"),
    CBD("Dispersion") ;

    private final String SpecificationName;
}
