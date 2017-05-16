package edu.osu.cs362;
/**
 *  This class provides a basic set of test cases for the
 *  Appt class.
 */
import org.junit.Test;

import static org.junit.Assert.*;

public class ApptTest {
    /**
     * Test that the get methods 
     * (getStartMinute(), getStartHour(), getStartDay(), getStartMonth(), getStartYear(), 
     * getDescription(), getValid()) 
     * work as expected.
     */
	 @Test
	  public void test01()  throws Throwable  {
		 int startHour = 0,
		 	startMinute = 0,
		 	startDay = 26,
		 	startMonth = 5,
		 	startYear = 2017;
		 String title = "Birthday Party",
		 	description = "This is my birthday party.";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(startHour,
		          startMinute,
		          startDay,
		          startMonth,
		          startYear,
		          title,
		          description);
		 //Assertions
		 assertTrue(appt.getValid());
		 assertEquals(startHour, appt.getStartHour());
		 assertEquals(startMinute, appt.getStartMinute());
		 assertEquals(startDay, appt.getStartDay());
		 assertEquals(startMonth, appt.getStartMonth());
		 assertEquals(startYear, appt.getStartYear());
		 assertEquals(title, appt.getTitle());
		 assertEquals(description, appt.getDescription());        		
	 }

	//Test that toString() works properly for pm times
	@Test
	public void test02() throws Throwable {
		int startHour = 23, //Change to 23 to test bounds
			startMinute = 59, //These are all edge cases
			startDay = 31,
			startMonth = 12,
			startYear = 2017;
		String title = "Show",
			description = "The show you planned";
		//Construct a new Appointment object with the initial data
		Appt appt = new Appt(startHour,
				startMinute,
				startDay,
				startMonth,
				startYear,
				title,
				description);
		
		// assertions
		assertTrue(appt.getValid());
		assertEquals("	12/31/2017 at 11:59pm ,Show, The show you planned\n", appt.toString());
		
		// Test with invalid appointment
		appt.setStartHour(666);
		assertFalse(appt.getValid());
		assertEquals(null, appt.toString());
	
	}
	
	//Test that toString() works properly for am times
	@Test
	public void test03() throws Throwable {
		int startHour = 11, //Boundary case
			startMinute = 0,
			startDay = 2,
			startMonth = 12,
			startYear = 2017;
		String title = "Show",
			description = "The show you planned";
		//Construct a new Appointment object with the initial data
		Appt appt = new Appt(startHour,
				startMinute,
				startDay,
				startMonth,
				startYear,
				title,
				description);
		
		// assertions
		assertTrue(appt.getValid());
		assertEquals("	12/2/2017 at 11:0am ,Show, The show you planned\n", appt.toString());
		
	}
	
	//Test that toString() works properly for an hour value of 0
	@Test
	public void test04() throws Throwable {
		int startHour = 0,
			startMinute = 0,
			startDay = 1,
			startMonth = 1,
			startYear = 2017;
		String title = "Show",
			description = "The show you planned";
		//Construct a new Appointment object with the initial data
		Appt appt = new Appt(startHour,
				startMinute,
				startDay,
				startMonth,
				startYear,
				title,
				description);

		// assertions
		assertTrue(appt.getValid());
		assertEquals("	1/1/2017 at 12:0am ,Show, The show you planned\n", appt.toString());
		
	}
	
	 //Test that startHour in setStartMinute() has to be valid
	 @Test
	 public void test05() throws Throwable {
		 int startHour = 21,
		 	startMinute = 0,
		 	startDay = 2,
		 	startMonth = 12,
		 	startYear = 2017;
	      //Construct a new Appointment object with the initial data
	      Appt appt = new Appt(startHour,
	              startMinute,
	              startDay,
	              startMonth,
	              startYear,
	              "",
	              "");
	      // Initially valid
	      assertTrue(appt.getValid());

	      // Hour greater than 23
	      appt.setStartHour(24);
	      assertFalse(appt.getValid());
	      appt.setStartHour(23);

	      // Hour less than 0
	      appt.setStartHour(-1);
	      assertFalse(appt.getValid());
	  }

