/*
* Graphics.java
* Class implementing the GUI and control of the game
* Nika Prairie
* ICS3U
* January 15, 2018
*/
package application;
// Tutorial from http://code.makery.ch/library/javafx-8-tutorial/part1/ 
// used to help write this

// Playing card images are from Byron Knoll: http://code.google.com/p/vector-playing-cards/
// the cards are open source

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import application.Player;
import application.Dealer;
import application.DeckOfCards;

import java.util.Optional;

import application.Card;





public class Graphics {

	static String DEFAULT_NAME = "James Bond";
	static String RSRC_PATH = "/application/resources/";
	static int DEFAULT_MONEY = 200;
	static int DEFAULT_DECKS_TO_USE = 6;
	static int MAX_CARDS_IN_HAND = 11;
	static int DEALER_CUT_OFF = 17;
	private Player player;

	private DeckOfCards deckCards;
	private Dealer dealer;


	public enum Status {
		WaitingForBet,
		MoreCards,
		EndOfGame
	}
	private Status status;

	private Image cardback;

	@FXML
	private ImageView dealerImage1;

	@FXML
	private ImageView dealerImage2;

	@FXML
	private ImageView dealerImage3;

	@FXML
	private ImageView dealerImage4;

	@FXML
	private ImageView dealerImage5;

	@FXML
	private ImageView dealerImage6;

	@FXML
	private ImageView dealerImage7;

	@FXML
	private ImageView dealerImage8;

	@FXML
	private ImageView dealerImage9;

	@FXML
	private ImageView dealerImage10;

	@FXML
	private ImageView dealerImage11;

	@FXML
	private ImageView playerhand1Image1;

	@FXML
	private ImageView playerhand1Image2;

	@FXML
	private ImageView playerhand1Image3;

	@FXML
	private ImageView playerhand1Image4;

	@FXML
	private ImageView playerhand1Image5;

	@FXML
	private ImageView playerhand1Image6;

	@FXML
	private ImageView playerhand1Image7;

	@FXML
	private ImageView playerhand1Image8;

	@FXML
	private ImageView playerhand1Image9;

	@FXML
	private ImageView playerhand1Image10;

	@FXML
	private ImageView playerhand1Image11;


	// I setup these arrays to make it easier to update
	// the images, I don't have to actually use each variable
	// instead I can use the array with the number of the card
	// to do the update
	private ImageView dealerImages[];
	private ImageView playerImageshand1[];

	@FXML
	private MenuItem quitMenuItem;

	@FXML
	private MenuItem editNameMenuItem;

	@FXML
	private MenuItem aboutMenuItem;

	@FXML
	private Button chip1;

	@FXML
	private Button chip5;

	@FXML
	private Button chip10;

	@FXML
	private Button chip25;

	@FXML
	private Button chip100;

	@FXML
	private Button hitButton;

	@FXML
	private Button standButton;

	@FXML
	private Button dealCardsButton;

	@FXML
	private Button doubleButton;

	@FXML
	private Button surrenderButton;

	@FXML
	private Button yesButton;

	@FXML
	private Button noButton;


	@FXML
	private AnchorPane winLoosePane;

	@FXML
	private Label fundsLabel;

	@FXML
	private Label betLabel;

	@FXML
	private Label hand1ScoreLabel;

	@FXML
	private Text winText;

	@FXML
	private Text looseText;

	@FXML
	private Text drawText;

	@FXML
	private Label dealerScoreLabel;

	@FXML
	private Label nameLabel;

	/**
	 * This constructor setups up the member variables to start the game, for example
	 * the player, the deck of cards, the dealer, the dealer's images for the cards
	 * the players images for the cards and the variable holding the picture of the card back
	 * are all created
	 * 
	 * Pre-Conditions:
	 * 
	 *  None
	 * 
	 * Post-Conditions:
	 * 
	 * The variable members player, status, deckCards, dealer, dealerImages, playerImagehand1
	 * and cardback are all created
	 * 
	 * Inputs: None
	 * 
	 */
	public Graphics() {
		System.out.println("Graphics");
		this.status = Status.WaitingForBet;
		this.player = new Player(DEFAULT_NAME, DEFAULT_MONEY);
		this.deckCards = new DeckOfCards(DEFAULT_DECKS_TO_USE);
		this.dealer = new Dealer();
		this.dealerImages = new ImageView[11];
		this.playerImageshand1 = new ImageView[11];
		this.cardback = new Image(RSRC_PATH+"cardback.jpg");

	}

