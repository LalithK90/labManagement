package lk.GRILMSystem.labManagement.asset.compound.entity.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CompoundLabTestStatus {

    INTEST("In the testing process"),
    RELEASED("Released"),
    APPROVED("Approved"),
    DISPOSED("Disposed");

    private final String status;

}
