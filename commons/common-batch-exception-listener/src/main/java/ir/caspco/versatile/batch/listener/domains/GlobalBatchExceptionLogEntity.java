package ir.caspco.versatile.batch.listener.domains;

import ir.caspco.versatile.batch.listener.ItemError;
import ir.caspco.versatile.context.domains.AuditEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@SuperBuilder
@Entity
@Table(name = GlobalBatchExceptionLogEntity.TABLE)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString(callSuper = true)
public class GlobalBatchExceptionLogEntity extends AuditEntity {

    public static final String TABLE = "GLOBAL_BATCH_EXCEPTION_LOG";


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "SQ_" + GlobalBatchExceptionLogEntity.TABLE + "_ID")
    private BigDecimal id;

    private Long jobId;

    private String stepName;

    @Enumerated(EnumType.STRING)
    private ItemError itemError;

    @Lob
    private String item;

    private String exceptionName;

    @Lob
    private String exceptionMessage;

    @Lob
    private String stackTrace;
}
