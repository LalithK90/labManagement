package lk.GRILMSystem.labManagement.asset.sampleReceiving.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.GRILMSystem.labManagement.asset.compound.entity.Compound;
import lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.Enum.SampleReceivingStatus;
import lk.GRILMSystem.labManagement.util.audit.AuditEntity;
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
@JsonFilter("SampleReceiving")
public class SampleReceiving extends AuditEntity {

    private String batchNo;

    @Enumerated(EnumType.STRING)
    private SampleReceivingStatus sampleReceivingStatus;

    @ManyToOne
    private Compound compound;

    @OneToMany
    private List<SampleReceivingLabTest> sampleReceivingLabTests;



}
