package lk.lab_management.asset.sample_receiving.dao;

import lk.lab_management.asset.sample_receiving.entity.SampleReceiving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleReceivingDao extends JpaRepository<SampleReceiving, Integer> {
}
