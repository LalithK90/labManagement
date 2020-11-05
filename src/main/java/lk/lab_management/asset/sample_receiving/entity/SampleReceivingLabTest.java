package lk.lab_management.asset.sample_receiving.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.lab_management.asset.compound.entity.Enum.LabTestName;
import lk.lab_management.asset.sample_receiving.entity.Enum.Acceptability;
import lk.lab_management.asset.sample_receiving.entity.Enum.SampleReceivingLabTestStatus;
import lk.lab_management.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("SampleReceivingLabTest")
public class SampleReceivingLabTest extends AuditEntity {

    private String remarks;

    @Enumerated(EnumType.STRING)
    private SampleReceivingLabTestStatus sampleReceivingLabTestStatus;

    @Enumerated(EnumType.STRING)
    private Acceptability acceptability;

    @Enumerated(EnumType.STRING)
    private LabTestName labTestName;

    @ManyToOne(cascade = CascadeType.ALL)
    private SampleReceiving sampleReceiving;

    @OneToMany(mappedBy = "sampleReceivingLabTest", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<SampleReceivingLabTestResult> sampleReceivingLabTestResults;
}
