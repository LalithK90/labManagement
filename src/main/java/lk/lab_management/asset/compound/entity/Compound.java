package lk.lab_management.asset.compound.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.lab_management.asset.common_asset.model.enums.LiveDead;
import lk.lab_management.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("Compound")
public class Compound extends AuditEntity {

    private String code, name;

    @Column( nullable = false, precision = 10, scale = 2 )
    private BigDecimal price;

    @OneToMany(mappedBy = "compound")
    private List<Specification> specifications;

    @Enumerated( EnumType.STRING )
    private LiveDead liveDead;

}
