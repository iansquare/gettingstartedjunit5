package patientintake;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ClinicCalendarShould {

   private ClinicCalendar calendar;

   @BeforeEach
   void init() {
      calendar = new ClinicCalendar(LocalDate.of(2022, 9, 3));
   }

   @Test
   void allowEntryOfAnAppointment() {
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
      calendar.addAppointment("Ian", "McDonald", "avery",
              "09/03/2022 6:00 pm");
      assertTrue(calendar.hasAppointment(LocalDate.of(2022, 9, 3)));
   }

   @Test
   void returnFalseForHasAppointmentsIfThereAreNoAppointments() {
      assertFalse(calendar.hasAppointment(LocalDate.of(2022, 9, 6)));
   }

   @Test
   void returnCurrentDaysAppointments(){
      calendar.addAppointment("Ian", "McDonald", "avery",
              "09/03/2022 4:00 pm");
      calendar.addAppointment("Ian", "McDonald", "avery",
              "09/03/2022 6:00 pm");
      calendar.addAppointment("Ian", "McDonald", "avery",
              "09/04/2022 7:00 pm");
      assertEquals(2, calendar.getTodayAppointments().size());


   }
}