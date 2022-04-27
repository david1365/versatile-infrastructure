package ir.caspco.versatile.jms.client.common.vo;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
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
public class HeadOfficeContract {

    private Long id;
    private String code;
    private String typeCode;
    private String title;
    private String contractNumber;
    private Date startDate;
    private Date expireDate;
    private Date effectiveDate;
    private BigDecimal amount;
    private String description;
    private String centerType1;
    private String centerValue1;
    private String accountId;
    private Long centerId1;
    private Boolean notActive;
    private String centerTypeDescription;
    private String projectNumber;

    private Long version;

}

