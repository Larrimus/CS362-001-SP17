package edu.osu.cs362;
/**
 *  This class provides a basic set of test cases for the
 *  TimeTable class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;




import static org.junit.Assert.*;

public class TimeTableTest {
	private static final long TestTimeout = 10 * 500 * 1; //Timeout at 5 seconds
	private static final int NUM_TESTS = 10000;

	/*Tests everything to do with the size of appointments, appointment lists 
	 * & the number of days between appointments with getApptRange() & deleteAppt()
	 */
	@Test
	public void test01()  throws Throwable  {
		long startTime = Calendar.getInstance().getTimeInMillis(),
				elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		System.out.println("Start testing...");
		
		Random rand = new Random();

		int startHour,
			startMinute,
			startDay,
			startMonth,
			startYear;
		short length;
		String title,
			description;
		boolean isValid;
		char[] text = new char[300];
		
		LinkedList<Appt> appts;
		TimeTable table;
		Appt appointment;

    	GregorianCalendar gregCalendar;
    	CalDay calendarDay;
    	for(int iteration = 0;  elapsed < TestTimeout; iteration++){
    		//if(rand.nextInt(1000) == 0){
			startHour = rand.nextInt(30);
			startMinute = rand.nextInt(70);
			startDay = rand.nextInt(50);
			startMonth = rand.nextInt(20);
			startYear = rand.nextInt(3000);
			length = (byte)(rand.nextInt(100));
			if(length > 0){
				for (int i = 0; i < length; i++)
					text[i] = (char)(rand.nextInt('~' - ' ') + ' ');
				text[length - 1] = '\0';
				title = new String(text);
			}else
				title = null;
			length = (byte)(rand.nextInt(200));
			if(length > 0){
				for (int i = 0; i < length; i++)
					text[i] = (char)(rand.nextInt('~' - ' ') + ' ');
				text[length - 1] = '\0';
				description = new String(text);
			}else
				description = null;
		
			appts = new LinkedList<Appt>();

			table = new TimeTable();
			
			appointment = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);

			
			
			appts.add(appointment);
	
			table = new TimeTable();
			gregCalendar = new GregorianCalendar(startYear, startMonth, startDay);
			
			if(appointment.getValid()){
				byte count = 0;
				for (Appt time : appts){
					if(time != null){
						count++;
					}
				}
				assertEquals(1, count);
				
				appts = table.deleteAppt(appts, appointment);
				

				count = 0;
				for (Appt time : appts){
					if(time != null){
						count++;
					}
				}
			}
			startHour = rand.nextInt(30);
			startMinute = rand.nextInt(70);
			startDay = rand.nextInt(50);
			startMonth = rand.nextInt(20);
			startYear = rand.nextInt(3000);
			appointment = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);
			appts = table.deleteAppt(appts, appointment);
			
			assertEquals(null, table.deleteAppt(appts, appointment));
			assertEquals(null, table.deleteAppt(null, appointment));
			assertEquals(null, table.deleteAppt(appts, null));
			
			elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
	        if((iteration%10000)==0 && iteration!=0 )
	              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
		}
		System.out.println("Done testing...");
	
	}
}
