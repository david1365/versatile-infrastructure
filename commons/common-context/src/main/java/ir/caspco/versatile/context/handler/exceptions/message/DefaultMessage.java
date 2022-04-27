package ir.caspco.versatile.context.handler.exceptions.message;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@SuperBuilder
@NoArgsConstructor
public class DefaultMessage {
    private boolean error;
    private int resultNumber;
    private Object data;
}
