package pokerBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;

import pokerEnums.eCardNo;
import pokerEnums.eHandStrength;
import pokerEnums.eRank;

public class Hand {
	private UUID playerID;
	@XmlElement
	private ArrayList<Card> CardsInHand;
	private ArrayList<Card> BestCardsInHand;


	@XmlElement
	private int bNatural = 1; //Attribute to determine if a hand is 'Wild' or not. 0 == false, 1 == true

	@XmlElement
	private int HandStrength;
	@XmlElement
	private int HiHand;
	@XmlElement
	private int LoHand;
	@XmlElement
	private ArrayList<Card> Kickers = new ArrayList<Card>();

	private boolean bScored = false;

	private boolean Flush;
	private boolean Straight;
	private boolean Ace;
	private static Deck dNonWildDeck = new Deck(); //Creates new deck with no Jokers/Wild cards

	private ArrayList<Hand> PossibleHands = new ArrayList<Hand>(); //All possible hands given # Wild Cards in hand

	/**
	 * Defalt constructor for Hand
	 */
	public Hand() {

	}

	/**
	 * Adds a card to the Hand
	 * @param c
	 */
	public void AddCardToHand(Card c) {
		if (this.CardsInHand == null) {
			CardsInHand = new ArrayList<Card>();
		}
		this.CardsInHand.add(c);
	}

	/**
	 * Returns a card from the Hand
	 * @param location
	 * @return
	 */
	public Card GetCardFromHand(int location) {
		return CardsInHand.get(location);
	}

	/**
	 * Constructor for Hand that takes in specific Deck
	 * @param d
	 */
	public Hand(Deck d) {
		ArrayList<Card> Import = new ArrayList<Card>();
		for (int x = 0; x < 5; x++) {
			Import.add(d.drawFromDeck());
		}
		CardsInHand = Import;
	}

	/**
	 * Populates Hand with 5 cards
	 * @param setCards
	 */
	public Hand(ArrayList<Card> setCards) {
		this.CardsInHand = setCards;
	}

	/**
	 * Returns the cards in a hand
	 * @return
	 */
	public ArrayList<Card> getCards() {
		return CardsInHand;
	}

	/**
	 * Returns the best hand
	 * @return
	 */
	public ArrayList<Card> getBestHand() {
		return BestCardsInHand;
	}

	public void setPlayerID(UUID playerID) {
		this.playerID = playerID;
	}

	public UUID getPlayerID() {
		return playerID;
	}

	/**
	 * Setter for BestHand
	 * @param BestHand
	 */
	public void setBestHand(ArrayList<Card> BestHand) {
		this.BestCardsInHand = BestHand;
	}

	/**
	 * Returns the Hand Strength of a hand
	 * @return
	 */
	public int getHandStrength() {
		return HandStrength;
	}

	/**
	 * Returns the Kickers in a given Hand
	 * @return
	 */
	public ArrayList<Card> getKicker() {
		return Kickers;
	}

	/**Returns the strength of the 2nd highest hand
	 * @return
	 */
	public int getHighPairStrength() {
		return HiHand;
	}

	/**
	 * Returns the strength of the 3rd highest hand
	 * @return
	 */
	public int getLowPairStrength() {
		return LoHand;
	}

	/**
	 * Returns truth value of whether or not there is an Ace in the hand
	 * @return
	 */
	public boolean getAce() {
		return Ace;
	}

	/**
	 * Returns 1 (true) or 0 (false) depending on whether hand is 'Wild' (0) or not (1)
	 * @return
	 */
	public int getbNatural() {
			
			return this.bNatural;
		}

	/**
	 * Takes in a hand and analyzes each card. If the card is a Joker or wild card, it sets
	 * the hand as unnatural. Then, it calls SubstituteHand on the hand. Ultimately, what is returned
	 * is an ArrayList of Hand ranging from 1 hand (if there were not Jokers/wild cards to millions of
	 * hands.
	 * @param h
	 * @return
	 */
	private static ArrayList<Hand> ExplodeHands(Hand h) {
		ArrayList<Hand> HandsToReturn = new ArrayList<Hand>();
		HandsToReturn.add(h);

		for (int a = 0; a < h.CardsInHand.size(); a++) {
			if (h.CardsInHand.get(a).getRank().getRank() == eRank.JOKER.getRank()
					|| h.CardsInHand.get(a).getWild() == true) {
				h.bNatural = 0; //Hand is not natural
			}
		}

		for (

		int a = 0; a < h.CardsInHand.size(); a++)

		{
			HandsToReturn = SubstituteHand(HandsToReturn, a);
		}

		return HandsToReturn; //ArrayList of all possible Hands

	}

