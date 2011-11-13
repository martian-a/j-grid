package models;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TreeSet;

import javax.xml.transform.TransformerException;

import models.Talk;
import models.Timeslot;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.w3c.dom.Document;

import uk.co.bluegumtree.code.java.util.XmlReadException;
import uk.co.bluegumtree.code.java.util.XmlReader;

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
	
	@Test
	public void testParseDate_InSummer() {
		
		// Announce
		System.out.println(this.name.getMethodName());
		
		String dateString = "2011-08-29T09:30:00Z";
		GregorianCalendar result = Timeslot.parseDate(dateString);
		
		System.out.println(result.getTime().toString());
		
		assertEquals(2011, result.get(Calendar.YEAR));
		assertEquals(7, result.get(Calendar.MONTH)); // January == 0
		assertEquals(Calendar.AUGUST, result.get(Calendar.MONTH));
		assertEquals(29, result.get(Calendar.DAY_OF_MONTH));
		assertEquals(9, result.get(Calendar.HOUR_OF_DAY));
		assertEquals(30, result.get(Calendar.MINUTE));
		assertEquals(0, result.get(Calendar.SECOND));
		assertEquals(DataObject.TIMEZONE, result.getTimeZone());
		
	}
	
	@Test
	public void testParseDate_InWinter() {
		
		// Announce
		System.out.println(this.name.getMethodName());
		
		String dateString = "2011-12-29T09:30:00Z";
		GregorianCalendar result = Timeslot.parseDate(dateString);
		
		System.out.println(result.getTime().toString());
		
		assertEquals(2011, result.get(Calendar.YEAR));
		assertEquals(11, result.get(Calendar.MONTH)); // January == 0
		assertEquals(Calendar.DECEMBER, result.get(Calendar.MONTH));
		assertEquals(29, result.get(Calendar.DAY_OF_MONTH));
		assertEquals(9, result.get(Calendar.HOUR_OF_DAY));
		assertEquals(30, result.get(Calendar.MINUTE));
		assertEquals(0, result.get(Calendar.SECOND));
		assertEquals(DataObject.TIMEZONE, result.getTimeZone());
		
	}
	
	@Test
	public void testLoad_duration() {
		
		// Announce
		System.out.println(this.name.getMethodName());
				
		// Load the XML file into a DOM object
		File seedFile = Paths.get("./db/seeds/test/timeslots/duration.xml").toFile();
		
		try {
		
			// Construct the timeslots
			TreeSet<Timeslot> result = Timeslot.load(seedFile);			
			Timeslot[] timeslots = new Timeslot[3]; 			
			result.toArray(timeslots);
					
			// Check that there are the correct number of timeslots
			assertEquals(3, timeslots.length);
						
			// Check the actual values meet the expected values for each timeslot
			assertEquals(Timeslot.parseDate("2011-10-29T09:30:00Z"), timeslots[0].getStart());
			assertEquals(Timeslot.parseDate("2011-10-29T10:00:00Z"), timeslots[0].getEnd());
			assertEquals(Timeslot.parseDate("2011-10-29T10:00:00Z"), timeslots[1].getStart());
			assertEquals(Timeslot.parseDate("2011-10-29T10:20:00Z"), timeslots[1].getEnd());
			assertEquals(Timeslot.parseDate("2011-10-29T10:20:00Z"), timeslots[2].getStart());
			assertEquals(Timeslot.parseDate("2011-10-29T10:30:00Z"), timeslots[2].getEnd());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}										
	}
	
	@Test
	public void testLoad_break() {
		
		// Announce
		System.out.println(this.name.getMethodName());
				
		// Load the XML file into a DOM object
		File seedFile = Paths.get("./db/seeds/test/timeslots/break.xml").toFile();
		
		try {
		
			// Construct the timeslots
			TreeSet<Timeslot> result = Timeslot.load(seedFile);	
			
			// Check that there are the correct number of timeslots
			assertEquals(6, result.size());
			
			Timeslot[] timeslots = new Timeslot[6]; 			
			result.toArray(timeslots);					
						
			// Check the actual values meet the expected values for each timeslot
			assertEquals(Timeslot.parseDate("2011-10-29T09:30:00Z"), timeslots[0].getStart());
			assertEquals(Timeslot.parseDate("2011-10-29T10:00:00Z"), timeslots[0].getEnd());
			assertEquals(Timeslot.parseDate("2011-10-29T10:15:00Z"), timeslots[1].getStart());
			assertEquals(Timeslot.parseDate("2011-10-29T10:35:00Z"), timeslots[1].getEnd());
			assertEquals(Timeslot.parseDate("2011-10-29T10:45:00Z"), timeslots[2].getStart());
			assertEquals(Timeslot.parseDate("2011-10-29T10:55:00Z"), timeslots[2].getEnd());
			
			assertEquals(Timeslot.parseDate("2011-10-29T11:05:00Z"), timeslots[3].getStart());
			assertEquals(Timeslot.parseDate("2011-10-29T11:15:00Z"), timeslots[3].getEnd());
			assertEquals(Timeslot.parseDate("2011-10-29T11:20:00Z"), timeslots[4].getStart());
			assertEquals(Timeslot.parseDate("2011-10-29T11:30:00Z"), timeslots[4].getEnd());
			assertEquals(Timeslot.parseDate("2011-10-29T11:35:00Z"), timeslots[5].getStart());
			assertEquals(Timeslot.parseDate("2011-10-29T11:45:00Z"), timeslots[5].getEnd());			
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}										
	}
	
	@Test
	public void testLoad_repeat() {
		
		// Announce
		System.out.println(this.name.getMethodName());
				
		// Load the XML file into a DOM object
		File seedFile = Paths.get("./db/seeds/test/timeslots/repeat.xml").toFile();
		
		try {
		
			// Construct the timeslots
			TreeSet<Timeslot> result = Timeslot.load(seedFile);
			
			// Check that there are the correct number of timeslots
			assertEquals(12, result.size());			
			
			Timeslot[] timeslots = new Timeslot[12]; 			
			result.toArray(timeslots);							
						
			// Check the actual values meet the expected values for each timeslot
			assertEquals(Timeslot.parseDate("2011-10-29T09:30:00Z"), timeslots[0].getStart());
			assertEquals(Timeslot.parseDate("2011-10-29T09:40:00Z"), timeslots[0].getEnd());
			assertEquals(Timeslot.parseDate("2011-10-29T09:40:00Z"), timeslots[1].getStart());
			assertEquals(Timeslot.parseDate("2011-10-29T09:50:00Z"), timeslots[1].getEnd());
			assertEquals(Timeslot.parseDate("2011-10-29T09:50:00Z"), timeslots[2].getStart());
			assertEquals(Timeslot.parseDate("2011-10-29T10:00:00Z"), timeslots[2].getEnd());

			assertEquals(Timeslot.parseDate("2011-10-29T11:00:00Z"), timeslots[3].getStart());
			assertEquals(Timeslot.parseDate("2011-10-29T11:10:00Z"), timeslots[3].getEnd());
			assertEquals(Timeslot.parseDate("2011-10-29T11:15:00Z"), timeslots[4].getStart());
			assertEquals(Timeslot.parseDate("2011-10-29T11:25:00Z"), timeslots[4].getEnd());
			assertEquals(Timeslot.parseDate("2011-10-29T11:30:00Z"), timeslots[5].getStart());
			assertEquals(Timeslot.parseDate("2011-10-29T11:40:00Z"), timeslots[5].getEnd());
			
			assertEquals(Timeslot.parseDate("2011-10-29T11:40:00Z"), timeslots[6].getStart());
			assertEquals(Timeslot.parseDate("2011-10-29T11:50:00Z"), timeslots[6].getEnd());
			assertEquals(Timeslot.parseDate("2011-10-29T11:50:00Z"), timeslots[7].getStart());
			assertEquals(Timeslot.parseDate("2011-10-29T12:00:00Z"), timeslots[7].getEnd());
			assertEquals(Timeslot.parseDate("2011-10-29T12:00:00Z"), timeslots[8].getStart());
			assertEquals(Timeslot.parseDate("2011-10-29T12:10:00Z"), timeslots[8].getEnd());
			
			assertEquals(Timeslot.parseDate("2011-10-29T12:10:00Z"), timeslots[9].getStart());
			assertEquals(Timeslot.parseDate("2011-10-29T12:20:00Z"), timeslots[9].getEnd());
			assertEquals(Timeslot.parseDate("2011-10-29T12:25:00Z"), timeslots[10].getStart());
			assertEquals(Timeslot.parseDate("2011-10-29T12:35:00Z"), timeslots[10].getEnd());
			assertEquals(Timeslot.parseDate("2011-10-29T12:40:00Z"), timeslots[11].getStart());
			assertEquals(Timeslot.parseDate("2011-10-29T12:50:00Z"), timeslots[11].getEnd());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}										
	}
}
