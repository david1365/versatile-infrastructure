package ir.caspco.versatile.gateway.common.context.properties;

import lombok.Data;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
public class GatewayRoute {
    private String id;

    private String uri;

    private String path;

    private String userName;

    private String password;

    private FilterConfig filter;
}
