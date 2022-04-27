package ir.caspco.versatile.jms.client.common.enums.hampa;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public enum HampaSettlementType {

    BUY("01"),
    CHARGE("02");

    private final String value;

    HampaSettlementType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static HampaSettlementType fromValue(String v) {
        for (HampaSettlementType pt : HampaSettlementType.values()) {
            if (pt.value.equals(v)) {
                return pt;
            }
        }
        throw new IllegalArgumentException(v);
    }
}