package lk.lab_management.asset.sample_receiving.dao;

import lk.lab_management.asset.compound.entity.Enum.LabTestName;
import lk.lab_management.asset.sample_receiving.entity.Enum.Acceptability;
import lk.lab_management.asset.sample_receiving.entity.Enum.SampleReceivingLabTestStatus;
import lk.lab_management.asset.sample_receiving.entity.SampleReceivingLabTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SampleReceivingLabTestDao extends JpaRepository<SampleReceivingLabTest,Integer> {

    List<SampleReceivingLabTest> findByLabTestNameAndAcceptability(LabTestName labTestName, Acceptability acceptability);

    List<SampleReceivingLabTest> findByLabTestNameAndAcceptabilityAndSampleReceivingLabTestStatus(LabTestName labTestName, Acceptability acceptability, SampleReceivingLabTestStatus sampleReceivingLabTestStatus);
}
