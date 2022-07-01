import java.util.ArrayList;

public class CentralRegistry {

	private static ArrayList<Airport> airports = new ArrayList<>(); 
	private static ArrayList<Flight> flights = new ArrayList<>();
	
	public static void addFlight(Flight aFlight) { 
		
		aFlight.getAirportA().setConnections(aFlight.getAirportB());
		aFlight.getAirportB().setConnections(aFlight.getAirportA());
		aFlight.getAirportA().setCompanies(aFlight.getCompany());
		aFlight.getAirportB().setCompanies(aFlight.getCompany());
		
		flights.add(aFlight); 
		
	}
	
	public static void addAirport(Airport anAirport) {
		
		airports.add(anAirport);
		
	} // end of method
	
	public static Airport getLargestHub() {

		Airport l1 = airports.get(1);
		
		for(Airport lhub : airports) {
			if(lhub.getConnections().size() >l1.getConnections().size())
				l1 = lhub;
		}
		
		return l1;

	}
	
	public static Flight getLongestFlight() { 
	
		Flight fl = flights.get(1);
		
		for (Flight f : flights)
			if(f.getDuration() > fl.getDuration())
				fl = f;
			
		return fl;
		
	}
	
	public static Airport getAirport(String cityName) { 
		
		for (Airport a : airports)
			if(a.getCity().equals(cityName))
				return a;
		
		return null;
		
	}
	
	public static ArrayList<String> getDirectFlightsDetails(Airport a, Airport b) {
		
		ArrayList<String> flightDetails = new ArrayList<>();
		
		int i = 1;
		
		if(a.isDirectlyConnectedTo(b)) {
			for (Flight f : flights)
				if((f.getAirportA().equals(a) || f.getAirportA().equals(b)) && (f.getAirportB().equals(a) || f.getAirportB().equals(b))) { 
					flightDetails.add("(" + i + ")" + f.toString());
					i++;
					}
			return flightDetails;
		}
		
		return null;
		
	}
	
	public static ArrayList<String> getInDirectFlightsDetails(Airport a, Airport b) {

		ArrayList<String> Details = new ArrayList<String>();
		
		ArrayList<Airport> Commons = new ArrayList<Airport>();
		
		int count = 1;
		
		if(a.isInDirectlyConnectedTo(b)) {
			Commons =  a.getCommonConnections(b);
			Remove(Commons);
			
			for(Airport air : Commons) {
				Details.add("(" + count + ")" + air.getCity() + "," + air.getCodename() + " Airport");
				count++;
				}
			return Details;
		}
		
		return null;
		
	}

	public static ArrayList<String> RemoveAndOrder(ArrayList<String> alist) {
	
		int counter = 0;
	
		for(int i = 0; i<alist.size(); i++) { // removing duplicates
			for(int j = 0; j<alist.size(); j++)
				if(alist.get(i).equals(alist.get(j)))
					counter++;

			if(counter>1)
				alist.remove(i);
			counter = 0;
		}
	
		for(int i = 0; i<alist.size()-1; i++) // sorting by name 
			for (int j = i+1; j<alist.size(); j++) 
				if(alist.get(i).compareTo(alist.get(j)) > 0) {
					String temp = alist.get(i);
					alist.set(i, alist.get(j));
					alist.set(j, temp);
				}
			return alist;
		
	}

	public static ArrayList<Airport> Remove(ArrayList<Airport> alist) {
	
		int counter = 0;
	
		for(int i = 0; i<alist.size(); i++) { // removing duplicates
			for(int j = 0; j<alist.size(); j++)
				if(alist.get(i).equals(alist.get(j)))
					counter++;
			
			if(counter>1)
				alist.remove(i);
			counter = 0;
		}
		
		return alist;
		
	}

	public static ArrayList<Flight> getFlights() {
		
		return flights;
	
	}

}