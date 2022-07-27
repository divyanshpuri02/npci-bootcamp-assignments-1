package guidedcards;

import java.util.Comparator;
import java.util.Random;

public class CardRandomizer implements Comparator<Card> {
	
	private static CardRandomizer cardComparator;
	
	public static CardRandomizer getComparator () {
		if (cardComparator == null) {
			cardComparator = new CardRandomizer();
		}
		return cardComparator;
	}

	@Override
	public int compare(Card arg0, Card arg1) {
		Random random = new Random();
		int check = random.nextInt() % 3;
		return (check == 0) ? 0 : (check == 1) ? 1 : -1;
	}

}
