package TravelAgency;


public class Basket {

	//private attributes of Basket
	private final static int initialBasketSize = 20;
	private final static int incrementAmount = 5;
	private int numReservations;
	private Reservation[] listOfReservations;

	public Basket() {

		this.listOfReservations = new Reservation[initialBasketSize];
		this.numReservations = 0;

	}

	private void resizeBasket(int increment) {

		Reservation[] res = new Reservation[this.listOfReservations.length + increment];

		for (int i = 0; i < this.numReservations; i++) {

			res[i] = this.listOfReservations[i];

		}
		for (int i = this.numReservations; i < res.length; i++) {
			res[i] = null;
		}

		this.listOfReservations = res;

	}

	//returns a shallow copy of the array of reservations 
	public Reservation[] getProducts() {
		// add a shallow copy

		Reservation[] copy = new Reservation[this.numReservations];
		for (int i = 0; i < this.numReservations; i++) {

			copy[i] = this.listOfReservations[i];

		}

		return copy;

	}

	//adds a reservation which is passed in as input to the array of reservations
	public int add(Reservation r) {
		

		if (this.numReservations == this.listOfReservations.length) {

			this.resizeBasket(incrementAmount);

		}

		this.listOfReservations[this.numReservations] = r;
		this.numReservations++;

		return this.numReservations;

	}

	//removes the first reservation matching the one passed as input from the array of reservations
	public boolean remove(Reservation r) {

		boolean found = false;
		int i = 0;

		while ((i < this.numReservations) && (!found)) {

			if (this.listOfReservations[i].equals(r)) {

				found = true;

			} else {
				i++;
			}

		}
		if (!found) {

			return false;

		}

		

		for (int j = i; j < this.numReservations - 1; j++) {

			this.listOfReservations[j] = this.listOfReservations[j + 1];
		}
		this.listOfReservations[this.numReservations-1] = null;
		this.numReservations--;

		return true;

	}

	//clears all reservations from the array of reservations in the basket
	public void clear() {

		for (int i = 0; i < this.numReservations; i++) {

			this.listOfReservations[i] = null;

		}

		this.listOfReservations = new Reservation[0];
		this.numReservations = 0;

	}

	//returns number of reservaitons in the basket
	public int getNumOfReservations() {

		return this.numReservations;

	}

	//gets the total cost of all the reservations in the basket
	public int getTotalCost() {

		int totalValue = 0;

		for (int i = 0; i < this.numReservations; i++) {

			totalValue = totalValue + this.listOfReservations[i].getCost();

		}

		return totalValue;

	}

	public String toString() {
		return "Basket [numReservations = " + numReservations;
	}

	public static void basketTest() {

		Basket b = new Basket();

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
		HotelReservation hr1 = new HotelReservation("Nika", h1, "queen", 1);
		HotelReservation hr2 = new HotelReservation("niks", h2, "double", 2);
		HotelReservation hr3 = new HotelReservation("becks", h3, "king", 3);

		System.out.println("b.numReservations: " + b.numReservations);
		System.out.println(b.add(hr1));
		System.out.println(b.add(hr2));
		System.out.println(b.add(hr3));

		System.out.println(b.listOfReservations.length);
		Basket b1 = new Basket();
		System.out.println(b1.add(hr2));
		System.out.println(b1.add(hr3));
		b1.add(new FlightReservation("John", new Airport(5, 5, 5), new Airport(10, 10, 10)));
		System.out.println(b1);
		System.out.println(b1.numReservations);
		System.out.println(b1.listOfReservations);

	
		System.out.println("getTotalCost():" + b1.getTotalCost());
		b1.clear();
		System.out.println("clear of b1 " + b1);
		b1.remove(hr1);
		System.out.println("after removal hr1 from b1:" + b1);
		b1.add(hr1);
		System.out.println("Num of reservations b1:" + b1.getNumOfReservations());
		System.out.println("after add hr1 to b1:" + b1);
		b1.add(hr2);
		b1.add(hr3);
		System.out.println("Num of reservations b1:" + b1.getNumOfReservations());
		System.out.println("after add hr2 hr3 to b1:" + b1);
		System.out.println("-------");
		System.out.println("before removal of hr2: " + b);
		b.remove(hr2);
		System.out.println("Num of reservations:" + b.getNumOfReservations());
		System.out.println("after removal hr2: " + b);
		b.remove(hr3);
		System.out.println("Num of reservations:" + b.getNumOfReservations());
		System.out.println("after removal hr3: " + b);
		b.remove(hr3);
		System.out.println("Num of reservations:" + b.getNumOfReservations());
		System.out.println("after removal hr3 extra time: " + b);
	}

}