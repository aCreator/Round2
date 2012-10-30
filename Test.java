import java.util.*;

/** Test
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//Instantiate Bands and define who participates in different Events
		Band moonies = new Band("Moonshaker Sunnymaker", "Funk");
		Band preband = new Band("The Preband", "crazyhouse");
		HashSet<Band> ptpShow = new HashSet<Band>(); //ptp = participating Bands
		HashSet<Band> ptpRehearsal = new HashSet<Band>();
		ptpRehearsal.add(moonies);
		ptpShow.add(moonies);
		ptpShow.add(preband);
		String sep = "\n\n *************************\n";


		//Instantiate a few Songs, to add them to the Repertoires
		Song s1 = new Song("War Of The Wales",330, 350, 370);
		Song s2 = new Song("Hey You, Dance Or You'll Die!", 500, 660, 800);
		Song s3 = new Song("The Devil Eats His Supper Cold", 200, 240, 300);
		Song s4 = new Song("March Of The Deseased", 60, 230, 260);
		Song s5 = new Song("Deranged Psychopath On The Loose", 120, 280, 300);


		//Instantiate a few Repertoires for each Member, then create the Member
		Repertoire cRep = new Repertoire();
		cRep.addSong(s1);
		cRep.addSong(s2);
		cRep.addSong(s3);
		cRep.addSong(s4);
		cRep.addSong(s5);

		Repertoire pRep = new Repertoire();
		pRep.addSong(s1);
		pRep.addSong(s2);
		pRep.addSong(s3);

		Repertoire siRep = new Repertoire();
		siRep.addSong(s3);
		siRep.addSong(s4);
		siRep.addSong(s5);

		Repertoire saRep = new Repertoire(siRep);
		saRep.addSong(s1);
		saRep.addSong(s2);

		Repertoire flRep = new Repertoire(cRep);
		Repertoire jRep = new Repertoire(cRep);
		Repertoire feRep = new Repertoire(cRep);
		Repertoire gRep = new Repertoire(cRep);

		GregorianCalendar entry = new GregorianCalendar(2011, 11, 11); 

		//Instantiate Members with their Repertoires
		Member christoph = new Member("Christoph", "1012312", "Gesang", cRep, entry);
		Member jens = new Member("Jens", "53432312", "Schlagzeug", jRep, entry);
		Member paul = new Member("Paul", "9087312", "Gitarre", pRep, entry);
		Member simon = new Member("Simon", "131234", "Bass", siRep, entry);
		Member saladfingers = new Member("Saladfingers", "00000", "Flute", saRep, entry);
		Member flo = new Member("Flo", "06767775777", "Ipod", flRep);
		Member felix = new Member("Felix Ledauchauwsky", "067012345678", "Elektrogitarre... ein bisschen", feRep);
		Member gunther = new Member("Gunther", "06648753463", "nix", gRep);

		//Add the Members to the Bands
		moonies.addMember(christoph);
		moonies.addMember(jens);
		moonies.addMember(paul);
		moonies.addMember(saladfingers);
		moonies.addMember(flo);
		moonies.addMember(felix);
		moonies.addMember(simon);
		moonies.addMember(gunther);


		String names="";
		String delim="";
		for(Member m: moonies.getMembers()) {
			names += delim+m.getName();
			delim =", ";
		}
		//Get the Repertoire of the Band.
		System.out.println("Repertoire from "+names+"\n" + moonies.getRepertoire()+sep);

		moonies.removeMember(paul);
		moonies.removeMember(simon);

		names="";
		delim="";
		for(Member m: moonies.getMembers()) {
			names += delim+m.getName();
			delim =", ";
		}

		//Get the Repertoire of the changed Band.
		System.out.println("Changed Repertoire from "+names+"\n" + moonies.getRepertoire()+sep);

		Calendar von = new GregorianCalendar(2012, 1, 1);
		Calendar bis = new GregorianCalendar(2013, 11, 31);
		Calendar zukunft = new GregorianCalendar(2013, 1, 2);
		Calendar zach = new GregorianCalendar(2012, 9, 2);
		String vonbis = "("+niceDate(von)+"-"+niceDate(bis)+")";

		//Instantiate a couple of Places
		Place fledermaus = new Place("Fledermaus", "Taxi");
		Place zacherl = new Place("Zacherl", "Taxi");
		Place something = new Place("What Ever", "Ubahn");
		Place schottenball = new Place("Schottenball", "Ubahn");
		Place paradisrecords = new Place("ParadisRecords", "Bus");
		Place deinemama = new Place("Deine Mama", "Ubahn");

		//Instantiate a few Shows, and add them to the participating Bands
		Show atFledermaus = new Show(fledermaus, zukunft, 75, ptpShow, 600);
		moonies.addEvent(atFledermaus);
		Show atZacherl = new Show(zacherl, zach, 45, ptpShow, 300);
		moonies.addEvent(atZacherl);
		moonies.addEvent(new Show(something, zukunft, 75, ptpShow, 200));
		moonies.addEvent(new Show(schottenball, zukunft, 120, ptpShow, 1800));

		//Instantiate a few Rehearsals, change some data, and add them to the participating Bands
		Rehearsal ProbebeiMama = new Rehearsal(deinemama, new GregorianCalendar(), 120, ptpRehearsal, 0);
		Calendar pre = new GregorianCalendar();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		//Change cost of the rehearsal
		ProbebeiMama.setCosts(10);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {

		}
		//Change cost of the rehearsal to one in an earlier point in time
		ProbebeiMama.setCosts(ProbebeiMama.getCosts(pre).getAmount());
		moonies.addEvent(new Rehearsal(paradisrecords, new GregorianCalendar(), 120, ptpRehearsal, 80));
		moonies.addEvent(new Rehearsal(something, new GregorianCalendar(), 50, ptpRehearsal, 45));
		moonies.addEvent(ProbebeiMama);
		moonies.addEvent(new Rehearsal(fledermaus, new GregorianCalendar(), 120, ptpRehearsal, 70));


		//Answer the Event Requests of all Members
		//Then show how a change of mind takes effect (christoph)
		HashSet<Event> cReqs = christoph.getRequests();

		//Example of the Event requests one Member sees
		System.out.println("Christophs requests:"+niceString(cReqs)+sep);

		//Everyone decides to come
		for(Member m: moonies.getMembers()) {
			for (Event e: m.getRequests()) {
				m.answerRequest(e, "sounds awesome", true, true); //force past request answering
			}
		}

		System.out.println("Fledermaus-Antworten\n"+niceString(atFledermaus.getAllResponses())+sep);

		//Christoph Changes his mind
		System.out.println("Christoph Changes his mind");
		christoph.answerRequest(atFledermaus, "kann doch nicht", false);
		System.out.println("Fledermaus-Antworten\n"+niceString(atFledermaus.getAllResponses())+sep);
		
		//Christoph wants to change his response for an Event thats already in the past
		//It's not possible. Therefore the method returns false
		System.out.println("Changing a response for an Event in the past:");
		System.out.println(christoph.answerRequest(atZacherl, "kann doch nicht", false)+sep);

		
		//Zacherl Responses before forcing a response-change
		System.out.println("Zacherl-Antworten\n"+niceString(atZacherl.getAllResponses())+sep);
		
		//For testing purpose, I am going to force the change that wasn't possible before
		System.out.println("Force-changing a response for an Event in the past:");
		System.out.println(christoph.answerRequest(atZacherl, "kann doch nicht", false, true)+sep);
		
		//Zacherl Responses before forcing a response-change
		System.out.println("Zacherl-Antworten\n"+niceString(atZacherl.getAllResponses())+sep);
		
		
		//Permanent Member Calculation, expected: Permanent, Replacement, Not in band
		System.out.println(jens.isPermanentMember(moonies));
		System.out.println(christoph.isPermanentMember(moonies));
		System.out.println(paul.isPermanentMember(moonies));


		//Instantiate a few elements of income and expense
		moonies.addtoBudget(new Money("Merchandise", 100000000));
		moonies.addtoBudget(new Money("coke", -100000));
		moonies.addtoBudget(new Money("more coke", -923000));
		moonies.addtoBudget(new Money("Merchandise", 100000000));
		moonies.addtoBudget(new Money("Pizza", -90));
		System.out.println(moonies.getName()+", "+moonies.getGenre()+sep);

		System.out.println("ALL EVENTS "+vonbis+": "+niceString(moonies.getEvents(von, bis))+sep);

		System.out.println("SHOWS "+vonbis+": "+niceString(moonies.getShows(von, bis))+sep);
		System.out.println("REHEARSALS "+vonbis+": "+niceString(moonies.getRehearsals(von, bis))+sep);
		System.out.println("INCOME "+vonbis+": "+moonies.getEarnings(von, bis)+sep);
		System.out.println("EXPENSES "+vonbis+": "+moonies.getExpenses(von, bis)+sep);
		System.out.println("COKE EXPENSES "+vonbis+": "+moonies.getExpenses(von, bis, "coke")+sep);
		System.out.println("BALANCE "+vonbis+": "+moonies.getBalance(von, bis)+sep);
		
		
		//get all the current members, should be Flo, Jens, Felix, Saladfingers, Gunther, Christoph
		System.out.println("CURRENT MEMBERS: "+niceString(moonies.getMembers())+sep);
		
		//get all the current members, should be Jens, Saladfingers, Paul, Simon, Christoph
		System.out.println("PAST MEMBERS FROM "+niceDate(von)+niceString(moonies.getMembers(von))+sep);

	}

	/**Better String representation of all Iterables
	 * @param in generic Iterable
	 * @return a nice String representation of the generic Iterable
	 */
	public static String niceString(Iterable<?> in) {
		String out = "";

		for(Object x: in) {
			out+="\n"+x.toString();
		}
		return out;
	}


	/**Nice String representation of Calendar
	 * @param datum Calendar to be represented
	 * @return nice String representation of datum
	 */
	private static String niceDate(Calendar datum){
		String niceDatum = datum.get(5)+"."+(datum.get(2)+1)+"."+datum.get(1);
		return niceDatum;
	}
}
