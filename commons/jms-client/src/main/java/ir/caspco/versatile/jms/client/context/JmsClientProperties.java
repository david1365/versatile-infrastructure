package ir.caspco.versatile.jms.client.context;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Getter
@Component
public class JmsClientProperties {
    @Value(PropertiesConstants.VERSION)
    private String version;

    @Value(PropertiesConstants.CHANNEL)
    private String channel;

    @Value(PropertiesConstants.FILTER)
    private String filter;

    @Value(PropertiesConstants.CREDENTIALS)
    private String credentials;

    @Value(PropertiesConstants.CONNECTION_FACTORY)
    private String connectionFactory;

    @Value(PropertiesConstants.REQUEST_QUEUE)
    private String requestQueue;

    @Value(PropertiesConstants.RESPONSE_QUEUE)
    private String responseQueue;

    @Value(PropertiesConstants.QUEUE_RECEIVE_TIMEOUT)
    private Long queueReceiveTimeout;

    @Value(PropertiesConstants.OAUTH_SERVER_URL)
    private String oauthServerUrl;

    @Value(PropertiesConstants.WEBLOGIC_URL)
    private String webLogicUrl;

}