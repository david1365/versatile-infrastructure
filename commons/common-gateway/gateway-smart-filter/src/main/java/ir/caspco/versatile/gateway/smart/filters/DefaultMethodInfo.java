package ir.caspco.versatile.gateway.smart.filters;


import ir.caspco.versatile.common.util.EnvironmentUtil;

import ir.caspco.versatile.gateway.smart.filters.annotations.Request;
import ir.caspco.versatile.gateway.smart.filters.annotations.RequestBody;
import ir.caspco.versatile.gateway.smart.filters.annotations.Response;
import ir.caspco.versatile.gateway.smart.filters.annotations.ResponseBody;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static ir.caspco.versatile.gateway.smart.filters.annotations.Filter.ALL;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
public class DefaultMethodInfo implements MethodInfo {

    private final EnvironmentUtil environmentUtil;


    public DefaultMethodInfo(EnvironmentUtil environmentUtil) {
        this.environmentUtil = environmentUtil;
    }


    @Override
    public MethodInfoModel Collect(String prefix, Method[] methods) {

        Map<String, Method> requestMap = new ConcurrentHashMap<>();
        Map<String, Method> responseMap = new ConcurrentHashMap<>();

        Map<String, List<Method>> requestBodyMap = new ConcurrentHashMap<>();
        Map<String, List<Method>> responseBodyMap = new ConcurrentHashMap<>();


        Arrays.stream(methods).forEach(method -> {

            if (method.isAnnotationPresent(Request.class)) {

                Request request = method.getAnnotation(Request.class);

                putToMethodMap(requestMap, prefix, request.value(), method);


            } else if (method.isAnnotationPresent(RequestBody.class)) {

                RequestBody requestBody = method.getAnnotation(RequestBody.class);

                this.putToListMethodMap(requestBodyMap, prefix, requestBody.value(), method);

            } else if (method.isAnnotationPresent(Response.class)) {

                Response response = method.getAnnotation(Response.class);

                putToMethodMap(responseMap, prefix, response.value(), method);

            } else if (method.isAnnotationPresent(ResponseBody.class)) {

                ResponseBody responseBody = method.getAnnotation(ResponseBody.class);

                this.putToListMethodMap(responseBodyMap, prefix, responseBody.value(), method);

            }

        });

        return MethodInfoModel.builder()
                .request(requestMap)
                .requestBody(requestBodyMap)
                .response(responseMap)
                .responseBody(responseBodyMap)
                .build();

    }

    private void putToMethodMap(Map<String, Method> methodMap, String prefix, String[] keys, Method method) {

        Arrays.stream(keys).forEach(key -> {

            String tmpKey = getKey(prefix, key);

            methodMap.put(tmpKey, method);

        });

    }

    private String getKey(String prefix, String key) {

        return ALL.equals(key) ? ALL : prefix + environmentUtil.getProperty(key);

    }

    private void putToListMethodMap(Map<String, List<Method>> listMap, String prefix, String[] keys, Method method) {

        Arrays.stream(keys).forEach(key -> {
            String tmpKey = getKey(prefix, key);

            List<Method> methodList = listMap.get(tmpKey);

            if (methodList == null) {
                methodList = new ArrayList<>();
            }

            methodList.add(method);

            listMap.put(tmpKey, methodList);

        });

    }

}
