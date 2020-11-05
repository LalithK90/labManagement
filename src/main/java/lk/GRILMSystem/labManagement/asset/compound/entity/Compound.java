package lk.GRILMSystem.labManagement.asset.compound.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.GRILMSystem.labManagement.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("Compound")
public class Compound extends AuditEntity {

    private String code;

    private String name;

    private double price;

    @OneToMany(mappedBy = "compound",cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Specification> specifications;

}