	/**
	 * Determines number of Jokers/Wilds in Hand and substitutes each for every other card value in a 
	 * natural Deck. Each Hand combination is then stored in an ArrayList of all possible Hands and returned
	 * to the caller.
	 * @param inHands
	 * @param SubCardNo
	 * @return
	 */
	private static ArrayList<Hand> SubstituteHand(ArrayList<Hand> inHands, int SubCardNo) {

		ArrayList<Hand> SubHands = new ArrayList<Hand>();

		for (Hand h : inHands) {
			ArrayList<Card> c = h.getCards();
			if (c.get(SubCardNo).getRank().getRank() == eRank.JOKER.getRank() || c.get(SubCardNo).getWild() == true) {

				for (Card JokerSub : dNonWildDeck.getCards()) {
					ArrayList<Card> SubCards = new ArrayList<Card>();
					SubCards.add(JokerSub); //Adds substitute card for Joker/wild. Will iterate through entire naural deck

					for (int a = 0; a < 5; a++) { //Adds original cards that are not wild/Jokers
						if (SubCardNo != a) {
							SubCards.add(h.getCards().get(a));
						}
					}
					Hand subHand = new Hand(SubCards); //Creating new subHand
					SubHands.add(subHand);
				}
			} else {
				SubHands.add(h); //If no wilds/Jokers, subHands is an ArrayList of 1 element, the original Hand
			}
		}
		return SubHands;
	}

	/**
	 * Evaluates every hand in the ArrayList and determines the one with the highest Hand Strength. 
	 * The highest strength hand becomes the new Hand
	 * @param h
	 * @return
	 */
	public static Hand EvalHand(Hand h) {
		ArrayList<Hand> EvalHands = ExplodeHands(h);

		for (Hand EvalHand : EvalHands) {
			EvalHand.EvalHand();
		}

		Collections.sort(EvalHands, Hand.HandRank);

		return EvalHands.get(0);

	}
	
	/**
	 * Same method as above but already takes in an ArrayList of hands
	 * @param hands
	 * @return
	 */
	public static Hand EvalHand(ArrayList<Hand> hands)
	{
		for (Hand EvalHand: hands)
		{
			EvalHand.EvalHand();
		}
		
		Collections.sort(hands, Hand.HandRank);

		return hands.get(0);
		
	}
	
	/**
	 * Evaluates hand strength of Hand
	 */
	private void EvalHand() {

		// Evaluates if the hand is a flush and/or straight then figures out
		// the hand's strength attributes

		ArrayList<Card> remainingCards = new ArrayList<Card>();

		// Sort the cards!
		Collections.sort(CardsInHand, Card.CardRank);

		// Ace Evaluation
		if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == eRank.ACE) {
			Ace = true;
		}

