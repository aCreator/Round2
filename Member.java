
import java.util.*;
public class Member {

	private String name;
	private String phonenumber;
	private String instrument;
	private Calendar entry;
	private Calendar leave;
	/*Flo*/
	private Repertoire repertoire;
	private HashSet<Event> requests = new HashSet<Event>();

	/**Creates a new Band Member with the default entry-date: now
	 * 
	 * @param name The Name of this Member.
	 * @param tele The Phone Number of this Member.
	 * @param instrument The instrument this Member plays in the Band.
	 * @param rep The Repertoire this Member is able to play.
	 */

	public Member(String name, String tele, String instrument, Repertoire rep) {
		this(name,tele,instrument, rep, new GregorianCalendar());
	}

	/**Creates a new Band Member
	 * 
	 * @param name The Name of this Member.
	 * @param tele The Phone Number of this Member.
	 * @param instrument The instrument this Member plays in the Band.
	 * @param rep The Repertoire this Member is able to play.
	 * @param entry The date when this Member is added.
	 */

	public Member(String name, String tele, String instrument, Repertoire rep, Calendar entry) {
		this.name = name;
		this.phonenumber = tele;
		this.instrument = instrument;
		this.repertoire = rep;
		this.entry = new GregorianCalendar();
		this.leave = null;
		this.requests = new HashSet<Event>();
		this.entry = entry;
	}

	/** Returns the date when this Member was added.
	 * 
	 * @return The date when this Member was added.
	 */

	public Calendar getEntryDate() {
		return entry;
	}

	/**Sets the date when this Member is no longer active in the Band.
	 * 
	 * @param date The date when this Member is no longer active in the Band.
	 */

	public void setExitDate(Calendar date){

		leave = date;
	}

	/**Returns the date when this Member is no longer active in the Band.
	 * 
	 * @return The date when this Member is no longer active in the Band.
	 */

	public Calendar getExitDate() {
		return leave;
	}

	/**Returns the name of this Member.
	 * 
	 * @return The name of this Member.
	 */

	public String getName() {
		return name;
	}

	/**Returns the Phonenumber of this Member.
	 * 
	 * @return The Phonenumber of this Member.
	 */

	public String getPhonenumber() {
		return phonenumber;
	}

	/**Returns the instrument this Member is able to play.
	 * 
	 * @return The instrument this Member is able to play.
	 */

	public String getInstrument() {
		return instrument;
	}

	/**Removes this Member and sets the date of this occurence.
	 * 
	 */

	public void removeMember(){
		leave = new GregorianCalendar();
	}

	/**Returns a String Representation of this Member.
	 * @return A String Representation of this Member.
	 */

	public String toString() {

		String niceEinstieg = entry.get(5)+"."+(entry.get(2)+1)+"."+entry.get(1);
		String r = "Name: "+name+", Instrument: "+ instrument+", Telefonnummer: "+ phonenumber + ", Beigetreten am: " + niceEinstieg;
		if (leave != null) {
			String niceAusstieg = leave.get(5)+"."+(leave.get(2)+1)+"."+leave.get(1);
			r += ", Ausgetreten am: "+niceAusstieg;
		}
		return r;
	}

	/**Puts an Event (request) to the Members HashSet of requests 
	 * @param in the Event to add to the Set
	 */
	public void putRequest(Event in) {
		requests.add(in);
	}

	/**Returns a HashSet of Events indicating all the Requests this Member has
	 * @return a HashSet of Events indicating all the Requests this Member has
	 */
	public HashSet<Event> getRequests() {
		HashSet<Event> out = new HashSet<Event>();
		out.addAll(requests);
		return out;
	}

	public boolean answerRequest(Event e, String response, boolean decision) {
		return answerRequest(e, response, decision, false);
	}
	
	/**Lets the Member respond to a request, as long as the Event for the Request
	 * is not in the past. If one wants to change a response in the past, the
	 * force parameter has to be set to true
	 * @param e the Event for which the user wants to give his response
	 * @param response a short note - the textual response
	 * @param decision the boolean value if the member can come to the event
	 * @param force boolean value that allows changes in the past
	 * @return true only if a change could be made
	 */
	public boolean answerRequest(Event e, String response, boolean decision, boolean force) {
		boolean returnvalue = e.setResponse(this, response, decision, force);
		if (returnvalue)
			requests.remove(e);
		return returnvalue;
	}

	/**Returns the Repertoire this Member is able to play.
	 * 
	 * @return The Repertoire this Member is able to play.
	 */

	public Repertoire getRep(){

		return repertoire;
	}

	/**Replaces the current Repertoire of this Member with a new one.
	 * 
	 * @param The Repertoire this Member is able to play.
	 */

	public void setRep(Repertoire rep){
		repertoire = rep;
	}

	/**
	 * Calculates if this member is a permanent member at the moment
	 * Meaning, did he play more than half of the last 3 months concerts
	 * @param band The Band of which the members' status is being determined 
	 * @return the member status
	 */
	public String isPermanentMember(Band band) {
		return isPermanentMember(band, new GregorianCalendar());
	}

	/**
	 * Calculates if this member was a permanent member at a particular moment
	 * Meaning, did he play more than half of the concerts in the 3 months prior to that moment
	 * 
	 * @param band The Band of which the members' status is being determined
	 * @param when a Calendar instance of a particular moment
	 * @return the member status at a particular moment
	 */
	public String isPermanentMember(Band band, Calendar when) {
		if(!band.getMembers(when).contains(this)) {
			return "Not part of the band";
		}
		
		Calendar von = (Calendar) when.clone();
		von.add(Calendar.MONTH, -3);
		when.add(Calendar.SECOND, 1);
		float playCount=0;

		HashSet<Show> shows = band.getShows(von, when);
		if(shows.size() == 0) {
			return "No Shows in the 3 Months prior to "+when.getTime(); 
		} else {
			for(Event e: shows) {
				for(Member m: e.getCrew()) {
					if(this.equals(m)) {
						playCount++;
					}
				}
			}

			return (playCount/shows.size() > 0.5)?"Permanent":"Replacement";
		}
	}
}
