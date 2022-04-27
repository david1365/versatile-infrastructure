package ir.caspco.versatile.context.domains;

import ir.caspco.versatile.context.enums.FlowStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

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
public class BasicBusinessLogEntity extends BasicLogEntity {

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private FlowStatus flowStatus = FlowStatus.CREATION;
}
