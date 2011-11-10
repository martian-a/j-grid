package models;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.TimeZone;

public abstract class DataObject extends Observable {

	public static final TimeZone TIMEZONE = TimeZone.getTimeZone("UTC");

	private GregorianCalendar createdAt;
	private GregorianCalendar updatedAt;

	public DataObject() {
		this.setUpdatedAt();
		createdAt = (GregorianCalendar) updatedAt.clone();
	}

	/**
	 * @return when, in time, this database record associated with this instance
	 *         was created.
	 */
	public Calendar getCreatedAt() {
		return this.createdAt;
	}

	/**
	 * @return when, in time, this database record associated with this instance
	 *         was last changed.
	 */
	public Calendar getUpdatedAt() {
		return this.updatedAt;
	}

	public void save() {
		this.setUpdatedAt();

		// TODO: Update database!
	}

	private void setUpdatedAt() {
		this.updatedAt = (GregorianCalendar) Calendar
				.getInstance(TIMEZONE);
	}

	// TODO: Use prepared statements when saving to the database
}
