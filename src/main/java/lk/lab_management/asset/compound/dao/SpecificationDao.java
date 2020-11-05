package lk.lab_management.asset.compound.dao;

import lk.lab_management.asset.compound.entity.Compound;
import lk.lab_management.asset.compound.entity.enums.LabTestName;
import lk.lab_management.asset.compound.entity.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecificationDao extends JpaRepository<Specification, Integer> {
    List<Specification> findByLabTestNameAndCompound(LabTestName labTestName, Compound compound);
}
