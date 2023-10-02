package comp303.assignment4;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

class TVShowTest  {


	@Test
	void testSetEpisodePrototype() {
		TVShow tv1 = new TVShow("title", Language.ANCIENT_GREEK, "studio");
		File myFile1 = new File("../Movies/mm1.mp4");
		tv1.createAndAddEpisode(myFile1, "episode 1");
		
		// White box test, ensure exception is thrown
		// if SetEpisodePrototype is not called
		assertThrows(NullPointerException.class, () -> {
			tv1.createAndAddEpisode();
		});
		
		TVShow tvProto = new TVShow("title2", Language.FRENCH, "DC");
		File myFile2 = new File("../Movies/mm2.mp4");
		tvProto.createAndAddEpisode(myFile2, "episode proto");
		tv1.setEpisodePrototype(tvProto);
		tv1.createAndAddEpisode();
		Episode ep = tv1.getEpisode(2);
		assertEquals(ep.getaPath().getName(), myFile2.getName());
		assertEquals(ep.getTitle(), "episode proto");
		
	}

	@Test
	void testCreateAndAddEpisode() {
		TVShow tv1 = new TVShow("title", Language.ANCIENT_GREEK, "studio");
		File myFile1 = new File("../Movies/mm1.mp4");
		tv1.createAndAddEpisode(myFile1, "episode 1");
		
		// White box test, ensure exception is thrown
		// if SetEpisodePrototype is not called
		assertThrows(NullPointerException.class, () -> {
			tv1.createAndAddEpisode();
		});	

		
		TVShow tvProto = new TVShow("title2", Language.FRENCH, "DC");
		File myFile2 = new File("../Movies/mm2.mp4");
		tvProto.createAndAddEpisode(myFile2, "episode proto");
		tv1.setEpisodePrototype(tvProto);

		tv1.createAndAddEpisode();
		Episode ep = tv1.getEpisode(2);
		assertEquals(ep.getaPath().getName(), myFile2.getName());
		assertEquals(ep.getTitle(), "episode proto");
	}

	@Test
	void testCreateAndAddEpisodeFile() {
		TVShow tv1 = new TVShow("title", Language.ANCIENT_GREEK, "studio");
		File myFile1 = new File("../Movies/mm1.mp4");
		tv1.createAndAddEpisode(myFile1, "episode 1");
		
		// White box test, ensure exception is thrown
		// if SetEpisodePrototype is not called
		assertThrows(NullPointerException.class, () -> {
			tv1.createAndAddEpisode(myFile1);
		});	

		
		TVShow tvProto = new TVShow("title2", Language.FRENCH, "DC");
		File myFile2 = new File("../Movies/mm2.mp4");
		tvProto.createAndAddEpisode(myFile2, "episode proto");
		tv1.setEpisodePrototype(tvProto);

		// White box test, ensure exception is thrown
		// if a null File is provided
		assertThrows(AssertionError.class, () -> {
			tv1.createAndAddEpisode((File)null);
		});	
		// Black box test, ensure Episode is created
		// with correct values
		File myFile3 = new File("../Movies/mm3.mp4");
		tv1.createAndAddEpisode(myFile3);
		Episode ep = tv1.getEpisode(2);
		assertEquals(ep.getaPath().getName(), myFile3.getName());
		assertEquals(ep.getTitle(), "episode proto");
	}

}
