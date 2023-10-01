package TravelAgency;


public class HotelReservation extends Reservation {

	// private attributes of a HotelReservation

	private Hotel hotelRes;
	private String type;
	private int numOfNights;
	private int price;
	
	
//constructor of the hotelReservation that takes a string for the name of the reservation
// a hotel, a type for the room, and a number of nights in int
	public HotelReservation(String name, Hotel h, String t, int num) {

		super(name);
		this.hotelRes = h;
		this.type = t;
		this.numOfNights = num;
		this.price = h.reserveRoom(t);

	}

	//returns number of nights for the stay
	public int getNumOfNights() {

		return this.numOfNights;

	}

	//returns total cost for the stay
	public int getCost() {

		return (this.numOfNights * this.price);

	}

//checks to ensure both objects are equal	
	
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HotelReservation other = (HotelReservation) obj;
		if (hotelRes == null) {
			if (other.hotelRes != null)
				return false;
		} else if (!hotelRes.equals(other.hotelRes))
			return false;
		if (numOfNights != other.numOfNights)
			return false;
		if (price != other.price)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (!this.reservationName().equals(other.reservationName()))
			return false;
		return true;
	}

	public String toString() {
		return "HotelReservation [hotelRes = " + hotelRes + ", type = " + type + ", numOfNights = " + numOfNights
				+ ", price = " + price + "]";
	}
	
	public static void hotelReservationTest() {
		Room room1 = new Room("king");
		Room room2 = new Room("queen");
		Room room3 = new Room("king");
		Room room4 = new Room("king");
		Room room5 = new Room("king");

		Room room6 = new Room("queen");
		Room room7 = new Room("queen");
		Room room8 = new Room("double");
		Room room9 = new Room("queen");
		Room room10 = new Room("queen");

		Room room11 = new Room("double");
		Room room12 = new Room("double");
		Room room13 = new Room("king");
		Room room14 = new Room("double");
		Room room15 = new Room("double");

		Room[] rooms1 = { room1, room2, room3, room4, room5 };
		Room[] rooms2 = { room6, room7, room8, room9, room10 };
		Room[] rooms3 = { room11, room12, room13, room14, room15 };

		

		Hotel h1 = new Hotel("Marriot", rooms1);
		Hotel h2 = new Hotel("Hilton", rooms2);
		Hotel h3 = new Hotel("Four Seasons", rooms3);
		Hotel h4 = new Hotel("Fairmont", rooms3);
		Hotel h5 = new Hotel("Fairmont", rooms1);
		HotelReservation hr1 = new HotelReservation("Nika", h1, "queen", 1);
		HotelReservation hr2 = new HotelReservation("niks", h2, "double", 2);
		HotelReservation hr3 = new HotelReservation("becks", h3, "king", 3);
		HotelReservation hr4 = new HotelReservation("becks", h4, "king", 3);
		HotelReservation hr5 = new HotelReservation("becks", h5, "king", 3);
		HotelReservation hr6 = new HotelReservation("becks", h5, "king", 6);
		System.out.println(hr1);
		System.out.println(hr2);
		System.out.println(hr3);
		System.out.println("hr1==hr1?"+ hr1.equals(hr1));
		System.out.println("hr1==hr2?"+ hr1.equals(hr2));
		System.out.println("hr3==hr4?"+ hr3.equals(hr4));
		System.out.println("hr4==hr5?"+ hr4.equals(hr5));
		System.out.println("hr5==hr6?"+ hr5.equals(hr6));
		System.out.println("hr5.getNumberOfNights() == 3?" + hr5.getNumOfNights());
		System.out.println("hr5.getCost(): " + hr5.getCost());
	}
	
}