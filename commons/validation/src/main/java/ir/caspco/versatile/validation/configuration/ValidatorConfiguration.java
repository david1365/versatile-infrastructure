package ir.caspco.versatile.validation.configuration;

import ir.caspco.versatile.context.handler.exceptions.annotations.MessagesPath;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Configuration
@MessagesPath("classpath:/i18n/validation")
public class ValidatorConfiguration {
    @Bean
    public Validator validator() {
        //Create ValidatorFactory which returns validator
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .buildValidatorFactory();

        //It validates bean instances
        return validatorFactory.getValidator();
    }
}
