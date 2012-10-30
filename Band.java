
import java.util.*;

/**A Band of Members.
 *
 */

public class Band {
	
	private String name;
	private String genre;
	private HashSet<Event> events = new HashSet<Event>();
	private HashSet<Member> members = new HashSet<Member>();
	//private HashSet<Member> replaceMembers = new HashSet<Member>();
	private Budget budget = new Budget();
	
	/**Creates a new Band.
	 * 
	 * @param name The name of this Band.
	 * @param genre The Genre of this Band.
	 */
	public Band(String name, String genre) {
		this.name = name;
		this.genre = genre;
	}
	
	/** Returns a HashSet of all the places the band has visited
	 * @return HashSet of all the places the band has visited
	 */
	public HashSet<Place> places(){
		HashSet<Place> rt = new HashSet<Place>();
		for(Event e: events){
			if(!rt.contains(e.getPlace())){
				rt.add(e.getPlace());
			}
		}
		return rt;
	}
	
	/** Returns a HashSet of all the places the band has visited, containing a certain kind of infrastructure
	 * @param infra the infrastructure of interest
	 * @return HashSet of all the places the band has visited, containing a certain kind of infrastructure
	 */
	public HashSet<Place> places(String infra){
		HashSet<Place> rt = new HashSet<Place>();
		for(Event e: events){
			if(!rt.contains(e.getPlace())&&e.getPlace().getInfrastructure().contains(infra)){
				rt.add(e.getPlace());
			}
		}
		return rt;
	}
	/** Adds or removes a certain Amount of Money to the Budget
	 * @param b Money
	 */
	public void addtoBudget(Money b){
		budget.add(b);
	}
	
	
	/**Returns the Repertoire of this Band of a specified Date.
	 * 
	 * @param date The Date of interest.
	 * @return The Repertoire of the specified Date.
	 */
	public Repertoire getRepertoire(Calendar date) {
		
		return new Crew(getMembers(date)).getRepertoire();
	}
	
	/**Returns the Repertoire of this Band.
	 * 
	 * @return The Repertoire of this Band.
	 */
	
	public Repertoire getRepertoire() {
		
		return new Crew(members).getRepertoire();
	}
	
	/**Adds a new Member to this Band.
	 * 
	 * @param m A new Member.
	 */
	
	public void addMember(Member m){
		members.add(m);
	}
	
	/**Removes a Member from this Band.
	 * 
	 * @param m A Member to be removed.
	 */
	
	public void removeMember(Member m){
		
		if(members.contains(m)) {
			
			members.remove(m);
			m.removeMember();
			members.add(m);
		}
	}
	
	/**Returns the current Members of this Band.
	 * 
	 * @return The current Members of this Band.
	 */
	
	public HashSet<Member> getMembers() {
		HashSet<Member> out = new HashSet<Member>();
		for(Member m: members) {
			if(m.getExitDate() == null) {
				out.add(m);
			}
		}
		return out;
	}
	
	/**Returns the Members of this Band on a specified Date.
	 * 
	 * @return The Members of this Band on a specified Date.
	 */
	
	public HashSet<Member> getMembers(Calendar date) {
		HashSet<Member> out = new HashSet<Member>();
		
		for(Member m: members) {
			if (m.getEntryDate().before(date)){
				if (m.getExitDate()==null || m.getExitDate().after(date)) {
					out.add(m);
				}
			}
		}
		
		return out;
	}
	
	/** Returns the Rehearsals of this Band in a defined time interval.
	 * 
	 * @param from The start of the time interval.
	 * @param to The End of the time interval.
	 * @return A Set of Rehearsals.
	 */
	public HashSet<Rehearsal> getRehearsals(Calendar from, Calendar to) {
		HashSet<Rehearsal> out = new HashSet<Rehearsal>();
		
		for(Event e: events) {
			if (e instanceof Rehearsal) {
				if (e.getDate().before(to) && e.getDate().after(from)) {
					out.add((Rehearsal)e);
				}
			}
		}
		return out;
	}
	
	/** Returns the Shows of this Band in a defined time interval.
	 * 
	 * @param from The start of the time interval.
	 * @param to The End of the time interval.
	 * @return A Set of Shows.
	 */

	public HashSet<Show> getShows(Calendar von, Calendar bis) {
		HashSet<Show> out = new HashSet<Show>();
		
		for(Event e: events) {
			if(e instanceof Show) {
				if (e.getDate().before(bis) && e.getDate().after(von)) {
					out.add((Show)e);
				}
			}
		}
		
		return out;
	}	

	public HashSet<Event> getEvents(Calendar von, Calendar bis) {
		return events;
	}
	
	/**Returns the name of this Band.
	 * 
	 * @return The name of this Band.
	 */
	
	public String getName() {
		return name;
	}
	
	/**Returns the genre of this Band.
	 * 
	 * @return The genre of this Band.
	 */
	
	public String getGenre() {
		return genre;
	}
	
	/** Returns all the Income/Outcome of the Events in a HashSet
	 * @return Income/Outcome of the Events
	 */
	public HashSet<Money> getEventMoney(){
		HashSet<Money> EventBudget = new HashSet<Money>();
		for (Event e: events){
			if(e instanceof Rehearsal) {
				EventBudget.add(((Rehearsal)e).getCosts());
			}else if(e instanceof Show){
				EventBudget.add(((Show)e).getSalary());
			}
		}
		return EventBudget;
	}
	/** Returns the Expenses of this Band in a defined time interval.
	 * 
	 * @param from The start of the time interval.
	 * @param to The End of the time interval.
	 * @return A sum of expenses.
	 */
	public int getExpenses(Calendar from, Calendar to){
		budget.addEventmoney(getEventMoney());
		return budget.getExpenses(from, to);
	}
	/** Returns the Expenses of a certain kind of this Band in a defined time interval.
	 * @param The start of the time interval.
	 * @param to The End of the time interval.
	 * @param use The kind of expenses.
	 * @return A sum of expenses.
	 */
	public int getExpenses(Calendar from, Calendar to, String use){
		budget.addEventmoney(getEventMoney());
		return budget.getExpenses(from, to, use);
	}
	/** Returns the Earnings of a certain kind of this Band in a defined time interval.
	 * 
	 * @param from The start of the time interval.
	 * @param to The End of the time interval.
	 * @param use The kind of income.
	 * @return A sum of earnings.
	 */
	public int getEarnings(Calendar from, Calendar to, String use){
		budget.addEventmoney(getEventMoney());
		return budget.getIncome(from, to, use);
	}

	/** Returns the Earnings of this Band in a defined time interval.
	 * 
	 * @param from The start of the time interval.
	 * @param to The End of the time interval.
	 * @return A sum of earnings.
	 */
	
	public int getEarnings(Calendar from, Calendar to){
		budget.addEventmoney(getEventMoney());
		return budget.getIncome(from, to);
	}
	/** Returns the balance of this Band in a defined time interval.
	 * 
	 * @param from The start of the time interval.
	 * @param to The End of the time interval.
	 * @return A balance of earnings and expenses.
	 */
	
	public int getBalance(Calendar von, Calendar bis) {
		return this.getEarnings(von, bis)-this.getExpenses(von, bis);
	}
	
	/** Adds an Event to the Band.
	 * @param e The event.
	 */
	public void addEvent(Event e){
		events.add(e);
	}
	
}
