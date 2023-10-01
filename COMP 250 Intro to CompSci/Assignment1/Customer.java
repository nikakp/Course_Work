package TravelAgency;

public class Customer {

	//private attributes of  Customer
	private String name;
	private int balance;
	private Basket resOfCustomer;

	//constructor of Customer which takes in the name of the customer and balance in cents
	public Customer(String n, int b) {

		this.name = n;
		this.balance = b;
		this.resOfCustomer = new Basket();

	}

	//returns the name of the customer
	public String getName() {

		return this.name;

	}

	//returns the balance of the customer
	public int getBalance() {

		return this.balance;

	}

	//returns the basket of the customer
	public Basket getBasket() {

		return this.resOfCustomer;

	}

	//adds funds to this customer by taking in an int and updating the balace of their account
	//and returning the new balance
	public int addFunds(int a) {

		if (a < 0) {

			throw new IllegalArgumentException("Your input needs to be >= 0");

		}

		this.balance = this.balance + a;
		return this.balance;

	}

	//adds a reservation to the customers basket and returns the number of reservations
	public int addToBasket(Reservation r) {

		if (this.name.equals(r.reservationName())) {

			this.resOfCustomer.add(r);

			return this.resOfCustomer.getNumOfReservations();

		}

		throw new IllegalArgumentException("The customers name did not match the name on the reservation");

	}

	
	//adds a reservation to the customers basket depending on if the boolean breaky is true or false
	// will determine if the reservation is of hotel type or bnb type and returns the 
	//number of reservations
	public int addToBasket(Hotel h, String t, int num, boolean breaky) {

		if (breaky) {

			HotelReservation hotelResWithBreaky = new BnBReservation(this.name, h, t, num);

			return this.resOfCustomer.add(hotelResWithBreaky);

		}

		HotelReservation hotelRes = new HotelReservation(this.name, h, t, num);

		return this.resOfCustomer.add(hotelRes);

	}

	//adds a flight reservation to the customers basket
	public int addToBasket(Airport a, Airport b) {

		FlightReservation reservation = new FlightReservation(this.name, a, b);

		return this.resOfCustomer.add(reservation);

	}

	//removes a reservation from the customers basket that matches the reservation passed in
	public boolean removeFromBasket(Reservation r) {

		return this.resOfCustomer.remove(r);

	}

	//gets balance left after the customer pays for their reservations 
	public int checkOut() {

		if (this.balance < this.resOfCustomer.getTotalCost()) {

			throw new IllegalStateException("You do not have sufficient funds to pay for your stay at this hotel");

		}

		int balanceLeft = this.balance - this.resOfCustomer.getTotalCost();

		this.resOfCustomer.clear();

		return balanceLeft;

	}
	
	public String toString() {
		return "BnBReservation ["+super.toString()+"]";
		}
	
	public static void customerTest() {
		Customer c = new Customer("Nika Prairie", 0);
		System.out.println("c: "+c);
		System.out.println("c.getName():" + c.getName());
		c.addFunds(10);
		System.out.println("c.getBalance()==10?:" + c.getBalance());
		c.checkOut();
		System.out.println("c after checkOut: "+c);
		
		try {
			c.addFunds(-1);
			System.out.println("We should have generated invalidRoom");
		} catch (IllegalArgumentException e) {
			System.out.println("Exception caught successfully for addFund(-1)!");
		}
		
		
		Room room1 = new Room("king");
		Room room2 = new Room("queen");
		Room room3 = new Room("king");
		Room room4 = new Room("king");
		Room room5 = new Room("king");

		Room room6 = new Room("queen");

		Room[] rooms1 = { room1, room2, room3, room4, room5, room6 };

		Hotel h1 = new Hotel("Marriot", rooms1);

		System.out.println("C before c.addToBasket(h1,\"queen\", 5, false);");

		int res = c.addToBasket(h1, "queen", 5, false);
		System.out.println("C after c.addToBasket(h1, \"queen\", 5, false);, " + c + "res=" + res);
		res = c.addToBasket(h1, "queen", 5, true);
		System.out.println("C after c.addToBasket(h1, \"queen\", 5, true);, " + c + "res=" + res);
		Airport a = new Airport(10, 10, 200);
		Airport b = new Airport(2, 2, 300);
		res = c.addToBasket(a, b);
		System.out.println("C after res = c.addToBasket(a, b) " + c + "res=" + res);
		try {
			c.checkOut();
			System.out.println("We should have generated an exception for checkout");
		} catch (IllegalStateException e) {
			System.out.println("Exception caught successfully for checkout()!");
		}
		c.addFunds(120874);
		System.out.println("Cost of basket: " + c.getBasket().getTotalCost());
		c.checkOut();
		HotelReservation r1 = new HotelReservation("Nika Prairie", h1, "King", 1);

		c.addToBasket(r1);

		System.out.println("Removed from basket : " + c.removeFromBasket(r1));
		
	try {
		   c.addToBasket(r1);
		   
		   }
	catch (IllegalArgumentException e) {
				   System.out.println("Exception caught successfully!");
			   
		   }
	
	try {
		   c.addToBasket(r1);
		    
		   }
	catch (IllegalArgumentException e) {
				   System.out.println("Exception caught successfully!");
			   
		   }
	
	System.out.println("Removed from basket : " + c.removeFromBasket(r1)); 
	
	
	}
	
	
}