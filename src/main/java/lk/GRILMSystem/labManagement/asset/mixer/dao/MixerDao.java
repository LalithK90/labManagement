package lk.GRILMSystem.labManagement.asset.mixer.dao;

import lk.GRILMSystem.labManagement.asset.mixer.entity.Mixer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MixerDao extends JpaRepository<Mixer, Integer> {
}
