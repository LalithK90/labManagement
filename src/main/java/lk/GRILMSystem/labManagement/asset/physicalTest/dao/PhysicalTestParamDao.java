package lk.GRILMSystem.labManagement.asset.physicalTest.dao;

import lk.GRILMSystem.labManagement.asset.physicalTest.entity.PhysicalTestParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalTestParamDao extends JpaRepository<PhysicalTestParam, Integer> {
}
