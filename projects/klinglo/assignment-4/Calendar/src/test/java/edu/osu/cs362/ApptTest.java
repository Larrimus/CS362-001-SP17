package edu.osu.cs362;
/**
 *  This class provides a basic set of test cases for the
 *  Appt class.
 */
import org.junit.Test;
import java.util.Calendar;
import java.util.Random;


import static org.junit.Assert.*;

public class ApptTest {
	private static final long TestTimeout = 10 * 500 * 1; //Timeout at 5 seconds
	private static final int NUM_TESTS = 10000;

    //Test that getValid() works as expected.
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
		char[] text = new char[200];
		String title,
			description;
		boolean isValid;
		
		for(int iteration = 0;  elapsed < TestTimeout; iteration++){
			startHour = rand.nextInt(30) - 1;
			startMinute = rand.nextInt(70) - 1;
			startDay = rand.nextInt(50);
			startMonth = rand.nextInt(20);
			startYear = rand.nextInt(3000);
			length = (byte)(rand.nextInt(100));
			//if(length > 0){
				for (int i = 0; i < length; i++)
					text[i] = (char)(rand.nextInt('~' - ' ') + ' ');
				text[length] = '\0';
				title = new String(text);
			//}else
				//title = null;
			length = (short)(rand.nextInt(200));
			//System.out.println(length);
			//if(length > 0){
				for (int i = 0; i < length; i++)
					text[i] = (char)(rand.nextInt('~' - ' ') + ' ');
				text[length] = '\0';
				description = new String(text);
			//}else
				//description = null;
			
			//Construct a new Appointment object with the initial data	
			Appt appt = new Appt(startHour,
			        startMinute,
			        startDay,
			        startMonth,
			        startYear,
			        title,
			        description);
			if(startHour<0 || startHour>23)
		   		isValid = false;
		   	else
		       	if(startMinute<0 || startMinute>59)
		       		isValid = false;
		       	else
		           	if(startDay<1 || startDay>31)
		           		isValid = false;
		           	else
		               	if(startMonth<1 || startMonth>12)
		               		isValid = false;
		               	else
		               		isValid = true;

			//Assertions
			assertEquals(isValid, appt.getValid());
			
			assertEquals(title, appt.getTitle());
		    assertEquals(description, appt.getDescription());
		    
		    elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
	        if((iteration%10000)==0 && iteration!=0 )
	              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
		}
		System.out.println("Done testing...");
	}
	
}