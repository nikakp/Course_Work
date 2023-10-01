import java.util.Arrays;
import java.util.Random;

public class TrainLine {

	private TrainStation leftTerminus;
	private TrainStation rightTerminus;
	private String lineName;
	private boolean goingRight;
	public TrainStation[] lineMap;
	public static Random rand;

	public TrainLine(TrainStation leftTerminus, TrainStation rightTerminus, String name, boolean goingRight) {
		this.leftTerminus = leftTerminus;
		this.rightTerminus = rightTerminus;
		this.leftTerminus.setLeftTerminal();
		this.rightTerminus.setRightTerminal();
		this.leftTerminus.setTrainLine(this);
		this.rightTerminus.setTrainLine(this);
		this.lineName = name;
		this.goingRight = goingRight;

		this.lineMap = this.getLineArray();
	}

	public TrainLine(TrainStation[] stationList, String name, boolean goingRight)
	/*
	 * Constructor for TrainStation input: stationList - An array of TrainStation
	 * containing the stations to be placed in the line name - Name of the line
	 * goingRight - boolean indicating the direction of travel
	 */
	{
		TrainStation leftT = stationList[0];
		TrainStation rightT = stationList[stationList.length - 1];

		stationList[0].setRight(stationList[stationList.length - 1]);
		stationList[stationList.length - 1].setLeft(stationList[0]);

		this.leftTerminus = stationList[0];
		this.rightTerminus = stationList[stationList.length - 1];
		this.leftTerminus.setLeftTerminal();
		this.rightTerminus.setRightTerminal();
		this.leftTerminus.setTrainLine(this);
		this.rightTerminus.setTrainLine(this);
		this.lineName = name;
		this.goingRight = goingRight;

		for (int i = 1; i < stationList.length - 1; i++) {
			this.addStation(stationList[i]);
		}

		this.lineMap = this.getLineArray();
	}

	public TrainLine(String[] stationNames, String name, boolean goingRight) {
		/*
		 * Constructor for TrainStation. input: stationNames - An array of String
		 * containing the name of the stations to be placed in the line name - Name of
		 * the line goingRight - boolean indicating the direction of travel
		 */
		TrainStation leftTerminus = new TrainStation(stationNames[0]);
		TrainStation rightTerminus = new TrainStation(stationNames[stationNames.length - 1]);

		leftTerminus.setRight(rightTerminus);
		rightTerminus.setLeft(leftTerminus);

		this.leftTerminus = leftTerminus;
		this.rightTerminus = rightTerminus;
		this.leftTerminus.setLeftTerminal();
		this.rightTerminus.setRightTerminal();
		this.leftTerminus.setTrainLine(this);
		this.rightTerminus.setTrainLine(this);
		this.lineName = name;
		this.goingRight = goingRight;
		for (int i = 1; i < stationNames.length - 1; i++) {
			this.addStation(new TrainStation(stationNames[i]));
		}

		this.lineMap = this.getLineArray();

	}

	// adds a station at the last position before the right terminus
	public void addStation(TrainStation stationToAdd) {
		TrainStation rTer = this.rightTerminus;
		TrainStation beforeTer = rTer.getLeft();
		rTer.setLeft(stationToAdd);
		stationToAdd.setRight(rTer);
		beforeTer.setRight(stationToAdd);
		stationToAdd.setLeft(beforeTer);

		stationToAdd.setTrainLine(this);

		this.lineMap = this.getLineArray();
	}

	public String getName() {
		return this.lineName;
	}

	public int getSize() {

		int numReturn = this.lineMap.length;
		return numReturn;

	}

	public void reverseDirection() {
		this.goingRight = !this.goingRight;
	}

	
	public TrainStation travelOneStation(TrainStation current, TrainStation previous) {

		TrainStation yourTrainStation = findStation(current.getName());

		
		if ( current.hasConnection && !current.getTransferStation().equals(previous)) {

			return current.getTransferStation();

		}

		return this.getNext(current);

	}

