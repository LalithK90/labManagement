package lk.lab_management.asset.employee.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Designation {
    SQA("Senior Quality Assurance Manager"),
    QA("Quality Assurance Manager"),
    TA("Technical Manager"),
    LA("Lab Assistance"),
    CA("Cashier"),
    HRM("HR Manager"),
    ACC("Accountant");

    private final String designation;
}
