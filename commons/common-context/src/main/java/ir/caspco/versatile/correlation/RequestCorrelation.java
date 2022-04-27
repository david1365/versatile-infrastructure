package ir.caspco.versatile.correlation;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.List;
import java.util.UUID;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public class RequestCorrelation {

    public static final String X_CORRELATION_ID = "X-Correlation-ID";

    private static final ThreadLocal<UUID> id = new ThreadLocal<>();


    public static UUID getId() {
        return id.get();
    }

    public static void setId(UUID correlationId) {
        id.set(correlationId);
    }

    public static void setId(ServerHttpRequest request) {

        HttpHeaders httpHeaders = request.getHeaders();
        List<String> correlationIds = httpHeaders.get(X_CORRELATION_ID);

        id.set(getCorrelationId(correlationIds));
    }

    public static void setId(ServerRequest request) {

        ServerRequest.Headers headers = request.headers();
        List<String> correlationIds = headers.header(X_CORRELATION_ID);

        id.set(getCorrelationId(correlationIds));
    }

    private static UUID getCorrelationId(List<String> correlationIds) {
        return correlationIds == null || correlationIds.isEmpty() ? null : UUID.fromString(correlationIds.get(0));
    }
}
