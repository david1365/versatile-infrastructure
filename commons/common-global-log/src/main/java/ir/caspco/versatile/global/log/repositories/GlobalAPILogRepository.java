package ir.caspco.versatile.global.log.repositories;

import ir.caspco.versatile.global.log.domains.GlobalAPILogEntity;
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
public interface GlobalAPILogRepository extends JpaRepository<GlobalAPILogEntity, BigDecimal> {
}
