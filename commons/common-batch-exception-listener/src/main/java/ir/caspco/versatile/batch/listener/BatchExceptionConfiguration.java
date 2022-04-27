package ir.caspco.versatile.batch.listener;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Configuration
@EnableJpaRepositories("ir.caspco.versatile.batch.listener.repositories")
@EntityScan("ir.caspco.versatile.batch.listener.domains")
public class BatchExceptionConfiguration {
}

