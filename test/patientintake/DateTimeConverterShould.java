package patientintake;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("DateTimeConverter should")
class DateTimeConverterShould {

    @Nested
    @DisplayName("convert string with 'today' keyword")
    class TodayTests {
        @Test
        @DisplayName("correctly")
        void convertTodayStringCorrectly() {
            LocalDate today = LocalDate.of(2022, 9, 3);
            LocalDateTime actualResult = DateTimeConverter.convertStringToDateTime("today 1:00 pm", today);
            assertEquals(LocalDateTime.of(2022, 9, 3, 13, 0), actualResult,
                    () -> "Failed to convert 'today' string to expected date time, today passed was: " + today);

        }

        @Test
        @DisplayName("regardless of case")
        void convertTodayStringCorrectlyCaseInsensitive() {
            LocalDate today = LocalDate.of(2022, 9, 3);
            LocalDateTime actualResult = DateTimeConverter.convertStringToDateTime("ToDay 1:00 pm", today);
            assertEquals(LocalDateTime.of(2022, 9, 3, 13, 0), actualResult,
                    () -> "Failed to convert 'today' string to expected date time, today passed was: " + today);
        }

    }



    @Test
    @DisplayName("convert expected date time pattern in string correctly")
    void convertCorrectPatternToDateTime() {
        LocalDateTime actualResult = DateTimeConverter.convertStringToDateTime("9/3/2022 1:00 pm",
                LocalDate.of(2022, 9, 3));
        assertEquals(LocalDateTime.of(2022, 9, 3, 13, 0), actualResult);
    }

    @Test
    @Tag("dateTime")
    @DisplayName("throw exception if entered pattern of string is incorrect")
    void throwExceptionIfIncorrectPatternProvided() {
        Throwable error = assertThrows(RuntimeException.class, () ->
                DateTimeConverter.convertStringToDateTime("9/3/2022 100 pm",
                        LocalDate.of(2022, 9, 3)));
        assertEquals("Unable to create date time from: [9/3/2022 100 pm], " +
                "please enter with format [M/d/yyyy h:mm a], Text '9/3/2022 100 PM' " +"" +
                "could not be parsed at index 12", error.getMessage());
    }

}