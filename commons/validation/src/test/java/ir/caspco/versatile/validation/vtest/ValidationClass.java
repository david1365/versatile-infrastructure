package ir.caspco.versatile.validation.vtest;

import ir.caspco.versatile.validation.model.ValidationModel;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
public class ValidationClass {

    public @NotNull String m1(@NotNull @Valid ValidationModel validationModel) {
        return null;
    }
}
