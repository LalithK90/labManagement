package lk.GRILMSystem.labManagement.asset.physicalTest.entity;


import lk.GRILMSystem.labManagement.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhysicalTestParam extends AuditEntity {


    private double sgMin;
    private double sgMax;
    private double hardnessMin;
    private double hardnessMax;
    private double tenStMin;
    private String tenStUnit;
    private double modulusMin;
    private double modulusMax;
    private String modulusUnit;
    private double eabMin;
    private double eabMax;
    private double tearStMin;
    private double tearStMax;
    private String tearStUnit;
    private double abrasionMin;
    private double abrasionMax;
    private double cbdMin;
    private double cbdMax;




}
