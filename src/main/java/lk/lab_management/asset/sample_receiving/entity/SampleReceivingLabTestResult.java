package lk.lab_management.asset.sample_receiving.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.lab_management.asset.compound.entity.Specification;
import lk.lab_management.asset.sample_receiving.entity.enums.LabTestResultStatus;
import lk.lab_management.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter( "SampleReceivingLabTestResult" )
public class SampleReceivingLabTestResult extends AuditEntity {

  private float result;

  @Enumerated( EnumType.STRING )
  private LabTestResultStatus labTestResultStatus;

  @ManyToOne
  private SampleReceivingLabTest sampleReceivingLabTest;

  @ManyToOne( fetch = FetchType.EAGER )
  private Specification specification;


}
