package ir.caspco.versatile.batch.listener.repositories;

import ir.caspco.versatile.batch.listener.domains.GlobalBatchExceptionLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Repository
public interface GlobalBatchExceptionLogRepository extends JpaRepository<GlobalBatchExceptionLogEntity, BigDecimal> {
}
