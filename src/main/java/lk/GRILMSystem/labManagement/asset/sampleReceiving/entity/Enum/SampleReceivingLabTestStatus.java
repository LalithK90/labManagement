package lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SampleReceivingLabTestStatus {

    NOTRESULTENTER("Result not Enter"),
    RESULTENTER("Result Enter");

    private final String sampleReceivingLabTestStatus;
}
