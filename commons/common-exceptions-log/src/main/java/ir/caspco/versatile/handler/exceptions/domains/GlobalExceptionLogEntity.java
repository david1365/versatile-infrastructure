package ir.caspco.versatile.handler.exceptions.domains;

import ir.caspco.versatile.context.domains.BasicLogEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@Table(name = GlobalExceptionLogEntity.TABLE)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class GlobalExceptionLogEntity extends BasicLogEntity {

    public static final String TABLE = "GLOBAL_EXCEPTION_LOG";


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "SQ_" + GlobalExceptionLogEntity.TABLE + "_ID")
    private BigDecimal id;

    private String parentExceptionName;

    @Lob
    private String parentExceptionMessage;

    @Lob
    private String parentStackTrace;

    private String exceptionName;

    @Lob
    private String exceptionMessage;

    @Lob
    private String stackTrace;
}
