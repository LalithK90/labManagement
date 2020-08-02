package lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SampleReceivingLabTestStatus {
    NOTRESULTENTER("Result not Enter"),
    RESULTENTER("Result Enter"),
    EMAILSENT("Email Sent"),
    REPORTGENERATE("Report Generate");

    private final String sampleReceivingLabTestStatus;
}
