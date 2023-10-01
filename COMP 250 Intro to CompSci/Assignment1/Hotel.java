package TravelAgency;


public class Hotel {

	private String hotelName;
	private Room[] rooms;
	
	//constructor for a hotel that takes a string, and an array of rooms as input

	public Hotel(String n, Room[] r) {

		this.hotelName = n;

		this.rooms = new Room[r.length];

		for (int i = 0; i < r.length; i++) {

			this.rooms[i] = new Room(r[i]);

		}

	}
	//reserves a room by finding an available room and returning the cost of that room and changing 
	//its availability

	public int reserveRoom(String type) {

		Room r = Room.findAvailableRoom(this.rooms, type);

		if (null != r) {

			r.changeAvailability();
			return r.getPrice();

		}

		throw new IllegalArgumentException("We were unable to reserve a room");

	}
	
	//cancels a room when you give it the type of room you need to cancel

	public boolean cancelRoom(String type) {

		return Room.makeRoomAvailable(this.rooms, type);

	}

	public static void hotelTest() {

		Room room1 = new Room("king");
		Room room2 = new Room("king");
		Room room3 = new Room("king");
		Room room4 = new Room("double");
		Room room5 = new Room("queen");
		Room room6 = new Room("double");
		Room room7 = new Room("queen");

		Room[] rooms = { room1, room2, room3, room4, room5, room6, room7 };
		Room[] emptyRooms = {};

		Hotel h1 = new Hotel("marriot", rooms);
		Hotel h2 = new Hotel("Motel 6", emptyRooms);

		for (int i = 0; i < h1.rooms.length; i++) {
			System.out.println("Room " + i + ": " + h1.rooms[i]);
		}
		System.out.println("h1: " + h1);
		System.out.println("h2:" + h2);

		System.out.println("h1.reserveRoom(\"queen\"):" + h1.reserveRoom("queen"));
		try {
			System.out.println("h1.reserveRoom(\"queen\"):" + h1.reserveRoom("heart shapped"));
			System.out.println("Error, you should not see this!");
		} catch (IllegalArgumentException e) {
			System.out.println("Exception caught successfully!");
		}
		try {
			System.out.println("hotel with no rooms testing, h2.reserveRoom(\"queen\"):" + h2.reserveRoom("queen"));
			System.out.println("Error, you should see an exception!");
		} catch (IllegalArgumentException e) {
			System.out.println("Exception caught successfully for a hotel with no rooms when calling reserveRoom!");
		}
		System.out.println("before cancel:"+h1);
		h1.cancelRoom("queen");
		System.out.println("after cancel:"+h1);
	}


	public String toString() {
		return "Hotel [hotelName=" + hotelName;
	}

}