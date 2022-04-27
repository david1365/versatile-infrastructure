package ir.caspco.versatile.gateway.smart.filters;

import ir.caspco.versatile.common.util.EnvironmentUtil;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ServerWebExchange;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ir.caspco.versatile.common.util.reflect.ReflectUtil.getMethod;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        EnvironmentUtil.class,
        DefaultMethodInfo.class
})
@TestPropertySource("classpath:MutationInformationBodyTest/config~development.properties")
class MethodInfoTest {

    @Autowired
    private MethodInfo methodInfo;


    @Test
    void mutation() {
        MutatedClass mutatedClass = new MutatedClass();

        Map<String, List<Method>> expectedRequestBody = expectedRequestBody(mutatedClass.getClass(), "/path");

        MethodInfoModel actual = methodInfo.Collect("/wallet/pec/path", mutatedClass.getClass().getMethods());

        String path = "/wallet/pec/path/Customer/AddCustomerTest";
        List<Method> addCustomerMethods = actual.getRequestBody().get(path).stream()
                .sorted((o1, o2) -> o2.getName().compareTo(o1.getName()))
                .collect(Collectors.toList());

        actual.getRequestBody().put(path, addCustomerMethods);

        assertEquals(expectedRequestBody, actual.getRequestBody());

        assertEquals(expectedRequest(mutatedClass.getClass(), "/path"), actual.getRequest());

    }


    @Test
    void responseMutation() {
        ResponseMutatedClass mutatedClass = new ResponseMutatedClass();

        MethodInfoModel actual = methodInfo.Collect("/wallet/pec/path", mutatedClass.getClass().getMethods());

        assertEquals(expectedResponseBody(mutatedClass.getClass(), "/path"), actual.getResponseBody());
    }

    @Test
    void mutationWithOutPath() {
        WithOutPathClass mutatedClass = new WithOutPathClass();

        Map<String, List<Method>> expectedRequestBody = expectedRequestBody(mutatedClass.getClass(), "");

        MethodInfoModel actual = methodInfo.Collect("/wallet/pec", mutatedClass.getClass().getMethods());

        String path = "/wallet/pec/Customer/AddCustomerTest";
        List<Method> addCustomerMethods = actual.getRequestBody().get(path).stream()
                .sorted((o1, o2) -> o2.getName().compareTo(o1.getName()))
                .collect(Collectors.toList());

        actual.getRequestBody().put(path, addCustomerMethods);

        assertEquals(expectedRequestBody, actual.getRequestBody());

        assertEquals(expectedRequest(mutatedClass.getClass(), ""), actual.getRequest());
    }

    private Map<String, List<Method>> expectedRequestBody(Class<?> aClass, final String path) {

        List<Method> injectForAll = new ArrayList<>();
        addMethod(aClass, injectForAll, "injectForAll");

        List<Method> injectForAddCustomer = new ArrayList<>();
        addMethod(aClass, injectForAddCustomer, "injectForShared");
        addMethod(aClass, injectForAddCustomer, "injectForAddCustomer");

        List<Method> injectForGetCustomerTransaction = new ArrayList<>();
        addMethod(aClass, injectForGetCustomerTransaction, "injectForShared");

        List<Method> injectForDeChargeWallet = new ArrayList<>();
        addMethod(aClass, injectForDeChargeWallet, "injectForShared");

        Map<String, List<Method>> expectedMethodMap = new HashMap<>();
        expectedMethodMap.put("*", injectForAll);

        expectedMethodMap.put("/wallet/pec" + path + "/Customer/AddCustomerTest", injectForAddCustomer);

        expectedMethodMap.put("/wallet/pec" + path + "/Report/GetCustomerTransactionTest", injectForGetCustomerTransaction);

        expectedMethodMap.put("/wallet/pec" + path + "/FinancialOperation/DeChargeWalletTest", injectForGetCustomerTransaction);

        return expectedMethodMap;
    }

    private void addMethod(Class<?> aClass, List<Method> aMethods, String methodName) {
        aMethods.add(getMethod(aClass, methodName, ServerWebExchange.class, Map.class));
    }

    private Map<String, Method> expectedRequest(Class<?> aClass, final String path) {

        Map<String, Method> expectedMethodMap = new HashMap<>();
        expectedMethodMap.put("/wallet/pec" + path + "/Customer/AddCustomerTest", getMethod(aClass, "mutatePath", ServerWebExchange.class));

        return expectedMethodMap;
    }

    private Map<String, List<Method>> expectedResponseBody(Class<?> aClass, final String path) {

        List<Method> injectForResponseBody = new ArrayList<>();
        addMethod(aClass, injectForResponseBody, "injectForAddCustomerResponse");

        Map<String, List<Method>> expectedMethodMap = new HashMap<>();
        expectedMethodMap.put("/wallet/pec" + path + "/Customer/AddCustomerTest", injectForResponseBody);

        return expectedMethodMap;

    }

}