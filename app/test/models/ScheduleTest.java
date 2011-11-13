package models;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Paths;

import javax.xml.transform.TransformerException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import uk.co.bluegumtree.code.java.util.XmlReadException;

public class ScheduleTest {

	Schedule defaultSchedule;
	
	@Rule
	public TestName name = new TestName();
	
	@Before
	public void setUp() throws Exception {		
		
		File seedData = Paths.get("./db/seeds/test/defaultSchedule.xml").toFile();
		try {
			defaultSchedule = new Schedule(seedData);
		} catch (XmlReadException e) {
			System.err.println(e.getFile());	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	@Test
	public void testSchedule_File() {

		assertNotNull(defaultSchedule.getRooms());
		assertEquals(3, defaultSchedule.getRooms().size());
		
		assertNotNull(defaultSchedule.getTimeslots());
		assertEquals(3, defaultSchedule.getTimeslots().size());
		
		assertTrue(defaultSchedule.getSlots() == null);
		
	}
	
		

}
