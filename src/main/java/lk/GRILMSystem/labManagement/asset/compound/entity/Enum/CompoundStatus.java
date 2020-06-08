package lk.GRILMSystem.labManagement.asset.compound.entity.Enum;

public enum CompoundStatus {

    INTEST("In the testing process"),
    RELEASED("Released"),
    APPROVED("Approved"),
    DISPOSED("Disposed");

    private final String compoundStatus;

    CompoundStatus(String compoundStatus){
        this.compoundStatus = compoundStatus;
    }

    public String getCompoundStatus() {
        return compoundStatus;
    }
}
