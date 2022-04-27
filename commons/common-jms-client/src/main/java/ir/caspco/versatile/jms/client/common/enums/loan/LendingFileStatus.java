package ir.caspco.versatile.jms.client.common.enums.loan;

/**
 * @author Davood Akbari - 1399
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public enum LendingFileStatus {

    TEMPORARY("10"),
    REJECTED("20"),
    PRIMARY_APPROVAL("30"),
    INCOMPLETE_COLLATERAL("40"),
    INCOMPLETE_ADVANCE_PAY("42"),
    ACTIVE("50"),
    INCOMPLETE_GRANT("60"),
    COMPLETE_GRANT("70"),
    UN_DUE("90"),
    CURRENT_DUE("80"),
    PAST_DUE("100"),
    ARREARS("110"),
    REPAYMENT_SUSPICIOUS("120"),
    REVOKED("130"),
    NORMAL_SETTLEMENT("160"),
    MANDATORY_SETTLEMENT("140"),
    BEFORE_DUE_SETTLEMENT("150"),
    AFTER_DUE_SETTLEMENT("170"),
    UB_NORMAL_SETTLEMENT("180");

    private final String code;

    LendingFileStatus(final String code) {
        this.code = code;
    }

    public final String getCode() {
        return code;
    }
}
