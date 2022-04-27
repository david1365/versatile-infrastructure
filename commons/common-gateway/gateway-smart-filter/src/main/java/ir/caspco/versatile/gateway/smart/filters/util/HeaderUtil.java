package ir.caspco.versatile.gateway.smart.filters.util;

import ir.caspco.versatile.common.util.MyProperties;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

//TODO from davood akbari: Do not forget to test.

@Component
public class HeaderUtil {
    private final MyProperties myProperties;


    public HeaderUtil(MyProperties myProperties) {
        this.myProperties = myProperties;
    }


    public String getRequestId(ServerHttpRequest serverHttpRequest) {

        return "Id:" + serverHttpRequest.getId() +
                "-G:" + serverHttpRequest.getLocalAddress() +
                "-CL:" + serverHttpRequest.getRemoteAddress();

    }

    public Charset getMediaTypeCharset(@Nullable MediaType mediaType) {

        if (mediaType != null && mediaType.getCharset() != null) {
            return mediaType.getCharset();
        } else {
            return myProperties.getMyDefaultCharset();
        }

    }

}
