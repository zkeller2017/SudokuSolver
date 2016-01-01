import java.io.*;
import java.util.Scanner;
/**
	The GameBoard class creates and sets up the Sudoku GameBoard.
	It can also modify the board. It should be able to provide functions
	helpful to solving the board, but those are not working.
	@author Zachary Keller
	@version final

*/
public class GameBoard

{
	/**
		The 2D list that contains the actual values for the board
	*/
	private int[][] board;

	/**
		The Constructor for the GameBoard class initializes board by
		taking in a file withe the board information, and turning it into a
		2D array of ints.
		
	*/
	public GameBoard()
	{
		String pathname = "SudokuBoard.csv";
		File file = new File(pathname);	
		Scanner input = null;
		try
		{
			input = new Scanner(file);
		}
		catch (FileNotFoundException ex)
		{
			System.out.println(" Cannot open " + pathname );
			System.exit(1);
		}
		int index = 0;
		int[][] tempBoard = new int[9][9];
		String[][] tempString = new String[9][9];
		while( input.hasNextLine() )
		{
			String s = input.nextLine();
			tempString[index] = s.split(",");
			index += 1;
		}
		for (int i = 0; i < tempString.length; i++)
		{
			for (int k = 0; k < tempString[i].length; k++)
			{
				tempBoard[i][k] = Integer.parseInt(tempString[i][k]);
			}
		}
		board = tempBoard;
	}
	
	/**
		Copy Constructor for the GameBoard Class
		@param other The GameBoard to be copied
	*/
	public GameBoard(GameBoard other)
	{
		board= new int[9][9];
		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[i].length; j++)
			{
				board[i][j] = other.board[i][j];
			}
		}
	}
	/**
		Inserts a number at a given spot on the board
		@param row The Row for the number
		@param col The Column for the number
		@param num The Number to be added	
	*/
	public void add(int row, int col, int num)
	{
		board[row][col] = num;
	}
	/**
		Finds all the numbers that could possibly go in a spot. It does this
		by starting with a Vector of numbers 1-9, and then removing them
		when they conflict with spots already on the board
		@param row The Row of the spot being checked
		@param col The Column of the spot being checked
	*/	
	public Vector<Integer> checkSpot(int row, int col)
	{
		Vector<Integer> poss = new Vector<Integer>(9);
		int spot = 0;
		for (int a = 0; a < 9; a++)
		{
			poss.add(a + 1);
		}
		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[i].length; j++)
			{				
				spot = board[i][j];
				//System.out.println("i: " + i + " row: " + row);
				if (i == row || j == col || (row/3 == i /3 && col/3 == j/3))
				{
					System.out.println("i: " + i + " row: " + row + "j: " + j + " col: " + col + "spot: " + spot);
					System.out.println(this);
					if ((row != i || col != j) && spot != 0)
					{
						//System.out.println("spot" + spot);
						poss.remove(new Integer(spot));
						System.out.println("poss " + poss);
					}
				}
			}
		}
		return poss;
	}
	/**
		Finds the spot on the game board that has the fewest number of
		options for numbers to go there
		@return An array that contains the row and column of the most constrained spot
	*/
	public int[] findMostConstrained()
	{
		int most = 9;
		int[] rowColMost = new int[2];
		for (int a = 0; a < board.length; a++)
		{
			for (int b = 0; b < board[a].length; b++)
			{
				if (board[a][b] == 0)
				{
					Vector<Integer> place = checkSpot(a,b);
					System.out.println("place size  " + place.size());
					if (place.size() < most && place.size() > 0)
					{
						rowColMost[0] = a; 
						rowColMost[1] = b;
						most = place.size();
					}
				}
			}
		}
		
		//System.out.println("row: " + rowColMost[0]);
		//System.out.println("col: " + rowColMost[1]);
		//System.out.println(this);
		return rowColMost;
	}
	/**
		Checks if the sudoku board has been finished
		@precondition There will never be spots filled with incorrect numbers
		@return whether or not the board is completed
	*/
	public boolean checkWinner()
	{
		for (int a = 0; a < board.length; a++)
		{
			for (int b = 0; b < board[a].length; b++)
			{
				if (board[a][b] == 0)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	/**
		Returns a string representation of the board
		@return A string representation of the board
	*/
	public String toString()
	{
		String s = "";
		for (int a = 0; a < 41; a++)
		{
			s += "-";
		}
		s += "\n";
		for (int a = 0; a < 41; a++)
		{
			s += "-";
		}
		s += "\n";
		for (int i = 0; i < board.length; i++)
		{
			for (int k = 0; k < board[i].length; k++)
			{
				if (k % 3 == 0)
					s += "|";
				s+= "|";
				s+= " ";
				if (board[i][k] > 0)
					s += board[i][k];
				else
					s += " ";
				s+= " ";
				
			}
			s += "||";
			s += "\n";
			for (int a = 0; a < 41; a++)
			{
				s += "-";
			}
			if (i % 3 == 2)
			{
				s += "\n";
				for (int a = 0; a < 41; a++)
				{
					s += "-";
				}
			}
			s += "\n";
		}
		return s;
	}
}