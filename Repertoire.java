	import java.util.*;
	
	/** A Repertoire of Songs
	  */

	public class Repertoire {
	
		ArrayList<Song> repertoire; 
		HashMap<Integer, Repertoire> repList = new HashMap<Integer, Repertoire>();
		int key = -1;
		boolean mergeAble;
	
	/** Creates a new empty Repertoire. */
	
	public Repertoire(){
		repertoire = new ArrayList<Song>();
		mergeAble = false;
	}
	
	/** Creates a new Repertoire from an existing Repertoire *
	  * @param rt An existing Repertoire
	  */
	
	public Repertoire(Repertoire rt){
		
		repertoire = new ArrayList<Song>();
		
		for(int i = 0; i < rt.getSize(); i++){
			repertoire.add(rt.getSong(i));
		}
		mergeAble = true;
	}
		
	/** Returns the song at index i.
	  * @param i The index of the song.
	  * @return The song at index i.
	  */
	
	public Song getSong(int i){
	
		return repertoire.get(i);
	}
	
	/** Adds a song at index i to the repertoire.
	  * @param s The song that is added to the repertoire.
	  * @param i The index of the added song.
	  */
	
	public void addSong(Song s, int i){
		mergeAble = true;
		repertoire.add(i, s);
	}
	
	/** Adds a song to the end of the repertoire.
	  * @param s The song that is added to the repertoire.
	  */
	  
	public void addSong(Song s){
		mergeAble = true;
		repertoire.add(s);
	}
	
	/** Returns the size of the repertoire.
	  * @return The size of the repertoire.
	  */
	  
	public int getSize(){
		
		return repertoire.size();
	}
	
	/** Deletes the song with index i from the repertoire.
	  * @param i The index of the song which is removed from the repertoire.
	  */
	
	public void removeSong(int i){
		repertoire.remove(i);
	}
		
	/** Returns a String representation of this Repertoire.
	  * @return The String representation of this Repertoire.
	  */
	
	public String toString(){
		
		String str = "(Total Songs: "+getSize()+")\n";
		
		for(Song s: repertoire){
			
			str += s.toString() + "\n";
			
		}
		
		return str;
	}
	
	/**
	 * Merges current Repertoire with another one.
	 * 
	 * @param r Repertoire to merge with
	 * @return Repertoire with all the songs, that are available in both.
	 */
	
	public Repertoire mergeRep(Repertoire r) {
		
		if(!this.mergeAble) {
			//System.out.println("zero");
			return new Repertoire(r);
		} else {
			Repertoire out = new Repertoire();
			int size = r.getSize();
			for(int i=0; i<size; i++) {
				Song s = r.getSong(i);
				if(this.repertoire.contains(s)) {
					out.addSong(s);
				}
			}
			return out;
		}
	
	}
			
	/**Save the current Repertoire.
	 * 
	 * @return The key to retrieve this Repertoire.
	 */
	
	public int saveRepertoire(){
		
		key++;
		
		repList.put(key, new Repertoire(this));
		
		return key;
	}
	
	/**Load a previously saved Repertoire.
	 *  
	 * @param k The key to retrieve the Repertoire.
	 * @return The loaded Repertoire.
	 */
	
	public Repertoire loadRepertoire(int k){
		
		return repList.get(k);
	}

}