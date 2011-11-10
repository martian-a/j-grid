package models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class GridTest {

	Grid defaultGrid;
	
	@Rule
	public TestName name = new TestName();
	
	@Before
	public void setUp() throws Exception {
		
		defaultGrid = new Grid();
	}	

	@Test
	public void testGrid() {

		assertTrue(defaultGrid.getSlots() == null);
		
	}
		

}
