package guidedcards;

public class Player {
	
	private String name;
	private int totalCoins;
	private Card chosenCard;
	private boolean chosenOrientation;
	private int currentBet;
	
	public Player(String name) {
		this.name = name;
		totalCoins = 100;
	}
	
	public boolean bet(int coins) {
		if (this.totalCoins >= coins) {
			currentBet = coins;
			this.totalCoins -= coins;
			return true;
		}	
		else {
			currentBet = 0;
			return false;
		}
	}
	
	public void addWinnings (int winnings) {
		this.totalCoins += winnings;
	}
	
	public void setChosenCard(int rank, int suit) {
		this.chosenCard = new Card(rank, suit);
	}
	
	public void setChosenOrientation(boolean isIn) {
		this.chosenOrientation = isIn;
	}

	public String getName() {
		return name;
	}

	public int getTotalCoins() {
		return totalCoins;
	}

	public Card getChosenCard() {
		return chosenCard;
	}

	public boolean getChosenOrientation() {
		return chosenOrientation;
	}

	public int getCurrentBet() {
		return currentBet;
	}

}
