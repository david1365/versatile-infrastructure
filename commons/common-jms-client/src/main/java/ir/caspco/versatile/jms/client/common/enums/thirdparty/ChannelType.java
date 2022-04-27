package ir.caspco.versatile.jms.client.common.enums.thirdparty;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public enum ChannelType {

    MOBILE_BANK("05"),
    PERSONAL_INTERNET_BANK("59"),
    CORPORATE_INTERNET_BANK("101");

    private final String value;

    ChannelType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
