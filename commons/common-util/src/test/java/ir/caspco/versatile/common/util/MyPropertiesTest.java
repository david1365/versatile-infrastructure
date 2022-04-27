package ir.caspco.versatile.common.util;

import ir.caspco.versatile.common.util.MyProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        MyProperties.class
})
@TestPropertySource("classpath:MyGatewayTest/application.properties")
class MyPropertiesTest {
    @Autowired
    private MyProperties myProperties;


    @Test
    void init() {
        assertEquals("", myProperties.getMyActivatedProfile());
    }


    @Test
    public void getMySocket() {

        assertEquals(myProperties.gmyHost().getHostAddress() + ":" + myProperties.getMyPort(), myProperties.mySocket());

    }

    @Test
    public void getMyUri() {
        assertEquals("http://" + myProperties.mySocket(), myProperties.myUri().toString());
    }

}