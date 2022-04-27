package ir.caspco.versatile.jms.client.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@SuperBuilder
@NoArgsConstructor
public class ContractVO {

    private long id;
    private long parentID;
    private String code;
    private String shortTitle;
    private String longTitle;
    private boolean isActive;
    private long childOrder;
    private Date activationDate;
    private Long version;
}
