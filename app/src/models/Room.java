package models;

class Room extends DataObject {

	private String name;
	private String shortcode;
	private String description;
	private String facilities;
	private int capacity;
	
	public Room() {
		super();
		this.name = null;
		this.shortcode = null;
		this.description = null;
		this.capacity = 0;
	}
	
	public Room(String nameIn) {
		this();
		this.setName(nameIn);
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
	 */
	public void setCapacity(int capacityIn) {
		
		// TODO: Validation: check that the capacity is a positive number				
		
		this.capacity = capacityIn;
	}
	
	/**
	 * @return the capacity of this room
	 */
	public int getCapacity() {
		return this.capacity;
	}
	
}
