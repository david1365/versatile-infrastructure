package ir.caspco.versatile.context.enums;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public enum OAuth2GrantTypes {

    AUTHORIZATION_CODE("authorization_code"),
    IMPLICIT("implicit"),
    PASSWORD("password"),
    CLIENT_CREDENTIALS("client_credentials"),
    REFRESH_TOKEN("refresh_token");


    private final String value;

    OAuth2GrantTypes(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}