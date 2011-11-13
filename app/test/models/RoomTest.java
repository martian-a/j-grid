package models;

import static org.junit.Assert.*;

import java.util.zip.DataFormatException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class RoomTest {	
	
	String defaultShortcode;
	Room defaultRoom;
	
	@Rule
	public TestName name = new TestName();
	
	@Before
	public void setUp() throws Exception {
		
		defaultShortcode = "tst";
		defaultRoom = new Room(defaultShortcode);
		
	}	
	
	
	@Test
	public void testRoom() {
		
		// Announce
		System.out.println(this.name.getMethodName());
		
		assertTrue(defaultRoom.getName() == null);
		assertTrue(defaultRoom.getShortcode() == defaultShortcode);
		assertTrue(defaultRoom.getDescription() == null);
		assertTrue(defaultRoom.getFacilities() == null);
		assertTrue(defaultRoom.getCapacity() == 0);
		assertNotNull(defaultRoom.getCreatedAt());
		assertEquals(defaultRoom.getCreatedAt(), defaultRoom.getUpdatedAt());		
		
	}

	@Test
	public void testSetGetName() {
		
		// Announce
		System.out.println(this.name.getMethodName());
		
		String testName = "Test Room 1";		
		defaultRoom.setName(testName);	
		assertTrue(defaultRoom.getName().equals(testName));
			
	}	
	
	@Test
	public void testGetShortcode() {
		
		// Announce
		System.out.println(this.name.getMethodName());
		
		String testShortcode = "SCD";
		defaultRoom.setShortcode(testShortcode);
		assertTrue(defaultRoom.getShortcode().equals(testShortcode));
	}
	
	@Test
	public void testGetDescription() {
		
		// Announce
		System.out.println(this.name.getMethodName());
		
		String testDescription = "A sample description";	
		defaultRoom.setDescription(testDescription);
		assertTrue(defaultRoom.getDescription().equals(testDescription));
	}

	@Test
	public void testGetFacilities() {
		
		// Announce
		System.out.println(this.name.getMethodName());		
		
		String testFacilities = "A sample description of the facilities";
		defaultRoom.setFacilities(testFacilities);
		assertTrue(defaultRoom.getFacilities().equals(testFacilities));
	}
	
	@Test
	public void testGetCapacity() {
		
		// Announce
		System.out.println(this.name.getMethodName());		
		
		int testCapacity = 250;
		try {
			defaultRoom.setCapacity(testCapacity);
		} catch (DataFormatException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		assertTrue(defaultRoom.getCapacity() == testCapacity);
	}

}
