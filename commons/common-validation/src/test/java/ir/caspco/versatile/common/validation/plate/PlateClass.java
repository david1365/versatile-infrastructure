package ir.caspco.versatile.common.validation.plate;

import ir.caspco.versatile.common.validation.annotations.IsValidPlate;
import org.springframework.stereotype.Component;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
public class PlateClass {
    public void plate(@IsValidPlate Integer plate) {

    }
}
