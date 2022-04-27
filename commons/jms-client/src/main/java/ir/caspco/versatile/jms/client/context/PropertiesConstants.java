package ir.caspco.versatile.jms.client.context;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public final class PropertiesConstants {

    public static final String VERSION = "${client.core.version:1.3.16.0}";

    public static final String CHANNEL = "${client.core.channel:BRANCH}";

    public static final String FILTER = "${client.core.filter:ServerNode-Lotus-david}";

    public static final String CREDENTIALS = "${client.core.credentials:test@1001:IRR}";

    public static final String CONNECTION_FACTORY = "${client.core.connectionFactory:com.casp.jms.mainConnectionFactory}";

    public static final String REQUEST_QUEUE = "${client.core.request.queue:com.casp.jms.queue.SandboxQueueRequest}";

    public static final String RESPONSE_QUEUE = "${client.core.response.queue:com.casp.jms.queue.SandboxQueueResponse}";

    public static final String QUEUE_RECEIVE_TIMEOUT = "${client.queue.request.timeout:30000}";

    public static final String OAUTH_SERVER_URL = "${client.oauth.server.url:}";

    public static final String WEBLOGIC_URL = "${client.webLogic.url:t3://192.168.17.149:7001}";

}
