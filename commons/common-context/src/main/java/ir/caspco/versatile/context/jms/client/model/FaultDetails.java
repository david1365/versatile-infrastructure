package ir.caspco.versatile.context.jms.client.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
public class FaultDetails {
    private List<String> messages;
    private String debugInfo;
    private String faultCode;
    private String faultContext;
    private String referralFaultCode;
    private boolean isReferralSelfManaged;
    private Map<String, Object> parameters;

    private String fa_IR;
    private String en_US;
    private String code;
}
