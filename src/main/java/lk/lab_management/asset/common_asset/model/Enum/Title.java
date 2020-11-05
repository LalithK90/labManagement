package lk.lab_management.asset.common_asset.model.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Title {
    MR("Mr. "),
    MRS("Mrs. "),
    MISS("Miss. "),
    MS("Ms. "),
    DR("Dr. "),
    DRMRS("Dr(Mrs). "),
    PRO("Prof. ");

    private final String title;
}
