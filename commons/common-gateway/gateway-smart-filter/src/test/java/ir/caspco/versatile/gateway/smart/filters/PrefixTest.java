package ir.caspco.versatile.gateway.smart.filters;

import ir.caspco.versatile.gateway.smart.filters.annotations.Filter;
import ir.caspco.versatile.gateway.smart.filters.annotations.Path;
import ir.caspco.versatile.gateway.smart.filters.SmartGatewayFilter;
import org.springframework.stereotype.Component;


/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

//TODO from davood akbari: Do not forget to test.

@Component
@Filter(prefix = "/prefix", order = 123)
@Path("/path")
@SuppressWarnings("unused")
public class PrefixTest extends SmartGatewayFilter {
}

