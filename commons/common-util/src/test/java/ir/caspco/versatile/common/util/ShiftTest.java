package ir.caspco.versatile.common.util;


import ir.caspco.versatile.common.util.exceptions.ClassObjectIsNotSetException;
import ir.caspco.versatile.common.util.model.CustomerModel;
import ir.caspco.versatile.common.util.model.DateModel;
import ir.caspco.versatile.common.util.model.MobileModel;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DefaultDataBuffer;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;

import java.nio.charset.Charset;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */
class ShiftTest {
    public static Integer value = 123;
    public static Shift<Integer> integerShift = Shift.just(value);

    @Test
    void toBytes() {

    }

    @Test
    void toStringRaw() {
        String expected = value.toString();

        assertEquals(expected, integerShift.toJson());
    }

    @Test
    void toObject() {
        Integer expected = value;

        assertEquals(expected, integerShift.toObject());
    }

    @Test
    void toDataBuffer() {
        byte[] strBytes = "ali".getBytes();

        DefaultDataBuffer DataBuffer = new DefaultDataBufferFactory().wrap(strBytes);

        assertEquals(new String(strBytes), Shift.just(DataBuffer).toJson());
    }

    @Test
    void stringTest() {

        String json = "{\n" +
                "  \"NationalCode\" : \"9961298985984\",\n" +
                "  \"MobileNumber\" : \"09129440636\"\n" +
                "}";

        CustomerModel expectedCustomer = CustomerModel.builder()
                .mobileNumber("09129440636")
                .nationalCode("9961298985984")
                .build();

        MobileModel expectedMobile = MobileModel.builder()
                .mobileNumber("09129440636")
                .build();


        assertEquals(expectedCustomer, Shift.just(json).toShift(CustomerModel.class).toObject());

        assertEquals(expectedMobile, Shift.just(expectedCustomer).toShift(MobileModel.class).toObject());

        assertEquals(expectedMobile, Shift.just(json).toShift(MobileModel.class).toObject());

    }

    @Test
    void toShift() {
        String expected = "123 + 345";

        Shift<String> actual = integerShift.toShift(String.class).map(s -> s + " + 345");

        assertEquals(expected, actual.toObject());

        assertTrue(actual.toObject() instanceof String);
    }


    @Test
    void getModelMode() {
        Integer expected = 456;
        Integer actual = Shift.fromFile("profile/wallet-pec-inject", "TST").toShift(Integer.class).toObject();

        assertEquals(expected, actual);
    }

    @Test
    void getModel() {
        Integer expected = 123;
        Integer actual = Shift.fromFile("profile/wallet-pec-inject").toShift(Integer.class).toObject();

        assertEquals(expected, actual);
    }

    @Test
    void testToObject() {

        try {

            Shift.just("json").toObject();

            fail();

        } catch (Exception e) {

            assertTrue(e instanceof ClassObjectIsNotSetException);

        }

    }

    @Test
    void nullTest() {
        try {

            Shift.just((Object) null);

            fail();

        } catch (Error e) {

            assertTrue(e instanceof AssertionError);

        }

        try {

            Shift.just((DataBuffer) null);

            fail();

        } catch (Error e) {

            assertTrue(e instanceof AssertionError);

        }

        try {

            Shift.just((String) null);

            fail();

        } catch (Error e) {

            assertTrue(e instanceof AssertionError);

        }

        try {

            Shift.just((String) null, (Charset) null);

            fail();

        } catch (Error e) {

            assertTrue(e instanceof AssertionError);

        }
    }

    @Test
    void setDateFormat() {

        //--static----------------
        String staticJson = "{\"date\": \"2020/01-01\"}";

        Shift.setDateFormat("yyyy/MM-dd");

        Shift.just(staticJson).toShift(DateModel.class).toObject();

        Shift.fromFile("Shift/static-date-format").toShift(DateModel.class).toObject();


        //---Instance-------------
        String instanceJson = "{\"date\": \"2020-01/01\"}";
        String dateFormat = "yyyy-MM/dd";

        Shift.just(instanceJson).dateFormat(dateFormat).toShift(DateModel.class).toObject();

        Shift.just(instanceJson)
                .dateFormat(dateFormat)
                .toShift(DateModel.class)
                .map(dateModel -> {
                    return dateModel;
                })
                .toObject();

        Shift.fromFile("Shift/instance-date-format").dateFormat(dateFormat).toShift(DateModel.class).toObject();


        //--static----------------
        Shift.just(staticJson).toShift(DateModel.class).toObject();

    }

    @Test
    void dateFormatObjectToJson() {
        Shift.setDefaultDateFormat();

        String expectedJson = "{\r\n" +
                "  \"date\" : \"2020/02-01\"\r\n" +
                "}";

        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.FEBRUARY, 1);

        DateModel input = DateModel.builder()
                .date(calendar.getTime())
                .build();

        String actualJson = Shift.just(input).dateFormat("yyyy/MM-dd").toJson();

        assertEquals(expectedJson, actualJson);
    }
}