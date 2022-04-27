package ir.caspco.versatile.gateway.smart.filters.cash;

import ir.caspco.versatile.gateway.smart.filters.util.HeaderUtil;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

//TODO from davood akbari: Do not forget to test.
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CashBuilder<T> {

    private final HeaderUtil headerUtil;


    public CashBuilder(HeaderUtil headerUtil) {
        this.headerUtil = headerUtil;
    }

    private final Map<String, T> map = new ConcurrentHashMap<>();

    public DCash<T> build(ServerWebExchange exchange) {

        String key = headerUtil.getRequestId(exchange.getRequest());

        return new DCash<T>(map, key);

    }

}
