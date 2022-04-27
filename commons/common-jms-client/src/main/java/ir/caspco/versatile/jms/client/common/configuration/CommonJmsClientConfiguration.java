package ir.caspco.versatile.jms.client.common.configuration;

import ir.caspco.versatile.context.handler.exceptions.annotations.MessagesPath;
import org.springframework.context.annotation.Configuration;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Configuration
@MessagesPath({
        "classpath:/i18n/common-jms-client-messages",
        "classpath:/i18n/common-jms-client-field-validations"
})
public class CommonJmsClientConfiguration {
}
