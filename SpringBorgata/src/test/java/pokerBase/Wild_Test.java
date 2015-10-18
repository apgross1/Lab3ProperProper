package pokerBase;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import pokerEnums.eCardNo;
import pokerEnums.eHandStrength;
import pokerEnums.eRank;
import pokerEnums.eSuit;

public class Wild_Test {

	/**
	 * Tests for 5 of a kind
	 */
	@Test
	public void FiveOfAKind() {

		ArrayList<Card> Wilds = new ArrayList<Card>();

		Wilds.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.SPADES, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.HEARTS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.DIAMONDS, eRank.TWO, 0));

		int NbrOfJokers = 0;
		
		Deck d = new Deck(NbrOfJokers,Wilds);
		Hand h = new Hand();
		
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.THREE, true));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.JACK, true));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.QUEEN, true));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.ACE, 0));

		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.ACE, 0));
		h = Hand.EvalHand(h);
		
		assertTrue(h.getHandStrength() == eHandStrength.FiveOfAKind.getHandStrength());
		
	}
	
	/**
	 * Tests for Royal Flush
	 */
	@Test
	public void RoyalFlush() {
		ArrayList<Card> Wilds = new ArrayList<Card>();

		Wilds.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.SPADES, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.HEARTS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.DIAMONDS, eRank.TWO, 0));

		int NbrOfJokers = 0;
		
		Deck d = new Deck(NbrOfJokers,Wilds);
		Hand h = new Hand();
		
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TEN, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));

		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.ACE, 0));
		h = Hand.EvalHand(h);
		
		assertTrue(h.getHandStrength() == eHandStrength.RoyalFlush.getHandStrength());
	}
	
	/**
	 * Tests for natural Royal Flush (no Wilds)
	 */
	@Test
	public void NaturalRoyalFlush() {
		int NbrOfJokers = 0;
		
		Deck d = new Deck(NbrOfJokers);
		Hand h = new Hand();
		
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TEN, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.JACK, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.QUEEN, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.KING, 0));

		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.ACE, 0));
		h = Hand.EvalHand(h);
		System.out.println(h.getHandStrength());
		assertTrue(h.getHandStrength() == eHandStrength.RoyalFlush.getHandStrength());
	}
	
	
	/**
	 * Tests for Straight Flush (Wilds)
	 */
	@Test
	public void StraightFlush() {
		ArrayList<Card> Wilds = new ArrayList<Card>();

		Wilds.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.SPADES, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.HEARTS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.DIAMONDS, eRank.TWO, 0));
		int NbrOfJokers = 0;
		
		Deck d = new Deck(NbrOfJokers,Wilds);
		Hand h = new Hand();
		
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.THREE, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.FOUR, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.FIVE, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));

		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
		h = Hand.EvalHand(h);
		assertTrue(h.getHandStrength() == eHandStrength.StraightFlush.getHandStrength());
	}
	
	/**
	 * Tests for Straight Flush (no Wilds)
	 */
	@Test
	public void nStraightFlush() {
		int NbrOfJokers = 0;
		
		Deck d = new Deck(NbrOfJokers);
		Hand h = new Hand();
		
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.THREE, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.FOUR, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.FIVE, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.SIX, 0));

		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.SEVEN,0));
		h = Hand.EvalHand(h);
		assertTrue(h.getHandStrength() == eHandStrength.StraightFlush.getHandStrength());
	}
	/**
	 * Tests for Four of a Kind
	 */
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

	/**
	 * Tests for 4 of a Kind in different position
	 */
	@Test
	public void FourOfAKind_2() {
		Deck d = new Deck();
		
		//Position 1
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS, eRank.TEN, 0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.TEN, 0));
		h.AddCardToHand(new Card(eSuit.SPADES, eRank.TEN, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TEN, 0));
		h.AddCardToHand(new Card(eSuit.CLUBS, eRank.KING, 0));
		h = Hand.EvalHand(h);

		assertTrue(h.getHandStrength() == eHandStrength.FourOfAKind.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.TEN.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker().size() == 1);
		
		//Position 2
		Hand h1 = new Hand();
		h1.AddCardToHand(new Card(eSuit.CLUBS, eRank.KING, 0));
		h1.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.TEN, 0));
		h1.AddCardToHand(new Card(eSuit.SPADES, eRank.TEN, 0));
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.TEN, 0));
		h1.AddCardToHand(new Card(eSuit.CLUBS, eRank.TEN, 0));
		h1 = Hand.EvalHand(h1);


		assertTrue(h1.getHandStrength() == eHandStrength.FourOfAKind.getHandStrength());
		assertTrue(h1.getHighPairStrength() == eRank.TEN.getRank());
		assertTrue(h1.getLowPairStrength() == 0);
		assertTrue(h1.getKicker().size() == 1);
	}
	
	
	/**
	 * JUnit test for Full House with wilds to prove that when there is at least 2 wilds in the hand, a
	 * full house is not possible. The hand will evaluate to a four of a kind
	 */
	@Test
	public void FullHouse() {
		ArrayList<Card> Wilds = new ArrayList<Card>();
	
		Wilds.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.SPADES, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.HEARTS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.DIAMONDS, eRank.TWO, 0));
		int NbrOfJokers = 0;
		
		Deck d = new Deck(NbrOfJokers,Wilds);
		Hand h = new Hand();
		
		//Will return a proper Full House
        h.AddCardToHand(new Card(eSuit.HEARTS, eRank.THREE, 0));
        h.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.THREE, 0));
        h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
        h.AddCardToHand(new Card(eSuit.HEARTS, eRank.SIX, 0));
        h.AddCardToHand(new Card(eSuit.HEARTS, eRank.SIX, 0));


		h = Hand.EvalHand(h);
		assertTrue(h.getHandStrength() == eHandStrength.FullHouse.getHandStrength());
		assertFalse(h.getHandStrength() == eHandStrength.FourOfAKind.getHandStrength());
		assertTrue(h.getKicker() == null);
		
		//Will return Four of a Kind
		Hand h1 = new Hand();

        h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.THREE, 0));
        h1.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.THREE, 0));
        h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.THREE, 0));
        h1.AddCardToHand(new Card(eSuit.CLUBS, eRank.TWO, true));
        h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.SIX, 0));
		h1 = Hand.EvalHand(h1);
		
		System.out.println(h1.getHandStrength());
		assertFalse(h1.getHandStrength() == eHandStrength.FullHouse.getHandStrength());
		assertTrue(h1.getHandStrength() == eHandStrength.FourOfAKind.getHandStrength());
		assertTrue(h1.getKicker().get(0).getRank() == eRank.SIX);
	}
	
	/**
	 * Tests for Full House (no Wilds)
	 */
	@Test
	public void nFullHouse() {
		Deck d = new Deck();
		
		//Position 1
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS, eRank.SIX, 0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.SIX, 0));
		h.AddCardToHand(new Card(eSuit.SPADES, eRank.SIX, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.THREE, 0));
		h.AddCardToHand(new Card(eSuit.CLUBS, eRank.THREE, 0));
		h = Hand.EvalHand(h);

		assertTrue(h.getHandStrength() == eHandStrength.FullHouse.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.SIX.getRank());
		assertTrue(h.getLowPairStrength() == eRank.THREE.getRank());
		assertTrue(h.getKicker() == null);
		
		//Position 2
		Hand h1 = new Hand();
		h1.AddCardToHand(new Card(eSuit.CLUBS, eRank.THREE, 0));
		h1.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.THREE, 0));
		h1.AddCardToHand(new Card(eSuit.SPADES, eRank.THREE, 0));
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.SIX, 0));
		h1.AddCardToHand(new Card(eSuit.CLUBS, eRank.SIX, 0));
		h1 = Hand.EvalHand(h1);

		assertTrue(h1.getHandStrength() == eHandStrength.FullHouse.getHandStrength());
		assertTrue(h1.getHighPairStrength() == eRank.THREE.getRank());
		assertTrue(h1.getLowPairStrength() == eRank.SIX.getRank());
		assertTrue(h1.getKicker() == null);
	}
	
	/**
	 * Tests for Flush
	 */
	@Test
	public void Flush() {
		ArrayList<Card> Wilds = new ArrayList<Card>();

		Wilds.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.SPADES, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.HEARTS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.DIAMONDS, eRank.TWO, 0));
		int NbrOfJokers = 0;
		
		Deck d = new Deck(NbrOfJokers,Wilds);
		Hand h = new Hand();
		
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.ACE, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.FIVE, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.JACK, 0));

		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
		h = Hand.EvalHand(h);
		assertTrue(h.getHandStrength() == eHandStrength.Flush.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.ACE.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker() == null);
	}
	
	/**
	 * Tests for Flush (No Wilds)
	 */
	@Test
	public void nFlush() {
		int NbrOfJokers = 0;
		
		Deck d = new Deck(NbrOfJokers);
		Hand h = new Hand();
		
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.ACE, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.FIVE, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.JACK, 0));

		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.SIX, 0));
		h = Hand.EvalHand(h);
		assertTrue(h.getHandStrength() == eHandStrength.Flush.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.ACE.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker() == null);
	}
	
	/**
	 * Tests for Straight
	 */
	@Test
	public void Straight() {
		ArrayList<Card> Wilds = new ArrayList<Card>();

		Wilds.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.SPADES, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.HEARTS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.DIAMONDS, eRank.TWO, 0));
		int NbrOfJokers = 0;
		
		Deck d = new Deck(NbrOfJokers,Wilds);
		//Low straight
		Hand h1 = new Hand();
		
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.THREE, 0));
		h1.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.FOUR, 0));
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.FIVE, 0));
		h1.AddCardToHand(new Card(eSuit.CLUBS, eRank.TWO, true));

		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
		h1 = Hand.EvalHand(h1);
		assertTrue(h1.getHandStrength() == eHandStrength.Straight.getHandStrength());
		assertTrue(h1.getHighPairStrength() == eRank.ACE.getRank());
		assertTrue(h1.getLowPairStrength() == 0);
		assertTrue(h1.getKicker() == null);
		
		//Mid Straight
		Hand h = new Hand();
		
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.FOUR, 0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.FIVE, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.SIX, 0));
		h.AddCardToHand(new Card(eSuit.CLUBS, eRank.TWO, true));

		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
		h = Hand.EvalHand(h);
		assertTrue(h.getHandStrength() == eHandStrength.Straight.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.EIGHT.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker() == null);
		
		//High Straight
		Hand h2 = new Hand();
		
		h2.AddCardToHand(new Card(eSuit.HEARTS, eRank.TEN, 0));
		h2.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.JACK, 0));
		h2.AddCardToHand(new Card(eSuit.HEARTS, eRank.QUEEN, 0));
		h2.AddCardToHand(new Card(eSuit.CLUBS, eRank.KING, true));

		h2.AddCardToHand(new Card(eSuit.HEARTS, eRank.ACE, true));
		h2 = Hand.EvalHand(h2);
		assertTrue(h2.getHandStrength() == eHandStrength.Straight.getHandStrength());
		assertTrue(h2.getHighPairStrength() == eRank.ACE.getRank());
		assertTrue(h2.getLowPairStrength() == 0);
		assertTrue(h2.getKicker() == null);
	}
	
	/**
	 *Tests for Straight (no Wilds) 
	 */
	@Test
	public void nStraight() {
		int NbrOfJokers = 0;
		
		Deck d = new Deck(NbrOfJokers);
		Hand h = new Hand();
		
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.THREE, 0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.FOUR, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.FIVE, 0));
		h.AddCardToHand(new Card(eSuit.CLUBS, eRank.SIX, 0));

		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.SEVEN, 0));
		h = Hand.EvalHand(h);
		assertTrue(h.getHandStrength() == eHandStrength.Straight.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.SEVEN.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker() == null);
	}
	
	/**
	 * Tests for Three of a Kind
	 */
	@Test
	public void ThreeOfAKind() {
		ArrayList<Card> Wilds = new ArrayList<Card>();

		Wilds.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.SPADES, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.HEARTS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.DIAMONDS, eRank.TWO, 0));
		int NbrOfJokers = 0;
		
		Deck d = new Deck(NbrOfJokers,Wilds);
		Hand h = new Hand();
		
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.KING, 0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.THREE, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.SIX, 0));
		h.AddCardToHand(new Card(eSuit.CLUBS, eRank.TWO, true));

		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
		h = Hand.EvalHand(h);
		assertTrue(h.getHandStrength() == eHandStrength.ThreeOfAKind.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.KING.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		
		assertTrue(h.getKicker().get(1).getRank() == eRank.THREE);
		assertTrue(h.getKicker().get(0).getRank() == eRank.SIX);
	}
	
	/**
	 * Tests for Three of a Kind (no Wilds)
	 */
	@Test
	public void nThreeOfAKind() {
		int NbrOfJokers = 0;
		
		Deck d = new Deck(NbrOfJokers);
		
		//Position 1
		Hand h = new Hand();
		
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.FOUR, 0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.FOUR, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.FOUR, 0));
		h.AddCardToHand(new Card(eSuit.CLUBS, eRank.THREE, 0));

		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, 0));
		h = Hand.EvalHand(h);
		assertTrue(h.getHandStrength() == eHandStrength.ThreeOfAKind.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.FOUR.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		
		assertTrue(h.getKicker().get(1).getRank() == eRank.TWO);
		assertTrue(h.getKicker().get(0).getRank() == eRank.THREE);
		
		//Position 2
		Hand h1 = new Hand();
		
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.FIVE, 0));
		h1.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.FOUR, 0));
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.FOUR, 0));
		h1.AddCardToHand(new Card(eSuit.CLUBS, eRank.FOUR, 0));

		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, 0));
		h1 = Hand.EvalHand(h1);
		assertTrue(h1.getHandStrength() == eHandStrength.ThreeOfAKind.getHandStrength());
		assertTrue(h1.getHighPairStrength() == eRank.FOUR.getRank());
		assertTrue(h1.getLowPairStrength() == 0);
		
		assertTrue(h1.getKicker().get(1).getRank() == eRank.TWO);
		assertTrue(h1.getKicker().get(0).getRank() == eRank.FIVE);
		
		//Position 3
		Hand h2 = new Hand();
		
		h2.AddCardToHand(new Card(eSuit.HEARTS, eRank.SIX, 0));
		h2.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.FIVE, 0));
		h2.AddCardToHand(new Card(eSuit.HEARTS, eRank.FOUR, 0));
		h2.AddCardToHand(new Card(eSuit.CLUBS, eRank.FOUR, 0));

		h2.AddCardToHand(new Card(eSuit.HEARTS, eRank.FOUR, 0));
		h2 = Hand.EvalHand(h2);
		assertTrue(h2.getHandStrength() == eHandStrength.ThreeOfAKind.getHandStrength());
		assertTrue(h2.getHighPairStrength() == eRank.FOUR.getRank());
		assertTrue(h2.getLowPairStrength() == 0);
		
		assertTrue(h2.getKicker().get(1).getRank() == eRank.FIVE);
		assertTrue(h2.getKicker().get(0).getRank() == eRank.SIX);
	}
	
	/**
	 * A test that proves that even with one wild card a two pair will always evaluate to at least 
	 * A three of a kind
	 */
	@Test
	public void TwoPair() {
		ArrayList<Card> Wilds = new ArrayList<Card>();

		Wilds.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.SPADES, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.HEARTS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.DIAMONDS, eRank.TWO, 0));
		int NbrOfJokers = 0;
		
		Deck d = new Deck(NbrOfJokers,Wilds);
		Hand h = new Hand();
		
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.KING, 0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.THREE, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.KING, 0));
		h.AddCardToHand(new Card(eSuit.CLUBS, eRank.FIVE, 0));

		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
		h = Hand.EvalHand(h);
		assertTrue(h.getHandStrength() == eHandStrength.ThreeOfAKind.getHandStrength());
		assertFalse(h.getHandStrength() == eHandStrength.TwoPair.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.KING.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		
		assertTrue(h.getKicker().get(1).getRank() == eRank.THREE);
		assertTrue(h.getKicker().get(0).getRank() == eRank.FIVE);
	}
	
	/**
	 * Tests for Two Pair (no Wilds)
	 */
	@Test
	public void nTwoPair() {
		int NbrOfJokers = 0;
		
		Deck d = new Deck(NbrOfJokers);
		
		//Position 1
		Hand h = new Hand();
		
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.KING, 0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.KING, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.THREE, 0));
		h.AddCardToHand(new Card(eSuit.CLUBS, eRank.THREE, 0));

		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, 0));
		h = Hand.EvalHand(h);
		assertTrue(h.getHandStrength() == eHandStrength.TwoPair.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.KING.getRank());
		assertTrue(h.getLowPairStrength() == eRank.THREE.getRank());
		assertTrue(h.getKicker().get(0).getRank() == eRank.TWO);
		
		//Position 2
		Hand h1 = new Hand();
		
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.KING, 0));
		h1.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.KING, 0));
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.FOUR, 0));
		h1.AddCardToHand(new Card(eSuit.CLUBS, eRank.THREE, 0));

		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.THREE, 0));
		h1 = Hand.EvalHand(h1);
		assertTrue(h1.getHandStrength() == eHandStrength.TwoPair.getHandStrength());
		assertTrue(h1.getHighPairStrength() == eRank.KING.getRank());
		assertTrue(h1.getLowPairStrength() == eRank.THREE.getRank());
		assertTrue(h1.getKicker().get(0).getRank() == eRank.FOUR);
	}
	
	/**
	 * Tests for Pair
	 */
	@Test
	public void Pair() {
		ArrayList<Card> Wilds = new ArrayList<Card>();

		Wilds.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.SPADES, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.HEARTS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.DIAMONDS, eRank.TWO, 0));
		int NbrOfJokers = 0;
		
		Deck d = new Deck(NbrOfJokers,Wilds);
		Hand h = new Hand();
		
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.KING, 0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.FIVE, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.ACE, 0));
		h.AddCardToHand(new Card(eSuit.CLUBS, eRank.TWO, true));

		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.NINE, 0));
		h = Hand.EvalHand(h);
		assertTrue(h.getHandStrength() == eHandStrength.Pair.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.ACE.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		
		assertTrue(h.getKicker().get(0).getRank() == eRank.KING);
		assertTrue(h.getKicker().get(1).getRank() == eRank.NINE);
		assertTrue(h.getKicker().get(2).getRank() == eRank.FIVE);
	}
	
	/**
	 * Tests for Pair (no Wilds)
	 */
	@Test
	public void nPair() {
		int NbrOfJokers = 0;
		
		Deck d = new Deck(NbrOfJokers);
		
		//Position 1
		Hand h = new Hand();
		
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.KING, 0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.KING, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.THREE, 0));
		h.AddCardToHand(new Card(eSuit.CLUBS, eRank.TWO, 0));

		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.NINE, 0));
		h = Hand.EvalHand(h);
		assertTrue(h.getHandStrength() == eHandStrength.Pair.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.KING.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		
		assertTrue(h.getKicker().get(0).getRank() == eRank.NINE);
		assertTrue(h.getKicker().get(1).getRank() == eRank.THREE);
		assertTrue(h.getKicker().get(2).getRank() == eRank.TWO);
		
		//Position 2
		Hand h1 = new Hand();
		
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.KING, 0));
		h1.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.TEN, 0));
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.TEN, 0));
		h1.AddCardToHand(new Card(eSuit.CLUBS, eRank.FIVE, 0));

		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, 0));
		h1 = Hand.EvalHand(h1);
		assertTrue(h1.getHandStrength() == eHandStrength.Pair.getHandStrength());
		assertTrue(h1.getHighPairStrength() == eRank.TEN.getRank());
		assertTrue(h1.getLowPairStrength() == 0);
		
		assertTrue(h1.getKicker().get(0).getRank() == eRank.KING);
		assertTrue(h1.getKicker().get(1).getRank() == eRank.FIVE);
		assertTrue(h1.getKicker().get(2).getRank() == eRank.TWO);
		
		//Position 3
		Hand h2 = new Hand();
		
		h2.AddCardToHand(new Card(eSuit.HEARTS, eRank.KING, 0));
		h2.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.QUEEN, 0));
		h2.AddCardToHand(new Card(eSuit.HEARTS, eRank.TEN, 0));
		h2.AddCardToHand(new Card(eSuit.CLUBS, eRank.TEN, 0));

		h2.AddCardToHand(new Card(eSuit.HEARTS, eRank.FIVE, 0));
		h2 = Hand.EvalHand(h2);
		assertTrue(h2.getHandStrength() == eHandStrength.Pair.getHandStrength());
		assertTrue(h2.getHighPairStrength() == eRank.TEN.getRank());
		assertTrue(h2.getLowPairStrength() == 0);
		
		assertTrue(h2.getKicker().get(0).getRank() == eRank.KING);
		assertTrue(h2.getKicker().get(1).getRank() == eRank.QUEEN);
		assertTrue(h2.getKicker().get(2).getRank() == eRank.FIVE);
		
		//Position 4
		Hand h3 = new Hand();
		
		h3.AddCardToHand(new Card(eSuit.HEARTS, eRank.KING, 0));
		h3.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.QUEEN, 0));
		h3.AddCardToHand(new Card(eSuit.HEARTS, eRank.JACK, 0));
		h3.AddCardToHand(new Card(eSuit.CLUBS, eRank.TEN, 0));

		h3.AddCardToHand(new Card(eSuit.HEARTS, eRank.TEN, 0));
		h3 = Hand.EvalHand(h3);
		assertTrue(h3.getHandStrength() == eHandStrength.Pair.getHandStrength());
		assertTrue(h3.getHighPairStrength() == eRank.TEN.getRank());
		assertTrue(h3.getLowPairStrength() == 0);
		
		assertTrue(h3.getKicker().get(0).getRank() == eRank.KING);
		assertTrue(h3.getKicker().get(1).getRank() == eRank.QUEEN);
		assertTrue(h3.getKicker().get(2).getRank() == eRank.JACK);
	}
	
	/**
	 * Tests for High Card
	 * No test for High Card with Wild because it would evaluate to Pair
	 */
	@Test
	public void nHighCard() {
		int NbrOfJokers = 0;
		
		Deck d = new Deck(NbrOfJokers);
		Hand h = new Hand();
		
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.KING, 0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.TEN, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.ACE, 0));
		h.AddCardToHand(new Card(eSuit.CLUBS, eRank.TWO, 0));

		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.NINE, 0));
		h = Hand.EvalHand(h);
		assertTrue(h.getHandStrength() == eHandStrength.HighCard.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.ACE.getRank());
		assertTrue(h.getLowPairStrength() == 0);

		assertTrue(h.getKicker().get(0).getRank() == eRank.KING);
		assertTrue(h.getKicker().get(1).getRank() == eRank.TEN);
		assertTrue(h.getKicker().get(2).getRank() == eRank.NINE);
		assertTrue(h.getKicker().get(3).getRank() == eRank.TWO);
	}
	
	////////////////////////////////////WILD COMPARISON TESTS//////////////////////////////////////////
	@Test
	public void CompareFiveRoyal() {
	//Five of a Kind and Royal Flush
	//Making Royal Flush
		ArrayList<Card> Wilds = new ArrayList<Card>();
	
		Wilds.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.SPADES, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.HEARTS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.DIAMONDS, eRank.TWO, 0));
	
		int NbrOfJokers = 0;
		
		Deck d = new Deck(NbrOfJokers,Wilds);
		Hand h = new Hand();
		
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TEN, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
	
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.ACE, 0));
		h = Hand.EvalHand(h);
		
		//Making 5 of a kind
		Hand h1 = new Hand();
		
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.THREE, true));
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.JACK, true));
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.QUEEN, true));
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.ACE, 0));
	
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.ACE, 0));
		h1 = Hand.EvalHand(h1);
		
		ArrayList<Hand> twoHands1 = new ArrayList<Hand>();
		twoHands1.add(h);
		twoHands1.add(h1);
		
		Collections.sort(twoHands1, Hand.HandRank);
		
		Hand winningHand1 = twoHands1.get(0);
		assertTrue(winningHand1.getHandStrength() == eHandStrength.FiveOfAKind.getHandStrength());
	}
	
	@Test
	public void compareStraightRoyal(){
	//Straight Flush and Royal Flush//
		ArrayList<Card> Wilds = new ArrayList<Card>();
	
		Wilds.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.SPADES, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.HEARTS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.DIAMONDS, eRank.TWO, 0));
	
		int NbrOfJokers = 0;
		Deck d = new Deck(NbrOfJokers,Wilds);
		
		//Making Royal Flush
		Hand h = new Hand();
		
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TEN, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
	
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.ACE, 0));
		h = Hand.EvalHand(h);
		
		//Making Straight Flush
		Hand h1 = new Hand();
		
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.THREE, 0));
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.FOUR, 0));
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.FIVE, 0));
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
	
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
		h1 = Hand.EvalHand(h1);
		
		ArrayList<Hand> twoHands = new ArrayList<Hand>();
		twoHands.add(h);
		twoHands.add(h1);
		
		Collections.sort(twoHands, Hand.HandRank);
		
		Hand winningHand2 = twoHands.get(0);
		assertTrue(winningHand2.getHandStrength() == eHandStrength.RoyalFlush.getHandStrength());
	}
	
	@Test
	public void compareFourKindFullHouse(){
	//Four of a Kind and Full House//
		ArrayList<Card> Wilds = new ArrayList<Card>();
	
		Wilds.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.SPADES, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.HEARTS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.DIAMONDS, eRank.TWO, 0));
	
		int NbrOfJokers = 0;
		Deck d = new Deck(NbrOfJokers,Wilds);
		//Making Four of a Kind
		Hand h = new Hand();
	
	    h.AddCardToHand(new Card(eSuit.HEARTS, eRank.THREE, 0));
	    h.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.THREE, 0));
	    h.AddCardToHand(new Card(eSuit.HEARTS, eRank.THREE, 0));
	    h.AddCardToHand(new Card(eSuit.CLUBS, eRank.TWO, true));
	    h.AddCardToHand(new Card(eSuit.HEARTS, eRank.SIX, 0));
		h = Hand.EvalHand(h);
		
		//Making Full House
		Hand h1 = new Hand();
		
	    h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.THREE, 0));
	    h1.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.THREE, 0));
	    h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
	    h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.SIX, 0));
	    h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.SIX, 0));
	
	
		h1 = Hand.EvalHand(h1);
		
		ArrayList<Hand> twoHands = new ArrayList<Hand>();
		twoHands.add(h);
		twoHands.add(h1);
		
		Collections.sort(twoHands, Hand.HandRank);
		
		Hand winningHand2 = twoHands.get(0);
		assertTrue(winningHand2.getHandStrength() == eHandStrength.FourOfAKind.getHandStrength());
	}
	
	@Test
	public void compareFullHouseThreeKind(){
	//Three of a Kind and Full House//
		ArrayList<Card> Wilds = new ArrayList<Card>();
	
		Wilds.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.SPADES, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.HEARTS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.DIAMONDS, eRank.TWO, 0));
	
		int NbrOfJokers = 0;
		Deck d = new Deck(NbrOfJokers,Wilds);
		
		//Making Three of a Kind
		Hand h = new Hand();
		
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.KING, 0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.THREE, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.SIX, 0));
		h.AddCardToHand(new Card(eSuit.CLUBS, eRank.TWO, true));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
		
		h = Hand.EvalHand(h);
		
		//Making Full House
		Hand h1 = new Hand();
		
	    h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.THREE, 0));
	    h1.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.THREE, 0));
	    h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
	    h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.SIX, 0));
	    h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.SIX, 0));
	
	
		h1 = Hand.EvalHand(h1);
		
		ArrayList<Hand> twoHands = new ArrayList<Hand>();
		twoHands.add(h);
		twoHands.add(h1);
		
		Collections.sort(twoHands, Hand.HandRank);
		
		Hand winningHand2 = twoHands.get(0);
		assertTrue(winningHand2.getHandStrength() == eHandStrength.FullHouse.getHandStrength());
	}
	
	@Test
	public void compareFlushStraight(){
	//Flush and Straight//
		ArrayList<Card> Wilds = new ArrayList<Card>();
	
		Wilds.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.SPADES, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.HEARTS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.DIAMONDS, eRank.TWO, 0));
	
		int NbrOfJokers = 0;
		Deck d = new Deck(NbrOfJokers,Wilds);
		//Making Flush
		Hand h = new Hand();
		
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.ACE, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.FIVE, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.JACK, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
		
		h = Hand.EvalHand(h);
		
		//Making Straight
		Hand h1 = new Hand();
		
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.THREE, 0));
		h1.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.FOUR, 0));
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.FIVE, 0));
		h1.AddCardToHand(new Card(eSuit.CLUBS, eRank.TWO, true));
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
		
		h1 = Hand.EvalHand(h1);
		
		ArrayList<Hand> twoHands = new ArrayList<Hand>();
		twoHands.add(h);
		twoHands.add(h1);
		
		Collections.sort(twoHands, Hand.HandRank);
		
		Hand winningHand2 = twoHands.get(0);
		assertTrue(winningHand2.getHandStrength() == eHandStrength.Flush.getHandStrength());
	}
	
	@Test
	public void compareStraightStraightFlush(){
	//Straight and Straight Flush//
		ArrayList<Card> Wilds = new ArrayList<Card>();
	
		Wilds.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.SPADES, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.HEARTS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.DIAMONDS, eRank.TWO, 0));
	
		int NbrOfJokers = 0;
			Deck d = new Deck(NbrOfJokers,Wilds);
		//Making Straight Flush
		Hand h = new Hand();
		
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.THREE, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.FOUR, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.FIVE, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
		
		h = Hand.EvalHand(h);
		
		//Making Straight
		Hand h1 = new Hand();
		
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.THREE, 0));
		h1.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.FOUR, 0));
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.FIVE, 0));
		h1.AddCardToHand(new Card(eSuit.CLUBS, eRank.TWO, true));
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
		
		h1 = Hand.EvalHand(h1);
		
		ArrayList<Hand> twoHands = new ArrayList<Hand>();
		twoHands.add(h);
		twoHands.add(h1);
		
		Collections.sort(twoHands, Hand.HandRank);
		
		Hand winningHand2 = twoHands.get(0);
		assertTrue(winningHand2.getHandStrength() == eHandStrength.StraightFlush.getHandStrength());
	}
	
	@Test
	public void compareThreeofKind2Pair(){
	//Three of a Kind and Two Pair//
		ArrayList<Card> Wilds = new ArrayList<Card>();
	
		Wilds.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.SPADES, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.HEARTS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.DIAMONDS, eRank.TWO, 0));
	
		int NbrOfJokers = 0;
		Deck d = new Deck(NbrOfJokers,Wilds);
		//Three of a Kind
		Hand h = new Hand();
		
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.KING, 0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.THREE, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.SIX, 0));
		h.AddCardToHand(new Card(eSuit.CLUBS, eRank.TWO, true));
	
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
		h = Hand.EvalHand(h);
		
		//Two Pair
		Hand h1 = new Hand();
		
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.KING, 0));
		h1.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.THREE, 0));
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.KING, 0));
		h1.AddCardToHand(new Card(eSuit.CLUBS, eRank.FIVE, 0));
	
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
		h1 = Hand.EvalHand(h1);
		
		ArrayList<Hand> twoHands = new ArrayList<Hand>();
		twoHands.add(h);
		twoHands.add(h1);
		
		Collections.sort(twoHands, Hand.HandRank);
		
		Hand winningHand2 = twoHands.get(0);
		assertTrue(winningHand2.getHandStrength() == eHandStrength.ThreeOfAKind.getHandStrength());
	}
	
	@Test
	public void compareTwoPairPair(){
	//Pair and Two Pair BUT Two Pairs always default to at least 3 of a kind with 1 wild card
		ArrayList<Card> Wilds = new ArrayList<Card>();
	
		Wilds.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.SPADES, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.HEARTS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.DIAMONDS, eRank.TWO, 0));
	
		int NbrOfJokers = 0;
		Deck d = new Deck(NbrOfJokers,Wilds);
		//Pair
		Hand h = new Hand();
		
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.KING, 0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.KING, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.THREE, 0));
		h.AddCardToHand(new Card(eSuit.CLUBS, eRank.TWO, 0));
	
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.NINE, 0));
		h = Hand.EvalHand(h);
		
		//Two Pair (but really Three of a Kind
		Hand h1 = new Hand();
		
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.KING, 0));
		h1.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.THREE, 0));
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.KING, 0));
		h1.AddCardToHand(new Card(eSuit.CLUBS, eRank.FIVE, 0));
	
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
		h1 = Hand.EvalHand(h1);
		
		ArrayList<Hand> twoHands = new ArrayList<Hand>();
		twoHands.add(h);
		twoHands.add(h1);
		
		Collections.sort(twoHands, Hand.HandRank);
		
		Hand winningHand2 = twoHands.get(0);
		assertTrue(winningHand2.getHandStrength() == eHandStrength.ThreeOfAKind.getHandStrength());
	}
	
	
	////////////////////////////COMPARISON TESTS FOR BOTH WILD AND NATURAL///////////////////////////
	@Test
	public void CompareRoyalFlush() {
	//Wild Royal Flush
		ArrayList<Card> Wilds = new ArrayList<Card>();
	
		Wilds.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.SPADES, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.HEARTS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.DIAMONDS, eRank.TWO, 0));
	
		int NbrOfJokers = 0;
		
		Deck d = new Deck(NbrOfJokers,Wilds);
		Hand h = new Hand();
		
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TEN, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.TWO, true));
	
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.ACE, 0));
		h = Hand.EvalHand(h);
		
		//Natural Royal Flush
		int NbrOfJokers1 = 0;
		
		Deck d1 = new Deck(NbrOfJokers);
		Hand h1 = new Hand();
		
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.TEN, 0));
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.JACK, 0));
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.QUEEN, 0));
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.KING, 0));
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.ACE, 0));
		
		h1 = Hand.EvalHand(h1);
		
		ArrayList<Hand> twoHands = new ArrayList<Hand>();
		twoHands.add(h);
		twoHands.add(h1);
		
		Collections.sort(twoHands, Hand.HandRank);
		
		Hand winningHand = twoHands.get(0);
		assertTrue(winningHand.getHandStrength() == eHandStrength.RoyalFlush.getHandStrength());
		assertTrue(winningHand.getHandStrength() == twoHands.get(1).getHandStrength());
		
		//Tests to see if the comparator evaluated the natural royal flush to be the highest hand
		assertTrue(winningHand.getbNatural() == 1);
	}
	
	@Test
	public void TwoPairHighHand() {
	//Both have high hand but one has wild card
		ArrayList<Card> Wilds = new ArrayList<Card>();
	
		Wilds.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.SPADES, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.HEARTS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.DIAMONDS, eRank.TWO, 0));
	
		int NbrOfJokers = 0;
		Deck d = new Deck(NbrOfJokers,Wilds);
		
		//High Card
		Hand h = new Hand();
		
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.KING, 0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.TEN, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.ACE, 0));
		h.AddCardToHand(new Card(eSuit.CLUBS, eRank.TWO, 0));
	
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.NINE, 0));
		h = Hand.EvalHand(h);
		
		//High Card with Wild (Will evaluate to Pair)
		Hand h1 = new Hand();
		
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.KING, 0));
		h1.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.TEN, 0));
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.ACE, 0));
		h1.AddCardToHand(new Card(eSuit.CLUBS, eRank.TWO, true));
	
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.NINE, 0));
		h1 = Hand.EvalHand(h1);
		
		ArrayList<Hand> twoHands = new ArrayList<Hand>();
		twoHands.add(h);
		twoHands.add(h1);
		
		Collections.sort(twoHands, Hand.HandRank);
		
		Hand winningHand = twoHands.get(0);
		assertTrue(winningHand.getHandStrength() == eHandStrength.Pair.getHandStrength());
		
		//Indicates the winning hand had a wild card
		assertTrue(winningHand.getbNatural() == 1);
	}
	
	@Test
	public void WildKickers() {
	//Testing Kicker comparison with two wild hands
		ArrayList<Card> Wilds = new ArrayList<Card>();
	
		Wilds.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.SPADES, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.HEARTS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.DIAMONDS, eRank.TWO, 0));
	
		int NbrOfJokers = 0;
		Deck d = new Deck(NbrOfJokers,Wilds);
		
		//High Card (Pair with wild)
		Hand h = new Hand();
		
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.KING, 0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.TEN, 0));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.ACE, 0));
		h.AddCardToHand(new Card(eSuit.CLUBS, eRank.TWO, true));
		h.AddCardToHand(new Card(eSuit.HEARTS, eRank.NINE, 0));
		
		h = Hand.EvalHand(h);
		
		//High Card (Pair with wild)
		Hand h1 = new Hand();
		
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.KING, 0));
		h1.AddCardToHand(new Card(eSuit.DIAMONDS, eRank.TEN, 0));
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.ACE, 0));
		h1.AddCardToHand(new Card(eSuit.CLUBS, eRank.TWO, true));
		h1.AddCardToHand(new Card(eSuit.HEARTS, eRank.EIGHT, 0));
		
		h1 = Hand.EvalHand(h1);
		
		ArrayList<Hand> twoHands = new ArrayList<Hand>();
		twoHands.add(h);
		twoHands.add(h1);
		
		Collections.sort(twoHands, Hand.HandRank);
		
		Hand winningHand = twoHands.get(0);
		assertTrue(winningHand.getHandStrength() == eHandStrength.Pair.getHandStrength());
		
		//Determines if winning hand was Hand h, whose final Kicker has a higher value as that of Hand h1
		assertTrue(winningHand.getKicker().get(2).getRank() == eRank.NINE);
	}
}

	