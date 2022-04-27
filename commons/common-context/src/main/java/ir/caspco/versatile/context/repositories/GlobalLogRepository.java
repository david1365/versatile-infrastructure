package ir.caspco.versatile.context.repositories;

import ir.caspco.versatile.context.domains.GlobalLogEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Repository
public interface GlobalLogRepository extends CrudRepository<GlobalLogEntity, BigDecimal> {
}
