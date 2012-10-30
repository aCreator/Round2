import java.util.*;


/** A Class to represent Income/Outcome
 *
 */
public class Money {
	
	private int amount;
	private String use;
	private Calendar time;
	
	/** Creates a new Money entity
	 * @param use what the money was used on, or where it came from
	 * @param amount amount of money subtracted/added
	 */
	public Money(String use, int amount){
		this.amount = amount;
		this.use = use;
	}
	/** Creates a new Money entity
	 * @param use what the money was used on, or where it came from
	 * @param amount amount of money subtracted/added
	 * @param time point in time, where the money was created
	 */
	public Money(String use, int amount, Calendar time){
		this.amount = amount;
		this.use = use;
		this.time = time;
	}
	/** returns Amount
	 * @return amount
	 */
	public int getAmount() {
		return amount;
	}
	/** returns Use
	 * @return use
	 */
	public String getUse() {
		return use;
	}
	public String toString(){
		return "Use :" + use + ", Amount: " + amount;
	}
	/** Returns time
	 * @return time
	 */
	public Calendar getTime(){
		return time;
	}
}
