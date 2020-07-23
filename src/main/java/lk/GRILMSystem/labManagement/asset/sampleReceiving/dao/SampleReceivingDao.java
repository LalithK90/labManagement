package lk.GRILMSystem.labManagement.asset.sampleReceiving.dao;

import lk.GRILMSystem.labManagement.asset.sampleReceiving.entity.SampleReceiving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleReceivingDao extends JpaRepository<SampleReceiving, Integer> {
}
