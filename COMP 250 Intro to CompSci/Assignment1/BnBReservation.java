package TravelAgency;

public class BnBReservation extends HotelReservation {

	// constructor of the bnb reservation
	public BnBReservation(String name, Hotel h, String type, int num) {

		super(name, h, type, num);

	}

	
	//returns the price of a stay at the bnb with the breakfast included
	public int getCost() {

		final int breakfastCost = 1000;
		return (super.getCost() + (super.getNumOfNights() * breakfastCost));

	}

	public static void bnbReservationTest() {

		Room room1 = new Room("king");
		Room room2 = new Room("king");
		Room room3 = new Room("king");
		Room room4 = new Room("double");
		Room room5 = new Room("queen");

		Room[] rooms = { room1, room2, room3, room4, room5 };

		Hotel h = new Hotel("marriot", rooms);

		BnBReservation res = new BnBReservation("Nika Prairie", h, "kINg", 4);

		System.out.println("BnBReservation Cost : " + res.getCost());
		System.out.println("BnBReservation Num of Nights : " + res.getNumOfNights());
		System.out.println("BnBReservation Name on Reso : " + res.reservationName());

	}


}