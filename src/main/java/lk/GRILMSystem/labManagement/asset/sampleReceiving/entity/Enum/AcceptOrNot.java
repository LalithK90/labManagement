package lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AcceptOrNot {

    ACCEPT("Accept"),
    REJECT("Reject");

    private final String acceptOrNot;
}
