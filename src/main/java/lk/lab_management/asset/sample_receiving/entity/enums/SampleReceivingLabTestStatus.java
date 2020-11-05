package lk.lab_management.asset.sample_receiving.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SampleReceivingLabTestStatus {

    NOTRESULTENTER("Result not Enter"),
    RESULTENTER("Result Enter");

    private final String sampleReceivingLabTestStatus;
}
