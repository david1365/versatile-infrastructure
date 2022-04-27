package ir.caspco.versatile.gateway.smart.filters;

import ir.caspco.versatile.gateway.smart.filters.annotations.Filter;
import ir.caspco.versatile.gateway.smart.filters.annotations.Request;
import ir.caspco.versatile.gateway.smart.filters.annotations.RequestBody;
import ir.caspco.versatile.gateway.smart.filters.SmartGatewayFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Map;

import static ir.caspco.versatile.gateway.smart.filters.annotations.Filter.ALL;


/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */


@Component
@Filter(prefix = "wallet-pec.prefix")
@SuppressWarnings("unused")
public class WithOutPathClass extends SmartGatewayFilter {

    @Request("wallet-pec.paths.AddCustomer")
    public ServerWebExchange mutatePath(ServerWebExchange exchange) {
        return exchange;
    }

    @RequestBody(ALL)
    public Map<String, Object> injectForAll(ServerWebExchange exchange, Map<String, Object> body) {

        return body;

    }

    @RequestBody("wallet-pec.paths.AddCustomer")
    public Map<String, Object> injectForAddCustomer(ServerWebExchange exchange, Map<String, Object> body) {

        return body;

    }

    @RequestBody({
            "wallet-pec.paths.AddCustomer",
            "wallet-pec.paths.GetCustomerTransaction",
            "wallet-pec.paths.DeChargeWallet"
            ,})
    public Map<String, Object> injectForShared(ServerWebExchange exchange, Map<String, Object> body) {


        return body;

    }

}

