package guidedcards;

import java.util.LinkedList;
import java.util.Random;

public class Deck {
	
	private LinkedList<Card> cards;
	
	public Deck() {
		cards = new LinkedList<>();
		for (int suit = Card.SPADES; suit <= Card.CLUBS; suit++) {
			for (int rank = Card.ACE; rank <= Card.KING; rank++) {
				cards.add(new Card(rank, suit));
			}
		}
	}
	
	public void shuffle() {
		CardRandomizer rand = new CardRandomizer();
		cards.sort(rand);
	}
	
	public Card deal() {
		return cards.pop();
	}
	
	public int cardsLeft() {
		return cards.size();
	}
	
	@Override
	public String toString() {
		return cards.toString();
	}
	
	public static void main(String[] args) {
		Deck d = new Deck();
		d.shuffle();
		System.out.println(d);
		System.out.println(d.deal());
		System.out.println(d.cardsLeft());
		
		
	}

}
