package guidedcards;

import java.util.Comparator;

public class CardComparator implements Comparator<Card> {
	
	private static CardComparator cardComparator;
	
	public static CardComparator getComparator () {
		if (cardComparator == null) {
			cardComparator = new CardComparator();
		}
		return cardComparator;
	}

	@Override
	public int compare(Card arg0, Card arg1) {
		if (arg0.getSuit() < arg1.getSuit()) {
			return -1;
		}
		else if (arg0.getSuit() > arg1.getSuit()) {
			return 1;
		}
		else {
			if (arg0.getRank() < arg1.getRank()) {
				return -1;
			}
			else if (arg0.getRank() > arg1.getRank()) {
				return 1;
			}
			else return 0;
		}
	}

}
