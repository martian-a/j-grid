package models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class ScheduleTest {

	Schedule defaultSchedule;
	
	@Rule
	public TestName name = new TestName();
	
	@Before
	public void setUp() throws Exception {
		
		defaultSchedule = new Schedule();
	}	

	@Test
	public void testSchedule() {

		assertTrue(defaultSchedule.getSlots() == null);
		
	}
		

}
