package TravelAgency;

import java.lang.Math;

public class Airport {

	private int xcoord;
	private int ycoord;
	private int fees;

	public Airport(int x, int y, int f) {

		this.xcoord = x;
		this.ycoord = y;
		this.fees = f;

	}

	public int getFees() {

		return this.fees;

	}

	public static int getDistance(Airport a, Airport b) {

		//get each y and x value by finding the difference between x1, x2 and y1, y2
		
		int xDelta = (a.xcoord - b.xcoord);
		int yDelta = (a.ycoord - b.ycoord);

		//calculate the distance between two airports

		double distance = Math.sqrt(Math.pow(xDelta, 2) + Math.pow(yDelta, 2));

		//returns the rounded up distance value, as an integer 
		
		return (int) Math.ceil(distance);
	}


	
	public static void airportTest() {
		
		Airport a = new Airport(10,10,200);
		Airport b = new Airport(2,2,300);
		Airport c = new Airport(-1,-1,300);
		
		int distance = getDistance(a,b);
		
		System.out.println("The distance between " +a+" and "+b+" is: " + distance);
		distance = getDistance(a,c);
		System.out.println("The distance between " +a+" and "+c+" is: " + distance);
		System.out.println("Fees for "+a+" are: " + a.getFees());
	}

	@Override
	public String toString() {
		return "Airport [xcoord=" + xcoord + ", ycoord=" + ycoord + ", fees=" + fees + "]";
	}
	public static void main(String[] args) {
		
	Room.roomTest();
	Hotel.hotelTest();
	airportTest();
		Basket.basketTest();
	Customer.customerTest();
	 BnBReservation.bnbReservationTest();
		FlightReservation.flightReservationTest();

	HotelReservation.hotelReservationTest();
	//  Reservation, can't do directly since it's an abstract type
		//SyntaxTester.tester();
		//MiniTester.miniTest();
		System.out.println("Testing completed");

		return;
	}
	
}