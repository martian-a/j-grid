package models;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.TimeZone;

import models.Talk;
import models.Timeslot;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class TimeslotTest {

	Timeslot defaultTimeslot;
	GregorianCalendar defaultStart;
	GregorianCalendar defaultEnd;
	Talk defaultTalk;	
	
	@Rule
	public TestName name = new TestName();
	
	@Before
	public void setUp() throws Exception {
		
		defaultStart = new GregorianCalendar(2011,01,01,12,00);
		defaultStart.setTimeZone(DataObject.TIMEZONE);
		defaultEnd = new GregorianCalendar(2011,01,01,12,30);
		defaultEnd.setTimeZone(DataObject.TIMEZONE);
			
		defaultTimeslot = new Timeslot(defaultStart, defaultEnd);	
		defaultTalk = new Talk("Test title", "Test description", "Test speaker");
	}

	@Test
	public void testTimeslot() {
		
		// Announce
		System.out.println(this.name.getMethodName());
				
		assertTrue(defaultTimeslot.getStart() == defaultStart);
		assertTrue(defaultTimeslot.getEnd() == defaultEnd);
		assertTrue(defaultTimeslot.getName() == null);
		assertTrue(defaultTimeslot.getGlobalTalk() == null);
		assertTrue(defaultTimeslot.hasGlobalTalk() == false);
		assertNotNull(defaultTimeslot.getCreatedAt());
		assertEquals(defaultTimeslot.getCreatedAt(), defaultTimeslot.getUpdatedAt());		

	}

	@Test
	public void testSetGetName() {
		
		// Announce
		System.out.println(this.name.getMethodName());
		
		assertTrue(defaultTimeslot.getName() == null);
		
		String testName1 = "Test Timeslot 1";
		defaultTimeslot.setName(testName1);
		
		assertEquals(defaultTimeslot.getName(), testName1);
		
		String testName2 = "Test Timeslot 2";
		defaultTimeslot.setName(testName2);
			
		assertEquals(defaultTimeslot.getName(), testName2);
	}

	@Test
	public void testSetGetStart() {
		
		// Announce
		System.out.println(this.name.getMethodName());
		
		assertTrue(defaultTimeslot.getStart() == defaultStart);
		
		GregorianCalendar testStart1 = new GregorianCalendar(2011,11,11,11,11);				
		testStart1.setTimeZone(DataObject.TIMEZONE);				
		
		defaultTimeslot.setStart(testStart1);
		
		assertEquals(defaultTimeslot.getStart(), testStart1);
		
		GregorianCalendar testStart2 = new GregorianCalendar(2012,12,12,12,12);							
		testStart2.setTimeZone(DataObject.TIMEZONE);
		
		defaultTimeslot.setStart(testStart2);
			
		assertEquals(defaultTimeslot.getStart(), testStart2);		
		
	}

	@Test
	public void testSetGetEnd() {
		
		// Announce
		System.out.println(this.name.getMethodName());
		
		assertTrue(defaultTimeslot.getEnd() == defaultEnd);
		
		GregorianCalendar testEnd1 = new GregorianCalendar(2011,11,11,11,11);				
		testEnd1.setTimeZone(DataObject.TIMEZONE);				
		
		defaultTimeslot.setEnd(testEnd1);
		
		assertEquals(defaultTimeslot.getEnd(), testEnd1);
		
		GregorianCalendar testEnd2 = new GregorianCalendar(2012,12,12,12,12);							
		testEnd2.setTimeZone(DataObject.TIMEZONE);
		
		defaultTimeslot.setEnd(testEnd2);
			
		assertEquals(defaultTimeslot.getEnd(), testEnd2);		
		
	}

	@Test
	public void testSetGetHasGlobalTalk() {

		// Announce
		System.out.println(this.name.getMethodName());

		assertEquals(defaultTimeslot.getGlobalTalk(), null);
		assertFalse(defaultTimeslot.hasGlobalTalk());
		
		defaultTimeslot.setGlobalTalk(defaultTalk);
		
		assertEquals(defaultTimeslot.getGlobalTalk(), defaultTalk);
		assertTrue(defaultTimeslot.hasGlobalTalk());
	}


	@Test
	public void testCancelGlobalTalk() {

		// Announce
		System.out.println(this.name.getMethodName());
		
		defaultTimeslot.setGlobalTalk(defaultTalk);		
		assertTrue(defaultTimeslot.hasGlobalTalk());
		
		defaultTimeslot.cancelGlobalTalk();
		
		assertEquals(defaultTimeslot.getGlobalTalk(), null);
		assertFalse(defaultTimeslot.hasGlobalTalk());
		
	}

	@Test
	public void testHasGlobalTalk() {

		// Announce
		System.out.println(this.name.getMethodName());
		
		assertEquals(defaultTimeslot.getGlobalTalk(), null);
		assertFalse(defaultTimeslot.hasGlobalTalk());		
		defaultTimeslot.setGlobalTalk(defaultTalk);	
		
		assertTrue(defaultTimeslot.hasGlobalTalk());		
		
		defaultTimeslot.cancelGlobalTalk();
		assertFalse(defaultTimeslot.hasGlobalTalk());
		
	}

}
