package models;

import java.util.HashSet;

public class Grid {
	
	private HashSet<Slot> slots;
	
	/**
	 * @return all the slots in the grid.
	 */
	public HashSet<Slot> getSlots() {
		return this.slots;
	}

}
