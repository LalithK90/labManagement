package lk.GRILMSystem.labManagement.asset.physicalTest.dao;

import lk.GRILMSystem.labManagement.asset.physicalTest.entity.PhysicalTest;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhysicalTestDao extends JpaRepository<PhysicalTest, Integer> {
}
