package models;

import models.DataObject;

public class Talk extends DataObject {
	
	private String title;
	private String description;
	private String speaker;
	
	private Talk() {
		super();
		this.title = null;
		this.description = null;
		this.speaker = null;
	}
	
	public Talk(String titleIn, String descriptionIn, String speakerIn) {
		this();
		
		// TODO: Validation: ignore values that equate to null
		// TODO: Validation: check that the talk has a non-null value for at least the title or speaker
		
		this.title = titleIn;
		this.description = descriptionIn;
		this.speaker = speakerIn;
	}
	
	/**
	 * @param titleIn the title of the current talk
	 */
	public void setTitle(String titleIn) {
		this.title = titleIn;
	}
	
	/**
	 * @return the title of this talk
	 */
	public String getTitle() {
		return this.title;
	}
		
	/**
	 * @param descriptionIn a description of the current talk
	 */
	public void setDescription(String descriptionIn) {
		this.description = descriptionIn;
	}
	
	/**
	 * @return the description of this talk
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @param speakerIn the name of the person giving this talk
	 */
	public void setSpeaker(String speakerIn) {
		this.speaker = speakerIn;
	}
	
	/**
	 * @return the name of the person giving this talk
	 */
	public String getSpeaker() {
		return this.speaker;
	}
}
