package models;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class SlotTest {
	
	Slot defaultSlot;
	Timeslot defaultTimeslot;
	Room defaultRoom;
	Talk defaultTalk;
	
	@Rule
	public TestName name = new TestName();
	
	@Before
	public void setUp() throws Exception {		
				
		GregorianCalendar start = new GregorianCalendar(2011,01,01,12,00);
		start.setTimeZone(TimeZone.getTimeZone("UTC"));
		GregorianCalendar end = new GregorianCalendar(2011,01,01,12,30);
		end.setTimeZone(TimeZone.getTimeZone("UTC"));
			
		defaultTimeslot = new Timeslot(start, end);
		defaultRoom = new Room();
		defaultSlot = new Slot(defaultTimeslot, defaultRoom);
		defaultTalk = new Talk("Test title", "Test description", "Test speaker");
	}	
	
	@Test
	public void testSlot() {
		
		// Announce
		System.out.println(this.name.getMethodName());
		
		assertTrue(defaultSlot.getTimeslot() == defaultTimeslot);
		assertTrue(defaultSlot.getRoom() == defaultRoom);
		assertTrue(defaultSlot.getTalk() == null);
		assertTrue(defaultSlot.isEmpty() == true);
		assertTrue(defaultSlot.isLocked() == false);
		assertNotNull(defaultSlot.getCreatedAt());
		assertEquals(defaultSlot.getCreatedAt(), defaultSlot.getUpdatedAt());		

		
	}

	@Test
	public void testSetGetTalk() {
		
		// Announce
		System.out.println(this.name.getMethodName());
		
		assertTrue(defaultSlot.getTalk() == null);
		assertTrue(defaultSlot.isEmpty() == true);
		
		defaultSlot.setTalk(defaultTalk);
		
		assertTrue(defaultSlot.getTalk().equals(defaultTalk));
		assertTrue(defaultSlot.isEmpty() == false);	
		
	}
	
	@Test
	public void testRemoveTalk() {
		
		// Announce
		System.out.println(this.name.getMethodName());
		
		defaultSlot.setTalk(defaultTalk);				
		
		assertTrue(defaultSlot.getTalk().equals(defaultTalk));
		assertTrue(defaultSlot.isEmpty() == false);
		
		defaultSlot.removeTalk();
		
		assertTrue(defaultSlot.getTalk() == null);
		assertTrue(defaultSlot.isEmpty() == true);
		
	}	

	@Test
	public void testIsEmpty() {
		
		// Announce
		System.out.println(this.name.getMethodName());
		
		assertTrue(defaultSlot.getTalk() == null);
		assertTrue(defaultSlot.isEmpty() == true);
		
		defaultSlot.setTalk(defaultTalk);
		
		assertTrue(defaultSlot.getTalk().equals(defaultTalk));
		assertTrue(defaultSlot.isEmpty() == false);	
		
		defaultSlot.removeTalk();

		assertTrue(defaultSlot.getTalk() == null);
		assertTrue(defaultSlot.isEmpty() == true);
		
	}
	
	@Test
	public void testLockUnlockIsLocked() {
		
		// Announce
		System.out.println(this.name.getMethodName());						
		
		assertTrue(defaultSlot.isLocked() == false);
		
		defaultSlot.lock();		
		assertTrue(defaultSlot.isLocked() == true);

		defaultSlot.unlock();		
		assertTrue(defaultSlot.isLocked() == false);
		
	}
	
	
}
