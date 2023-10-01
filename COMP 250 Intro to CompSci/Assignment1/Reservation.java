package TravelAgency;

public abstract class Reservation {

	//private field of Reservation
	private String name;

	//constructor for reservation which takes a string
	public Reservation(String n) {

		this.name = n;
	}

	//returns the name under the reservaiton
	public final String reservationName() {

		return this.name;
	}
	
	
//gets the cost of the reservaiton
	public abstract int getCost();
	
	
//checks if two reservaitons are equal
	public abstract boolean equals(Object obj);
	
	
	public String toString() {
		return "Reservation [name=" + name + "]";
	}


	
}