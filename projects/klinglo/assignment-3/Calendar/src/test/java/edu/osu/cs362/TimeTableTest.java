package edu.osu.cs362;
/**
 *  This class provides a basic set of test cases for the
 *  TimeTable class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;




import static org.junit.Assert.*;

public class TimeTableTest {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	/*Tests everything to do with the size of appointments, appointment lists 
	 * & the number of days between appointments with getApptRange() & deleteAppt()
	 */
	@Test
	public void test01()  throws Throwable  {
		int hour = 13,
			min = 30,
			day = 1,
			month = 10,
			year = 2017,
			hour2 = 10,
			min2 = 0,
			day2 = 29,
			month2 = 11,
			year2 = 2017;
		
		LinkedList<Appt> appts = new LinkedList<Appt>();
		
		Appt appointment1 = new Appt(hour, min, day, month, year, "Prostate Exam", "..."),
				appointment2 = new Appt(hour2, min2, day2, month2, year2, "Dentist", "Having all the teeth removed.");
		
		appts.add(appointment1);
		appts.add(appointment2);
		

		TimeTable table = new TimeTable();
		GregorianCalendar firstDayCalendar = new GregorianCalendar(year, month, day);
		GregorianCalendar secondDayCalendar = new GregorianCalendar(year2, month2, day2); 
		LinkedList<CalDay> calendarDayList = table.getApptRange(appts, firstDayCalendar, secondDayCalendar);
		assertEquals(58, calendarDayList.size()); //get the number of days in calD

		secondDayCalendar.set(year2, month2 + 1, day2);
		calendarDayList = table.getApptRange(appts, firstDayCalendar, secondDayCalendar);		
		assertEquals(89, calendarDayList.size()); //check that adding a month adds the correct number of days
		assertEquals(1, calendarDayList.getFirst().getSizeAppts()); //assert that there is one appointment that day
		assertEquals(0, calendarDayList.get(9).getSizeAppts()); //assert that there is no appointment on an invalid day
		

		//Test the size after adding an invalid Appointment & an appointment before the first day
		appts.add(new Appt(25, 65, 99, 13, 0, null, null));
		appts.add(new Appt(0, 0, 1, 1, year, null, null));
		byte count = 0;
		for (CalDay calendarDay : calendarDayList)
			count += calendarDay.getSizeAppts();
		assertEquals(2, count);
		
		appts = table.deleteAppt(appts, calendarDayList.getFirst().getAppts().getFirst());
		calendarDayList = table.getApptRange(appts, firstDayCalendar, secondDayCalendar);
		assertEquals(0, calendarDayList.getFirst().getSizeAppts()); //assert that the first appointment has been removed

		
		assertNull(table.deleteAppt(appts, null));//Removing a null appointment should do nothing
	
	}

	//Tests deleteApt() with a null appointment 
	@Test
	public void test02()  throws Throwable{
		TimeTable table = new TimeTable();
		LinkedList<Appt> appts = new LinkedList<Appt>();
		Appt appt = new Appt(11,45,2,2,2017, null, null);
		appts.add(appt);
		assertNull(table.deleteAppt(null, appt));
	}

	//Tests deleteApt() with an invalid appointment 
	@Test
	public void test03()  throws Throwable{
		TimeTable table = new TimeTable();
		LinkedList<Appt> appts = new LinkedList<Appt>();
		Appt appt = new Appt(11,45,2,2,2017, null, null);
		appts.add(appt);
		Appt appt2 = new Appt(112,45,2,2,2017, null, null);		
		assertNull(table.deleteAppt(appts, appt2));
	}

	//Tests deleteApt() with a valid appointment not added to the TimeTable
	@Test
	public void test04()  throws Throwable{
		TimeTable table = new TimeTable();
		LinkedList<Appt> appts = new LinkedList<Appt>();
		Appt appt = new Appt(11,45,2,2,2017, null, null);
		appts.add(appt);
		Appt appt2 = new Appt(12,45,2,2,2017, null, null);		
		assertNull(table.deleteAppt(appts, appt2));
	}
	//Tests the ordering of days with getApptRage() & deleteAppt()
	@Test
	public void test05()  throws Throwable  {
		TimeTable table = new TimeTable();
		int hour = 13,
			min = 30,
			day = 1,
			month = 10,
			year = 2017,
			hour2 = 10,
			min2 = 0,
			day2 = 29,
			month2 = 11,
			year2 = 2017;
		
		Appt firstAppointment = new Appt(hour, min, day, month, year, "Prostate Exam", "..."),
				secondAppointment = new Appt(hour2, min2, day2, month2, year2, "Dentist", "Having all the teeth removed.");

		GregorianCalendar firstDayCalendar = new GregorianCalendar(year, month, day),
				secondDayCalendar = new GregorianCalendar(year2, month2, day2);
		

		LinkedList<Appt> appts = new LinkedList<Appt>();
		
		appts.add(firstAppointment);
		appts.add(secondAppointment);
		
		//LinkedList<CalDay> calendarDayList = table.getApptRange(appts, firstDayCalendar, secondDayCalendar);		

		// Try with days in wrong order
		exception.expect(IllegalArgumentException.class);
		LinkedList<CalDay> calendarDayList = table.getApptRange(appts, secondDayCalendar, firstDayCalendar);	

		byte count = 0;
		for (CalDay calendarDay : calendarDayList)
			count += calendarDay.getSizeAppts();
		assertEquals(0, count);
		assertNull(calendarDayList);
	}
}
