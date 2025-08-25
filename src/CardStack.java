/* Jackson Oravetz
 * Dr. Thomas
 * EECS 2500
 * 10/6/2023
 * The goal of this code is to take an input of an array of cards, some flipped upside and some flipped rigtside up. 
 * A sequence of flips are performed until the cards are in a single stack. The cards that are upside in the final stack
 * are then outputted bottom to top. This code utilizes a stack class and an LLNode class in order to implement
 * a linked list stack. One of these stacks will be used for each index of the arrays of cards. The top, pop, and push 
 * methods of the LinkedStack are utilized to complete the flipping of the cards. The code will cease function if a
 * negative row/column is detected, if a card string is invalid length, or if a character of the card string is invalid.
 * A top flip consists of taking each card from the top row */
import java.util.Scanner;

public class CardStack
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);		//open scanner
		int testNum = 1;			//var to keep track of # of test runs
		int numCards = 1;			//var for total cards, initialized to one for sake of do while
		//do while to loop code for each iteration, breaks when outer breaks
		outer: do {
			int n = input.nextInt();		//number of rows
			int m = input.nextInt();		//number of columns
			
			if (n <= 0 || m <= 0)			//ends program if n or m is 0 or less
				break outer;
			
			int numFlips = n + m - 2;		//total number of flips
			numCards = n * m;				//rows * columns = total number of cards
			String flips = "";				//String declaration
			
			LinkedStack<Integer>[][] stacks = new LinkedStack[n][m];		//Create an array
																			//of Linked Stacks
			String[][] cardInputs = new String[n][m];		//Create array to take str input
					
			for (int i = 0; i < stacks.length; i++ )		//Create a new stack for each index
			{												//of the LinkedStack Array
			for (int j = 0; j < stacks[i].length; j++)		//and take corresponding str input					
				{
					stacks[i][j] = new LinkedStack<Integer>();
					cardInputs[i][j] = input.next();
				}
			} 
			
			//Code for if there is only one card input, 
			//if its face down print it, otherwise don't print a str
			if (numCards == 1)
			{
				if (cardInputs[0][0].charAt(0) == 'D')
					System.out.print("Test " + testNum + ": " + cardInputs[0][0].charAt(1) + "" + cardInputs[0][0].charAt(2));
				else if (cardInputs[0][0].charAt(0) == 'U')
					System.out.print("Test " + testNum + ":");
			}
			
			//if there is any flips, take the str input
			if (numFlips > 0)
			{
				flips = input.next(); 	//take flip input
			}
							
					
			//push converted string onto corresponding stack
			for (int i = 0; i < stacks.length; i++)
			{
				for (int j = 0; j < stacks[i].length; j++)
				{
					if (cardInputs[i][j].length() != 3)					//if string isnt length 3
						break outer;
					else if (cardConverterInt(cardInputs[i][j]) == 0)	//or if converter returns zero, break the loop
						break outer;
					else												//otherwise push int onto stack
						stacks[i][j].push(cardConverterInt(cardInputs[i][j]));
				}
			}
			
			if (testNum == 1)		//formatting
				System.out.println();
			
			//call the flip method if there are flips
			if (numFlips > 0)
			{
				flipCall(stacks, flips, numFlips, numCards, testNum);
			}
				
			
			testNum++;		//increment the testNum
			
			
			System.out.println();	//formatting
		}	
		while (numCards != 0);		//do runs if number of cards isn't zero
	}
	
	//Method to change the cards string into a corresponding integer
	public static int cardConverterInt(String card)
	{
		int cardVal = 0;		//Variable to keep track of cards value
		
		//Adds a number 1-13 to cardVal depending on the 3rd letter/num in string
		if (card.charAt(2) == 'A' || card.charAt(2) == 'a')
				cardVal += 1;
		else if (card.charAt(2) == '2')
			cardVal += 2;
		else if (card.charAt(2) == '3')
			cardVal += 3;
		else if (card.charAt(2) == '4')
			cardVal += 4;
		else if (card.charAt(2) == '5')
			cardVal += 5;
		else if (card.charAt(2) == '6')
			cardVal += 6;
		else if (card.charAt(2) == '7')
			cardVal += 7;
		else if (card.charAt(2) == '8')
			cardVal += 8;
		else if (card.charAt(2) == '9')
			cardVal += 9;
		else if (card.charAt(2) == 'T' || card.charAt(2) == 't')
			cardVal += 10;
		else if (card.charAt(2) == 'J' || card.charAt(2) == 'j')
			cardVal += 11;
		else if (card.charAt(2) == 'Q' || card.charAt(2) == 'q')
			cardVal += 12;
		else if (card.charAt(2) == 'K' || card.charAt(2) == 'k')
			cardVal += 13;
		else					//return zero if none are true
			return 0;
		
		//adds to cardVal depending on second letter of string
		if (card.charAt(1) == 'S' || card.charAt(1) == 's')
			cardVal = cardVal;
		else if (card.charAt(1) == 'C' || card.charAt(1) == 'c')
			cardVal += 13;
		else if (card.charAt(1) == 'D' || card.charAt(1) == 'd')
			cardVal += 26;
		else if (card.charAt(1) == 'H' || card.charAt(1) == 'h')
			cardVal += 39;
		else
			return 0;			//return zero if none are true
		
		//multiplies cardVal by negative one if card is upside down 
		if (card.charAt(0) == 'D' || card.charAt(0) == 'd' )
			cardVal *= -1;
		else if (card.charAt(0) == 'U' || card.charAt(0) == 'u')
			cardVal *= 1;
		else
			return 0;			//return zero if none are true
		
		return cardVal;			//returns cardVal to caller
		
	}
	
	//Method to convert string back to int
	public static String cardConverterString(double cardInt)
	{
		String cardString = "";	//var for string return
		
		if (cardInt < 0)		//if int is negative, add D, if positive, add U
			cardString += 'D';
		else if (cardInt > 0) 
			cardString += 'U';
		
		//divide abs(int) by thirteen and assign suit based on range of solution
		if (Math.abs(cardInt) / 13 <= 1)
			cardString += 'S';
		else if (Math.abs(cardInt) / 13 > 1 && Math.abs(cardInt) / 13 <= 2)
			cardString += 'C';
		else if (Math.abs(cardInt) / 13 > 2 && Math.abs(cardInt) / 13 <= 3)
			cardString += 'D';
		else if (Math.abs(cardInt) / 13 > 3 && Math.abs(cardInt) / 13 <= 4)
			cardString += 'H';
		
		//find the card through rigorous testing of checking the ints
		if (Math.abs(cardInt) == 1 || Math.abs(cardInt) == 14 || Math.abs(cardInt) == 27 || Math.abs(cardInt) == 40)
			cardString += 'A';
		else if (Math.abs(cardInt) == 2 || Math.abs(cardInt) == 15 || Math.abs(cardInt) == 28 || Math.abs(cardInt) == 41)
			cardString += '2';
		else if (Math.abs(cardInt) == 3 || Math.abs(cardInt) == 16 || Math.abs(cardInt) == 29 || Math.abs(cardInt) == 42)
			cardString += '3';
		else if (Math.abs(cardInt) == 4 || Math.abs(cardInt) == 17 || Math.abs(cardInt) == 30 || Math.abs(cardInt) == 43)
			cardString += '4';
		else if (Math.abs(cardInt) == 5 || Math.abs(cardInt) == 18 || Math.abs(cardInt) == 31 || Math.abs(cardInt) == 44)
			cardString += '5';
		else if (Math.abs(cardInt) == 6 || Math.abs(cardInt) == 19 || Math.abs(cardInt) == 32 || Math.abs(cardInt) == 45)
			cardString += '6';
		else if (Math.abs(cardInt) == 7 || Math.abs(cardInt) == 20 || Math.abs(cardInt) == 33 || Math.abs(cardInt) == 46)
			cardString += '7';
		else if (Math.abs(cardInt) == 8 || Math.abs(cardInt) == 21 || Math.abs(cardInt) == 34 || Math.abs(cardInt) == 47)
			cardString += '8';
		else if (Math.abs(cardInt) == 9 || Math.abs(cardInt) == 22 || Math.abs(cardInt) == 35 || Math.abs(cardInt) == 48)
			cardString += '9';
		else if (Math.abs(cardInt) == 10 || Math.abs(cardInt) == 23 || Math.abs(cardInt) == 36 || Math.abs(cardInt) == 49)
			cardString += 'T';
		else if (Math.abs(cardInt) == 11 || Math.abs(cardInt) == 24 || Math.abs(cardInt) == 37 || Math.abs(cardInt) == 50)
			cardString += 'J';
		else if (Math.abs(cardInt) == 12 || Math.abs(cardInt) == 25 || Math.abs(cardInt) == 38 || Math.abs(cardInt) == 51)
			cardString += 'Q';
		else if (Math.abs(cardInt) == 13 || Math.abs(cardInt) == 26 || Math.abs(cardInt) == 39 || Math.abs(cardInt) == 52)
			cardString += 'K';
		
		return cardString;		//returns solution
	}
	
	//method to call flips and print final output
	public static void flipCall(LinkedStack<Integer>[][] stacks, String flips, int numFlips, int numCards, int testNum)
	{
		String[] cardStack = new String[numCards];	//normal String array to save cards of final stack
		int temp = 0;
		
		//loop for every flip
		for (int i = 0; i < numFlips; i++)
		{
			char direction = flips.charAt(i);		 //look at each flip letter, 
			if (direction == 'T' || direction == 't')//send it to corresponding flip method
				topFlip(stacks);
			if (direction == 'L' || direction == 'l')
				leftFlip(stacks);
			if (direction == 'B' || direction == 'b')
				bottomFlip(stacks);
			if (direction == 'R' || direction == 'r')
				rightFlip(stacks);
		}
		
		//if the stack is not empty, top it, convert the top, 
		//add it to the cardstack, pop the stack and increment temp
		for (int i = 0; i < stacks.length; i++)
		{
			for (int j = 0; j < stacks[i].length; j++)
			{
				if (stacks[i][j].isEmpty() == false)
				{
					while (stacks[i][j].isEmpty() == false)
					{
						int cardInt = stacks[i][j].top();
						cardStack[temp] = cardConverterString(cardInt);
						stacks[i][j].pop();
						temp++;
					}
				}
			}
		}
		
		//Print the test #:, then print the suit and number of each card
		System.out.print("Test " + testNum + ": ");
		for (int i = cardStack.length - 1; i >= 0; i--)
		{
			if (cardStack[i].charAt(0) == 'D')
				System.out.print(cardStack[i].charAt(1) + "" + cardStack[i].charAt(2) + " ");
		}
		
	}
	
	//method to flip card over
	public static int flip(int cardVal)
	{
		return cardVal * -1;
	}
	
	//method to complete a top flip
	public static void topFlip(LinkedStack<Integer>[][] stacks)
	{
		int topRow = 0;
		//if a not empty stack is found starting at [0][0], save topRow and break the loop
		outer: for (int i = 0; i < stacks.length; i++)
		{
			for (int j = 0; j < stacks[i].length; j++)
			{
				if (stacks[i][j].isEmpty() == false)
				{
					topRow = i;
					break outer;
				}
					
			}
		}
		
		//if a not empty stack in the topRow is found, pop, flip, 
		//and push all values to the row below
		for (int i = 0; i < stacks[0].length; i++)
		{
			if (stacks[topRow][i].isEmpty() == false)
			{
				while (stacks[topRow][i].isEmpty() == false)
				{
				int card = stacks[topRow][i].top();
				card = flip(card);
				stacks[topRow][i].pop();
				stacks[topRow + 1][i].push(card);
				}
			}
		}
	}
	
	//method for leftFlip
	public static void leftFlip(LinkedStack<Integer>[][] stacks)
	{
		int leftCol = 0;
		//if a not empty stack is found starting at [0][0], save leftCol and break the loop
		outer: for (int i = 0; i < stacks.length; i++)
		{
			for (int j = 0; j < stacks[i].length; j++)
			{
				if (stacks[i][j].isEmpty() == false)
				{
					leftCol = j;
					break outer;
				}
			}
		}
		
		//if a not empty stack is found, pop, 
		//flip, and push to the col to the right
		for (int i = 0; i < stacks.length; i++)
		{
			if (stacks[i][leftCol].isEmpty() == false)
			{
				while (stacks[i][leftCol].isEmpty() == false)
				{
				int card = stacks[i][leftCol].top();
				card = flip(card);
				stacks[i][leftCol].pop();
				stacks[i][leftCol + 1].push(card);
				}
			}
		}
	}
	
	//method for bottomFlip
	public static void bottomFlip(LinkedStack<Integer>[][] stacks)
	{
		int bottomRow = 0;
		////if a not empty stack is found starting at last indexes, save bottomRow and break the loop
		outer: for (int i = stacks.length - 1; i >= 0; i--)
		{
			for (int j = stacks[i].length - 1; j >= 0; j--)
			{
				if (stacks[i][j].isEmpty() == false)
				{
				bottomRow = i;
				break outer;
				}
			}
		}
		
		//if a not empty stack is found, pop,
		//flip, and push onto stack above
		for (int i = 0; i < stacks[0].length; i++)
		{
			if (stacks[bottomRow][i].isEmpty() == false)
			{
				while (stacks[bottomRow][i].isEmpty() == false)
				{
					int card = stacks[bottomRow][i].top();
					card = flip(card);
					stacks[bottomRow][i].pop();
					stacks[bottomRow - 1][i].push(card);
				}
			}
		}
		
	}
	
	//method for rightFlip
	public static void rightFlip(LinkedStack<Integer>[][] stacks)
	{
		int rightCol = 0;
		//if a not empty stack is found starting at last indexes, save rightCol and break the loop
		outer: for (int i = stacks.length - 1; i >= 0; i--)
		{
			for (int j = stacks[i].length - 1; j >= 0; j--)
			{
				if (stacks[i][j].isEmpty() == false)
				{
					rightCol = j;
					break outer;
				}
			}
		}
		
		//if a not empty stack is found, pop
		//flip, and push to the right
		for (int i = 0; i < stacks.length; i++)
		{
			if (stacks[i][rightCol].isEmpty() == false)
			{
				while (stacks[i][rightCol].isEmpty() == false)
				{
					int card = stacks[i][rightCol].top();
					card = flip(card);
					stacks[i][rightCol].pop();
					stacks[i][rightCol - 1].push(card);
				}
			}
		}
	}
	
}
