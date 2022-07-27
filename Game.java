package guidedcards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
	private List<Player> players;
	private Player host;
	private Deck deck;
	
	public Game(List<Player> players) {
		this.players = players;
		int rand = new Random().nextInt(0, players.size());
		host = players.get(rand);
		deck = new Deck();
		deck.shuffle();
	}
	
	public Player findLoser(Card currentCard, boolean currentOrientation) {
		Player loser = null;
		for (Player p : players) {
			if (p.equals(host))
				continue;
			if ((p.getChosenCard().equals(currentCard) &&
					p.getChosenOrientation() != currentOrientation) ||
					p.getCurrentBet() == 0) {
				loser = p;
			}
		}
		return loser;
	}
	
	public Player findWinner(Card currentCard, boolean currentOrientation) {
		Player winner = null;
		for (Player p : players) {
			if (p.equals(host))
				continue;
			if (p.getChosenCard().equals(currentCard) && 
					p.getChosenOrientation() == currentOrientation &&
					p.getCurrentBet() > 0) {
				winner = p;
			}
		}
		return winner;
	}
	
	public void takeBets() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Host is " + host.getName());
		for (Player p : players) {
			System.out.println(p.getName() + "'s turn");
			System.out.println("Enter bet");
			while(!p.bet(sc.nextInt())) {
				if (p.getTotalCoins() == 0) {
					System.out.println("Insufficient funds, " +
							p.getName() + ", you can't play anymore");
					players.remove(p);
					break;
				}
				System.out.println("Enter a smaller bet, you only have " + 
						p.getTotalCoins() + " to play with");
			}
			if (p.getCurrentBet() >= 0 && p != host) {
				System.out.println("Enter chosen card rank & suit" + 
						"rank 1-13, suit: SPADES = 1, HEARTS = 2, DIAMONDS = 3, CLUBS = 4");
				int rank = sc.nextInt();
				int suit = sc.nextInt();
				p.setChosenCard(rank, suit);
				
				System.out.println("In or out?");
				String inOut = sc.next();
				
				p.setChosenOrientation(inOut.equalsIgnoreCase("in"));
			}
		}
	}
	
	public Player playRound() {
		boolean currentOrientation = true; //IN
		Player roundWinner = null;
		List<Player> winnerCandidates = players;
		takeBets();
		while (deck.cardsLeft() > 0 && roundWinner == null && winnerCandidates.size() > 1) {
			Card currentCard = deck.deal();
			System.out.println("Current card: " + currentCard +
					" Current orientation " + currentOrientation);
			roundWinner = findWinner(currentCard, currentOrientation);
			Player turnLoser = findLoser(currentCard, currentOrientation);
			if (roundWinner != null)
				System.out.println("Winner found, stopping game");
			else if (turnLoser != null) {
				System.out.println(turnLoser.getName() + " has lost their bet, continuing game");
				winnerCandidates.remove(turnLoser);
			}
			else
				System.out.println("Winner not found, continuing game");
			currentOrientation = !currentOrientation;
		}
		if (roundWinner == null)
			roundWinner = host;
		int winnings = 0;
		for (Player p : players) {
			winnings += p.getCurrentBet();
		}
		roundWinner.addWinnings(winnings);
		host = roundWinner;
		System.out.println("Winner of the round and new host is: " + roundWinner.getName() 
			+ " they won " + winnings + " coins");
		return roundWinner;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Player p1 = new Player("Aruvi");
		Player p2 = new Player("Suha");
		Player p3 = new Player("Sreeja");
		
		ArrayList<Player> players = new ArrayList<>();
		
		players.add(p1);
		players.add(p2);
		players.add(p3);
		
		Game game = new Game(players);
		boolean keepPlaying = true;
		while (keepPlaying) {
			game.playRound();
			System.out.println("Press Y to continue playing, otherwise type anything else");
			String userChoice = sc.next();
			keepPlaying = userChoice.equalsIgnoreCase("y");
		}
		
	}
	
	
}
