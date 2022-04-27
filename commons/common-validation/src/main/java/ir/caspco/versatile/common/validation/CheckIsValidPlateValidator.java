package ir.caspco.versatile.common.validation;

import ir.caspco.versatile.common.validation.annotations.IsValidPlate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * @author Davood Akbari - 1399
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public class CheckIsValidPlateValidator implements ConstraintValidator<IsValidPlate, Integer> {

    private static final int LENGTH = 9;
    private static final int[] ALPHABET = {1, 2, 3, 4, 6, 7, 10, 15, 17, 19, 21, 24, 27, 28, 29, 30, 31, 32};


    @Override
    public boolean isValid(Integer plate, ConstraintValidatorContext constraintValidatorContext) {

        if (plate == null) {
            return true;
        }

        final String strPlate = plate.toString();
        final int strPlateLength = strPlate.length();

        if (strPlateLength != LENGTH) {
            return false;
        }

        int city = Integer.parseInt(strPlate.substring(strPlateLength - 2, strPlateLength));
        if (city < 10) {
            return false;
        }

        int alphabet = Integer.parseInt(strPlate.substring(2, 4));

        return Arrays.stream(ALPHABET).anyMatch(value -> alphabet == value);

    }

}
