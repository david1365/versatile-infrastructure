package ir.caspco.versatile.gateway.smart.filters;

import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@Builder
public class MethodInfoModel {

    private Map<String, Method> request;

    private Map<String, List<Method>> requestBody;

    private Map<String, Method> response;

    private Map<String, List<Method>> responseBody;

}
