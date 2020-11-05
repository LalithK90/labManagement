package lk.lab_management.asset.sample_receiving.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SampleReceivingStatus {
    ACTIVE("Active"),
    DISABLED("Disabled"),
    PAID("Paid"),
    PPAID("Partially Paid");

    private final String sampleReceivingStatus;
}
