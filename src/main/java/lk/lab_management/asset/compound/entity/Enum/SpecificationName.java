package lk.lab_management.asset.compound.entity.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SpecificationName {

    SG("Specific Gravity"),
    HARDNESS("Hardness"),
    RES("Resilience"),
    TENST("Tensile Strength"),
    MODULUS("Modulus"),
    EAB("E.A.B"),
    TEARST("Tear Strength"),
    ABRASION("Abrasion"),
    CBD("Dispersion"),
    ML("ML"),
    MH("MH"),
    TS2("TS2"),
    T90("T90"),
    VISCOSITY("Viscosity");

    private final String specificationName;


}
