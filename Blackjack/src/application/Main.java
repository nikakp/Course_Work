/*
* Main.java
* Class that gets called to start the blackjack game
* Nika Prairie, Derya Baran, and Marissa Boch
* ICS3U
* January 15, 2018
*/
package application;
//Tutorial from http://code.makery.ch/library/javafx-8-tutorial/part1/ 
//used to help write this

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;


public class Main extends Application {
	private Stage primaryStage;
	private VBox rootLayout;

	/**
	 * 
	 * 
	 * Pre-Conditions: None
	 * 
	 * 
	 * Post-Conditions:
	 * The game starts to run with the GUI
	 * 
	 * 
	 * Inputs:
	 *
	 * primaryStage: The principal stage to be used by the game
	 * 
	 */
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Blackjack");

		initRootLayout();
	}

	/**
	 * 
	 * 
	 * Pre-Conditions: The primaryStage is setup in this
	 * 
	 * 
	 * Post-Conditions:
	 *
	 * The graphical objects are initialized, along with the matching 
	 *  member variables
	 * 
	 * The game starts to run
	 * 
	 * 
	 * Inputs: None
	 * 
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(Main.class.getResource("blackjack2.fxml"));
			
			rootLayout = (VBox) loader.load();
			
			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			
			primaryStage.setScene(scene);
			
			// Give the controller access to the main app.
            @SuppressWarnings("unused")
			Graphics graphics = loader.getController();
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}

	
	/**
	 * 
	 * 
	 * Pre-Conditions: None
	 * 
	 * 
	 * Post-Conditions: No changes
	 * 
	 * 
	 * Inputs: None
	 * 
	 * Returns primaryStage of type Stage 
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	/**
	 * 
	 * 
	 * Pre-Conditions: None
	 * 
	 * Post-Conditions: No changes
	 * 
	 * Inputs: None
	 * 
	 * Returns rootLayout of type Vbox
	 */
	public VBox getRootLayout() {
		return rootLayout;
	}

	/**
	 * 
	 * 
	 * Pre-Conditions: None
	 * 
	 * 
	 * Post-Conditions: 
	 *
	 * calls the launch function method which is part of the inherited 
	 * Application object part of the Java FX library. 
	 * The game starts running after this call.
	 * 
	 * Inputs:
	 * 
	 * args: A list of String arguments, not used
	 * 
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
