package ir.caspco.versatile.global.log.domains;

import ir.caspco.versatile.context.domains.BasicGlobalLogEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

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
@Table(name = GlobalAPILogEntity.TABLE)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class GlobalAPILogEntity extends BasicGlobalLogEntity {

    public static final String TABLE = "GLOBAL_API_LOG";


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "SQ_" + GlobalAPILogEntity.TABLE + "_ID")
    private BigDecimal id;

    @Lob
    private String responseHeaders;

    @Lob
    private String responseBody;

    @Enumerated(EnumType.STRING)
    private HttpStatus status;
}
