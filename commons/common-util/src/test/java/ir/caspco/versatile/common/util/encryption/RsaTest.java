package ir.caspco.versatile.common.util.encryption;

import ir.caspco.versatile.common.util.exceptions.AliasIncorrectException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        RsaConfiguration.class,
})
@TestPropertySource("classpath:MyGatewayTest/application.properties")
class RsaTest {

    @Autowired
    @Qualifier("caspco-rsa")
    private Cryptography rsa;

    @Autowired
    @Qualifier("incorrectRsaAlias")
    private Cryptography incorrectRsaAlias;

    private static final String message = "{\n" +
            "  \"cardEmbossData\" : {\n" +
            "    \"track1\" : \"%B6221061211000395^CORPORATE/FIRST*LAST*F^02011010310700571000000?\",\n" +
            "    \"track2\" : \"6221061211000395=0201101031075710\",\n" +
            "    \"track3\" : \"100100000000202203180000=0000000000=000000\"\n" +
            "  },\n" +
            "  \"cardPrintData\" : {\n" +
            "    \"cardNumber\" : \"6221061211000395\",\n" +
            "    \"persianName\" : \"داود اکبری\",\n" +
            "    \"englishName\" : \"\\\"HAMPA\\\"\",\n" +
            "    \"cvv2\" : \"447\",\n" +
            "    \"issueDate\" : \"0101\",\n" +
            "    \"persianExpireDate\" : \"14020131\",\n" +
            "    \"gregorianExpireDate\" : \"0201\"\n" +
            "  }\n" +
            "}";

    @Test
    void sign() {

        final String signature = rsa.sign(message);

        assertTrue(rsa.verify(signature, message));
    }

    @Test
    void encrypt() {

        final String encryptedMessage = rsa.encrypt(message);
        final String decryptedMessage = rsa.decrypt(encryptedMessage);

        assertEquals(message, decryptedMessage);
    }

    @Test
    void encryptHello() {

        String message = "hello";

        final String encryptedMessage = rsa.encrypt(message);
        final String decryptedMessage = rsa.decrypt(encryptedMessage);

        assertEquals(message, decryptedMessage);
    }


    @Test
    void aliasIncorrectException() {

        try {

            incorrectRsaAlias.keyPair();

            fail();
        } catch (Exception e) {

            assertTrue(e instanceof AliasIncorrectException);
        }
    }
}