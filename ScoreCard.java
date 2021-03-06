/*
 * A class that represents a score card of a game of Fortnite Yahtzee.
 * Implemented using HashMap.
 * 
 * CPSC224-02, Spring 2018
 * Final Assignment
 * 
 * @authors Isak Bjornson, Kevin Shaw, & Cole deSilva
 * @version v1.0
 */

import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextArea;

public class ScoreCard
{
	//HashMap score card implementation.
	private Map<Integer, Integer> scoreCard;
	
	//Totals.
	private int upperTotal;
	private int lowerTotal;
	private int grandTotal;

	//Local variable to store the number of sides on the dice.
	private static final int diceSide = 9;
	private static final int diceNum = 5;
	
	/*
	 * Constructor for a score card.
	 * 
	 * @diceSides The specified number of sides of dice that come from the config file
	 */
	public ScoreCard()
	{
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
		
		if(total >= 110)
		{
			total += 40;
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
	 * Sets values for the upper score card for the game of Yahtzee.
	 * 
	 * @theHand The hand the player has in the game.
	 */
	public void upperScores(Hand theHand, ScoreCard tmpCard)
	{
		int total = 0;
		int i = 0;
		for (int dieValue = 1; dieValue <= diceSide; dieValue++, i++)
        {
            int currentCount = 0;
            for (int diePosition = 0; diePosition < diceNum; diePosition++)
            {
                if (theHand.get(diePosition).getRoll() == dieValue)
                {
                    currentCount++;
                }
            }
            
            	tmpCard.set(i, (dieValue * currentCount));
            	total += (dieValue * currentCount);
        }
		
		if(total >= 110)
		{
			tmpCard.setUpper(total + 40);
		}
		else
		{
			tmpCard.setUpper(total);
		}
	}
	
	/*
	 * Sets values for the lower score card for the game of Yahtzee.
	 * 
	 * @theHand The hand the player has in the game.
	 */
	public void lowerScores(Hand theHand, ScoreCard tmpCard)
	{
		int total = 0;
		if(theHand.maxOfAKindFound() >= 3)
		{
			tmpCard.set(diceSide, theHand.totalAllDice() + 10);
			total += 10;
		}
		
		if(theHand.maxOfAKindFound() >= 4)
		{
			tmpCard.set(diceSide + 1, theHand.totalAllDice() + 20);
			total += 20;
		}
		
		if(theHand.fullHouseFound())
		{
			tmpCard.set(diceSide + 2, 25);
			total += 25;
		}
		
		if(theHand.versatileVictor())
		{
			tmpCard.set(diceSide + 3, 30);
			total += 30;
		}
		
		if(theHand.havingABlast())
		{
			tmpCard.set(diceSide + 4, 35);
			total += 35;
		}
		
		if(theHand.masterfulMarksman())
		{
			tmpCard.set(diceSide + 5, 40);
			total += 40;
		}
		
		if(theHand.runninAndGunnin())
		{
			tmpCard.set(diceSide + 6, 40);
			total += 40;
		}
		
		if(theHand.maxOfAKindFound() >= 5)
		{
			tmpCard.set(diceSide + 7, 50);
			total += 50;
		}
		tmpCard.setLower(total);
	}
	
	/*
	 * Prints out the score card with totals (upper, lower, and grand).
	 */
	public void printComplete(JTextArea area)
	{
		for(int i = 0; i < scoreCard.size(); i++)
		{
			if(i < diceSide)
			{
				switch(i)
				{
					case 1:
						area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the PISTOL line\n");
						break;
					case 2:
						area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the CROSSBOW line\n");
						break;
					case 3:
						area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the MINIGUN line\n");
						break;
					case 4:
						area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the SUBMACHINEGUN line\n");
						break;
					case 5:
						area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the GRENADELAUNCHER line\n");
						break;
					case 6:
						area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the SHOTGUN line\n");
						break;
					case 7:
						area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the SNIPER line\n");
						break;
					case 8:
						area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the ROCKETLAUNCHER line\n");
						break;
					case 9:
						area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the ASSAULTRIFLE line\n");
						break;
				}
			}
			else if(i >= diceSide)
			{
				if(i == diceSide)
				{
					area.append("Total of the upper score card is: " + upperTotal+"\n");
					area.append("\n");
					area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the 3 of a kind line\n");
				}
				else if(i == diceSide + 1)
				{
					area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the 4 of a kind line\n");
				}
				else if(i == diceSide + 2)
				{
					area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the Full House line\n");
				}
				else if(i == diceSide + 3)
				{
					area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the \"Versatile Victor\" line\n");
				}
				else if(i == diceSide + 4)
				{
					area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the \"Having a Blast\" line\n");
				}
				else if(i == diceSide + 5)
				{		
					area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the \"Masterful Marksman\" line\n");
				}
				else if(i == diceSide + 6)
				{
					area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the \"Runnin' and Gunnin'\" line\n");
				}
				else if(i == diceSide + 7)
				{
					area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the Yahtzee line\n");
				}
			}
		}
		area.append("Total of the lower score card is: " + lowerTotal+"\n");
		area.append("\n");
		area.append("Grand Total is: " + grandTotal+"\n");
		area.append("\n");
		area.append("\n");
	}
	
	//Prints out the score card without totals.
	public void print(JTextArea area)
	{		
		for(int i = 0; i < scoreCard.size(); i++)
		{
			if(i < diceSide)
			{
				switch(i)
				{
					case 0:
						area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the PISTOL line\n");
						break;
					case 1:
						area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the CROSSBOW line\n");
						break;
					case 2:
						area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the MINIGUN line\n");
						break;
					case 3:
						area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the SUBMACHINEGUN line\n");
						break;
					case 4:
						area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the GRENADELAUNCHER line\n");
						break;
					case 5:
						area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the SHOTGUN line\n");
						break;
					case 6:
						area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the SNIPER line\n");
						break;
					case 7:
						area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the ROCKETLAUNCHER line\n");
						break;
					case 8:
						area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the ASSAULTRIFLE line\n");
						break;
				}
			}
			else if(i >= diceSide)
			{
				if(i == diceSide)
				{
					area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the 3 of a kind line\n");
				}
				else if(i == diceSide + 1)
				{
					area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the 4 of a kind line\n");
				}
				else if(i == diceSide + 2)
				{
					area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the Full House line\n");
				}
				else if(i == diceSide + 3)
				{
					area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the \"Versatile Victor\" line\n");
				}
				else if(i == diceSide + 4)
				{
					area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the \"Having a Blast\" line\n");
				}
				else if(i == diceSide + 5)
				{		
					area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the \"Masterful Marksman\" line\n");
				}
				else if(i == diceSide + 6)
				{
					area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the \"Runnin' and Gunnin'\" line\n");
				}
				else if(i == diceSide + 7)
				{
					area.append((i + 1) + ": Score " + scoreCard.get(i) + " on the Yahtzee line\n");
				}
			}
		}
	}
}
