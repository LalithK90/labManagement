package lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Acceptability {

    ACCEPT("Accept"),
    REJECT("Reject"),
    PENDING("Pending");

    private final String acceptability;
}
