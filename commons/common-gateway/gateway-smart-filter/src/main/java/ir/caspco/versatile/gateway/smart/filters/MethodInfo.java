package ir.caspco.versatile.gateway.smart.filters;

import ir.caspco.versatile.gateway.smart.filters.MethodInfoModel;

import java.lang.reflect.Method;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

interface MethodInfo {

    MethodInfoModel Collect(String prefix, Method[] methods);

}
