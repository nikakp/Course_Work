package comp303.assignment3;

import java.io.*;


class Driver {
	private static final Language English = null;
	public static void main(String[] args) {
		//test();
		Library myLib = Library.instance("Nika");
		// Note that myLib2 and myLib refer to the same
		// object, so the impact of myLib2 is that 
		// it has changed the name of the library
		Library myLib2 = Library.instance("Bozo");
		// Example of getting the name of the library
		// it will print out Bozo
		System.out.println("Library name: ["+myLib.getName()+"]");
		
		// If you try to access the E-mail address of the library
		// an exception will be generated
		try {
			System.out.println("E-mail: ["+myLib2.getEmail()+"]");
		} catch(Exception e) {
			System.out.println("An exception is generated, as expected if you don't set the E-mail");
		}
		
		// Once you set the E-mail address you can retrieve it
		myLib2.changeEmail("bozo@hotmail.com");
		System.out.println("E-mail: ["+myLib.getEmail()+"]");
		
		// You can also change the E-mail and name of the singleton
		// using the instance method 
		Library myLib3 = Library.instance("Bozo2", "bozo@bozo.com");
		System.out.println("Library name: ["+myLib.getName()+"]");
		System.out.println("Library e-mail: ["+myLib.getEmail()+"]");

		// You can only have a single object for a given movie title
		File myFile = new File("../Movies/mm1.mp4");
		Movie mv1 = Movie.get(myFile,"Sound of Music", Language.ENGLISH, "Studio1");
		System.out.println("mv1 Title: " + mv1.getTitle() + "  lang:" + mv1.getLanguage() );
		// Note that if a movie object exists with the same title
		// it will be returned, and neither file, language nor studio will be updated 
		File myFile2 = new File("../Movies/mm1.mp4");
		Movie mv2 = Movie.get(myFile2,"Sound of Music", Language.FRENCH, "Studio2");
		System.out.println("mv2 Title: " + mv2.getTitle() + "  lang:" + mv2.getLanguage() );
		// The below passes as only a single instance of an object with a given title
		// can exist
		assert mv1 == mv2;

		// You can only have a single object for a given TV show title
		TVShow tv1 = TVShow.get("Grey's anatomy", Language.ENGLISH, "Studio1");
		System.out.println("tv1 Title: " + tv1.getTitle() + "  lang:" + tv1.getLanguage() );
		// Note that if a TVShow object exists with the same title
		// it will be returned, and neither file, language nor studio will be updated 
		TVShow tv2 = TVShow.get("Grey's anatomy", Language.FRENCH, "Studio2");
		System.out.println("tv2 Title: " + tv2.getTitle() + "  lang:" + tv2.getLanguage() );
		// The below passes as only a single instance of an object with a given title
		// can exist
		assert tv2 == tv1;
		
		// Watchlists can be compared to determine if they have the same content
		WatchList w1 = new WatchList("w1");
		WatchList w2 = new WatchList("w2");
		if (w1.equals(w2)) {
			System.out.println("w1 equals w2");
		}
		w1.addWatchable(tv1);
		if (!w1.equals(w2)) {
			System.out.println("w1 and w2 are different");
		} 
		w2.addWatchable(tv2); //tv2 is an alias for tv1
		if (w1.equals(w2)) {
			System.out.println("w1 equals w2 again");
		}



	}

