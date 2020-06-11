package lk.GRILMSystem.labManagement.asset.compound.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.GRILMSystem.labManagement.asset.compound.entity.Enum.CompoundPropertyName;
import lk.GRILMSystem.labManagement.asset.compound.entity.Enum.SpecificationName;
import lk.GRILMSystem.labManagement.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("Specification")
public class Specification extends AuditEntity {

    @NotNull
    private String min;
    private String max;


    @Enumerated(EnumType.STRING)
    private CompoundPropertyName compoundPropertyName;

    @Enumerated(EnumType.STRING)
    private SpecificationName specificationName;

    @ManyToOne
    private Compound compound;

}
