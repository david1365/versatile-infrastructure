package ir.caspco.versatile.common.util.reflect.cash;

import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@Builder
public class MethodInformation {
    private Object object;
    private Method method;
}