	// You can modify the header to this method to handle an exception. You cannot
	// make any other change to the header.
	public TrainStation getNext(TrainStation station) {

		TrainStation yourStation = findStation(station.getName());

		if (yourStation.getRight() == null && yourStation.getLeft() == null) {

			throw new StationNotFoundException("Sorry but your train station could nto be found");

		}
		// if going right
		// if we are at the end of the line, turnaround
		// return the left node
		// else return the right node
		// else we are going left
		// if we are at the end of the line, turnaround
		// return the right node
		// else return left node

		if (this.goingRight) {

			if (yourStation.getRight() == null) {

				reverseDirection();

				return yourStation.getLeft();

			} else {

				return yourStation.getRight();

			}

		} else {

			if (yourStation.getLeft() == null) {

				reverseDirection();

				return yourStation.getRight();

			} else {

				return yourStation.getLeft();

			}

		}

	}

	// You can modify the header to this method to handle an exception. You cannot
	// make any other change to the header.
	public TrainStation findStation(String name) {

		for (TrainStation station : this.lineMap) {

			if (station.getName().equals(name)) {

				return station;

			}

		}

		throw new StationNotFoundException("I'm sorry but the station was not found");

	}

	public void sortLine() {

		TrainStation[] yourTrainStations = this.getLineArray();

		int n = yourTrainStations.length;

		for (int i = 0; i < n; i++) {

			TrainStation station = yourTrainStations[i];
			int j = i - 1;

			while (j >= 0 && 0 > station.getName().compareToIgnoreCase(yourTrainStations[j].getName())) {

				yourTrainStations[j + 1] = yourTrainStations[j];
				j--;

			}

			yourTrainStations[j + 1] = station;

		}

		this.updatePointers(yourTrainStations);

	}

	public TrainStation[] getLineArray() {

		int counter = 0;

		TrainStation current = this.leftTerminus;
		TrainStation prev = null;

		while ((current != null) && (prev != current)) {
			prev = current;
			current = current.getRight();
			counter++;

		}

		TrainStation[] arrayCopy = new TrainStation[counter];

		current = this.leftTerminus;

		for (int i = 0; i < counter; i++) {

			arrayCopy[i] = current;
			current = current.getRight();

		}

		return arrayCopy;

	}

	private TrainStation[] shuffleArray(TrainStation[] array) {
		Random rand = new Random();
		
		rand.setSeed(11);

		for (int i = 0; i < array.length; i++) {
			int randomIndexToSwap = rand.nextInt(array.length);
			TrainStation temp = array[randomIndexToSwap];
			array[randomIndexToSwap] = array[i];
			array[i] = temp;
		}
		this.lineMap = array;
		return array;
	}

	
	private void updatePointers(TrainStation[] shuffledArray) {

		TrainStation prev = null;

		for (int i = 0; i < this.lineMap.length - 1; i++) {
			this.lineMap[i] = shuffledArray[i];
			this.lineMap[i].setLeft(prev);
			this.lineMap[i].setRight(shuffledArray[i + 1]);
			this.lineMap[i].setNonTerminal();
			prev = this.lineMap[i];
		}
		if (this.getSize() > 0) {
			this.lineMap[this.lineMap.length - 1] = shuffledArray[shuffledArray.length - 1];
			this.lineMap[this.lineMap.length - 1].setLeft(prev);
			this.lineMap[this.lineMap.length - 1].setRight(null);
			this.lineMap[this.lineMap.length - 1].setNonTerminal();
		}
		this.leftTerminus = this.lineMap[0];
		this.leftTerminus.setLeftTerminal();
		this.rightTerminus = this.lineMap[this.getSize() - 1];
		this.rightTerminus.setRightTerminal();
	}


	public void shuffleLine() {

		// you are given a shuffled array of trainStations to start with
		TrainStation[] lineArray = this.getLineArray();
		TrainStation[] shuffledArray = shuffleArray(lineArray);

		this.updatePointers(shuffledArray);

		

	}

	public String toString() {
		TrainStation[] lineArr = this.getLineArray();
		String[] nameArr = new String[lineArr.length];
		for (int i = 0; i < lineArr.length; i++) {
			nameArr[i] = lineArr[i].getName();
		}
		return Arrays.deepToString(nameArr);
	}

