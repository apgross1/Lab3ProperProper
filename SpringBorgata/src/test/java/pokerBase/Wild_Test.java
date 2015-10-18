package pokerBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import pokerEnums.eCardNo;
import pokerEnums.eHandStrength;
import pokerEnums.eRank;
import pokerEnums.eSuit;

public class Wild_Test {

	@Test
	public void FourOfAKind_1() {

		ArrayList<Card> Wilds = new ArrayList<Card>();

		Wilds.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.SPADES, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.HEARTS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.DIAMONDS, eRank.TWO, 0));

		int NbrOfJokers = 0;
		
		Deck d = new Deck(NbrOfJokers,Wilds);
		Hand h = new Hand();
		
		h.AddCardToHand(new Card(eSuit.CLUBS, eRank.TWO, true));
		h.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.TEN, 0));
		h.AddCardToHand(new Card(eSuit.SPADES, eRank.TEN, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TEN, 0));
		h.AddCardToHand(new Card(eSuit.CLUBS, eRank.NINE, 0));
		h = Hand.EvalHand(h);


		System.out.println(h.getHandStrength());
		
		assertTrue(h.getHandStrength() == eHandStrength.FourOfAKind.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.TEN.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker().size() == 1);

		Card c1 = h.getKicker().get(eCardNo.FirstCard.getCardNo());

		// Check to see if the kicker is a NINE
		assertTrue(c1.getRank().getRank() == eRank.NINE.getRank());

	}

	@Test
	public void FourOfAKind_2() {
		Deck d = new Deck();
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS, eRank.TEN, 0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.TEN, 0));
		h.AddCardToHand(new Card(eSuit.SPADES, eRank.TEN, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TEN, 0));
		h.AddCardToHand(new Card(eSuit.CLUBS, eRank.KING, 0));
		h = Hand.EvalHand(h);
		;

		assertTrue(h.getHandStrength() == eHandStrength.FourOfAKind.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.TEN.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker().size() == 1);
	}

	
}
