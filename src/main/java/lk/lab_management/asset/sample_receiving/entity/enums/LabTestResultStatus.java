package lk.lab_management.asset.sample_receiving.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LabTestResultStatus {

    PASS("Pass"),
    FAIL("Fail");

    private final String labTestResultStatus;
}