		// Flush Evaluation
		if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand.get(eCardNo.SecondCard.getCardNo())
				.getSuit()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
						.get(eCardNo.ThirdCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
						.get(eCardNo.FourthCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
						.get(eCardNo.FifthCard.getCardNo()).getSuit()) {
			Flush = true;
		} else {
			Flush = false;
		}

		// Straight Evaluation
		if (Ace) {
			// Looks for Ace, King, Queen, Jack, 10
			if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank() == eRank.KING
					&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == eRank.QUEEN
					&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank() == eRank.JACK
					&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TEN) {
				Straight = true;
				// Looks for Ace, 2, 3, 4, 5
			} else if (CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TWO
					&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank() == eRank.THREE
					&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == eRank.FOUR
					&& CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank() == eRank.FIVE) {
				Straight = true;
			} else {
				Straight = false;
			}
			// Looks for straight without Ace
		} else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
				.getRank() == CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank() + 1
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank() == CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank() + 2
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank() == CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank() + 3
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank() == CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank() + 4) {
			Straight = true;
		} else {
			Straight = false;
		}

		// Evaluates the hand type
		if (Straight == true && Flush == true && CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TEN
				&& Ace) {
			ScoreHand(eHandStrength.RoyalFlush, 0, 0, null);
		}

		// Straight Flush
		else if (Straight == true && Flush == true) {
			remainingCards = null;
			ScoreHand(eHandStrength.StraightFlush, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
					0, remainingCards);
		}

		// five of a Kind

		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.FifthCard.getCardNo()).getRank()) {
			remainingCards = null;
			ScoreHand(eHandStrength.FiveOfAKind, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(), 0,
					remainingCards);
		}

		// Four of a Kind

		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.SecondCard.getCardNo()).getRank()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
						.get(eCardNo.ThirdCard.getCardNo()).getRank()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
						.get(eCardNo.FourthCard.getCardNo()).getRank()) {

			remainingCards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));
			ScoreHand(eHandStrength.FourOfAKind, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(), 0,
					remainingCards);
		}

		else if (CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.SecondCard.getCardNo()).getRank()
				&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == CardsInHand
						.get(eCardNo.ThirdCard.getCardNo()).getRank()
				&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == CardsInHand
						.get(eCardNo.FourthCard.getCardNo()).getRank()) {

			remainingCards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
			ScoreHand(eHandStrength.FourOfAKind, CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank(), 0,
					remainingCards);
		}

		// Full House
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.ThirdCard.getCardNo()).getRank()
				&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank() == CardsInHand
						.get(eCardNo.FifthCard.getCardNo()).getRank()) {
			remainingCards = null;
			ScoreHand(eHandStrength.FullHouse, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
					CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank(), remainingCards);
		}

		else if (CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.FifthCard.getCardNo()).getRank()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
						.get(eCardNo.SecondCard.getCardNo()).getRank()) {
			remainingCards = null;
			ScoreHand(eHandStrength.FullHouse, CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank(),
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(), remainingCards);
		}

		// Flush
		else if (Flush) {
			remainingCards = null;
			ScoreHand(eHandStrength.Flush, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(), 0,
					remainingCards);
		}

		// Straight
		else if (Straight) {
			remainingCards = null;
			ScoreHand(eHandStrength.Straight, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(), 0,
					remainingCards);
		}

		// Three of a Kind
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.ThirdCard.getCardNo()).getRank()) {

			remainingCards.add(CardsInHand.get(eCardNo.FourthCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));
			ScoreHand(eHandStrength.ThreeOfAKind, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(), 0,
					remainingCards);
		}

		else if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.FourthCard.getCardNo()).getRank()) {
			remainingCards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));

			ScoreHand(eHandStrength.ThreeOfAKind, CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank(),
					0, remainingCards);
		} else if (CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.FifthCard.getCardNo()).getRank()) {
			remainingCards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.SecondCard.getCardNo()));
			ScoreHand(eHandStrength.ThreeOfAKind, CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank(), 0,
					remainingCards);
		}

		// Two Pair
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.SecondCard.getCardNo()).getRank()
				&& (CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == CardsInHand
						.get(eCardNo.FourthCard.getCardNo()).getRank())) {

			remainingCards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));

			ScoreHand(eHandStrength.TwoPair, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
					CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank(), remainingCards);
		} else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.SecondCard.getCardNo()).getRank()
				&& (CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank() == CardsInHand
						.get(eCardNo.FifthCard.getCardNo()).getRank())) {

			remainingCards.add(CardsInHand.get(eCardNo.ThirdCard.getCardNo()));

			ScoreHand(eHandStrength.TwoPair, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
					CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank(), remainingCards);
		} else if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.ThirdCard.getCardNo()).getRank()
				&& (CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank() == CardsInHand
						.get(eCardNo.FifthCard.getCardNo()).getRank())) {

			remainingCards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
			ScoreHand(eHandStrength.TwoPair, CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank(),
					CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank(), remainingCards);
		}

		// Pair
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.SecondCard.getCardNo()).getRank()) {

			remainingCards.add(CardsInHand.get(eCardNo.ThirdCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.FourthCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));
			ScoreHand(eHandStrength.Pair, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(), 0,
					remainingCards);
		} else if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.ThirdCard.getCardNo()).getRank()) {
			remainingCards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.FourthCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));
			ScoreHand(eHandStrength.Pair, CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank(), 0,
					remainingCards);
		} else if (CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.FourthCard.getCardNo()).getRank()) {

			remainingCards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.SecondCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));

			ScoreHand(eHandStrength.Pair, CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank(), 0,
					remainingCards);
		} else if (CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.FifthCard.getCardNo()).getRank()) {

			remainingCards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.SecondCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.ThirdCard.getCardNo()));

			ScoreHand(eHandStrength.Pair, CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank(), 0,
					remainingCards);
		}

		else {
			remainingCards.add(CardsInHand.get(eCardNo.SecondCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.ThirdCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.FourthCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));

			ScoreHand(eHandStrength.HighCard, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(), 0,
					remainingCards);
		}
	}

	/**
	 * Scores all elements of Hand
	 * @param hST
	 * @param HiHand
	 * @param LoHand
	 * @param kickers
	 */
	private void ScoreHand(eHandStrength hST, int HiHand, int LoHand, ArrayList<Card> kickers) {
		this.HandStrength = hST.getHandStrength();
		this.HiHand = HiHand;
		this.LoHand = LoHand;
		this.Kickers = kickers;
		this.bScored = true;

	}

	/**
	 * Custom sort to figure the best hand in an array of hands
	 */
	public static Comparator<Hand> HandRank = new Comparator<Hand>() {

		public int compare(Hand h1, Hand h2) {

			int result = 0;

			result = h2.getHandStrength() - h1.getHandStrength();

			if (result != 0) {
				return result;
			}

			result = h2.getHighPairStrength() - h1.getHighPairStrength();
			if (result != 0) {
				return result;
			}

			result = h2.getLowPairStrength() - h1.getLowPairStrength();
			if (result != 0) {
				return result;
			}

			
			if ((h2.Kickers == null) || (h1.Kickers == null))
			{
				return 0;
			}
			

			try
			{
				if (h2.Kickers.size() >= eCardNo.FirstCard.getCardNo() +1 )
				{
					if (h1.Kickers.size() >= eCardNo.FirstCard.getCardNo() +1)
					{
						result = h2.getKicker().get(eCardNo.FirstCard.getCardNo()).getRank().getRank() - h1.getKicker().get(eCardNo.FirstCard.getCardNo()).getRank().getRank();
					}
					if (result != 0)
					{
						return result;
					}
				}				
			}
			catch (Exception e)
			{				
				System.out.println(e.getMessage());
				throw new RuntimeException(e);
			}			

			
			try
			{
				if (h2.Kickers.size() >= eCardNo.SecondCard.getCardNo() +1 )
				{
					if (h1.Kickers.size() >= eCardNo.SecondCard.getCardNo() +1)
					{
						result = h2.getKicker().get(eCardNo.SecondCard.getCardNo()).getRank().getRank() - h1.getKicker().get(eCardNo.SecondCard.getCardNo()).getRank().getRank();
					}
					if (result != 0)
					{
						return result;
					}
				}				
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				throw new RuntimeException(e);				
			}			
			
			try
			{
				if (h2.Kickers.size() >= eCardNo.ThirdCard.getCardNo() +1 )
				{
					if (h1.Kickers.size() >= eCardNo.ThirdCard.getCardNo() +1)
					{
						result = h2.getKicker().get(eCardNo.ThirdCard.getCardNo()).getRank().getRank() - h1.getKicker().get(eCardNo.ThirdCard.getCardNo()).getRank().getRank();
					}
					if (result != 0)
					{
						return result;
					}
				}				
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				throw new RuntimeException(e);
			}
			
			try
			{
				if (h2.Kickers.size() >= eCardNo.FourthCard.getCardNo() +1 )
				{
					if (h1.Kickers.size() >= eCardNo.FourthCard.getCardNo() +1)
					{
						result = h2.getKicker().get(eCardNo.FourthCard.getCardNo()).getRank().getRank() - h1.getKicker().get(eCardNo.FourthCard.getCardNo()).getRank().getRank();
					}
					if (result != 0)
					{
						return result;
					}
				}				
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				throw new RuntimeException(e);				
			}
			
			return 0;
		}
	};

	
	
}
