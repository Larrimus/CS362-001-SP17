package edu.osu.cs362;
/**
 *  This class provides a basic set of test cases for the
 *  CalDay class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalDayTest {
	private static final long TestTimeout = 10 * 500 * 1; //Timeout at 5 seconds
	private static final int NUM_TESTS = 10000;

    //Test addAppt()
    @Test
	public void test05() {
    	long startTime = Calendar.getInstance().getTimeInMillis(),
				elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		System.out.println("Start testing...");
    	
    	Random rand = new Random();

		int startHour,
			startMinute,
			startDay,
			startMonth,
			startYear,
			endHour,
			endMinute,
			endDay,
			endMonth,
			endYear;
		short length;
		String startTitle,
			startDescription,
			endTitle,
			endDescription;
		boolean isValid1,
			isValid2;
		char[] text = new char[300];

    	GregorianCalendar gregCalendar;
    	CalDay calendarDay;
    	for(int iteration = 0;  elapsed < TestTimeout; iteration++){
			startHour = rand.nextInt(30) - 1;
			startMinute = rand.nextInt(70) - 1;
			startDay = rand.nextInt(50);
			startMonth = rand.nextInt(20);
			startYear = rand.nextInt(3000);
			length = (byte)(rand.nextInt(100));
			if(length > 0){
				for (int i = 0; i < length; i++)
					text[i] = (char)(rand.nextInt('~' - ' ') + ' ');
				text[length - 1] = '\0';
				startTitle = new String(text);
			}else
				startTitle = null;
			length = (byte)(rand.nextInt(200));
			if(length > 0){
				for (int i = 0; i < length; i++)
					text[i] = (char)(rand.nextInt('~' - ' ') + ' ');
				text[length - 1] = '\0';
				startDescription = new String(text);
			}else
				startDescription = null;

			endHour = rand.nextInt(30) - 1;
			endMinute = rand.nextInt(70) - 1;
			endDay = rand.nextInt(50);
			endMonth = rand.nextInt(20);
			endYear = rand.nextInt(3000);
			length = (byte)(rand.nextInt(100));
			if(length > 0){
				for (int i = 0; i < length; i++)
					text[i] = (char)(rand.nextInt('~' - ' ') + ' ');
				text[length - 1] = '\0';
				endTitle = new String(text);
			}else
				endTitle = null;
			length = (byte)(rand.nextInt(200));
			if(length > 0){
				for (int i = 0; i < length; i++)
					text[i] = (char)(rand.nextInt('~' - ' ') + ' ');
				text[length - 1] = '\0';
				endDescription = new String(text);
			}else
				endDescription = null;
			
	    	gregCalendar = new GregorianCalendar(startYear, startMonth, startDay);
	    	calendarDay = new CalDay(gregCalendar);
	
	    	Appt appointment1 = new Appt(startHour, startMinute, startDay, startMonth, startYear, startTitle, startDescription),
	    			appointment2 = new Appt(endHour, endMinute, endDay, endMonth, endYear, endTitle, endDescription);
	
	    	// Ensure appointments are valid
	    	isValid1 = appointment1.getValid();
	    	isValid2 = appointment2.getValid();
	
	    	// There are no existing Appts
			assertEquals(0, calendarDay.getSizeAppts());
	
			if(isValid1){
		    	// One appointment added
				calendarDay.addAppt(appointment1);
				assertEquals(appointment1, calendarDay.getAppts().getFirst());
			}
			else{
				// Adding invalid Appts should not affect CalDay size
				int size = calendarDay.getSizeAppts();
				calendarDay.addAppt(appointment1);
				assertEquals(size, calendarDay.getSizeAppts());
			}
			if(isValid2){
		    	// One appointment added
				calendarDay.addAppt(appointment2);
			}
			else{
				// Adding invalid Appts should not affect CalDay size
				int size = calendarDay.getSizeAppts();
				calendarDay.addAppt(appointment2);
				assertEquals(size, calendarDay.getSizeAppts());
			}
			elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
	        if((iteration%10000)==0 && iteration!=0 )
	              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
		}
		System.out.println("Done testing...");
	}


    /*/Test toString()
	@Test
	public void test06() {
		int year = 2017,
			month  = 5,
			day = 26;

		GregorianCalendar gregCalendar = new GregorianCalendar(year, month, day);
		CalDay calendarDay = new CalDay(gregCalendar);

		Appt appointment1 = new Appt(18, 10, day, month, year, "Evening", null),
			appointment2 = new Appt(7, 30, day, month, year, "Morning", null);

		calendarDay.addAppt(appointment1);
		calendarDay.addAppt(appointment2);

		//Appointments added successfully
		assertEquals(2, calendarDay.getSizeAppts());

		// String is correct
		assertEquals("\t --- 5/26/2017 --- \n" +
				" --- -------- Appointments ------------ --- \n" +
				"\t5/26/2017 at 7:30am ,Morning, \n" +
				" \t5/26/2017 at 6:10pm ,Evening, \n" +
				" \n",
				calendarDay.toString());

		// Invalid CalDay should print nothing
		calendarDay.valid = false;
		assertEquals("", calendarDay.toString());
	}*/
}
