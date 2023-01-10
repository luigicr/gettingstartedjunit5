package patientintake;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ClinicCalendarShould {

   private ClinicCalendar calendar;

   @BeforeAll
   static void initAll() {
      System.out.println("Before all...");
   }

   @BeforeEach
   void init() {
      System.out.println("Before each...");
      calendar = new ClinicCalendar(LocalDate.of(2023, 1, 10));
   }

   @Test
   void allowEntryOfAnAppointment() {
      System.out.println("Entry appointment...");
      calendar.addAppointment("Jim", "Weaver", "avery",
         "09/01/2018 2:00 pm");
      List<PatientAppointment> appointments = calendar.getAppointments();
      assertNotNull(appointments);
      assertEquals(1, appointments.size());
      PatientAppointment enteredAppt = appointments.get(0);
      assertAll(
          () -> assertEquals("Jim", enteredAppt.getPatientFirstName()),
          () -> assertEquals("Weaver", enteredAppt.getPatientLastName()),
          () -> assertEquals(Doctor.avery, enteredAppt.getDoctor()),
          () -> assertEquals("9/1/2018 02:00 PM",
             enteredAppt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a", Locale.US)))
      );

   }

   @Test
   void returnTrueForHasAppointmentsIfThereAreAppointments() {
      System.out.println("Has appointments...");
      calendar.addAppointment("Jim", "Weaver", "avery",
          "09/01/2018 2:00 pm");
      assertTrue(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));
   }
   @Test
   void returnFalseForHasAppointmentsIfThereAreNoAppointments() {
      System.out.println("No appointments...");
      assertFalse(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));
   }

   @Test
   void returnCurrentDaysAppointments() {
      System.out.println("Current day appointments...");
      calendar.addAppointment("Jim", "Weaver", "avery",
          "01/10/2023 2:00 pm");
      calendar.addAppointment("Jim", "Weaver", "avery",
          "01/10/2023 2:00 pm");
      calendar.addAppointment("Jim", "Weaver", "avery",
          "09/01/2023 2:00 pm");
      assertEquals(2, calendar.getTodayAppointments().size());
   }

   @AfterEach
   void tearDownEachTest() {
      System.out.println("After each...");
   }

   @AfterAll
   static void testDownTestClass() {
      System.out.println("After all...");
   }
}
