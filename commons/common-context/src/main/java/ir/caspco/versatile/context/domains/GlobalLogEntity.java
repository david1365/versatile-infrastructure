package ir.caspco.versatile.context.domains;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.math.BigDecimal;

import static ir.caspco.versatile.context.domains.Schema.MOBILE;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@Audited
@SuperBuilder
@Entity
@Table(name = GlobalLogEntity.TABLE, schema = MOBILE)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class GlobalLogEntity extends AuditEntity {

    public static final String TABLE = "GLOBAL_LOG";


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = MOBILE + ".SQ_" + GlobalLogEntity.TABLE + "_ID")
    private BigDecimal id;

    @Column(unique = true)
    private String requestId;

    private String clientIp;

    @Lob
    private String requestHeaders;

    @Lob
    private String responseHeaders;

    @Lob
    private String requestBody;

    @Lob
    private String responseBody;

    @Enumerated(EnumType.STRING)
    private HttpMethod httpMethod;

    @Enumerated(EnumType.STRING)
    private HttpStatus status;

}
