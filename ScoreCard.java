/*
 * A class that represents a score card of a game of Yahtzee.
 * Implemented using HashMap.
 * 
 * CPSC224-02, Spring 2018
 * Programming Assignment #6
 * 
 * @author Cole deSilva
 * @version v2.0 3/22/2018
 */

import java.util.HashMap;
import java.util.Map;

public class ScoreCard
{
	//HashMap score card implementation.
	private Map<Integer, Integer> scoreCard;
	
	//Totals.
	private int upperTotal;
	private int lowerTotal;
	private int grandTotal;

	//Local variable to store the number of sides on the dice.
	private int diceSide;
	
	/*
	 * Constructor for a score card.
	 * 
	 * @diceSides The specified number of sides of dice that come from the config file
	 */
	public ScoreCard(int diceSides)
	{
		diceSide = diceSides;
		scoreCard = new HashMap<Integer, Integer>();
		for(int i = 0; i <= diceSide + 7; i++)
		{
			scoreCard.put(i, 0);
		}
	}
	
	//Checks if the whole score card is completed.
	//I don't think I used it in this implementation.
	public boolean isCompleted()
	{	
		boolean completed = true;
		for(int i = 0; i < scoreCard.size() && completed; i++)
		{
			if(scoreCard.get(i) == 0)
			{
				completed = true;
			}
		}
		return completed;
	}
	
	/*
	 * Sets upper score card total.
	 * 
	 * @total The value to be set as the upper total.
	 */
	public void setUpper(int total)
	{
		upperTotal = total;
	}
	
	/*
	 * Calculates upper total for a card that doesn't get passed through the upperScore() and lowerScore() functions.
	 * 
	 * @returns The total of the upper portion of the score card.
	 */
	public int calculateUpper()
	{
		int total = 0;
		for(int i = 0; i < diceSide; i++)
		{
			total += scoreCard.get(i);
		}
		
		if(total >= 63)
		{
			total += 35;
		}
		return total;
	}
	
	/*
	 * Sets lower score card total.
	 * 
	 * @total The value to be set as the lower total.
	 */
	public void setLower(int total)
	{
		lowerTotal = total;
	}
	
	/*
	 * Calculates lower total for a card that doesn't get passed through the upperScore() and lowerScore() functions.
	 * 
	 * @returns The total of the lower portion of the score card.
	 */
	public int calculateLower()
	{
		int total = 0;
		for(int i = diceSide; i < scoreCard.size(); i++)
		{
			total += scoreCard.get(i);
		}
		return total;
	}
	
	/*
	 * Sets grand total.
	 */
	public void calculateGrand()
	{
		grandTotal = upperTotal + lowerTotal;
	}
	
	/*
	 * Sets a value associated with a certain line on the score card.
	 * 
	 * @scoreRow The line number of the score card.
	 * @value The value to store.
	 */
	public void set(int scoreRow, int value)
	{
		scoreCard.put(scoreRow, value);
	}
	
	/*
	 * Gets a value associated with a certain line on the score card.
	 * 
	 * @key The line number of the score card; The key used to get the value.
	 * 
	 * @returns The value associated with the specified line on the score card.
	 */
	public int get(int key)
	{
		return scoreCard.get(key);
	}
	
	/*
	 * Prints out the score card with totals (upper, lower, and grand).
	 */
	public void printComplete()
	{
		for(int i = 0; i < scoreCard.size(); i++)
		{
			if(i < diceSide)
			{
				System.out.println((i + 1) + ": Score " + scoreCard.get(i) + " on the " + (i + 1) + " line");
			}
			else if(i >= diceSide)
			{
				if(i == diceSide)
				{
					System.out.println("Total of the upper score card is: " + upperTotal);
					System.out.println();
					System.out.println((i + 1) + ": Score " + scoreCard.get(i) + " on the 3 of a kind line");
				}
				else if(i == diceSide + 1)
				{
					System.out.println((i + 1) + ": Score " + scoreCard.get(i) + " on the 4 of a kind line");
				}
				else if(i == diceSide + 2)
				{
					System.out.println((i + 1) + ": Score " + scoreCard.get(i) + " on the Full House line");
				}
				else if(i == diceSide + 3)
				{
					System.out.println((i + 1) + ": Score " + scoreCard.get(i) + " on the Small Striaght line");
				}
				else if(i == diceSide + 4)
				{
					System.out.println((i + 1) + ": Score " + scoreCard.get(i) + " on the Large Straight line");
				}
				else if(i == diceSide + 5)
				{
					System.out.println((i + 1) + ": Score " + scoreCard.get(i) + " on the Yahtzee line");
				}
				else if(i == diceSide + 6)
				{
					System.out.println((i + 1) + ": Score " + scoreCard.get(i) + " on the chance line");
				}
			}
		}
		System.out.println("Total of the lower score card is: " + lowerTotal);
		System.out.println();
		System.out.println("Grand Total is: " + grandTotal);
	}
	
	//Prints out the score card without totals.
	public void print()
	{		
		for(int i = 0; i < scoreCard.size(); i++)
		{
			if(i < diceSide)
			{
				System.out.println((i + 1) + ": Score " + scoreCard.get(i) + " on the " + (i + 1) + " line");
			}
			else if(i >= diceSide)
			{
				if(i == diceSide)
				{
					System.out.println((i + 1) + ": Score " + scoreCard.get(i) + " on the 3 of a kind line");
				}
				else if(i == diceSide + 1)
				{
					System.out.println((i + 1) + ": Score " + scoreCard.get(i) + " on the 4 of a kind line");
				}
				else if(i == diceSide + 2)
				{
					System.out.println((i + 1) + ": Score " + scoreCard.get(i) + " on the Full House line");
				}
				else if(i == diceSide + 3)
				{
					System.out.println((i + 1) + ": Score " + scoreCard.get(i) + " on the Small Striaght line");
				}
				else if(i == diceSide + 4)
				{
					System.out.println((i + 1) + ": Score " + scoreCard.get(i) + " on the Large Straight line");
				}
				else if(i == diceSide + 5)
				{		
					System.out.println((i + 1) + ": Score " + scoreCard.get(i) + " on the Yahtzee line");
				}
				else if(i == diceSide + 6)
				{
					System.out.println((i + 1) + ": Score " + scoreCard.get(i) + " on the chance line");
				}
			}
		}
	}
}