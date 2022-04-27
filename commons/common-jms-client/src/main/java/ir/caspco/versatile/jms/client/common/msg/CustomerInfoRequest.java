package ir.caspco.versatile.jms.client.common.msg;


import ir.caspco.versatile.jms.client.common.vo.UserVO;
import lombok.Builder;
import lombok.Data;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
@Builder
public class CustomerInfoRequest {
    private UserVO userDto;
}
