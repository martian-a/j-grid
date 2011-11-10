package models;

public class Slot extends DataObject {
	
	private Timeslot timeslot;
	private Room room;
	private Talk talk;
	private boolean locked;
	
	/**
	 * Sets default values for all attributes
	 */
	private Slot() {
		super();
		this.timeslot = null;
		this.room = null;
		this.talk = null;
		this.locked = false;
	}
	
	/**
	 * Creates a new, unlocked slot
	 * @param when the timeslot that the slot represents
	 * @param where the room that the slot represents
	 */
	public Slot(Timeslot when, Room where) {
		this(when, where, false);
	}
		
	/**
	 * Creates a new slot
	 * @param when the timeslot that the slot belongs in
	 * @param where the room that the slot represents
	 * @param lock whether or not the slot should be locked (true) or unlocked (false)
	 */
	public Slot(Timeslot when, Room where, boolean lock) {
		this();
		this.timeslot = when;
		this.room = where;
		this.locked = lock;
	}
	
	/**
	 * @return the timeslot that this slot is due to happen during.
	 */
	public Timeslot getTimeslot() {
		return this.timeslot;
	}
	
	/**
	 * @return the room that this slot is due to happen in.
	 */
	public Room getRoom() {
		return this.room;
	}	
	
	/**
	 * @param talkIn the talk to be scheduled in this slot.
	 */
	public void setTalk(Talk talkIn) {
		this.talk = talkIn;
	}
	
	/**
	 * @return the talk currently assigned to this slot
	 */
	public Talk getTalk() {
		return this.talk;
	}
	
	/**
	 * Unschedules the talk currently assigned to this slot.
	 * @return the talk that was assigned to this slot.
	 */
	public Talk removeTalk() {
		Talk currentTalk = this.talk;
		this.talk = null;
		return currentTalk;
	}
	
	/**
	 * @return whether or not a talk is currently scheduled to talk place in this slot; true if one is, false if not.
	 */
	public boolean isEmpty() {
		if (this.talk == null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Unlock this slot
	 */
	public void lock() {
		this.locked = true;
	}
	
	/**
	 * Lock this slot
	 */
	public void unlock() {
		this.locked = false;
	}
	
	/**	
	 * @return whether or not this slot is currently locked; true if it is locked, false if it isn't.
	 */
	public boolean isLocked() {
		return this.locked;
	}	

}
