package ir.caspco.versatile.common.util;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */
class MapUtilTest {

    @Test
    @SuppressWarnings("unchecked")
    void flatMap() {
        Map<String, String> expected = Shift.fromFile("Shift/expected-path").toShift(Map.class).toObject();

        Map<String, String> actual = MapUtil.flatMap(Shift.fromFile("Shift/path").toShift(Map.class).toObject());

        assertEquals(expected, actual);
    }

    @Test
    @SuppressWarnings("unchecked")
    void subMap() {
        Map expected = Shift.fromFile("Shift/expected-sub-map").toShift(Map.class).toObject();

        Map rootMap = Shift.fromFile("Shift/root-map").toShift(Map.class).toObject();

        Map actual = MapUtil.subMap(rootMap, "wallet-pec.request.body.inject.static");

        assertEquals(expected, actual);
    }

    @Test
    @SuppressWarnings("unchecked")
    void subMapIncorrectPath() {

        Map rootMap = Shift.fromFile("Shift/root-map").toShift(Map.class).toObject();

        Map actual = MapUtil.subMap(rootMap, "wallet-pec.request.body.inject.dynamic");

        assertTrue(actual.isEmpty());
    }

}