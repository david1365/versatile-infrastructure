package ir.caspco.versatile.gateway.smart.filters;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import ir.caspco.versatile.common.util.MyProperties;
import ir.caspco.versatile.common.util.Shift;
import ir.caspco.versatile.common.util.reflect.ReflectUtil;
import ir.caspco.versatile.gateway.smart.filters.annotations.AuxiliaryBody;
import ir.caspco.versatile.gateway.smart.filters.SmartUtil;
import ir.caspco.versatile.gateway.smart.filters.cash.CashBuilder;
import ir.caspco.versatile.gateway.smart.filters.cash.DCash;
import ir.caspco.versatile.gateway.smart.filters.cash.NumberOfParametersException;
import ir.caspco.versatile.gateway.smart.filters.util.HeaderUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.codec.multipart.Part;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.security.Principal;
import java.time.Instant;
import java.util.Map;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        CashBuilder.class,
        HeaderUtil.class,
        MyProperties.class
})
class SmartUtilTest {

    DataBuffer integerValueBuffer;
    DataBuffer urlValueBuffer;

    URL urlValue;
    Integer integerValue;

    @Autowired
    CashBuilder cashBuilder;

    @BeforeEach
    @SneakyThrows
    void setUp() {

        integerValueBuffer = Shift.just(23).toDataBuffer();
        urlValueBuffer = Shift.just(new URL("http://www.davood.com/")).toDataBuffer();

        urlValue = Shift.just(urlValueBuffer).toShift(URL.class).toObject();
        integerValue = Shift.just(integerValueBuffer).toShift(Integer.class).toObject();

    }

    @Test
    @SneakyThrows
    void placement() {

        Object[] expectedArgs = new Object[3];

        ServerWebExchange exchange = getExchange();

        DCash<?> cash = cashBuilder.build(exchange);

        expectedArgs[0] = urlValue;
        expectedArgs[1] = integerValue;
        expectedArgs[2] = cash;

        Method method = ReflectUtil.getMethod(this, "testMethodWithDCash", URL.class, Integer.class, DCash.class);


        Object[] actualArgs = SmartUtil.placement(method, exchange, cash, integerValueBuffer, urlValueBuffer);

        assertArrayEquals(expectedArgs, actualArgs);

    }

    public void testMethodWithDCash(@AuxiliaryBody URL url, Integer integer, DCash<?> dCash) {

    }


    @Test
    @SneakyThrows
    void testPlacementNotSameType() {

        ServerWebExchange exchange = getExchange();

        Method method = ReflectUtil.getMethod(this, "testMethod", URL.class, Integer.class);

        try {

            SmartUtil.placement(method, exchange, cashBuilder.build(exchange), urlValueBuffer);

            fail();

        } catch (Exception e) {
            assertTrue(e instanceof InvalidFormatException);
        }

    }

    public void testMethod(@AuxiliaryBody URL url, Integer integer) {

    }

    @Test
    @SneakyThrows
    void testPlacement() {

        Object[] expectedArgs = new Object[2];
        expectedArgs[0] = urlValue;

        ServerWebExchange exchange = getExchange();

        Method method = ReflectUtil.getMethod(this, "testMethodWithoutAuxiliary", URL.class, Integer.class);

        Object[] actualArgs = SmartUtil.placement(method, exchange, cashBuilder.build(exchange), urlValueBuffer);

        assertArrayEquals(expectedArgs, actualArgs);

    }

    public void testMethodWithoutAuxiliary(URL url, @AuxiliaryBody Integer integer) {

    }

    @Test
    @SneakyThrows
    void testPlacementGreaterThreeParam() {

        ServerWebExchange exchange = getExchange();

        Method method = ReflectUtil.getMethod(this, "testOnlyServerWebExchangeAuxiliaryBodyTwoBody",
                ServerWebExchange.class, Integer.class, Integer.class, Integer.class);

        try {

            SmartUtil.placement(method, exchange, cashBuilder.build(exchange), urlValueBuffer);

            fail();

        } catch (Exception e) {
            assertTrue(e instanceof NumberOfParametersException);
        }

    }

    @Test
    @SneakyThrows
    void testPlacementGreaterTwoBody() {

        ServerWebExchange exchange = getExchange();

        Method method = ReflectUtil.getMethod(this, "testAuxiliaryBodyTwoParam", URL.class, Integer.class, String.class);

        try {

            SmartUtil.placement(method, exchange, cashBuilder.build(exchange), urlValueBuffer);

            fail();

        } catch (Exception e) {
            assertTrue(e instanceof NumberOfParametersException);
        }

    }