	  //Test that startMinute in setStartMinute() has to be valid
	  @Test
	  public void test06() throws Throwable {
		  int startHour = 21,
		  	startMinute = 0,
		  	startDay = 2,
	      	startMonth = 12,
		  	startYear = 2017;
	      //Construct a new Appointment object with the initial data
	      Appt appt = new Appt(startHour,
	              startMinute,
	              startDay,
	              startMonth,
	              startYear,
	              "",
	              "");
	      // Initially valid
	      assertTrue(appt.getValid());

	      // Minute greater than 59
	      appt.setStartMinute(66);
	      assertFalse(appt.getValid());
	      appt.setStartMinute(13);

	      // Minute less than 0
	      appt.setStartMinute(-1);
	      assertFalse(appt.getValid());
	  }

	  //Test that startDay in setStartDay() has to be valid
	  @Test
	  public void test07() throws Throwable {
		  int startHour = 21,
		  	startMinute = 0,
		  	startDay = 2,
	      	startMonth = 12,
		  	startYear = 2017;
	      String title = "Day Test",
	      	description = "Using this appt to test invalid days";
	      //Construct a new Appointment object with the initial data
	      Appt appt = new Appt(startHour,
	              startMinute,
	              startDay,
	              startMonth,
	              startYear,
	              title,
	              description);
	      // Initially valid
	      assertTrue(appt.getValid());

	      // Day greater than 31
	      appt.setStartDay(43);
	      assertFalse(appt.getValid());
	      appt.setStartDay(2);

	      // Day less than 1
	      appt.setStartDay(0);
	      assertFalse(appt.getValid());
	  }

	  //Test that startMonth in setStartMonth() has to be valid
	  @Test
	  public void test08() throws Throwable {
		  int startHour = 21,
		  	startMinute = 0,
		  	startDay = 2,
	      	startMonth = 12,
		  	startYear = 2017;
	      String title = "Month Test",
	      	 description = "Using this appt to test invalid months";
	      //Construct a new Appointment object with the initial data
	      Appt appt = new Appt(startHour,
	              startMinute,
	              startDay,
	              startMonth,
	              startYear,
	              title,
	              description);
	      // Initially valid
	      assertTrue(appt.getValid());

	      // Month greater than 12
	      appt.setStartMonth(18);
	      assertFalse(appt.getValid());
	      appt.setStartMonth(12);

	      // Month less than 1
	      appt.setStartMonth(0);
	      assertFalse(appt.getValid());
	  }

	  //Test that setStartYear() works
	  @Test
	  public void test09() {
		  int startHour = 21,
		  	startMinute = 0,
		  	startDay = 2,
	      	startMonth = 12,
		  	startYear = 2100;

	      Appt appt = new Appt(startHour,
	              startMinute,
	              startDay,
	              startMonth,
	              startYear,
	              "",
	              "");
	      
	      assertTrue(appt.getValid());

	      assertEquals(startYear, appt.getStartYear());

	      appt.setStartYear(2017);
	      assertEquals(2017, appt.getStartYear());
	  }
	 
	  //Test that NULL strings in setDescription() & setTitle() are handled correctly
	  @Test
	  public void test10() {
		  int startHour = 21,
		  	startMinute = 0,
		  	startDay = 2,
	      	startMonth = 12,
		  	startYear = 2017;
	      String title = null,
	      	description = null;

	      Appt appt = new Appt(startHour,
	              startMinute,
	              startDay,
	              startMonth,
	              startYear,
	              title,
	              description);
	      
	      assertTrue(appt.getValid());

	      assertEquals("", appt.getTitle());
	      assertEquals("", appt.getDescription());
	  }
	
}
