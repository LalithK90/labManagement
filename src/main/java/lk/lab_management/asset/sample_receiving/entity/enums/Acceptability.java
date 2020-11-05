package lk.lab_management.asset.sample_receiving.entity.enums;

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
