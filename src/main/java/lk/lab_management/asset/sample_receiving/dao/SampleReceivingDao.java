package lk.lab_management.asset.sample_receiving.dao;

import lk.lab_management.asset.sample_receiving.entity.SampleReceiving;
import lk.lab_management.asset.sample_receiving.entity.enums.SampleReceivingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SampleReceivingDao extends JpaRepository< SampleReceiving, Integer > {
SampleReceiving findFirstByOrderByIdDesc();
  List<SampleReceiving> findBySampleReceivingStatus(SampleReceivingStatus sampleReceivingStatus);
}
