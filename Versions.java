import java.util.*;


/**
 * A Class to keep track of past versions of data
 */
public class Versions<A> {
	
	private HashMap<Calendar, A> versionen; 
	
	/** Creates a new Versions entity
	 * @param first the first version to be added
	 */
	public Versions(A first){
		versionen = new HashMap<Calendar, A>();
		versionen.put(new GregorianCalendar(), first);
	}
	
	/** Returns the newest version
	 * @return the newest version
	 */
	public A getVersion(){
		Calendar closest = null;
		Set<Calendar> keys = versionen.keySet();
		for(Calendar date: keys) {
			if(closest == null||date.after(closest)){
				closest = date;
			}
		}
		return versionen.get(closest);
	}
	/** Returns the newest version at this point of time
	 * @param time the point of time
	 * @return version with the creation time closest to time
	 */
	public A getVersion(Calendar time){
		Calendar closest = null;
		Set<Calendar> keys = versionen.keySet();
		for(Calendar date: keys) {
			if(closest == null||date.after(closest)){
				if(closest == null||closest.before(time)){
					closest = date;
				}
			}
		}
		if(closest==null){
			return null;
		}
		return versionen.get(closest);
	}
	/** Converts Calendar into a String
	 * @param datum Calendar to be translated into a String
	 * @return String showing the date of the Calendar
	 */
	private String niceDate(Calendar datum){
		String niceDatum = datum.get(5)+"."+(datum.get(2)+1)+"."+datum.get(1);
		return niceDatum;
	}
	/** Sets the newest version
	 * @param newest the object to be put as the new newest element
	 */
	public void setNewest(A newest) {
		versionen.put(new GregorianCalendar(), newest);
	}
	/* to String function
	 * 
	 */
	public String toString(){
		String r ="";
		Set<Calendar> keys = versionen.keySet();
		for(Calendar date: keys) {
			r += niceDate(date)+": "+versionen.get(date)+ " ,";
		}
		return r;
	}
}
