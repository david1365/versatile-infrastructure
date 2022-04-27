package ir.caspco.versatile.jms.client.common.enums.thirdparty;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public enum ThirdPartyGroupType {

    BUY_RECHARGE("1"),
    CHARITY("2");

    private final String value;

    ThirdPartyGroupType(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
