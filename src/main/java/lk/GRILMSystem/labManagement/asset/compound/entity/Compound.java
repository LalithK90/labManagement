package lk.GRILMSystem.labManagement.asset.compound.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.GRILMSystem.labManagement.asset.compound.entity.Enum.CompoundStatus;
import lk.GRILMSystem.labManagement.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonFilter("Compound")
public class Compound extends AuditEntity {

    private String compoundCode;
    private LocalDate receivedDate;
    private LocalDate releaseDate;
    private CompoundStatus compoundStatus;

}
