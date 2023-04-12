import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeUtilsTest {

    @ParameterizedTest
    @CsvSource({"0, 0:00:00", "59, 0:00:59", "60, 0:01:00", "3599, 0:59:59", "3600, 1:00:00", "86399, 23:59:59"})
    void testValidInput(int a, String returnValue) {
        assertEquals(returnValue, TimeUtils.secToTime(a));
    }

    @ParameterizedTest
    @CsvSource({"-1", "-75", "86400", "800000"})
    void testInvalidInput(int a) {
        assertEquals("-1", TimeUtils.secToTime(a));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Yksi"})
    public void testStringThrowsException(String input) {
        Assertions.assertThrows(NumberFormatException.class, () -> TimeUtils.secToTime(Integer.parseInt(input)));
    }
}