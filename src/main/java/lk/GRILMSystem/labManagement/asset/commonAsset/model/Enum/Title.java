package lk.GRILMSystem.labManagement.asset.commonAsset.model.Enum;

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
    PRO("Prof. "),;

    private final String title;
}
