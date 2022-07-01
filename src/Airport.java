import java.util.ArrayList;

public class Airport {

	private String name;
	private String codename;
	private String city;
	private String country;
	private ArrayList<String> companies = new ArrayList<>();
	private ArrayList<Airport> connections = new ArrayList<>();
	
	public Airport(String name, String codename, String city, String country) { 
		
		this.name = name;
		this.codename = codename;
		this.city = city;
		this.country = country;
		
	}

	public boolean isDirectlyConnectedTo(Airport Airport) { 
		                                                    
		for(Airport a : connections) {                      
			if (a.equals(Airport))
				return true;
			}
		
		return false;
		
	}
	
	public boolean isInDirectlyConnectedTo(Airport Airport) { 
		for(int i =0; i<connections.size(); i++) {            
			for(int j = 0; j<Airport.getConnections().size(); j++) {
			    if(connections.get(i).equals(Airport.getConnections().get(j)))
					return true;
				}
			}
		
		return false;
		
	}
	
	public ArrayList<Airport> getCommonConnections(Airport anAirport) { 
		ArrayList<Airport> common = new ArrayList<>();
		
		for(int i = 0; i < connections.size(); i++) {
			for(int j = 0; j < anAirport.getConnections().size(); j++) {
				if(connections.get(i).equals(anAirport.getConnections().get(j)))		
					common.add(connections.get(i));
			}
		}
		
		return common;
	
	}
	
	public void printCompanies() {
		
		for(String a: companies )
			System.out.println(a);
		
	}
	
	public boolean equals(Object obj) { // overriding equals so that it compares 2 airport objects by comparing their names
		
		Airport anAirport = (Airport)obj;
		
		return (name.equals(anAirport.name));
	}

	// below are getters and setters of airport class
	
	public void setConnections(Airport newConnection) {
		connections.add(newConnection);
	}
	
	public void setCompanies(String newCompany) {
		companies.add(newCompany);
	}

	public String getName() {
		return name;
	}

	public ArrayList<Airport> getConnections() {
		return connections;
	}
	
	public String getCity() {
		return city;
	}

	public String getCodename() {
		return codename;
	}

	public String getCountry() {
		return country;
	}

	public ArrayList<String> getCompanies() {
		return companies;
	}
	
}