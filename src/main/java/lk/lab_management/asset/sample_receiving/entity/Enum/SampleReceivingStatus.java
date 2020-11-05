package lk.lab_management.asset.sample_receiving.entity.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SampleReceivingStatus {
    ACTIVE("Active"),
    DISABLED("Disabled");

    private final String sampleReceivingStatus;
}
