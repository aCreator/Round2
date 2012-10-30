import java.util.*;

/** A Crew of Members. */

public class Crew implements Iterable<Member> {

	private HashSet<Member> members;
	private Repertoire repertoire;

	/**Creates  a new empty Crew.
	 * 
	 */

	public Crew() {

		members = new HashSet<Member>();
		repertoire = new Repertoire();
	}

	/**Creates  a new Crew.
	 * 
	 */

	public Crew(HashSet<Member> members) {
		this.members = members;
		repertoire = getRepertoire();
	}

	/**Adds a Member to this Crew.
	 * 
	 * @param m A new Member of this Crew.
	 */

	public void addMember(Member m) {
		members.add(m);
		repertoire.mergeRep(m.getRep());
	}


	/**Removes a Member from this Crew
	 * @param m Member to delete
	 */
	public void removeMember(Member m) {
		members.remove(m);
	}

	/**Returns the Members of this Crew.
	 * 
	 * @return The Members of this Crew.
	 */

	public HashSet<Member> getMembers() {

		return members;
	}

	/**Returns the Repertoire of this Crew.
	 * 
	 * @return The Repertoire of this Crew.
	 */

	public Repertoire getRepertoire() {
		repertoire = new Repertoire();
		for(Member m: members){
			if(m.getExitDate() == null){
				repertoire = repertoire.mergeRep(m.getRep());
			}
		}
		return repertoire;
	}
	
	/**Returns an int value of the number of Members in this crew
	 * @return how many members this crew has
	 */
	public int getSize() {
		return members.size();
	}

	@Override
	public Iterator<Member> iterator() {
		return members.iterator();
	}
}
