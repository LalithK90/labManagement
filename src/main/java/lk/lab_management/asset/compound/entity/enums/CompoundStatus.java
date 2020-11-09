package lk.lab_management.asset.compound.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CompoundStatus {

    INTEST("In the testing process"),
    RELEASED("Released"),
    APPROVED("Approved"),
    DISPOSED("Disposed");

    private final String status;

}
