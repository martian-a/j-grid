package models;

import java.util.Calendar;

public class Timeslot extends DataObject {

	private String name;
	private Calendar start;
	private Calendar end;
	private Talk globalTalk;
	
	/**
	 * Sets default values for all attributes
	 */
	private Timeslot() {
		super();
		this.name = null;
		this.start = null;
		this.end = null;
		this.globalTalk = null;
	}
	
	/**
	 * Creates a timeslot using the specified start and end time.
	 * @param startIn the point in time at which the session should begin
	 * @param endIn the piont in time at which the session should end
	 */
	public Timeslot(Calendar startIn, Calendar endIn) {
		this();
		this.start = startIn;
		this.end = endIn;
	}
	
	/**
	 * Sets the name of the current Timeslot
	 * @param nameIn the name to be assigned to this Timeslot.
	 */
	public void setName(String nameIn) {
		this.name = nameIn;
	}
	
	/**
	 * @return the human-friendly name for this timeslot, eg. Session 1, Lunch, etc.
	 */
	public String getName() {
		return this.name;
	}
	
	public void setStart(Calendar startIn) {
		
		// TODO: Validation: check that startIn occurs earlier than end.
		
		this.start = startIn;
	}
	
	/**
	 * @return the point in time that this timeslot is due to start.
	 */
	public Calendar getStart() {
		return this.start;		
	}
	
	public void setEnd(Calendar endIn) {
		
		// TODO: Validation: check that endIn occurs later than start.
		
		this.end = endIn;
	}
	
	/**
	 * @return the point in time that this timeslot is due to end.
	 */
	public Calendar getEnd() {
		return this.end;
	}
	
	/**
	 * @return the talk that's scheduled as global during this timeslot or null if no global talk is scheduled.
	 */
	public Talk getGlobalTalk() {
		return this.globalTalk;
	}
	
	/**
	 * Sets the specified talk as the global talk for this timeslot.
	 * 
	 * @param talk the talk that is to be global during this timeslot.
	 */
	public void setGlobalTalk(Talk talk) {
		this.globalTalk = talk;
	}

	/**
	 * Cancels the global talk for this timeslot.
	 */
	public void cancelGlobalTalk() {
		this.globalTalk = null;
	}
	
	/**
	 * @return true if a global talk is scheduled in this timeslot.
	 */
	public boolean hasGlobalTalk() {
		if (this.globalTalk != null) {
			return true;
		}
		return false;
	}
}
