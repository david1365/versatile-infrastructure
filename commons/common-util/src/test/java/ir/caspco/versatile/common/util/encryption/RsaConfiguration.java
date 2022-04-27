package ir.caspco.versatile.common.util.encryption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Configuration
public class RsaConfiguration {

    @Value("${cryptography.path:caspian-keystore.jks}")
    private String path;

    @Value("${cryptography.password:123456}")
    private char[] password;

    @Value("${cryptography.alias:caspco}")
    private String alias;

    @Bean("caspco-rsa")
    public Cryptography caspcoRsa() {

        return new Rsa(path, password, alias);
    }

    @Bean("incorrectRsaAlias")
    public Cryptography incorrectRsaAlias() {

        return new Rsa(path, password, alias + "1");
    }
}
