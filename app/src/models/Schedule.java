package models;

import java.util.HashSet;

public class Schedule {
	
	private HashSet<Slot> slots;
	
	/**
	 * @return all the slots in the Schedule.
	 */
	public HashSet<Slot> getSlots() {
		return this.slots;
	}

}
