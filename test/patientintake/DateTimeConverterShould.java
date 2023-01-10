package patientintake;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@Tag("dateTime")
@DisplayName("DateTimeConverter Should")
class DateTimeConverterShould {
  @Nested
  @DisplayName("Convert string with 'today' keyword")
  class TodayTests {
    @Test
    @DisplayName("correctly")
    void convertStringToDateTime() {
      LocalDate today = LocalDate.of(2023, 1, 10);
      LocalDateTime result = DateTimeConverter.convertStringToDateTime("today 1:00 pm", today);
      assertEquals(result, LocalDateTime.of(2023, 1, 10, 13, 0),
          () -> "Failed to convert 'today' string to expected date time, today passed was: " +today);
    }

    @Test
    @DisplayName("regardless of case")
    void convertStringCaseInsensitiveToDateTime() {
      LocalDate today = LocalDate.of(2023, 1, 10);
      LocalDateTime result = DateTimeConverter.convertStringToDateTime("tOday 1:00 pm", today);
      assertEquals(result, LocalDateTime.of(2023, 1, 10, 13, 0),
          () -> "Failed to convert 'today' string to expected date time, today passed was: " +today);
    }
  }

  @Test
  @DisplayName("Convert expected date time pattern in string correctly")
  void convertCorrectPatternToDateTime() {
    LocalDateTime result = DateTimeConverter.convertStringToDateTime("1/10/2023 1:00 pm", LocalDate.of(2023, 1, 10));
    assertEquals(result, LocalDateTime.of(2023, 1, 10, 13, 0));
  }

  @Test
  @DisplayName("Throw exception if entered pattern of string incorrect")
  void throwExceptionIfIncorrectPatternProvided() {
    Throwable error = assertThrows(RuntimeException.class, () -> DateTimeConverter.convertStringToDateTime("1/10/2023 100 pm", LocalDate.of(2023, 1, 10)));
    assertEquals("Unable to create date time from: [1/10/2023 100 pm], please enter with format [M/d/yyyy h:mm a], Text '1/10/2023 100 PM' could not be parsed at index 13", error.getMessage());
  }
}