package ir.caspco.versatile.common.validation.mobile;

import ir.caspco.versatile.common.validation.annotations.IsValidMobileNumber;
import org.springframework.stereotype.Component;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
public class MobileClass {
    public void setMobile(@IsValidMobileNumber String mobileNumber) {
    }
}
