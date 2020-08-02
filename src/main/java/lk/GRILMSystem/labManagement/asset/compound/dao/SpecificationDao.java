package lk.GRILMSystem.labManagement.asset.compound.dao;

import lk.GRILMSystem.labManagement.asset.compound.entity.Compound;
import lk.GRILMSystem.labManagement.asset.compound.entity.Enum.LabTestName;
import lk.GRILMSystem.labManagement.asset.compound.entity.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecificationDao extends JpaRepository<Specification, Integer> {
    List<Specification> findByLabTestNameAndCompound(LabTestName labTestName, Compound compound);
}
