package ir.caspco.versatile.common.util.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerModel {

    @JsonProperty("NationalCode")
    protected String nationalCode;

    @JsonProperty("MobileNumber")
    protected String mobileNumber;

    @JsonProperty("GroupWalletId")
    protected String groupWalletId;

}

