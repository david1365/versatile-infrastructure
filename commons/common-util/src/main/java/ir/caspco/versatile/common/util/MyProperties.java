package ir.caspco.versatile.common.util;

import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Component
@Getter
public class MyProperties {

    @Value("${server.port:8080}")
    private int myPort;

    @Value("${spring.profiles.active:}")
    private String myActivatedProfile;

    @Value("${server.ssl.enabled:false}")
    private boolean sslEnabled;

    @Value("${security.basic.username:admin}")
    private String username;

    @Value("${security.basic.password:1400GCaspco$GateWay#daak}")
    private String password;


    @PostConstruct
    public void init() {
        System.setProperty("gateway.uri", myUri().toString());
        System.setProperty("gateway.authorization", myBasicToken());

        myActivatedProfile =
                myActivatedProfile.equalsIgnoreCase("@spring.profiles.active@") ? "" : myActivatedProfile;
    }

    public String myBasicToken() {

        String auth = username + ":" + password;

        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.ISO_8859_1));

        return "Basic " + new String(encodedAuth);

    }

    @SneakyThrows
    public InetAddress gmyHost() {

        return InetAddress.getLocalHost();

    }

    public String mySocket() {

        return String.format("%s:%s", gmyHost().getHostAddress(), getMyPort());

    }

    @SneakyThrows
    public URI myUri() {

        String protocol = !sslEnabled ? "http" : "https";

        return new URI(String.format("%s://%s", protocol, mySocket()));

    }

    public Charset getMyDefaultCharset() {
        return StandardCharsets.UTF_8;
    }

}
