package models;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class TalkTest {
	
	String defaultTitle;
	String defaultDescription;
	String defaultSpeaker;
	Talk defaultTalk;
	
	@Rule
	public TestName name = new TestName();
	
	@Before
	public void setUp() throws Exception {		
		
		defaultTitle = "Test Title";
		defaultDescription = "Test Description";
		defaultSpeaker = "Test Speaker";
		defaultTalk = new Talk(defaultTitle, defaultDescription, defaultSpeaker);
		
	}	

	@Test
	public void testTalk() {
	
		// Announce
		System.out.println(this.name.getMethodName());
		
		assertEquals(defaultTalk.getTitle(), defaultTitle);
		assertEquals(defaultTalk.getDescription(), defaultDescription);
		assertEquals(defaultTalk.getSpeaker(), defaultSpeaker);
		assertNotNull(defaultTalk.getCreatedAt());
		assertEquals(defaultTalk.getCreatedAt(), defaultTalk.getUpdatedAt());		
		
	}

	@Test
	public void testSetGetTitle() {

		// Announce
		System.out.println(this.name.getMethodName());
		
		assertEquals(defaultTalk.getTitle(), defaultTitle);
		
		String alternateTitle = "Test Title";
		defaultTalk.setTitle(alternateTitle);
		
		assertEquals(defaultTalk.getTitle(), alternateTitle);
		
		defaultTalk.setTitle(null);		
		assertEquals(defaultTalk.getTitle(), null);
	}

	@Test
	public void testSetGetDescription() {
		
		// Announce
		System.out.println(this.name.getMethodName());
		
		assertEquals(defaultTalk.getDescription(), defaultDescription);
		
		String alternateDescription = "Test Description";
		defaultTalk.setDescription(alternateDescription);
		
		assertEquals(defaultTalk.getDescription(), alternateDescription);
		
		defaultTalk.setDescription(null);		
		assertEquals(defaultTalk.getDescription(), null);
		
	}

	@Test
	public void testSetGetSpeaker() {
		
		// Announce
		System.out.println(this.name.getMethodName());
		
		assertEquals(defaultTalk.getSpeaker(), defaultSpeaker);
		
		String alternateSpeaker = "alternate Speaker";
		defaultTalk.setSpeaker(alternateSpeaker);
		
		assertEquals(defaultTalk.getSpeaker(), alternateSpeaker);	
		
		defaultTalk.setSpeaker(null);		
		assertEquals(defaultTalk.getSpeaker(), null);
		
	}

}
