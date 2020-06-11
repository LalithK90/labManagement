package lk.GRILMSystem.labManagement.asset.compound.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.GRILMSystem.labManagement.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lk.GRILMSystem.labManagement.asset.compound.entity.Specification;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
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
/*    private LocalDate receivedDate;
    private LocalDate releaseDate;*/

    @OneToMany(mappedBy = "compound",cascade = CascadeType.PERSIST)
    private List<Specification> specifications;
}
