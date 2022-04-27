package ir.caspco.versatile.gateway.smart.filters;

import ir.caspco.versatile.gateway.smart.filters.annotations.Filter;
import ir.caspco.versatile.gateway.smart.filters.annotations.Path;
import ir.caspco.versatile.gateway.smart.filters.annotations.ResponseBody;
import ir.caspco.versatile.gateway.smart.filters.SmartGatewayFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Map;


/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
@Filter(prefix = "wallet-pec.prefix")
@Path("/path")
@SuppressWarnings("unused")
public class ResponseMutatedClass extends SmartGatewayFilter {

    @ResponseBody("wallet-pec.paths.AddCustomer")
    public Map<String, Object> injectForAddCustomerResponse(ServerWebExchange exchange, Map<String, Object> body) {
        return body;
    }

}

