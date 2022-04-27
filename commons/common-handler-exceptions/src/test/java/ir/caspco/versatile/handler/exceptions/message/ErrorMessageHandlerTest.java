package ir.caspco.versatile.handler.exceptions.message;

import ir.caspco.versatile.context.handler.exceptions.GatewayException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */


class ErrorMessageHandlerTest {

    private final EMHTest emhTest = new EMHTest();

    @Test
    void statusTest() {
        assertEquals(HttpStatus.BAD_REQUEST, emhTest.status(new NullPointerException()));
    }

    @Test
    void statusTestAnnotation() {
        assertEquals(HttpStatus.UNAUTHORIZED, emhTest.status(new ExceptionTest()));
    }

    @Test
    void statusTestResultNumber() {
        assertEquals(HttpStatus.BAD_REQUEST, emhTest.status(new GatewayException(1)));

        assertEquals(HttpStatus.BAD_REQUEST, emhTest.status(new GatewayException(123)));

        assertEquals(HttpStatus.BAD_REQUEST, emhTest.status(new GatewayException(1234)));

        assertEquals(HttpStatus.UNAUTHORIZED, emhTest.status(new GatewayException(140125)));

        assertEquals(HttpStatus.UNAUTHORIZED, emhTest.status(new GatewayException(1401)));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, emhTest.status(new GatewayException(50028)));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, emhTest.status(new GatewayException(500)));
    }

    @Test
    void errorIdTest() {
        assertEquals(110, emhTest.errorId(new GatewayException(110)));

        assertEquals(-1, emhTest.errorId(new GatewayException()));

        assertEquals(-1, emhTest.errorId(new NullPointerException()));

        assertEquals(110110, emhTest.errorId(new ExceptionTest()));
    }

}