
public class Place {
	
	private String infrastructure;
	private String name;
	/** Creates new place, consisting of name of the place and infrastructure
	 * @param n name of the place
	 * @param inf available infrastructure
	 */
	public Place(String n, String inf){
		infrastructure = inf;
		name = n;
	}
	/** Returns infrastructure
	 * @return infrastructure
	 */
	public String getInfrastructure() {
		return infrastructure;
	}
	/** Returns name
	 * @return name
	 */
	public String getName() {
		return name;
	}
	public String toString(){
		return "Place: "+name+", Infrastructure: "+infrastructure;
	}
}
