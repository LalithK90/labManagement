package lk.lab_management.asset.customer.entity.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CustomerType {

    INTERNAL("Internal"),
    EXTERNAL("External");

    private final String customerType;
}
