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
public class HeadOfficeContractVO {

    private BigDecimal amount;
    private String centerType1;
    private String centerValue1;
    private Long centerId1;
    private String code;
    private String contractNumber;
    private String description;
    private Date effectiveDate;
    private Date expireDate;
    private Long id;
    private Date startDate;
    private String title;
    private String typeCode;
    private Boolean notActive;
    private String centerTypeDescription;
    private String accountId;
    private String externalRef;
    private String projectNumber;
    private Long version;

}

