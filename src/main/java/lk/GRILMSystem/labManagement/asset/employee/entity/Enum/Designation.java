package lk.GRILMSystem.labManagement.asset.employee.entity.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Designation {

    LA("Laboratory Assistant"),
    SQA("Senior Quality Assurance Manager"),
    QA("Quality Assurance Manager"),
    CASH("Cashier");

    private final String designation;
}
