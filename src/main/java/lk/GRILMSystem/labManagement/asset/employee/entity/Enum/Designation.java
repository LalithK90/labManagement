package lk.GRILMSystem.labManagement.asset.employee.entity.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Designation {
    SQA("Senior Quality Assurance Manager"),
    QA("Quality Assurance Manager"),
    TA("Technical Manager"),
    LA("Lab Assistance"),
    CA("Cashier");

    private final String designation;
}
