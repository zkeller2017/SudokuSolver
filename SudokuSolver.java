/**
	The Sudoku Solver Class is supposed to solve a given sudoku Board,
	However it is not working. This is why I am punting, after laboring over
	this for a long time. It does, however, print out the set-up board, so run this
	file to see that work.
	@author Zachary Keller
	@version final
*/
public class SudokuSolver
{
	/**
		A Stack of GameBoards that is part of the algorithm to solve a puzzle
	*/
	private Stack<GameBoard> stack;
	
	/**
		The Sudoku Board that is being solved
	*/
	private GameBoard board;

	/**
		The Constructor for the Sudoku Solver Class. Creates a new stack and a new game board
	*/
	public SudokuSolver()
	{
		stack = new LinkedList<GameBoard>();
		board = new GameBoard();
		stack.push(board);
	}
	
	/**
		The poorly named function that is supposed to call the necessary functions
		to solve the board. This does not work and is riddled with both errors and
		print statements to figure out the errors.
	*/
	public void work()
	{
		GameBoard curr = new GameBoard(stack.pop());
		if (curr.checkWinner() == true)
		{
			return;
		}
		int[] mostConstrained = board.findMostConstrained();
		System.out.println("Const" + curr.checkSpot(mostConstrained[0], mostConstrained[1]));
		for (int i = 0; i < curr.checkSpot(mostConstrained[0], mostConstrained[1]).size(); i++)
		{
			GameBoard poss= new GameBoard(curr);
			poss.add(mostConstrained[0], mostConstrained[1], curr.checkSpot(mostConstrained[0], mostConstrained[1]).get(i));
			System.out.println(poss);
			stack.push(poss);
		}
		work();
	}
	
	
	/**
		The main creates new board and prints it out in all its glory.
		@param args	
	*/
	public static void main(String[] args)
	{
		GameBoard game = new GameBoard();
		System.out.println(game);
	}
}