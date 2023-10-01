package TravelAgency;

public class Room {

	//private attributes of Room
	private String type;
	private int price;
	private boolean availability;

	// constructor for a Room that takes a String as input
	// only constructs a Room if the String passed is double, queen or king

	public Room(String room) {

		if (room.equalsIgnoreCase("double")) {

			this.type = room;
			this.price = 9000;
			this.availability = true;

		} else if (room.equalsIgnoreCase("queen")) {

			this.type = room;
			this.price = 11000;
			this.availability = true;

		} else if (room.equalsIgnoreCase("king")) {

			this.type = room;
			this.price = 15000;
			this.availability = true;

		} else {

			throw new IllegalArgumentException("There is no room of such type that can be created");
		}

	}

	// constructor for a Room that takes as input another Room and creates a copy of
	// the input Room

	public Room(Room copy) {

		this.type = copy.type;
		this.price = copy.price;
		this.availability = copy.availability;

	}

	// returns type of Room

	public String getType() {
		return this.type;
	}

	// returns price of the Room

	public int getPrice() {
		return this.price;
	}

	// switches the availability of a Room

	public void changeAvailability() {
		this.availability = !this.availability;
	}

	// Looks through an array of Rooms to return the first available one of the
	// matching type

	public static Room findAvailableRoom(Room[] rooms, String type) {

		for (int i = 0; i < rooms.length; i++) {

			if (((rooms[i].getType()).equalsIgnoreCase(type)) && (rooms[i].availability == true)) {

			

				return rooms[i];
			}

		}
		return null;
	}

	// takes the first unavailable room of the correct type from the array of Rooms
	// and makes it available
	// and returns true if it is able to do so, returns false otherwise

	public static boolean makeRoomAvailable(Room[] rooms, String type) {

		for (int i = 0; i < rooms.length; i++) {

			if (((rooms[i].getType()).equalsIgnoreCase(type)) && (rooms[i].availability == false)) {

				rooms[i].changeAvailability();
				return true;

			}

		}

		return false;
	}

	public static void roomTest() {

		Room myRoom = new Room("queen");
		Room copyRoom = new Room(myRoom);

		System.out.println("Availibility of myRoom " + myRoom.availability);
		System.out.println("Price of myRoom " + myRoom.price);
		System.out.println("Type of myRoom " + myRoom.type);
		System.out.println();
		System.out.println("Availibility of copyRoom " + copyRoom.availability);
		System.out.println("Price of copyRoom " + myRoom.price);
		System.out.println("Type of myRoom " + myRoom.type);
		System.out.println();
		System.out.println("Price of myRoom " + myRoom.getPrice());
		System.out.println("Price of copyRoom " + copyRoom.getPrice());
		System.out.println();
		System.out.println("Type of myRoom " + myRoom.getType());
		System.out.println("Type of copyRoom " + copyRoom.getType());
		System.out.println();
		System.out.println("Availability of myRoom " + myRoom.availability);

		myRoom.changeAvailability();
		System.out.println(
				"Availability of myRoom after changeAvailability() method has been called " + myRoom.availability);

		copyRoom.changeAvailability();
		System.out.println("Called changeAvailability for copyRoom:" + copyRoom);
		copyRoom.changeAvailability();
		System.out.println("Called changeAvailability for copyRoom:" + copyRoom);

		Room room1 = new Room("king");
		Room room2 = new Room("king");
		Room room3 = new Room("king");
		Room room4 = new Room("double");
		Room room5 = new Room("queen");
		try {
			Room invalidRoom = new Room("heart shapped");
			System.out.println("We should have generated invalidRoom" + invalidRoom);
		} catch (IllegalArgumentException e) {
			System.out.println("Exception caught successfully!");
		}

		Room[] rooms = { room1, room2, room3, room4, room5 };
		Room[] emptyRooms = {};

		/*
		 * Room[] rooms = new Room[5];
		 * 
		 * rooms[0] = room1; rooms[1] = room2; rooms[2] = room3; rooms[3] = room4;
		 * rooms[4] = room5;
		 */

		Room returnedRoom = findAvailableRoom(rooms, "queen");

		System.out.println("Availibility of returnedRoom " + returnedRoom.availability);
		System.out.println("Price of returnedRoom " + returnedRoom.price);
		System.out.println("Type of returnedRoom " + returnedRoom.type);
		System.out.println();

		returnedRoom = findAvailableRoom(emptyRooms, "queen");
		System.out.println("returnedRoom = findAvailableRoom(emptyRooms, \"queen\"): " + returnedRoom);
		returnedRoom = findAvailableRoom(emptyRooms, "heart shapped");
		System.out.println("returnedRoom = findAvailableRoom(emptyRooms, \"heart shapped\"): " + returnedRoom);

		Room testRoom = new Room("queen");

		System.out.println("Availibility of testRoom " + testRoom.availability);

		testRoom.changeAvailability();

		System.out.println("Availibility of testRoom after changeAvailability function is called on testRoom "
				+ testRoom.availability);
		for(int i=0; i < rooms.length; i++) {
			rooms[i].changeAvailability();
		}
		returnedRoom = findAvailableRoom(rooms, "queen");
		System.out.println("Searching for available room when all are booked: " + returnedRoom);
		
		makeRoomAvailable(rooms,"queen");
		returnedRoom = findAvailableRoom(rooms, "queen");
		System.out.println("Searching for available room when all but one are booked: " + returnedRoom);
		
		makeRoomAvailable(rooms,"heart shapped");

		returnedRoom = findAvailableRoom(rooms, "queen");
		System.out.println("Searching for available room when invalid room type released: " + returnedRoom);

	}

	public String toString() {
		return "Room [type=" + type + ", price=" + price + ", availability=" + availability + "]";
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * 
	 * //FlightReservation.flightReservationTest();
	 * 
	 * }
	 */

}