	/**
	 * Create an array of the dealer images to make it easier to update
	 * the images displayed
	 * 
	 * Pre-Conditions:
	 * 
	 * The FXML data must have been loaded before this function can be called.
	 *  
	 * 
	 * Post-Conditions:
	 * 
	 * The array is setup with the dealer images
	 * 
	 * Inputs: None
	 * 
	 */
	private void setupDealerImages() {
		this.dealerImages[0] = this.dealerImage1;
		this.dealerImages[1] = this.dealerImage2;
		this.dealerImages[2] = this.dealerImage3;
		this.dealerImages[3] = this.dealerImage4;
		this.dealerImages[4] = this.dealerImage5;
		this.dealerImages[5] = this.dealerImage6;
		this.dealerImages[6] = this.dealerImage7;
		this.dealerImages[7] = this.dealerImage8;
		this.dealerImages[8] = this.dealerImage9;
		this.dealerImages[9] = this.dealerImage10;
		this.dealerImages[10] = this.dealerImage11;
	}

	/**
	 * Create an array of the player images to make it easier to update
	 * the images displayed
	 * 
	 * Pre-Conditions:
	 * 
	 * The FXML data must have been loaded before this function can be called.
	 *  
	 * 
	 * Post-Conditions:
	 * 
	 * The array is setup with the player images
	 * 
	 * Inputs: None
	 * 
	 */
	private void setupPlayerImages() {

		this.playerImageshand1[0] = this.playerhand1Image1;
		this.playerImageshand1[1] = this.playerhand1Image2;
		this.playerImageshand1[2] = this.playerhand1Image3;
		this.playerImageshand1[3] = this.playerhand1Image4;
		this.playerImageshand1[4] = this.playerhand1Image5;
		this.playerImageshand1[5] = this.playerhand1Image6;
		this.playerImageshand1[6] = this.playerhand1Image7;
		this.playerImageshand1[7] = this.playerhand1Image8;
		this.playerImageshand1[8] = this.playerhand1Image9;
		this.playerImageshand1[9] = this.playerhand1Image10;
		this.playerImageshand1[10] = this.playerhand1Image11;

	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 * 
	 * Pre-Conditions:
	 * 
	 *  The Graphics instance must have been created so that the variables can be
	 *  initialized
	 * 
	 * Post-Conditions:
	 * 
	 * All the GUI elements are set to their default values
	 * 
	 * Inputs: None
	 * 
	 */
	@FXML
	private void initialize() {
		fundsLabel.setText(Integer.toString(this.player.getFunds()));
		this.hand1ScoreLabel.setText("0");
		this.dealerScoreLabel.setText("0");
		this.betLabel.setText("0");
		this.nameLabel.setText(this.player.getName());
		System.out.println("Initialize");
		// Setup the buttons correctly
		this.disableAllButtons();
		this.enableBetting();
		this.yesButton.setVisible(false);
		this.noButton.setVisible(false);
		this.winLoosePane.setVisible(false);

		this.looseText.setVisible(false);
		this.winText.setVisible(false);
		this.drawText.setVisible(false);
		setupPlayerImages();
		setupDealerImages();
		for(int i=0; i < MAX_CARDS_IN_HAND; i++) {
			this.playerImageshand1[i].setVisible(false);
			this.dealerImages[i].setVisible(false);
		}
	}

	/**
	 *  This function disables all betting input
	 * 
	 * Pre-Conditions: 
	 * 
	 *  The graphics variables have been initialized
	 * 
	 * Post-Conditions:
	 * 
	 * All the betting chip buttons are disabled
	 * 
	 * Inputs: None
	 * 
	 */
	public void disableAllBetting() {
		chip1.setDisable(true);
		chip5.setDisable(true);
		chip10.setDisable(true);
		chip25.setDisable(true);
		chip100.setDisable(true);
	}

	/**
	 * This function disables all buttons
	 * 
	 * Pre-Conditions:
	 * 
	 * The graphics variables must have  been initialized 
	 * 
	 * Post-Conditions:
	 * 
	 * All the buttons are disabled.
	 * 
	 * Inputs: None
	 * 
	 */
	public void disableAllButtons() {
		this.disableAllBetting();
		this.standButton.setDisable(true);
		this.hitButton.setDisable(true);
		this.surrenderButton.setDisable(true);
		this.dealCardsButton.setDisable(true);
		this.doubleButton.setDisable(true);
		this.noButton.setDisable(true);
		this.yesButton.setDisable(true);
	}

	/**
	 * This function enables the betting buttons based on how much money the player
	 * has left
	 * 
	 * Pre-Conditions:
	 * 
	 * The graphics variables must have been setup
	 *  
	 * 
	 * Post-Conditions:
	 * 
	 * The relevant betting buttons are enabled. For example, if the player has only $50
	 * left, the 100 button will be disabled, but all the others will be enabled.
	 * 
	 * Inputs: None
	 * 
	 */
	public void enableBetting() {
		chip1.setDisable(true);
		chip5.setDisable(true);
		chip10.setDisable(true);
		chip25.setDisable(true);
		chip100.setDisable(true);

		if (player.getFunds() >= 100) {
			chip100.setDisable(false);
		}
		if (player.getFunds() >= 25) {
			chip25.setDisable(false);
		}
		if (player.getFunds() >= 10) {
			chip10.setDisable(false);
		}
		if (player.getFunds() >= 5) {
			chip5.setDisable(false);
		}
		if (player.getFunds() >= 1) {
			chip1.setDisable(false);
		}
	}

	/**
	 * Update the display to show the correct player's name, their score, available
	 * money, whether they can deal cards yet, and their score
	 * 
	 * Pre-Conditions:
	 * 
	 * The graphics variables have been setup 
	 * 
	 * Post-Conditions:
	 * 
	 * 
	 * Inputs: None
	 * 
	 */
	public void updateDisplay() {
		this.nameLabel.setText(player.getName());
		this.fundsLabel.setText(Integer.toString(player.getFunds()));
		this.betLabel.setText(Integer.toString(player.getBet()));
		// if the player is betting, they can start to get cards
		// once a bet is made and they can then press hit to get some cards
		if ( (this.status == Status.WaitingForBet) && (player.getBet() > 0) ){
			this.dealCardsButton.setDisable(false);
		}
		this.hand1ScoreLabel.setText(Integer.toString(player.blackJackScore()));
	}

	/**
	 * Update the player's bet amount and remaining money
	 * 
	 * Pre-Conditions:
	 * 
	 *  The graphics variables have been setup
	 * 
	 * Post-Conditions:
	 * 
	 * The player's bet is increased by bet, and the funds available is decreased
	 * by bet
	 * 
	 * The graphics output is updated to reflect the new bet and how much money
	 * they have left
	 * 
	 * 
	 * Inputs:
	 * 
	 * bet: Integer amount of the increase of the bet.
	 * 
	 */
	public void updateBet(int bet) {
		player.increaseBet(bet);
		player.decreaseFunds(bet);
		updateDisplay();
	}

	/**
	 * Called when the chip1 button is pressed, updates the bet amount by 1
	 * 
	 * Pre-Conditions:
	 * 
	 *  The graphics variables have been setup.
	 * 
	 * Post-Conditions:
	 * 
	 * The bet is increased by 1, funds reduced by 1, and the graphics are 
	 * updated to show the change
	 * 
	 * Inputs: None
	 * 
	 */
	@FXML
	private void  handlechip1() {
		System.out.println("1");
		updateBet(1);
	}
	/**
	 * Called when the chip1 button is pressed, updates the bet amount by 5
	 * 
	 * Pre-Conditions:
	 * 
	 *  The graphics variables have been setup.
	 * 
	 * Post-Conditions:
	 * 
	 * The bet is increased by 5, funds reduced by 5, and the graphics are 
	 * updated to show the change
	 * 
	 * Inputs: None
	 * 
	 */
	@FXML
	private void  handlechip5() {
		System.out.println("5");
		updateBet(5);
	}

	/**
	 * Called when the chip1 button is pressed, updates the bet amount by 10
	 * 
	 * Pre-Conditions:
	 * 
	 *  The graphics variables have been setup.
	 * 
	 * Post-Conditions:
	 * 
	 * The bet is increased by 10, funds reduced by 10, and the graphics are 
	 * updated to show the change
	 * 
	 * Inputs: None
	 * 
	 */
	@FXML
	private void  handlechip10() {
		System.out.println("10");
		updateBet(10);
	}

	/**
	 * Called when the chip1 button is pressed, updates the bet amount by 25
	 * 
	 * Pre-Conditions:
	 * 
	 *  The graphics variables have been setup.
	 * 
	 * Post-Conditions:
	 * 
	 * The bet is increased by 25, funds reduced by 25, and the graphics are 
	 * updated to show the change
	 * 
	 * Inputs: None
	 * 
	 */
	@FXML
	private void  handlechip25() {
		System.out.println("25");
		updateBet(25);
	}

	/**
	 * Called when the chip1 button is pressed, updates the bet amount by 100
	 * 
	 * Pre-Conditions:
	 * 
	 *  The graphics variables have been setup.
	 * 
	 * Post-Conditions:
	 * 
	 * The bet is increased by 100, funds reduced by 100, and the graphics are 
	 * updated to show the change
	 * 
	 * Inputs: None
	 * 
	 */
	@FXML
	private void  handlechip100() {
		System.out.println("100");
		updateBet(100);
	}

	/**
	 * Show in the GUI the dealer's hidden card, and show the dealer's score
	 * 
	 * Pre-Conditions:
	 * 
	 *  The graphics variables must have been initialized
	 * 
	 * Post-Conditions:
	 * 
	 * card i of the dealer is shown, and the dealer's score is displayed.
	 * 
	 * Inputs: None
	 * 
	 */
	public void revealDealerCard(int i) {
		Image image = new Image(RSRC_PATH+getCardFilename(this.dealer.getCard(i)));
		this.dealerImages[i].setImage(image);
		this.dealerImages[i].setVisible(true);
		this.dealerScoreLabel.setText(Integer.toString(this.dealer.blackJackScore()));
	}
	/**
	 * The player has either scored 21, or has decided to
	 * stand, or the dealer's first card was a 10, and the 2nd hidden card
	 * is an ace. We expect that this method is not called if
	 * the player has gone over 21
	 *
	 * 
	 * Pre-Conditions:
	 * 
	 * The graphics variables must have been initialized
	 * The player's score is not over 21 
	 * 
	 * Post-Conditions:
	 * 
	 *  The GUI is updated to tell the player wether they have won, lost or drawn.
	 *  The player's funds are updated, and they are asked if they want to play again or not
	 *  
	 * 
	 * Inputs: None
	 * 
	 */
	public void endHand() {
		System.out.println("endHand");
		this.disableAllButtons();

		// Show the dealer's 2nd card 
		revealDealerCard(1);

		if (this.dealer.blackJackScore() > this.player.blackJackScore()) {
			// Dealer wins
			endPlayerLost();
		} else {
			// Let's see if the dealer needs to take more cards
			while (this.dealer.blackJackScore() < DEALER_CUT_OFF)  {
				Card card = this.deckCards.getRandomCard();
				System.out.println("Dealer card: "+card);
				this.dealer.addCard(card);
				revealDealerCard(this.dealer.getNumCards()-1);
				this.updateDisplay();	
				this.dealerScoreLabel.setText(Integer.toString(this.dealer.blackJackScore()));
			}
			if (this.dealer.blackJackScore() == 21) {
				if (this.player.blackJackScore() == 21 ) {
					// If there is a tie, and the dealer or player has 
					// cards of all the same suit, they win
					if (this.dealer.areAllSameSuit() && this.player.areAllSameSuit()) {
						this.endDraw();
					} else if (this.dealer.areAllSameSuit()) {
						this.endPlayerLost();
					} else if (this.player.areAllSameSuit()){
						this.endPlayerWinning();
					} else {
						this.endDraw();
					}
				} else { // player has less than 21
					// The dealer wins
					endPlayerLost();
				}
				// Is it a draw, or did the dealer win?
			} else if (this.dealer.blackJackScore() > 21) {
				endPlayerWinning();
			} else if (this.dealer.blackJackScore() < 21) {
				if (this.dealer.blackJackScore() == this.player.blackJackScore()) {
					// If there is a tie, and the dealer or player has 
					// cards of all the same suit, they win
					if (this.dealer.areAllSameSuit() && this.player.areAllSameSuit()) {
						this.endDraw();
					} else if (this.dealer.areAllSameSuit()) {
						this.endPlayerLost();
					} else if (this.player.areAllSameSuit()){
						this.endPlayerWinning();
					} else {
						this.endDraw();
					}
				} else if (this.dealer.blackJackScore() > this.player.blackJackScore()) {
					this.endPlayerLost();
				} else {
					this.endPlayerWinning();
				}
			}
		}
	}
	/**
	 * This function displays the end of hand panel to tell the player
	 * if they have won, lost or there is a draw. It asks the player
	 * if they want to play again.
	 * 
	 * Pre-Conditions:
	 * 
	 * The graphics variables must have been initialized
	 * 
	 * Post-Conditions:
	 * 
	 *  Displays wether the player won, lost or tied. 
	 * 
	 * Inputs: 
	 * 
	 * A graphical Text object is passed in to display whether they won, lost or tied
	 */
	public void endPaneDisplay(Text text) {
		System.out.println("endPaneDisplay");
		this.disableAllButtons();
		this.disableAllBetting();
		text.setVisible(true);
		this.winLoosePane.setVisible(true);
		this.updateDisplay();
		this.yesButton.setVisible(true);
		this.yesButton.setDisable(false);
		this.noButton.setVisible(true);
		this.noButton.setDisable(false);
	}

	/**
	 * Reports to the player that they have lost. Updates their bet to 0,
	 * and decreased their funds by the amount they bet.
	 * 
	 * Pre-Conditions:
	 * 
	 * The graphics variables must have been initialized
	 * 
	 * Post-Conditions:
	 *  
	 * Their funds and bets are updated as per above.
	 * Waits for the user to say if they want to play again or not
	 * If not, the program exits
	 * 
	 * Inputs: None
	 * 
	 */
	public void endPlayerLost() {
		System.out.println("endPlayerLost");
		this.player.placeBet(0);
		this.updateDisplay();
		endPaneDisplay(this.looseText);
	}

	/**
	 * Reports to the player that they have tied. Updates their bet to 0,
	 * and increases their funds by the amount they bet.
	 * 
	 * Pre-Conditions:
	 * 
	 * The graphics variables must have been initialized
	 * 
	 * Post-Conditions:
	 *  
	 * Their funds and bets are updated as per above.
	 * Waits for the user to say if they want to play again or not
	 * If not, the program exits
	 * 
	 * Inputs: None
	 * 
	 */
	public void endDraw() {
		System.out.println("endDraw");
		this.player.increaseFunds(this.player.getBet());
		this.player.placeBet(0);
		this.updateDisplay();
		endPaneDisplay(this.drawText);
	}

	/**
	 * Reports to the player that they have won. Updates their bet to 0,
	 * and incrases their funds. The funds are increased by 3 to 2 if they have
	 * a black jack, by 2 if insurance was in place, meaning the dealer first card
	 * was an ace.
	 * 
	 * Pre-Conditions:
	 * 
	 * The graphics variables must have been initialized
	 * 
	 * Post-Conditions:
	 *  
	 * Their funds and bets are updated as per above.
	 * Waits for the user to say if they want to play again or not
	 * If not, the program exits
	 * 
	 * Inputs: None
	 * 
	 */
	public void endPlayerWinning() {
		System.out.println("endPlayerWinning");
		endPaneDisplay(winText);

		if (this.dealer.isInsuranceInEffect()) {
			this.player.increaseFunds(this.player.getBet()*2);
		} else {
			// If the player has blackjack, it pays 3 to 2
			if (this.player.blackJackScore()==21) {
				this.player.increaseFunds((this.player.getBet()*3)/2+this.player.getBet());
			} else {
				this.player.increaseFunds(this.player.getBet()*2);
			}
		}
		this.player.placeBet(0);
		this.updateDisplay();
	}

	/**
	 * Gives another card to the player
	 * 
	 * Pre-Conditions:
	 * 
	 * The graphics variables must have been initialized
	 * 
	 * Post-Conditions:
	 * 
	 * A card is added to the player
	 * The image of the new card is displayed on the GUI
	 * The player's score is displayed
	 * 
	 * Inputs: 
	 *
	 * card: The card passed in is given to the player and displayed
	 * 
	 */
	private void givePlayerCard(Card card) {
		System.out.println("givePlayerCard");
		// Get another card for the player
		// and show it
		//Card card = this.deckCards.getRandomCard();
		System.out.println("Player card: "+card);
		this.player.addCard(card);
		Image image = new Image(RSRC_PATH+getCardFilename(card));
		this.playerImageshand1[this.player.getNumCards()-1].setImage(image);
		this.playerImageshand1[this.player.getNumCards()-1].setVisible(true);
		this.updateDisplay();
	}

	/**
	 * This function is called when the double or hit button is pressed.
	 * The bet amount is doubled, and another card is dealt to the 
	 * player. If the player has scored 21 or higher, the hand is ended.
	 * if the player has more than 21, reveal the dealer's hidden card and 
	 * 
	 * Pre-Conditions:
	 * 
	 * The graphics variables must have been initialized
	 * 
	 * Post-Conditions:
	 * 
	 * The player's funds are decreased by the bet amount, and the bet doubled
	 * A card is given to the player and displayed. The score is updated based
	 * on the new card given. If the players score is 21 or higher then the game
	 * is ended, and the dealer's card is shown.
	 * Inputs: 
	 *
	 * bet: integer amount of the bet's increase
	 * 
	 */
	private void hitDoubleAction(int bet) {
		this.doubleButton.setDisable(true);
		// Double the bet
		this.player.decreaseFunds(bet);
		this.player.increaseBet(bet);
		givePlayerCard(this.deckCards.getRandomCard());
		this.updateDisplay();
		if (this.player.blackJackScore()==21) {
			revealDealerCard(1);
			endHand();
		} else if (this.player.blackJackScore() > 21) {
			revealDealerCard(1);
			endPlayerLost();
		}
	}

	/**
	 * This function is called when the hit button is pressed.
	 * It calls hitDoubleAction with a 0 bet increase to do the required
	 * work.
	 * 
	 * Pre-Conditions:
	 * 
	 * The graphics variables must have been initialized
	 * The game is already in progress
	 * 
	 * Post-Conditions:
	 * 
	 * The bet amount is not changed, see hitDoubleAction for the details
	 * 
	 * Inputs: None
	 * 
	 */
	@FXML
	private void  handlehit() {
		this.hitDoubleAction(0);

	}

	/**
	 * This function is called when the bouble button is pressed.
	 * It calls hitDoubleAction with the player's existing bet 
	 * to double the amount of the bet.
	 * 
	 * Pre-Conditions:
	 * 
	 * The graphics variables must have been initialized
	 * The game is already in progress
	 * 
	 * Post-Conditions:
	 * 
	 * The bet amount is doubled, see hitDoubleAction for the details
	 * 
	 * Inputs: None
	 * 
	 */
	@FXML
	private void  handleDouble() {
		System.out.println("double");
		this.hitDoubleAction(this.player.getBet());
	}

	/**
	 * This function is called when the stand button is pressed
	 * The game is ended as a result
	 *
	 * Pre-Conditions:
	 * 
	 * The graphics variables must have been initialized
	 * The game is already in progress
	 * 
	 * Post-Conditions:
	 * 
	 * The game is ended, and the player is told whether they won, lost or tied
	 * See endHand for more info on the post-conditions.
	 *   
	 * Inputs: None
	 * 
	 */
	@FXML
	private void  handlestand() {
		System.out.println("stand");
		endHand();
	}
	/**
	 * This function removes all of the dealers and players
	 * cards from the display, and removes all of the cards
	 * from the dealer and player
	 * 
	 * Pre-Conditions:
	 * 
	 * The graphics variables must have been initialized
	 *
	 * 
	 * Post-Conditions:
	 *
	 * Cards are no longer shown in the GUI
	 * All cards are removed from the player and the dealer 
	 * 
	 * Inputs: None
	 * 
	 */
	private void clearCards() {
		for(int i=0; i < MAX_CARDS_IN_HAND; i++) {
			this.playerImageshand1[i].setVisible(false);
			this.dealerImages[i].setVisible(false);
		}
		this.player.removeAllCards();
		this.dealer.removeAllCards();
	}

	/**
	 * This function is called when the Deal Cards button is
	 * pressed
	 * 
	 * Pre-Conditions:
	 * 
	 * The graphics variables must have been initialized
	 * A bet must have been placed before this action
	 * can be invoked
	 * 
	 * Post-Conditions:
	 * 
	 * 2 cards are dealt to the player, and both are displayed 
	 *   in the GUI. The Player's score is displayed
	 * 2 cards are dealt to the dealer, one is shown in the GUI
	 *   while the back of the second card is displayed
	 *   
	 * If the dealer has blackjack, then the game ends
	 *
	 * Inputs: None
	 * 
	 */
	@FXML
	private void  handleDealCards() {
		Card dealerCard1;
		Card dealerCard2;
		Card playerCard1;
		Card playerCard2;
		boolean testing = false;

		System.out.println("DealCards");
		this.disableAllBetting();
		this.status = Status.MoreCards;
		this.dealCardsButton.setDisable(true);
		this.hitButton.setDisable(false);
		this.doubleButton.setDisable(false);
		this.surrenderButton.setDisable(false);
		this.standButton.setDisable(false);


		System.out.println("Cards in deck: "+this.deckCards.getNumCards());
		// A maximum of 11 cards can be handed to the player, and a bit less
		// to the dealer on every hand. Ensure we have enough cards left in the deck
		// and if not, use a new deck of cards.
		if (this.deckCards.getNumCards() < 2*MAX_CARDS_IN_HAND) {
			System.out.println("Using a new deck");
			this.deckCards = new DeckOfCards(DEFAULT_DECKS_TO_USE);
		}

		if (testing == true) {
			dealerCard1 = new Card(CardRank.Seven, CardSuit.Club);
			dealerCard2 = new Card(CardRank.King, CardSuit.Club);
			playerCard1 = new Card(CardRank.Seven, CardSuit.Club);
			playerCard2 = new Card(CardRank.King, CardSuit.Heart);
		} else {
			dealerCard1 = this.deckCards.getRandomCard();
			dealerCard2 = this.deckCards.getRandomCard();
			playerCard1 = this.deckCards.getRandomCard();
			playerCard2 = this.deckCards.getRandomCard();
			
		}
		// Give the first card to the dealer, and make sure it's visible

		System.out.println("Dealer card: " + dealerCard1);
		System.out.println(getCardFilename(dealerCard1));
		Image image = new Image(RSRC_PATH+getCardFilename(dealerCard1));
		this.dealerImages[0].setImage(image);
		this.dealerImages[0].setVisible(true);
		this.dealer.addCard(dealerCard1);

		// Give the 2nd card to the dealer, and ensure it's not
		// seen by the player
		dealerCard2.hide();
		System.out.println("Dealer card: "+dealerCard2);
		this.dealer.addCard(dealerCard2);
		this.dealerImages[1].setImage(this.cardback);
		this.dealerImages[1].setVisible(true);

		//
		this.givePlayerCard(playerCard1);
		this.givePlayerCard(playerCard2);

		// Update what is displayed
		this.updateDisplay();
		// If the dealer's first card is worth 10 or is an ace, check to see if the dealer has a black jack
		if ( (this.dealer.blackJackScore() == 21) || 
			 (this.player.blackJackScore() == 21) ){
			this.endHand();
		}
	}


	/**
	 * This function is called when the surrender button is pressed.
	 * This hand is ended when this function is called.
	 * 
	 * Pre-Conditions:
	 * 
	 * The graphics variables must have been initialized
	 * A bet must have been placed
	 * 
	 * Post-Conditions:
	 * 
	 *  The game ends with the player loosing half their bet
	 * All the buttons, including betting, are disabled
	 * The betting amount is moved back to 0, the scores are
	 * updated. 
	 * A message showing that they have lost is shown
	 * and we wait for the player to indicate if they want to play
	 * again or now
	 *
	 * Inputs: None
	 * 
	 */
	@FXML
	private void  handleSurrender() {
		System.out.println("surrender");
		this.disableAllBetting();
		this.disableAllButtons();
		// Double the bet
		this.player.increaseFunds(this.player.getBet()/2);
		this.player.placeBet(0);
		this.updateDisplay();
		this.endPlayerLost();
	}
	/**
	 * This function is called based on whether the player
	 * wants to play another hand. 
	 * The betting and hand action buttons are disabled
	 * The Yes and No buttons are hidden, as are the
	 * win/loose/draw texts
	 * If the player wants to play another hand
	 *   The cards are removed from the display, and the cards
	 *   removed from the dealer and player.
	 *   the display is updated, the score is set to 0
	 *   and betting is enabled
	 * If the player doesn't want to continue playing, the program
	 * ends
	 * 
	 * Pre-Conditions:
	 * 
	 * The graphics variables must have been initialized
	 * 
	 * Post-Conditions:
	 * 
	 * All betting buttons are disabled and playing buttons
	 * The Yes and No buttons are hidden, as is the win/loose/tie
	 * text. 
	 * If the player wants to play another hand, the cards are 
	 * removed from the player and dealer, and no longer displayed
	 * The score is set to 0, and the Status setup for another hand
	 * The game exits if the player doesn't want to play again
	 * 
	 * Inputs: 
	 *
	 * again: a value of true is passed in to indicate that another hand of cards
	 *        is to be played. If false, the game exits
	 * 
	 */
	private void playAgain(boolean again) {
		this.disableAllBetting();
		this.disableAllButtons();
		this.yesButton.setVisible(false);
		this.noButton.setVisible(false);
		this.winLoosePane.setVisible(false);
		this.winText.setVisible(false);
		this.looseText.setVisible(false);
		this.drawText.setVisible(false);
		if (again) {
			this.clearCards();
			this.status = Status.WaitingForBet;
			this.updateDisplay();
			this.dealerScoreLabel.setText("0");
			this.enableBetting();
		} else {
			Platform.exit();
		}
	}

	/**
	 * This function is called when the Yes button is pressed.
	 * When it's pressed, another hand of cards is played by calling
	 * the playAgain function with true
	 * 
	 * Pre-Conditions:
	 * 
	 * The graphics variables must have been initialized
	 * 
	 * Post-Conditions:
	 * 
	 * Another hand of cards is played, see details in playAgain
	 * 
	 * Inputs: None
	 * 
	 */
	@FXML
	private void handleYes() {
		playAgain(true);
	}
	/**
	 * This function is called when the No button is pressed.
	 * When it's pressed, the game exits
	 * 
	 * Pre-Conditions:
	 * 
	 * The graphics variables must have been initialized
	 * 
	 * Post-Conditions:
	 * 
	 * The game exits
	 * 
	 * Inputs: None
	 * 
	 */
	@FXML
	private void handleNo() {
		playAgain(false);
	}

	/**
	 * This function method takes a card, and returns the 
	 * name of graphic image in the folder resources that has the
	 * graphic for the card
	 * 
	 * Pre-Conditions:
	 * 
	 * None
	 * 
	 * Post-Conditions:
	 * 
	 * None
	 * 
	 * Inputs: 
	 * 
	 * card: The card for which we want to determine the name of the 
	 *       file with the graphical representation of the card
	 *       
	 * Returns:
	 * 
	 *  the name of the file with the graphic image of the card
	 *  
	 */
	private String getCardFilename(Card card) {
		String rankString = "";
		switch(card.GetSuit()) {
		case Heart:
			rankString = "hearts";
			break;
		case Diamond:
			rankString = "diamonds";
			break;
		case Club:
			rankString = "clubs";
			break;
		case Spade:
			rankString = "spades";
			break;
		}

		switch(card.GetRank()) {
		case Two:
			return "2_of_"+rankString+".png";
		case Three:
			return "3_of_"+rankString+".png";
		case Four:
			return "4_of_"+rankString+".png";
		case Five:
			return "5_of_"+rankString+".png";
		case Six:
			return "6_of_"+rankString+".png";
		case Seven:
			return "7_of_"+rankString+".png";
		case Eight:
			return "8_of_"+rankString+".png";
		case Nine:
			return "9_of_"+rankString+".png";
		case Ten:
			return "10_of_"+rankString+".png";
		case King:
			return "king_of_"+rankString+".png";
		case Queen:
			return "queen_of_"+rankString+".png";
		case Jack:
			return "jack_of_"+rankString+".png";
		case Ace:
			return "ace_of_"+rankString+".png";
		}
		return "Unknown";
	}

	/**
	 * This function is called when the quit menuItem
	 * is pressed
	 * 
	 * Pre-Conditions:
	 * 
	 * The graphics variables must have been initialized
	 * 
	 * Post-Conditions:
	 * 
	 *    The game exits
	 * 
	 * Inputs:
	 *
	 * event: ActionEvent object passed in, but not used
	 * 
	 */
	@FXML
	private void handleExitAction(ActionEvent event) {
		System.out.println("exiting");
		Platform.exit();
	}
	/**
	 * This function is called when the about menu
	 * item is selected. It displays an about box
	 * with information about the authors 
	 *
	 * Pre-Conditions:
	 * 
	 * The graphics variables must have been initialized
	 * 
	 * Post-Conditions:
	 * 
	 * A dialog box is shown and we wait for the  
	 * user to accept
	 *
	 * Inputs: 
	 *
	 * event: An ActionEvent object is passed, but not used
	 * 
	 */
	@FXML
	private void handleAboutAction(ActionEvent event) {
		// Used examples from http://code.makery.ch/blog/javafx-dialogs-official/
		System.out.println("About");
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About");
		alert.setHeaderText("Hope you are enjoying our Blackjack game!");
		alert.setContentText("Written by Marissa Boch, Derya Baran & Nika Prairie");
		alert.showAndWait();
	}
	/**
	 * This function is called when the menu item to change
	 * the player's name is selected. A dialog box is shown
	 * to ask for the player's new name. The new name
	 * is stored in the player object, and the display
	 * updated to show the new name 
	 * 
	 * Pre-Conditions:
	 * 
	 * The graphics variables must have been initialized
	 * 
	 * Post-Conditions:
	 * 
	 *  The name in the player object is updated and 
	 *  the display changed to reflect it.
	 * 
	 * Inputs: None
	 * 
	 */
	@FXML
	private void handleNameChangeAction(ActionEvent event) {
		// Used examples from http://code.makery.ch/blog/javafx-dialogs-official/
		System.out.println("name change");
		TextInputDialog dialog = new TextInputDialog(this.player.getName());
		dialog.setTitle("Change the Player's name");
		dialog.setContentText("Please enter the player's name:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			System.out.println("Your name: " + result.get());
			this.player.setName(result.get());
			this.updateDisplay();
		}


	}

}
