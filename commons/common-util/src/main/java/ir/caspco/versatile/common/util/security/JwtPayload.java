package ir.caspco.versatile.common.util.security;


import ir.caspco.versatile.common.util.Shift;
import lombok.Data;

import java.util.Base64;
import java.util.Date;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@Data
public class JwtPayload {
    private Long iat;
    private Long exp;

    public static JwtPayload just(String token) {

        String[] chunks = token.split("\\.");

        Base64.Decoder decoder = Base64.getDecoder();

        String payload = new String(decoder.decode(chunks[1]));

        return Shift.just(payload).toShift(JwtPayload.class).toObject();
    }


    public boolean isExpired() {
        Long now = ((new Date().getTime() + 1) / 1000);

        return getExp() < now;
    }
}
