package lk.GRILMSystem.labManagement.asset.discountRatio.dao;

import lk.GRILMSystem.labManagement.asset.discountRatio.entity.DiscountRatio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface DiscountRatioDao extends JpaRepository< DiscountRatio, Integer > {
}