	public boolean equals(TrainLine line2) {

		// check for equality of each station
		TrainStation current = this.leftTerminus;
		TrainStation curr2 = line2.leftTerminus;

		try {
			while (current != null) {
				if (!current.equals(curr2))
					return false;
				else {
					current = current.getRight();
					curr2 = curr2.getRight();
				}
			}

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public TrainStation getLeftTerminus() {
		return this.leftTerminus;
	}

	public TrainStation getRightTerminus() {
		return this.rightTerminus;
	}

	public static void testTrainLine() {

		TrainStation train1 = new TrainStation("first");
		TrainStation train2 = new TrainStation("second");
		TrainStation train3 = new TrainStation("third");
		TrainStation train4 = new TrainStation("fourth");
		TrainStation train5 = new TrainStation("fifth");
		TrainStation train6 = new TrainStation("sixth");
		TrainStation train7 = new TrainStation("seventh");
		TrainStation train8 = new TrainStation("eighth");
		TrainStation train9 = new TrainStation("ninth");
		TrainStation train10 = new TrainStation("tenth");
		TrainStation train11 = new TrainStation("eleventh");
		TrainStation train12 = new TrainStation("twelveth");
		TrainStation train13 = new TrainStation("thirteenth");
		TrainStation train14 = new TrainStation("fourteenth");
		TrainStation train15 = new TrainStation("fifteenth");
		TrainStation train16 = new TrainStation("sixteenth");
		TrainStation train17 = new TrainStation("seventeenth");
		TrainStation train18 = new TrainStation("eighteenth");
		TrainStation train19 = new TrainStation("nineteenth");
		TrainStation train20 = new TrainStation("twentieth");
		TrainStation train21 = new TrainStation("twentifirst");

		TrainStation t1A = new TrainStation("t1A");
		TrainStation t2A = new TrainStation("t2A");
		TrainStation t3A = new TrainStation("t3A");
		TrainStation t4A = new TrainStation("t4A");

		TrainStation t1B = new TrainStation("t1B");
		TrainStation t2B = new TrainStation("t2B");
		TrainStation t3B = new TrainStation("t3B");
		TrainStation t4B = new TrainStation("t4B");

		TrainStation t1C = new TrainStation("t1C");
		TrainStation t2C = new TrainStation("t2C");
		TrainStation t3C = new TrainStation("t3C");
		TrainStation t4C = new TrainStation("t4C");

		TrainStation t1D = new TrainStation("t1D");
		TrainStation t2D = new TrainStation("t2D");
		TrainStation t3D = new TrainStation("t3D");
		TrainStation t4D = new TrainStation("t4D");

		TrainStation myTrain1 = new TrainStation("1.Hogsmeade");
		TrainStation myTrain2 = new TrainStation("2.StMungos");
		TrainStation myTrain3 = new TrainStation("3.WizardHat");
		TrainStation myTrain4 = new TrainStation("4.LittleWhinging");

		TrainStation[] myTrainListA = { t1A, t2A, t3A, t4A };
		TrainStation[] myTrainListB = { t1B, t2B, t3B, t4B };
		TrainStation[] myTrainListC = { t1C, t2C, t3C, t4C };
		TrainStation[] myTrainListD = { t1D, t2D, t3D, t4D };
		TrainStation[] myHarryPotterStations = { myTrain2, myTrain1, myTrain4, myTrain3 };

		TrainLine myTrainLineA = new TrainLine(myTrainListA, "A", true);
		TrainLine myTrainLineB = new TrainLine(myTrainListB, "B", true);
		TrainLine myTrainLineC = new TrainLine(myTrainListC, "C", false);
		TrainLine myTrainLineD = new TrainLine(myTrainListD, "D", true);
		TrainLine myHarryPotterLine = new TrainLine(myHarryPotterStations, "PotterLine", true);

		t1B.setConnection(myTrainLineA, t1A);
		t1A.setConnection(myTrainLineB, t1B);

		TrainStation[] firstList = { train1 };
		TrainStation[] secondList = { train2, train3 };
		TrainStation[] thirdList = { train4, train5, train6 };
		TrainStation[] fourthList = { train7, train8, train9, train10 };
		TrainStation[] fifthList = { train11, train12, train13, train14, train15 };
		TrainStation[] sixthList = { train16, train17, train18, train19, train20, train21 };

		TrainLine firstLine = new TrainLine(firstList, "1TrainLine", true);
		TrainLine secondLine = new TrainLine(secondList, "2TrainLine", true);
		TrainLine thirdLine = new TrainLine(thirdList, "3TrainLine", true);
		TrainLine fourthLine = new TrainLine(fourthList, "4TrainLine", true);
		TrainLine fifthLine = new TrainLine(fifthList, "5TrainLine", false);
		TrainLine sixthLine = new TrainLine(sixthList, "6TrainLine", false);

		// testing getSize() method
		System.out.println("Size of train line is: " + firstLine.getSize() + " and the name is " + firstLine.getName());
		System.out
				.println("Size of train line is: " + secondLine.getSize() + " and the name is " + secondLine.getName());
		System.out.println("Size of train line is: " + thirdLine.getSize() + " and the name is " + thirdLine.getName());
		System.out
				.println("Size of train line is: " + fourthLine.getSize() + " and the name is " + fourthLine.getName());

		// testing findStation() method
		try {
			System.out.println("Your station is : " + firstLine.findStation("BAHAHAH"));
			System.out.println("Failure: did not catch!");

		} catch (StationNotFoundException e) {
			System.out.println("Caught the missing train station!");
		}
		System.out.println("Your station is first: " + firstLine.findStation("first").getName());

		try {
			System.out.println("Your station is : " + secondLine.findStation("BAHAHAH"));
			System.out.println("Failure: did not catch!");

		} catch (StationNotFoundException e) {
			System.out.println("Caught the missing train station!");
		}
		System.out.println("Your station is third: " + secondLine.findStation("third").getName());

		try {
			System.out.println("Your station is : " + thirdLine.findStation("BAHAHAH"));
			System.out.println("Failure: did not catch!");

		} catch (StationNotFoundException e) {
			System.out.println("Caught the missing train station!");
		}
		System.out.println("Your station is fourth: " + thirdLine.findStation("fourth").getName());

		try {
			System.out.println("Your station is : " + thirdLine.findStation("BAHAHAH"));
			System.out.println("Failure: did not catch!");

		} catch (StationNotFoundException e) {
			System.out.println("Caught the missing train station!");
		}
		System.out.println("Your station is seventh: " + fourthLine.findStation("seventh").getName());
		System.out.println("Your station is eighth: " + fourthLine.findStation("eighth").getName());
		System.out.println("Your station is tenth: " + fourthLine.findStation("tenth").getName());

		// testing getNext() method

		System.out.println("You are at station " + train2.getName() + " The next station is third: "
				+ secondLine.getNext(train2).getName());
		System.out.println("You are at station " + train6.getName() + " The next station is fifth: "
				+ thirdLine.getNext(train6).getName());
		System.out.println("You are at station " + train9.getName() + " The next station is eighth: "
				+ fourthLine.getNext(train9).getName());
		System.out.println("You are at station " + train10.getName() + " The next station is ninth: "
				+ fourthLine.getNext(train10).getName());
		System.out.println("You are at station " + train14.getName() + " The next station is thirteenth: "
				+ fifthLine.getNext(train14).getName());
		System.out.println("You are at station " + train16.getName() + " The next station is seventeenth: "
				+ sixthLine.getNext(train16).getName());

		try {
			System.out.println("The next station is " + firstLine.getNext(train2));
		} catch (StationNotFoundException e) {
			System.out.println(
					"Caught the problem, there is no next train to get as there is only one train on this line!");
		}

		// testing travelOneSation() method

		System.out.println("Your train station is " + t1A.getName() + " on line " + t1A.getLine().getName()
				+ " the trasnfer station is " + t1A.getLine().travelOneStation(t1A, t1A.getRight()).getName());
		System.out.println("Your train station is " + t2C.getName() + " on line " + t2C.getLine().getName()
				+ ", the station you've travelled to is "
				+ t2C.getLine().travelOneStation(t2C, t2C.getRight()).getName());

		// testing shuffleLine() method

		myTrainLineD.shuffleLine();

		for (int i = 0; i < myTrainLineD.getSize(); i++) {

			System.out.println(myTrainLineD.lineMap[i].getName());

		}
		System.out.println();
		//// +
		System.out.println("Left: " + myTrainLineD.leftTerminus.getName() + ":L:"
				+ myTrainLineD.leftTerminus.isLeftTerminal() + " R:" + myTrainLineD.leftTerminus.isRightTerminal());

		System.out.println("Right: " + myTrainLineD.rightTerminus.getName() + ":L:"
				+ myTrainLineD.rightTerminus.isLeftTerminal() + " R:" + myTrainLineD.rightTerminus.isRightTerminal());
		System.out.println("Checking getRight:");
		for (int i = 0; i < myTrainLineD.getSize() - 1; i++) {

			System.out.println(myTrainLineD.lineMap[i].getRight().getName() + ":L:"
					+ myTrainLineD.lineMap[i].getRight().isLeftTerminal() + " R:"
					+ myTrainLineD.lineMap[i].getRight().isRightTerminal());

		}
		System.out.println("Checking getLeft:");
		for (int i = 1; i < myTrainLineD.getSize(); i++) {

			System.out.println("i: " + i + ":" + myTrainLineD.lineMap[i].getLeft().getName() + ":L:"
					+ myTrainLineD.lineMap[i].getLeft().isLeftTerminal() + " R:"
					+ myTrainLineD.lineMap[i].getLeft().isRightTerminal());

		}

		// testing sortLine() method

		myHarryPotterLine.shuffleLine();

		for (int i = 0; i < myHarryPotterLine.getSize(); i++) {

			System.out.println(myHarryPotterLine.lineMap[i].getName());

		}
		System.out.println();
		myHarryPotterLine.sortLine();

		for (int i = 0; i < myHarryPotterLine.getSize(); i++) {

			System.out.println(myHarryPotterLine.lineMap[i].getName());

		}

		for (int i = 0; i < myHarryPotterLine.getSize(); i++) {

			System.out.println(myHarryPotterLine.lineMap[i].getName());

		}
		System.out.println();
		//// +
		System.out.println("Left: " + myHarryPotterLine.leftTerminus.getName() + ":L:"
				+ myHarryPotterLine.leftTerminus.isLeftTerminal() + " R:"
				+ myHarryPotterLine.leftTerminus.isRightTerminal());

		System.out.println("Right: " + myHarryPotterLine.rightTerminus.getName() + ":L:"
				+ myHarryPotterLine.rightTerminus.isLeftTerminal() + " R:"
				+ myHarryPotterLine.rightTerminus.isRightTerminal());
		System.out.println("Checking getRight:");
		for (int i = 0; i < myHarryPotterLine.getSize() - 1; i++) {

			System.out.println(myTrainLineD.lineMap[i].getRight().getName() + ":L:"
					+ myHarryPotterLine.lineMap[i].getRight().isLeftTerminal() + " R:"
					+ myHarryPotterLine.lineMap[i].getRight().isRightTerminal());

		}
		System.out.println("Checking getLeft:");
		for (int i = 1; i < myHarryPotterLine.getSize(); i++) {

			System.out.println("i: " + i + ":" + myHarryPotterLine.lineMap[i].getLeft().getName() + ":L:"
					+ myHarryPotterLine.lineMap[i].getLeft().isLeftTerminal() + " R:"
					+ myHarryPotterLine.lineMap[i].getLeft().isRightTerminal());

		}

		System.out.println("Done!");
	}

}

//Exception for when searching a line for a station and not finding any station of the right name.
class StationNotFoundException extends RuntimeException {
	String name;

	public StationNotFoundException(String n) {
		name = n;
	}

	public String toString() {
		return "StationNotFoundException[" + name + "]";
	}

}