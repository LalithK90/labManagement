package lk.GRILMSystem.labManagement.asset.compound.dao;

import lk.GRILMSystem.labManagement.asset.compound.entity.Compound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompoundDao extends JpaRepository<Compound, Integer> {
}
