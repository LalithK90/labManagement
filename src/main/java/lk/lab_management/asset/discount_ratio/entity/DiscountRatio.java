package lk.lab_management.asset.discount_ratio.entity;

import lk.lab_management.asset.common_asset.model.enums.LiveDead;
import lk.lab_management.asset.discount_ratio.entity.enums.DiscountRatioStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DiscountRatio {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;


    private String name;


    @Column( nullable = false)
    private int amount;


    private DiscountRatioStatus discountRatioStatus;

    @Enumerated( EnumType.STRING )
    private LiveDead liveDead;

/*    @OneToMany
    @JoinColumn(name = "discount_ratio_id")
    private List<Invoice> invoices = new ArrayList<>();*/


}
