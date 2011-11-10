package models;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class DataObjectTest {

	DataObject defaultDataObject;
	
	@Before
	public void setUp() throws Exception {
		
		defaultDataObject = new MockDataObject();
		
	}
	
	@Rule
	public TestName name = new TestName();

	@Test
	public void testGetCreatedAt() {

		// Announce
		System.out.println(this.name.getMethodName());
		
		assertNotNull(defaultDataObject.getCreatedAt());
		assertTrue(defaultDataObject.getCreatedAt() instanceof Calendar);					
		
	}

	@Test
	public void testGetUpdatedAt() {

		// Announce
		System.out.println(this.name.getMethodName());
		
		assertNotNull(defaultDataObject.getUpdatedAt());
		assertTrue(defaultDataObject.getUpdatedAt() instanceof Calendar);					
		
		assertEquals(defaultDataObject.getCreatedAt(), defaultDataObject.getUpdatedAt());
		
		// Pause for a second to ensure that updatedAt should be different to createdAt
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			fail("Unable to pause during " + this.name.getMethodName());
		}
		
		// Force a change to updatedAt
		defaultDataObject.save();	
	
		// Check that updatedAt is no longer the same as createdAt
		assertFalse(defaultDataObject.getCreatedAt().equals(defaultDataObject.getUpdatedAt()));
		
		// Check that updatedAt is a point in time later than createdAt
		assertTrue(defaultDataObject.getUpdatedAt() instanceof Calendar);	
		assertTrue(defaultDataObject.getUpdatedAt().compareTo(defaultDataObject.getCreatedAt()) > 0);		
	}

	

	// Mock object
    public class MockDataObject extends DataObject {}
	
}