	public static void test() {

		// Question 1:
		//**************************************************
		//allow one Library object
		// name is mandatory and email is optional parameter

		// create a library with name only
		Library myLib = Library.instance("Nika");
		System.out.println("Creation of Library with name only : " + myLib.getName());
		Boolean status = false;
		try {
			myLib.getEmail();

		} catch(Exception e) {
			status = true;
		}
		if (status) {
			System.out.println("Passed, no E-mail available");
		} else {
			System.out.println("Failed, E-mail present");
		}

		// declare 2nd variable of Library type with the new name
		// expect to see both instances pointing the
		// same library with the new name
		Library myLib1 = Library.instance("Nika2");
		System.out.println("myLib1 : " + myLib1.getName());
		System.out.println("myLib  : " + myLib.getName());
		if (myLib != myLib1) {
			System.out.println("They are inequal");
		} else {
			System.out.println("They are equal");
		}

		// declare 3nd instance of Library type with the new name
		// and an email address
		// expect to see all vars pointing the
		// same library with the new name and email
		Library myOtherLib = Library.instance("Nika", "someone@hotmail.com");
		System.out.println(
				"Creation of Library with name and email : " + myOtherLib.getName() + " :  " + myOtherLib.getEmail());
		System.out.println("myLib  Name : " + myLib.getName() +  "  email: " + myLib.getEmail());
		System.out.println("myLib1 Name : " + myLib1.getName() + "  email: " + myLib1.getEmail());

		// Change the name using the first var
		// expect to see name changed on all
		myLib.changeName("new Lib Name");

		System.out.println("myLib      Name: [" + myLib.getName() + 
				"] email:  [" + myLib.getEmail()+ "]");

		System.out.println("myLib1     Name: [" + myLib1.getName() + 
				"] email:  [" + myLib1.getEmail() + "]");

		System.out.println("myOtherLib Name: [" + myOtherLib.getName() + 
				"] email:  [" + myOtherLib.getEmail()+ "]");

		// Change the name using the first var
		// expect to see name changed on all
		myLib.changeEmail("nika@hotmail.com");

		System.out.println("myLib      Name: [" + myLib.getName() + 
				"] email:  [" + myLib.getEmail()+ "]");

		System.out.println("myLib1     Name: [" + myLib1.getName() + 
				"] email:  [" + myLib1.getEmail()+ "]");

		System.out.println("myOtherLib Name: [" + myOtherLib.getName() + 
				"] email:  [" + myOtherLib.getEmail()+ "]");

		// Question 2:
		//**************************************************
		// for any given title there can ever be only one movie
		// the same for TV shows
		File myFile = new File("../Movies/mm1.mp4");
		Movie mv1 = Movie.get(myFile,"Sound of Music", Language.ENGLISH, "Studio1");
		System.out.println("mv1 Title: " + mv1.getTitle() + "  lang:" + mv1.getLanguage() ); 
		// Note that the language and studio is not changed as a result of the
		// below
		Movie mv2 = Movie.get(myFile,"Sound of Music", Language.FRENCH, "Studio2");
		System.out.println("mv2 Title: " + mv2.getTitle() + "  lang:" + mv1.getLanguage() ); 
		assert mv1 == mv2;

		mv2.setInfo("Friend", "Maddie");

		System.out.println("mv1 : " + "  info: " + mv1.getInfo("Friend") ); 

		// TV Shows
		// Similar to movies, TV shows can only have a unique instance of an object
		// with a unique title.
		TVShow tv1 = TVShow.get("Sound of Music", Language.ENGLISH, "Studio1");
		System.out.println("tv1 Title: " + tv1.getTitle() + "  lang:" + tv1.getLanguage() ); 

		TVShow tv2 = TVShow.get("Sound of Music", Language.FRENCH, "Studio2");
		System.out.println("tv2 Title: " + tv2.getTitle() + "  lang:" + tv2.getLanguage() ); 
		assert tv1 == tv2;

		tv2.setInfo("Friend", "Claudia");

		System.out.println("tv1 : " + "  info: " + tv1.getInfo("Friend") ); 

		//Question 3: Compare watchlists
		WatchList w1 = new WatchList("w1");
		WatchList w2 = new WatchList("w2");
		if (w1.equals(w2)) {
			System.out.println("Passed");
		} else {
			System.out.println("Failed");
		}
		w1.addWatchable(tv1);
		if (!w1.equals(w2)) {
			System.out.println("Passed");
		} else {
			System.out.println("Failed");
		}
		w2.addWatchable(tv1);
		if (w1.equals(w2)) {
			System.out.println("Passed");
		} else {
			System.out.println("Failed");
		}
		w2.addWatchable(tv2);
		if (!w1.equals(w2)) {
			System.out.println("Passed");
		} else {
			System.out.println("Failed");
		}
		w1.addWatchable(tv1);
		if (w1.equals(w2)) {
			System.out.println("Passed");
		} else {
			System.out.println("Failed");
		}

		if (!w1.equals(null)) {
			System.out.println("Passed");
		} else {
			System.out.println("Failed");
		}
		if (!w1.equals(tv1)) {
			System.out.println("Passed");
		} else {
			System.out.println("Failed");
		}
	}
}
