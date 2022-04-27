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
public class BranchVO {

    private long code;
    private String name;
    private long branchType;
    private long grade;
    private long regionCode;
    private long calenderTypeID;
    private long timetableID;
    private long signID;
    private String languageID;
    private String swiftID;
    private long department;
    private String checkDigit;
    private boolean status;
    private Date openDate;
    private Date effectiveDate;
    private long maxInsuranceLimitCurr;
    private long maxInsuranceLimitIrr;
    private String zone;
    private String zoneHeader;
    private long version;
    private Date lastActiveDate;
    private long previousState;
    private long lastState;
    private String internationalCode;
    private boolean currencyBranch;
    private long atmCount;
}
