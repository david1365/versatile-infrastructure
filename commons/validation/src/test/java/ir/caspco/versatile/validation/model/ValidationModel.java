package ir.caspco.versatile.validation.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@Builder
public class ValidationModel {

    @NotNull
    private String name;

    @NotNull(message = "ir.caspco.family")
    private String family;
}
