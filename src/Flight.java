public class Flight {

	private Airport AirportA;
	private Airport AirportB;
	private int duration;
	private String company;
	
	public Flight(Airport AirportA, Airport AirportB, int duration, String company) { 
		
		this.AirportA = AirportA;
		this.AirportB = AirportB;
		this.duration = duration;
		this.company = company;
	
	}
	
	public String toString() { // overriding toString method to get the desired format
	
		return String.format("Flight operated by " + this.company + ", duration " + this.duration + " minutes");
	
	}
	
	public boolean equals(Object obj) { // overriding equals for Flight class objects. 2 Flight objects are now equal when they have the same AirportA and AirportB
	
		Flight aFlight = (Flight)obj;
		
		if((aFlight.getAirportA().equals(AirportA) || aFlight.getAirportA().equals(AirportB)) && (aFlight.getAirportB().equals(AirportA) || 
				aFlight.getAirportB().equals(AirportB)))
			return true;
		else
			return false;
		
	}
	
	// below are getters and setters of flight class
	
	public int getDuration() {
		return duration;
	}

	public Airport getAirportA() {
		return AirportA;
	}

	public Airport getAirportB() {
		return AirportB;
	}

	public String getCompany() {
		return company;
	}

}