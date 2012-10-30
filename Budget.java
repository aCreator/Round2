import java.util.*;

/** A class to keep track of income and expenses
 *
 */
public class Budget {
	
	private HashSet<Money> budget;
	private HashSet<Money> events = new HashSet<Money>();
	
	/** Creates a new Budget
	 * 
	 */
	public Budget(){
		budget = new HashSet<Money>();
	}
	
	
	/** adds all the outcome and income of the events in an extra Hash
	 * @param m income and outcome of the evens
	 */
	public void addEventmoney(HashSet<Money> m){
		events = m;
	}
	
	
	/** adds income/outcome and also the point of time at which it was added
	 * @param b Money
	 */
	public void add(Money b){
		budget.add(new Money(b.getUse(), b.getAmount(), new GregorianCalendar()));
	}
	
	/** returns expenses at a given period of time as an Integer
	 * @param from beginning of the period of time
	 * @param to end of the period of time
	 * @return expenses
	 */
	public int getExpenses(Calendar from, Calendar to){
		int out = 0;
		for (Money m: budget){
			if(m.getTime().after(from) && m.getTime().before(to)&& m.getAmount()<0){
				out-=m.getAmount();
			}
		}
		for (Money m: events){
			if(m.getTime().after(from) && m.getTime().before(to)&& m.getAmount()<0){
				out-=m.getAmount();
			}
		}
		return out;
	}
	/** returns income at a given period of time as an Integer
	 * @param from beginning of the period of time
	 * @param to end of the period of time
	 * @return income
	 */
	public int getIncome(Calendar from, Calendar to){
		int out = 0;
		for (Money m: budget){
			if(m.getTime().after(from) && m.getTime().before(to)&& m.getAmount()>0){
				out+=m.getAmount();
			}
		}
		for (Money m: events){
			if(m.getTime().after(from) && m.getTime().before(to)&& m.getAmount()>0){
				out+=m.getAmount();
			}
		}
		return out;
	}
	/** returns expenses of a certain use at a given period of time as an Integer
	 * @param from beginning of the period of time
	 * @param to end of the period of time
	 * @param use what the money was used for
	 * @return expenses
	 */
	public int getExpenses(Calendar from, Calendar to, String use){
		int out = 0;
		for (Money m: budget){
			if(m.getTime().after(from) && m.getTime().before(to)&& m.getUse().contains(use)&& m.getAmount()<0){
				out-=m.getAmount();
			}
		}
		for (Money m: events){
			if(m.getTime().after(from) && m.getTime().before(to)&& m.getUse().contains(use)&& m.getAmount()<0){
				out-=m.getAmount();
			}
		}
		return out;
	}
	/** returns income of a certain use at a given period of time as an Integer
	 * @param from beginning of the period of time
	 * @param to end of the period of time
	 * @param use where the money came from
	 * @return income
	 */
	public int getIncome(Calendar from, Calendar to, String use){
		int out = 0;
		for (Money m: budget){
			if(m.getTime().after(from) && m.getTime().before(to)&& m.getUse().contains(use)&& m.getAmount()>0){
				out+=m.getAmount();
			}
		}
		for (Money m: events){
			if(m.getTime().after(from) && m.getTime().before(to)&& m.getUse().contains(use)&& m.getAmount()>0){
				out+=m.getAmount();
			}
		}
		return out;
	}
}