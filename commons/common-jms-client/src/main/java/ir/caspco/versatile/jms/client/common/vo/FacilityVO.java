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
public class FacilityVO {

    private long id;
    private String facilityCode;
    private long facilityVersion;
    private boolean lastFacilityVersionFlag;
    private String subsystemCode;
    private long financialProfileID;
    private String currencyCode;
    private long ledgerID;
    private long contractID;
    private ContractVO contract;
    private long regionID;
    private long loanInterestModelID;
    private long penaltyCalculationBaseID;
    private boolean repaymentMethodFlag;
    private boolean settlementMethodFlag;
    private String title;
    private Date startDate;
    private boolean branchLimitedFlag;
    private long amountMin;
    private long amountMax;
    private long durationMin;
    private long durationMax;
    private double interestRateMin;
    private double interestRateMax;
    private long sitDurationMin;
    private long sitDurationMax;
    private long installmentFrequencyMin;
    private long installmentFrequencyMax;
    private double penaltyRateMin;
    private double penaltyRateMax;
    private double minRatioOfAdvancePayFor;
    private double redemptionPercentOfInterest;
    private long discountReductionDay;
    private double discountReductionPercent;
    private long redemptionDay;
    private boolean instalmentFlag;
    private boolean activeFlag;
    private boolean proportionalDivisionFlag;
    private long commissionRateMin;
    private long commissionRateMax;
    private long commissionRateDefault;
    private double notReceiptCommission;
    private boolean collateralFlag;
    private boolean insuranceFlag;
    private double fieldBankShareInterestPercent;
    private boolean connectedAccountFlag;
    private boolean discountFlag;
    private double discountFactorRate;
    private boolean penaltyInHolidayFlag;
    private boolean earlySettlementBase;
    private boolean fundsFlag;
    private boolean leasingFlag;
    private long version;
    private long reducedCommissionDays;
    private long extensionDays;
    private boolean exportRevokingNotificationFlag;
    private long exportRevokingNotificationDays;
    private long reducedCommissionMounts;
    private boolean commissionCalculationByApprovalAmount;
    private long penaltyCalculationDays;
    private boolean creditCardFlag;
}
