package guidedcards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
	private List<Card> cards;
	
	public Hand() {
		cards = new ArrayList<>();
	}
	
	public Boolean addCard (Card card) {
		if (cards.indexOf(card) == -1 && card.isValid()) {
			cards.add(card);
			return true;
		}
		else {
			System.out.println("Card already present in hand");
			return false;
		}
	}
	
	public Boolean discardCard (Card card) {
		if (cards.indexOf(card) == -1) {
			System.out.println("Card not present");
			return false;
		}
		return cards.remove(card);
	}
	
	public void sort() {
		Collections.sort(cards, CardComparator.getComparator());
	}
	
	@Override
	public String toString() {
		return cards.toString();
	}
}