    @Test
    void isCountCorrect() {
        Method method = ReflectUtil.getMethod(this, "testAuxiliaryBodyOneBody", URL.class, Integer.class);

        assertTrue(SmartUtil.isCountBodyCorrect(method));

        method = ReflectUtil.getMethod(this, "testOnlyAuxiliaryBody", URL.class);

        assertTrue(SmartUtil.isCountBodyCorrect(method));

        method = ReflectUtil.getMethod(this, "testOnlyServerWebExchange", ServerWebExchange.class);

        assertTrue(SmartUtil.isCountBodyCorrect(method));

        method = ReflectUtil.getMethod(this, "testOnlyBody", URL.class);

        assertTrue(SmartUtil.isCountBodyCorrect(method));

        method = ReflectUtil.getMethod(this, "testOnlyServerWebExchangeAndOneBody", ServerWebExchange.class, Integer.class);

        assertTrue(SmartUtil.isCountBodyCorrect(method));

        method = ReflectUtil.getMethod(this, "testOnlyServerWebExchangeAndTwoBody", ServerWebExchange.class, Integer.class, Integer.class);

        assertFalse(SmartUtil.isCountBodyCorrect(method));

        method = ReflectUtil.getMethod(this, "testTwoBody", URL.class, Integer.class);

        assertFalse(SmartUtil.isCountBodyCorrect(method));

        method = ReflectUtil.getMethod(this, "testAuxiliaryBodyTwoParam", URL.class, Integer.class, String.class);

        assertFalse(SmartUtil.isCountBodyCorrect(method));

        method = ReflectUtil.getMethod(this, "testOnlyServerWebExchangeAuxiliaryBodyOneBody", ServerWebExchange.class, Integer.class, Integer.class);

        assertTrue(SmartUtil.isCountBodyCorrect(method));

        method = ReflectUtil.getMethod(this, "testOnlyServerWebExchangeAuxiliaryBodyTwoBody", ServerWebExchange.class, Integer.class, Integer.class, Integer.class);

        assertFalse(SmartUtil.isCountBodyCorrect(method));
    }

    public void testOnlyBody(URL url) {

    }

    public void testTwoBody(URL url, Integer integer) {

    }

    public void testOnlyAuxiliaryBody(@AuxiliaryBody URL url) {

    }

    public void testAuxiliaryBodyOneBody(@AuxiliaryBody URL url, Integer integer) {

    }

    public void testAuxiliaryBodyTwoParam(@AuxiliaryBody URL url, Integer integer, String s) {

    }

    public void testOnlyServerWebExchange(ServerWebExchange exchange) {

    }

    public void testOnlyServerWebExchangeAndOneBody(ServerWebExchange exchange, Integer integer) {

    }

    public void testOnlyServerWebExchangeAndTwoBody(ServerWebExchange exchange, Integer integer, Integer integer2) {

    }

    public void testOnlyServerWebExchangeAuxiliaryBodyOneBody(ServerWebExchange exchange, @AuxiliaryBody Integer integer, Integer integer2) {

    }

    public void testOnlyServerWebExchangeAuxiliaryBodyTwoBody(ServerWebExchange exchange, @AuxiliaryBody Integer integer, Integer integer2, Integer integer3) {

    }

    private ServerWebExchange getExchange() {
        return new ServerWebExchange() {
            @Override
            public ServerHttpRequest getRequest() {
                return new ServerHttpRequest() {
                    @Override
                    public String getId() {
                        return "3423423;ekwfwe";
                    }

                    @Override
                    public RequestPath getPath() {
                        return null;
                    }

                    @Override
                    public MultiValueMap<String, String> getQueryParams() {
                        return null;
                    }

                    @Override
                    public MultiValueMap<String, HttpCookie> getCookies() {
                        return null;
                    }

                    @Override
                    public String getMethodValue() {
                        return null;
                    }

                    @Override
                    public URI getURI() {
                        return null;
                    }

                    @Override
                    public Flux<DataBuffer> getBody() {
                        return null;
                    }

                    @Override
                    public HttpHeaders getHeaders() {
                        return null;
                    }
                };
            }

            @Override
            public ServerHttpResponse getResponse() {
                return null;
            }

            @Override
            public Map<String, Object> getAttributes() {
                return null;
            }

            @Override
            public Mono<WebSession> getSession() {
                return null;
            }

            @Override
            public <T extends Principal> Mono<T> getPrincipal() {
                return null;
            }

            @Override
            public Mono<MultiValueMap<String, String>> getFormData() {
                return null;
            }

            @Override
            public Mono<MultiValueMap<String, Part>> getMultipartData() {
                return null;
            }

            @Override
            public LocaleContext getLocaleContext() {
                return null;
            }

            @Override
            public ApplicationContext getApplicationContext() {
                return null;
            }

            @Override
            public boolean isNotModified() {
                return false;
            }

            @Override
            public boolean checkNotModified(Instant lastModified) {
                return false;
            }

            @Override
            public boolean checkNotModified(String etag) {
                return false;
            }

            @Override
            public boolean checkNotModified(String etag, Instant lastModified) {
                return false;
            }

            @Override
            public String transformUrl(String url) {
                return null;
            }

            @Override
            public void addUrlTransformer(Function<String, String> transformer) {

            }

            @Override
            public String getLogPrefix() {
                return null;
            }
        };
    }

}