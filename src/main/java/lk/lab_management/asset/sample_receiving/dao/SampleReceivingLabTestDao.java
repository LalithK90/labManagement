package lk.lab_management.asset.sample_receiving.dao;

import lk.lab_management.asset.compound.entity.enums.LabTestName;
import lk.lab_management.asset.sample_receiving.entity.enums.Acceptability;
import lk.lab_management.asset.sample_receiving.entity.enums.SampleReceivingLabTestStatus;
import lk.lab_management.asset.sample_receiving.entity.SampleReceivingLabTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SampleReceivingLabTestDao extends JpaRepository<SampleReceivingLabTest,Integer> {

    List<SampleReceivingLabTest> findByLabTestNameAndAcceptability(LabTestName labTestName, Acceptability acceptability);

    List<SampleReceivingLabTest> findByLabTestNameAndAcceptabilityAndSampleReceivingLabTestStatus(LabTestName labTestName, Acceptability acceptability, SampleReceivingLabTestStatus sampleReceivingLabTestStatus);

  List< SampleReceivingLabTest> findByCreatedAtIsBetween(LocalDateTime form, LocalDateTime to);

}
