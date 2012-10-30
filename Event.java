import java.util.*;


/**
 * An abstract Event, that has a Crew and participating Bands
 *
 */
public abstract class Event {
	protected Versions<Calendar> date;
	private Versions<Integer> duration;
	private Versions<Place> place;
	private Crew crew;
	private Crew notComing;
	private HashMap<Integer,String> responses;
	private HashSet<Band> participatingBands;

	/**Creates a new Event
	 * @param place where the Event happens
	 * @param datum when the Event happens
	 * @param dauer how long the Event lasts
	 * @param bands which bands are participating
	 */
	public Event(Place place, Calendar datum, int dauer, HashSet<Band> bands) {
		this.place = new Versions<Place>(place);
		this.date = new Versions<Calendar>(datum);
		this.duration = new Versions<Integer>(dauer);
		this.participatingBands = bands;
		this.crew = new Crew();
		this.notComing = new Crew();
		this.requestMembers();
		this.responses = new HashMap<Integer,String>();
	}
	
	/** Returns the place of the event at the given point in time
	 * @param datum point in time
	 * @return place
	 */
	public Place getPlace(Calendar datum) {
		return place.getVersion(datum);
	}

	/** Returns current place
	 * @return place
	 */
	public Place getPlace() {
		return place.getVersion();
	}

	/** sets new place
	 * @param place new place
	 */
	public void setPlace(Place place) {
		this.place.setNewest(place);
	}

	/** returns the current date, at which the Event will take place
	 * @return date
	 */
	public Calendar getDate() {
		return (Calendar) date.getVersion();
	}
	
	/** returns the date, at which the Event would have taken place, at the given point of time 
	 * @param datum point of time
	 * @return date
	 */
	public Calendar getDate(Calendar datum) {
		return date.getVersion(datum);
	}
	
	/** changes the date of the Event
	 * @param datum new date
	 */
	public void setDate(Calendar datum) {
		this.date.setNewest(datum);
	}

	/** returns the current duration of the event
	 * @return duration
	 */
	public int getDuration() {
		return duration.getVersion();
	}
	
	/** returns the duration of the event, at the given point in time
	 * @param datum given point in time
	 * @return date
	 */
	public Object getDuration(Calendar datum){
		return duration.getVersion(datum);
	}

	/** changes the Duration of the Event
	 * @param dauer
	 */
	public void setDuration(int dauer) {
		this.duration.setNewest(dauer);
	}
	
	/** toString Method of event
	 * @return String representation of the event
	 */
	protected String eventToString() {
		Calendar hammertime = date.getVersion();
		String niceDatum = hammertime.get(5)+"."+(hammertime.get(2)+1)+"."+hammertime.get(1);
		String ptp="", delim="";
		int memberSize = 0;
		for(Band b: participatingBands) {
			memberSize += b.getMembers().size();
			ptp += delim+b.getName();
			delim = ", ";
		}

		return "Confirmed Crew/Members: "+crew.getSize()+"/"+memberSize+"\n"
		+"Place: "+place.getVersion().getName()
		+" | Bands: "+ptp+" | Datum: "+niceDatum
		+" | Dauer: "+duration.getVersion()+"Min";
	}

	
	/**
	 * Sends a Request to all the Members of all the participating Bands of this Event
	 */
	private void requestMembers() {
		for(Band b: participatingBands) {
			b.addEvent(this);
			HashSet<Member> members = b.getMembers();
			for(Member m: members) {
				m.putRequest(this);
			}
		}
	}

	/**Returns the Request-Responses of the specified Member
	 * @param m Member who's response is requested 
	 * @return the Request-Response of the given Member
	 */
	public String getResponse(Member m) {
		return responses.get(m.hashCode());
	}

	/**Returns all the Members' Responses plus the Members' names 
	 * @return an ArrayList of Strings with a Member's name and response
	 */
	public ArrayList<String> getAllResponses() {
		ArrayList<String> out = new ArrayList<String>();

		for(Band b: participatingBands) {
			HashSet<Member> members = b.getMembers();
			for(Member m: members) {
				out.add(m.getName()+", "+responses.get(m.hashCode()));
			}
		}

		return out;
	}
	
	/**Sets the request Response of a given Member and adds that Member to the corresponding crew
	 * Doesn't allow setting of responses to Events in the Past unless the force parameter is set to true.
	 * Returns true if setting was made, false otherwise
	 * @param m Member that wants to set a response
	 * @param response String with a short note
	 * @param decision boolean value, if the Member can attend the Event
	 * @param force boolean value, if changes in the past should be allowed
	 * @return true if setting was made, false otherwise
	 */
	public boolean setResponse(Member m, String response, boolean decision, boolean force) {
		Calendar now = new GregorianCalendar();
		now.add(Calendar.SECOND, -1);
		if(force || now.before(this.getDate())) {
			responses.put(m.hashCode(), response);
			if (decision) {
				notComing.removeMember(m);
				crew.addMember(m);
			} else {
				crew.removeMember(m);
				notComing.addMember(m);
			}
			return true;
		} else {
			return false;
		}
	}

	/** Returns the crew
	 * @return crew
	 */
	public Crew getCrew() {
		return crew;
	}
	
	/** Returns the Members of the Band, who are not participating
	 * @return
	 */
	public Crew getNotComing() {
		return notComing;
	}
}
