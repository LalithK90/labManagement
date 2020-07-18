package lk.GRILMSystem.labManagement.asset.labTest.dao;


import lk.GRILMSystem.labManagement.asset.labTest.entity.LabTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabTestDao extends JpaRepository<LabTest, Integer> {
}
