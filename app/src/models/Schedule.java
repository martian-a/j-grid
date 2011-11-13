package models;

import java.io.File;
import java.io.IOException;
import java.util.TreeSet;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

import uk.co.bluegumtree.code.java.util.XmlReadException;
import uk.co.bluegumtree.code.java.util.XmlReader;

public class Schedule {
		
	private TreeSet<Room> rooms;
	private TreeSet<Timeslot> timeslots;
	private TreeSet<Slot> slots;
	
	public Schedule(File seedFile) throws XmlReadException, IOException, TransformerException {
		
		this.load(seedFile);
		
	}
	
	public TreeSet<Room> getRooms() {
		return this.rooms;
	}
	
	public TreeSet<Timeslot> getTimeslots() {
		return this.timeslots;
	}
	
	/**
	 * @return all the slots in the Schedule.
	 */
	public TreeSet<Slot> getSlots() {
		return this.slots;
	}
	
	private void load(File seedFile) throws XmlReadException, IOException, TransformerException {
		
		// Load the XML file into a DOM object
		Document seedXml = XmlReader.read(seedFile);
		
		// Construct the rooms according
		this.rooms = Room.load(seedXml);
		
		// Construct the timeslots
		this.timeslots = Timeslot.load(seedFile);
		
	}
}
