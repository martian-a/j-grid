package models;

import java.io.File;
import java.io.InputStream;
import java.util.TreeSet;
import java.util.zip.DataFormatException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import uk.co.bluegumtree.code.java.util.Logger;
import uk.co.bluegumtree.code.java.util.XmlReader;

class Room extends DataObject implements Comparable<Room> {

	private String shortcode;
	private String name;
	private int capacity;
	private String description;
	private String facilities;
	
	private Room() {
		super();
		this.shortcode = null;
		this.name = null;		
		this.capacity = 0;
		this.description = null;
		this.facilities = null;
	}
	
	public Room(String shortcodeIn) {
		this(shortcodeIn, null, null, null, null);
	}
	
	public Room(String shortcodeIn, String nameIn, Integer capacityIn, String descriptionIn, String facilitiesIn) {
		this();
		this.setShortcode(shortcodeIn);
		this.setName(nameIn);
		try {
			this.setCapacity(capacityIn);
		} catch (DataFormatException e) {
			Logger.log(e);
		}
		this.setDescription(descriptionIn);
		this.setFacilities(facilitiesIn);
	}
	
	/**
	 * Set the name of this room to the value specified. 
	 * @param nameIn the new name of this room.
	 */
	public void setName(String nameIn) {
		this.name = nameIn;
	}
	
	/**
	 * @return the full name of this room
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Set the shortcode for this room to the value specified.
	 * @param codeIn the new shortcode for this room.
	 */
	public void setShortcode(String codeIn) {
		
		// TODO: Validation: check that the code is no longer than 3 characters long.
		// TODO: Validation: check that the code is only made up of alpha-numeric characters.
		
		this.shortcode = codeIn;
	}
	
	/**
	 * @return this room's shortcode
	 */
	public String getShortcode() {
		return this.shortcode;
	}
	
	/**
	 * Set a description of this room.
	 * @param descriptionIn the new description of this room.
	 */
	public void setDescription(String descriptionIn) {
		
		// TODO: Validation: check that the description is no longer than 140 characters long.				
		
		this.description = descriptionIn;
	}
	
	/**	
	 * @return the description of this room
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Set a description of the facilities available in this room
	 * @param facilitiesIn a description of the facilities available in this room
	 */
	public void setFacilities(String facilitiesIn) {		
		
		this.facilities = facilitiesIn;
	}
	
	/**
	 * @return a textual list of the facilities in this room
	 */
	public String getFacilities() {
		return this.facilities;
	}
	
	/**
	 * Specify the approximate number of people that this room can hold.
	 * @param capacityIn the number of people that the room can hold
	 * @throws DataFormatException 
	 */
	public void setCapacity(Integer capacityIn) throws DataFormatException {
						
		if (capacityIn == null || capacityIn < 0) {		
			throw new DataFormatException("Positive integer expected.");
		}
		this.capacity = capacityIn;	
	}	
	
	/**
	 * @return the capacity of this room
	 */
	public int getCapacity() {
		return this.capacity;
	}
	
	/**
	 * Parses an XML seed file to create the rooms required for the schedule
	 * @param seedXml XML seed file
	 * @return a collection of rooms
	 */
	public static TreeSet<Room> load(Document seedXml) {
		
		TreeSet<Room> rooms = new TreeSet<Room>();
			
		NodeList roomsXml = XmlReader.readNodeList(seedXml, "event/rooms/room");
		for (int i = 0; i < roomsXml.getLength(); i++) {
			rooms.add(Room.load((Element) roomsXml.item(i)));
		}
		
		return rooms;
	}
	
	/**
	 * Constructs a new room object from seed data.
	 * @param roomXml an XML element containing the seed data
	 * @return the newly constructed room object
	 */
	public static Room load(Element roomXml) {		
				
		String shortcode = XmlReader.readString(roomXml, "@shortcode");
		String name  = XmlReader.readString(roomXml, "name");
		Integer capacity = XmlReader.readInteger(roomXml, "@capacity");
		String description = XmlReader.readString(roomXml, "description");
		String facilities = XmlReader.readString(roomXml, "facilities");				
		
		return new Room(shortcode, name, capacity, description, facilities);
	}
	
	@Override
	public int compareTo(Room anotherRoom) {

		// If the Rooms have the same shortcode
		// compare their names
		if (this.equals(anotherRoom)) {
			
			if (this.name.equals(anotherRoom.name)) {
				return this.name.compareToIgnoreCase(anotherRoom.name);
			}
			
		}
		
		// Compare their shortcodes
		return this.shortcode.compareToIgnoreCase(anotherRoom.shortcode);
	}
	
	public boolean equals(Room anotherRoom) {
		
		if (this.shortcode.equals(anotherRoom.shortcode)) {
			return true;
		}		
		return false;
	}
	
	
}
