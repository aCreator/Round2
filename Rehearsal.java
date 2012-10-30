import java.util.*;

/** A Rehearsal.
 * 
 */

public class Rehearsal extends Event {

	private  Versions<Money> cost;
	
	/** Creates a new Rehearsal.
	 * 
	 * @param place The place of this Rehearsal.
	 * @param datum The date of this Rehearsal.
	 * @param dauer The duration of this Rehearsal.
	 * @param bands The Bands that take part in this Rehearsal.
	 * @param cost The costs of this Rehearsal.
	 */
	
	public Rehearsal(Place place, Calendar datum, int dauer, HashSet<Band> bands, int cost) {
		
		super(place, datum, dauer, bands);
		this.cost= new Versions<Money>(new Money("Rehearsal",-cost));
	}
	
	/** Returns a String representation of this Rehearsal.
	 * 
	 */
	
	public String toString() {
		
		return super.eventToString()+" | Miete: Euro "+cost.getVersion().getAmount()*-1;
	}
	
	/** Returns the costs of this Rehearsal.
	 * 
	 * @return The costs of this Rehearsal.
	 */
	public Money getCosts() {
		return (new Money(cost.getVersion().getUse(),cost.getVersion().getAmount(), date.getVersion()));
	}
	
	/** Returns the costs of this Rehearsal at a certain point in time.
	 * @param time Point in time.
	 * @return The costs of this Rehearsal.
	 */
	public Money getCosts(Calendar time) {
		return (new Money(cost.getVersion(time).getUse(),cost.getVersion(time).getAmount(), date.getVersion()));
	}
	/** Replaces the costs of this Rehearsal with a new amount.
	 * 
	 * @param cost The new costs of this Rehearsal.
	 */
	public void setCosts(int cost) {
		this.cost.setNewest(new Money("Rehearsal", -cost));
	}

}
