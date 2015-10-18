package pokerBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pokerEnums.eRank;
import pokerEnums.eSuit;

public class Deck_Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Tests to see if the Deck is 52 cards
	 */
	@Test
	public void TestFullDeck() {
		int iDeckSize = 52;
		Deck d = new Deck();
		assertTrue(d.getTotalCards() == iDeckSize);		
	}
	
	/**
	 * Tests to see if drawing a card reduces the deck by 1
	 */
	@Test
	public void DrawOne() {
		int iDeckSize = 52;
		int iDeckSizeDrawOne = 51;
		
		Deck d = new Deck();
		assertTrue(d.getTotalCards() == iDeckSize);
		d.drawFromDeck();
		
		assertTrue(d.getTotalCards() == iDeckSizeDrawOne);
		
	}
	
	/**
	 * Tests for Jokers in Deck
	 */
	@Test
	public void JokerTest() {
		Deck deck = new Deck(5);
		
		//Determines if 5 new cards were added to the deck
		assertTrue(deck.getCards().size() == 57);
		
		//Determines if those 5 new cards were Jokers
		int jokerNum = 0;
		for (Card card: deck.getCards()) {
			if (card.getRank() == eRank.JOKER) {
				jokerNum += 1;
			}
			else
				continue;
		}
		
		assertTrue(jokerNum == 5);
 	}
	
	
	/**
	 * Tests for Wilds in Deck
	 */
	@Test
	public void WildTest() {
		ArrayList<Card> Wilds = new ArrayList<Card>();
	
		Wilds.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.SPADES, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.HEARTS, eRank.TWO, 0));
		Wilds.add(new Card(eSuit.DIAMONDS, eRank.TWO, 0));
		
		int numJokers = 0;
		
		Deck deck = new Deck(numJokers, Wilds);
		ArrayList<Card> assertList = new ArrayList<Card>();
		for (Card card : deck.getCards()) {
			if (card.getRank() == eRank.TWO) {
				assertList.add(card);
			}
			else
				continue;
		}
		//Determines if the cards pulled are wild
		assertTrue(assertList.get(0).getWild() == true);
		assertTrue(assertList.get(1).getWild() == true);
		assertTrue(assertList.get(2).getWild() == true);
		assertTrue(assertList.get(3).getWild() == true);
	}

}
