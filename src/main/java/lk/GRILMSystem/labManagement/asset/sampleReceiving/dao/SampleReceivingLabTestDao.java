package lk.GRILMSystem.labManagement.asset.sampleReceiving.dao;

import lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.SampleReceivingLabTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleReceivingLabTestDao extends JpaRepository<SampleReceivingLabTest,Integer> {
}
