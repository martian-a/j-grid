package models;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TreeSet;

import javax.xml.bind.DatatypeConverter;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import net.sf.saxon.TransformerFactoryImpl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import uk.co.bluegumtree.code.java.util.Logger;
import uk.co.bluegumtree.code.java.util.ResourceResolver;
import uk.co.bluegumtree.code.java.util.XmlReader;

public class Timeslot extends DataObject implements Comparable<Timeslot> {

	private String name;
	private GregorianCalendar start;
	private GregorianCalendar end;
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
	public Timeslot(GregorianCalendar startIn, GregorianCalendar endIn) {
		this(startIn, endIn, null);		
	}
	
	public Timeslot(GregorianCalendar startIn, GregorianCalendar endIn, String nameIn) {
		this();
		this.setStart(startIn);
		this.setEnd(endIn);	
		this.setName(nameIn);
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
	
	public void setStart(GregorianCalendar startIn) {
		
		// TODO: Validation: check that startIn occurs earlier than end.
		
		this.start = startIn;
	}
	
	/**
	 * @return the point in time that this timeslot is due to start.
	 */
	public GregorianCalendar getStart() {
		return this.start;		
	}
	
	public void setEnd(GregorianCalendar endIn) {
		
		// TODO: Validation: check that endIn occurs later than start.
		
		this.end = endIn;
	}
	
	/**
	 * @return the point in time that this timeslot is due to end.
	 */
	public GregorianCalendar getEnd() {
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
	
	
	/**
	 * Parses an XML seed file to create the rooms required for the schedule
	 * @param seedXml XML seed file
	 * @return a collection of rooms
	 * @throws TransformerException 
	 */
	public static TreeSet<Timeslot> load(File seedFile) throws TransformerException {
		
		TreeSet<Timeslot> timeslots = new TreeSet<Timeslot>();			
		
		// Create an instance of Saxon
		TransformerFactoryImpl factory = new TransformerFactoryImpl();

		// Create a resource resolver, for resolving
		// URIs from within XSL stylesheets
		ResourceResolver resourceResolver = new ResourceResolver();

		// Set the resource resolver on the FACTORY
		factory.setURIResolver(resourceResolver);

		// Create a transformer instance and load the XSL file		
		StreamSource xsl = new StreamSource(Paths.get("./lib/parse_timeslot_seeds.xsl").toFile());		
		Transformer transformer = factory.newTransformer(xsl);
						
		// Convert the XML document into a StreamSource
		StreamSource xml = new StreamSource(seedFile);
		
		// Prepare a container to hold the result of the transformation
		DOMResult result = new DOMResult();
		
		// Execute the transformation
		transformer.transform(xml, result);
		
		Document seedXml = (Document) result.getNode();
		
		NodeList timeslotsXml = XmlReader.readNodeList(seedXml, "timeslots/timeslot");
		for (int i = 0; i < timeslotsXml.getLength(); i++) {
			timeslots.add(Timeslot.load((Element) timeslotsXml.item(i)));
		}
				
		return timeslots;
	}
	
	/**
	 * Constructs a new timeslot object from seed data.
	 * @param timeslotXml an XML element containing the seed data
	 * @return the newly constructed timeslot object
	 */
	public static Timeslot load(Element timeslotXml) {		
				
		String startString = XmlReader.readString(timeslotXml, "@start");		
		String endString = XmlReader.readString(timeslotXml, "@end");	
		String name  = XmlReader.readString(timeslotXml, "name");		
		
		GregorianCalendar startDate = Timeslot.parseDate(startString);
		GregorianCalendar endDate = Timeslot.parseDate(endString);
		
		return new Timeslot(startDate, endDate, name);
	}
	
	public static GregorianCalendar parseDate(String dateStringIn) {
				
		GregorianCalendar date = (GregorianCalendar) DatatypeConverter.parseDateTime(dateStringIn);				
		return date;
	}

	@Override
	public int compareTo(Timeslot anotherTimeslot) {

		// If the timeslots have the same start and end time
		// consider them to be equal
		if (this.equals(anotherTimeslot)) {
			return 0;
		}
		
		// If the timeslots have the same start time
		if (this.start.compareTo(anotherTimeslot.start) == 0) {
			
			// Compare their end dates
			return this.end.compareTo(anotherTimeslot.end);
		}
		
		// If this timeslot begins before the other timeslot
		// consider this timeslot to be less than the other
		if (this.start.compareTo(anotherTimeslot.start) < 0) {
			return -1;
		}
						
		// This timeslot is greater than the other
		return 1;
	}
	
	public boolean equals(Timeslot anotherTimeslot) {
		
		if (this.start.equals(anotherTimeslot.start) && this.end.equals(anotherTimeslot.end)) {
			return true;
		}		
		return false;
	}
}
