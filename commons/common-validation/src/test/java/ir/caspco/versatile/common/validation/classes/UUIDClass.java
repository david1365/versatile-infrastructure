package ir.caspco.versatile.common.validation.classes;

import ir.caspco.versatile.common.validation.annotations.IsValidUUID;
import org.springframework.stereotype.Component;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
public class UUIDClass {
    public void UUID(@IsValidUUID String UUID) {

    }
}
