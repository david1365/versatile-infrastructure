package ir.caspco.versatile.context.rest.client.common.esb;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@SuperBuilder
@NoArgsConstructor
public class EsbErrorResult {
    private String timestamp;
    private int code;
    private Object message;
    private Description description;
    private Object errorDetails;
    private String exceptionDetail;
}
