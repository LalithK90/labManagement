package lk.GRILMSystem.labManagement.asset.LabTestParameter.dao;


import lk.GRILMSystem.labManagement.asset.LabTestParameter.entity.LabTestParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabTestParameterDao extends JpaRepository<LabTestParameter, Integer> {
}
