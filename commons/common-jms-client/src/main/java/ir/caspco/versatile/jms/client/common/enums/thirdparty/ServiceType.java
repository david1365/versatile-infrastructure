
package ir.caspco.versatile.jms.client.common.enums.thirdparty;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public enum ServiceType {

    NONE(0),
    MCI(1),
    RIGHTEL(2),
    IRANCELL_PREPAID_SIMCARD(3),
    IRANCELL_MAGICAL_PREPAID_SIMCARD(4),
    IRANCELL_POSTPAID_SIMCARD(5),
    IRANCELL_PREPAID_WIMAX(6),
    IRANCELL_POSTPAID_WIMAX(7),
    INTERNET_PACKAGE(8);


    private final int id;

    ServiceType(int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }

}
