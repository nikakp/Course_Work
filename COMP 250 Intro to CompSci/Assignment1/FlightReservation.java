package TravelAgency;

import java.lang.Math;

public class FlightReservation extends Reservation {

	//private attributes of FlightReservation
	private Airport departure;
	private Airport arrival;

	
	//makes sure that both airports are not equal and that they are not null
	public FlightReservation(String r, Airport d, Airport a) {

		super(r);
		if (null == a) {
			throw new IllegalArgumentException("The arrival airport provided is null");
		}
		if (null == d) {
			throw new IllegalArgumentException("The departure airport provided is null");
		}
		if (a.equals(d)) {

			throw new IllegalArgumentException("The two Airports in question, are the same");

		}

		this.departure = d;
		this.arrival = a;
	}

	//gets the cost of a flight reservation
	public int getCost() {

		final double kmPerGallon = 167.52;
		final int centsPerGallon = 124;
		final int flatFee = 5375;

		int distance = Airport.getDistance(this.departure, this.arrival);

		double numGallons = ((double) distance) / kmPerGallon;

		double total = numGallons * centsPerGallon + this.departure.getFees() + this.arrival.getFees() + flatFee;

		return ((int) Math.ceil(total));

	}

	//checks if both objects are equal
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlightReservation other = (FlightReservation) obj;
		if (arrival == null) {
			if (other.arrival != null)
				return false;
		} else if (!arrival.equals(other.arrival))
			return false;
		if (departure == null) {
			if (other.departure != null)
				return false;
		} else if (!departure.equals(other.departure))
			return false;
		// Forgot to compare names!!!
		if (!this.reservationName().equals(other.reservationName()))
			return false;
		return true;
	}

	public String toString() {
		return "FlightReservation [departure = " + departure + ", arrival = " + arrival + " super=[" 
	            + super.toString() + "]]";
	}

	public static void flightReservationTest() {

		Airport a = new Airport(500, 300, 1000);
		Airport b = new Airport(1000, -1000, 1000);
		Airport c = new Airport(1000, -1000, 1000);

		FlightReservation res = new FlightReservation("Nika Prairie", a, b);
		System.out.println("res: " + res);
		FlightReservation res2 = new FlightReservation("The Weekend", a, b);
		FlightReservation res3 = new FlightReservation(null, a, b);
		FlightReservation res4 = new FlightReservation("Nika Prairie", a, c);
		//FlightReservation res5 = new FlightReservation("Nika Prairie", a, null);
		FlightReservation res6 = new FlightReservation("Nika Prairie", c, b);

		System.out.println(res);
		System.out.println(res.getCost());
		System.out.println("res == res? " + res.equals(res));
		System.out.println("res == res2? " + res.equals(res2));
		System.out.println("res == res3? " + res.equals(res3));
		System.out.println("res == res4? " + res.equals(res4));
		System.out.println("res == res4? " + res.equals(res6));
		System.out.println("res == null? " + res.equals(null));
		System.out.println("res == a? " + res.equals(a));
		
	}

}
