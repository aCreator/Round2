/** A Song.
  */

public class Song {
	
	protected String title;
	private int shortDur;
	private int duration;
	private int extendedDur;
	
	/** Creates now a new Song. 
	  * @param title The song's title.
	  * @param duration The song's duration in seconds.
	  */
	
	public Song(String title, int shortDur, int duration, int extendedDur){
		
		this.title = title;
		this.shortDur = shortDur;
		this.duration = duration;
		this.extendedDur = extendedDur;
	}
		
	/** Returns the title of this song.
	  * @return The title of this song.
	  */
	
	public String getTitle(){
	
		return title;
	}
	
	/** Returns the duration of the short version of this song.
	  * @return The duration of this song in seconds.
	  */
	
	public int getShortDur(){
		
		return shortDur;
	}
	
	/** Returns the duration of the extended version of this song.
	  * @return The duration of this song in seconds.
	  */
	
	public int getExtendedDur(){
		
		return extendedDur;
	}
	
	
	/** Returns the duration of the standard version of this song.
	  * @return The duration of this song in seconds.
	  */
	
	public int getDuration(){
		
		return duration;
	}
		
	/** Returns a String representation of this song
	  * @return The String representation of this song.
	  */
	
	public String toString(){
			
		return "Song Title : " + title + " - Duration : " + duration + " Seconds." +
		"\n" + "Duration of short version: " + shortDur + " Seconds." + 
		"\n" + "Duration of extended version: " + extendedDur + " Seconds.\n"		;
	}
}
		
	

