package ir.caspco.versatile.context.domains;

import ir.caspco.versatile.correlation.RequestCorrelation;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@MappedSuperclass
@AllArgsConstructor
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class BasicLogEntity extends AuditEntity {

    @Builder.Default
    @Column(nullable = false, unique = true)
    private UUID correlationId = RequestCorrelation.getId();
}
