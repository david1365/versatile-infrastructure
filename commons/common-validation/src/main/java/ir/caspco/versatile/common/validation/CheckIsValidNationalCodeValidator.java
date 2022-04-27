package ir.caspco.versatile.common.validation;

import ir.caspco.versatile.common.validation.annotations.IsValidNationalCode;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Davood Akbari - 1399
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public class CheckIsValidNationalCodeValidator implements ConstraintValidator<IsValidNationalCode, String> {

    @Override
    public boolean isValid(String nationalCode, ConstraintValidatorContext constraintValidatorContext) {

        if (nationalCode == null) {
            return true;
        }

        if (!StringUtils.hasText(nationalCode)) {
            return false;
        }

        if (nationalCode.length() != 10) {
            return false;
        }

        if (
                nationalCode.equals("1111111111") ||
                        nationalCode.equals("2222222222") ||
                        nationalCode.equals("3333333333") ||
                        nationalCode.equals("4444444444") ||
                        nationalCode.equals("5555555555") ||
                        nationalCode.equals("6666666666") ||
                        nationalCode.equals("7777777777") ||
                        nationalCode.equals("8888888888") ||
                        nationalCode.equals("9999999999") ||
                        nationalCode.equals("0000000000")
        ) {
            return false;
        }

        char[] digitChars = nationalCode.toCharArray();
        int[] digits = new int[digitChars.length];
        int index = 0;
        for (char digitChar : digitChars) {

            if (!Character.isDigit(digitChar)) {
                return false;
            }

            digits[index++] = Integer.parseInt(String.valueOf(digitChar));

        }

        int checkDigit = digits[9];
        int sumOfDigits = 0;
        for (int i = 0; i < 9; i++) {
            sumOfDigits += digits[i] * (10 - i);
        }

        int calculatedCheckDigit = sumOfDigits % 11;

        return calculatedCheckDigit == 0 && checkDigit == calculatedCheckDigit ||
                calculatedCheckDigit == 1 && checkDigit == 1 ||
                calculatedCheckDigit > 1 && (checkDigit == 11 - calculatedCheckDigit);

    }

}
