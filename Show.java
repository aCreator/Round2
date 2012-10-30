import java.util.*;

/** A Show.
* 
*/

public class Show extends Event {
	
	private Versions<Money> salary;
	
	/** Creates a new Show
	 * 
	 * @param place The place of this Show.
	 * @param datum The date of this Show.
	 * @param dauer The duration of this Show.
	 * @param bands The Bands that take part in this Show.
	 * @param salary The amount of money earned in this Show.
	 */
	
	public Show(Place place, Calendar datum, int dauer, HashSet<Band> bands, int salary) {
		
		super(place, datum, dauer, bands);
		this.salary = new Versions<Money>(new Money("Show", salary));
	}
	
	/** Returns a String representation of this Show.
	 * 
	 */

	public String toString() {
		
		return super.eventToString()+" | Salary: Euro "+salary.getVersion().getAmount();
	}
	
	/** Returns the amount of money earned in this show.
	 * 
	 * @return The amount of money earned in this show
	 */
	public Money getSalary() {
		return (new Money(salary.getVersion().getUse(),salary.getVersion().getAmount(), date.getVersion()));
	}
	
	/** Replaces the income of this Show with a new amount.
	 * 
	 * @param salary The new income of this Show.
	 */
	public void setSalary(int salary) {
		this.salary.setNewest(new Money("Show", salary));
	}
	
}
