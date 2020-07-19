package lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SampleReceivingStatus {
    ACTIVE("Active"),
    DISABLED("Disabled");

    private final String sampleReceivingStatus;
}
