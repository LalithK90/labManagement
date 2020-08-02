package lk.GRILMSystem.labManagement.asset.labTest.entity.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LabTestStatus {
    ACT("Active"),
    CL("Close");

    private final String labTestStatus;

}
