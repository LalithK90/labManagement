package lk.GRILMSystem.labManagement.asset.mixer.entity;

import lk.GRILMSystem.labManagement.asset.compound.entity.Compound;
import lk.GRILMSystem.labManagement.asset.compound.entity.Enum.CompoundPropertyName;
import lk.GRILMSystem.labManagement.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.awt.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mixer extends AuditEntity {

    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private CompoundPropertyName compoundPropertyName;


    @ManyToOne
    private Compound compound;

}
