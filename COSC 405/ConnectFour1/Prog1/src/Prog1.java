import java.util.*;

/**
 * 
 * COSC 405-001 Assignment 1 Prog1.java
 *
 * Creates a game and calls display
 *
 * @author Erin Wirfel
 */
public class Prog1 {

	public static void main(String[] args) {
		// create game
		ConnectFour game = new ConnectFour();

		// call display
		game.start();
	}
}

/**
 * 
 * @author Erin
 *
 */
class ConnectFour {

	/**
	 * determine player, create new game.
	 */
	public void start() {
		Scanner input = new Scanner(System.in);
		int option = displayMenu();
		while (option != 0) {
			switch (option) {
			case 1:
				gameBoard playerstart = new gameBoard(1);
				break;

			case 2:
				gameBoard pcstart = new gameBoard(2);

				break;

			default:
				System.out.println("Invalid selection");
			}
			// redisplays menu
			option = displayMenu();
		}
		input.close();

		System.out.println("Done.");
		System.exit(0);
	}

	/**
	 * Displays menu and returns users choice.
	 *
	 * @return return integer of inputed option number
	 */
	private int displayMenu() {
		// displaying menu
		System.out.println();
		System.out.println("New Game");
		System.out.println("Player select, One(1) or Two(2)");
		System.out.println("Zero(0) to exit");
		System.out.println("-------------------------------");
		System.out.print("Enter Number: ");
		Scanner in = new Scanner(System.in);
		int x = in.nextInt();
		return x;
	}

}

/**
 * gameboard class creates 2d array.
 * 
 * @author Erin
 *
 */
class gameBoard {
	Scanner in = new Scanner(System.in);
	int moves = 0;
	char gameboard[][] = new char[6][7];
	boolean first = true;

	/**
	 * initiates empty board initiates first move
	 * 
	 * @param player
	 */
	public gameBoard(int player) {
		for (int i = 0; i < gameboard.length; i++) {
			for (int j = 0; j < gameboard[0].length; j++)
				gameboard[i][j] = '-';
		}
		switch (player) {
		case 1:
			chooseCol();
			break;
		case 2:
			PCMove();
			break;

		}
	}

	/**
	 * players move selection
	 */
	private void chooseCol() {

		System.out.println("Choose Column");
		int col = 0;
		try {
			col = in.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("You may only enter integers 0-6. Try again.");
			in.next();
			chooseCol();
		}

		boolean moved = false;
		if (col < 0 || col > gameboard[0].length - 1) {
			printboard();
			System.out.println("invalid move, try again");

			chooseCol();
		}
		for (int i = gameboard.length - 1; i >= 0; i--) {
			if (gameboard[i][col] == '-') {
				gameboard[i][col] = '\u2022';
				moved = true;
				moves++;
				break;
			}
		}
		if (!moved) {
			printboard();
			System.out.println("invalid move, try again");

			chooseCol();
		}

		printboard();
		checkWinner();
		PCMove();
	}

	/**
	 * PC move selection
	 */
	private void PCMove() {
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		BoardTree<char[][]> smartMove = new BoardTree<char[][]>();

		smartMove.set(gameboard, first);
		first = false;
		int col = smartMove.returnCol();

		boolean moved = false;

		while (gameboard[0][col] != '-') {
			if (col == 0) {
				col = 7;
			}
			col--;
		}

		for (int i = gameboard.length - 1; i >= 0; i--) {
			if (gameboard[i][col] == '-') {
				gameboard[i][col] = 'o';
				moved = true;
				moves++;
				break;
			}
		}

		if (!moved) {

			PCMove();
		}
		System.out.println("The computer's move: Column " + col);

		printboard();
		checkWinner();
		chooseCol();
	}

	/**
	 * Display current gameboard
	 */
	private void printboard() {

		System.out.println();

		for (int i = 0; i < gameboard.length; i++) {
			for (int j = 0; j < gameboard[0].length; j++) {
				System.out.print(gameboard[i][j] + " | ");

			}

			System.out.println();

		}
		System.out.println();
	}

	/**
	 * check current gameboard for winner
	 */
	private void checkWinner() {

		boolean winner = false;
		String winType = "";
		int col = 0;
		int row = 0;
		int counter = 0;
		int current = 0;
		int next = 0;

		if (moves == 42) {
			draw();
		}
		/**
		 * horizontal win
		 */
		while (row < gameboard.length && !winner) {
			counter = 0;
			while (col < gameboard[0].length - 1 && !winner) {
				current = gameboard[row][col];
				next = gameboard[row][col + 1];
				if (current == next && current != '-') {
					counter++;
				} else {
					counter = 0;
				}
				if (counter == 3) {
					winner = true;
					winType = "horizontal";
					win(winType, current);
				}
				col++;
			}
			row++;
			col = 0;
		}

		/**
		 * vertical win
		 */
		row = 0;
		col = 0;
		current = 0;
		next = 0;
		counter = 0;
		while (col < gameboard[0].length && !winner) {
			counter = 0;
			while (row < gameboard.length - 1 && !winner) {
				current = gameboard[row][col];
				next = gameboard[row + 1][col];
				if (current == next && current != '-') {
					counter++;
				} else {
					counter = 0;
				}
				if (counter == 3) {
					winner = true;
					winType = "vertical";
					win(winType, current);
				}
				row++;
			}
			col++;
			row = 0;
		}

		/**
		 * backward win
		 */
		for (int r = 0; r < gameboard.length - 3; r++) {
			for (int c = 0; c < gameboard[1].length - 3; c++) {

				if (gameboard[r][c] != '-' && gameboard[r][c] == gameboard[r + 1][c + 1]
						&& gameboard[r + 1][c + 1] == gameboard[r + 2][c + 2]
						&& gameboard[r + 2][c + 2] == gameboard[r + 3][c + 3]) {
					winner = true;
					winType = "backward diagonal";
					win(winType, gameboard[r][c]);

				}

			}
		}

		/**
		 * forward win
		 * 
		 * 
		 */
		for (int r = 0; r < gameboard.length - 3; r++) {

			for (int c = gameboard[0].length - 1; c > 2; c--) {

				if (gameboard[r][c] != '-' && gameboard[r][c] == gameboard[r + 1][c - 1]
						&& gameboard[r + 1][c - 1] == gameboard[r + 2][c - 2]
						&& gameboard[r + 2][c - 2] == gameboard[r + 3][c - 3]) {
					winner = true;
					winType = "forward diagonal";
					win(winType, gameboard[r][c]);
				}
			}
		}
	}

	/**
	 * alert and start new game if a draw occurs
	 */
	private void draw() {
		System.out.println("Board filled with no winner");
		ConnectFour n = new ConnectFour();
		n.start();
	}

	/**
	 * displays winner information
	 * 
	 * @param winType
	 * @param current
	 */
	private void win(String winType, int current) {
		String w = "";
		if (current == 8226) {
			w = "1 black";
		} else {
			w = "2 white";
		}
		System.out.println(winType + " Winner! player " + w);
		ConnectFour n = new ConnectFour();
		n.start();
	}

}

/**
 * Hill climbing search tree
 * 
 * @author Erin
 *
 * @param <E>
 */
class BoardTree<E> {
	private Node root;
	boolean first = false;

	/**
	 * set root of tree generate children, call to evaluate children
	 * 
	 * @param board
	 * @param first
	 * @return
	 */
	public boolean set(char[][] board, boolean first) {
		if (first) {
			this.first = first;
		}

		this.root = new Node(board);

		root.E1 = new Node(generate(board, 1));
		root.E1.index = 1;
		root.E2 = new Node(generate(board, 2));
		root.E2.index = 2;
		root.E3 = new Node(generate(board, 3));
		root.E3.index = 3;
		root.E4 = new Node(generate(board, 4));
		root.E4.index = 4;
		root.E5 = new Node(generate(board, 5));
		root.E5.index = 5;
		root.E6 = new Node(generate(board, 6));
		root.E6.index = 6;
		root.E7 = new Node(generate(board, 7));
		root.E7.index = 7;

		evaluate(root.E1);
		evaluate(root.E2);
		evaluate(root.E3);
		evaluate(root.E4);
		evaluate(root.E5);
		evaluate(root.E6);
		evaluate(root.E7);
		return true;

	}

	/**
	 * set PC column choice
	 * 
	 * @return
	 */
	public int returnCol() {
		if (first) {

			Random r = new Random();
			int col = r.nextInt(7);
			return col;

		}
		int col = nodeChoice(root) - 1;

		return col;
	}

	/**
	 * determine column index from children's highest heuristic
	 * 
	 * @param node
	 * @return
	 */
	public int nodeChoice(Node node) {
		int max1 = 0;
		int max2 = 0;
		int max3 = 0;
		int max4 = 0;
		int max5 = 0;
		int index1 = 0;
		int index2 = 0;
		int index3 = 0;
		int index4 = 0;
		int index5 = 0;
		Random r = new Random();

		if (root.E1.rating > root.E2.rating) {
			max1 = root.E1.rating;
			index1 = root.E1.index;
		} else {
			max1 = root.E2.rating;
			index1 = root.E2.index;
		}
		if (root.E3.rating > root.E4.rating) {
			max2 = root.E3.rating;
			index2 = root.E3.index;
		} else {
			max2 = root.E4.rating;
			index2 = root.E4.index;
		}
		if (root.E5.rating > root.E6.rating) {
			max3 = root.E5.rating;
			index3 = root.E5.index;
		} else {
			max3 = root.E6.rating;
			index3 = root.E6.index;
		}
		if (max1 > max2) {
			max4 = max1;
			index4 = index1;
		} else {
			max4 = max2;
			index4 = index2;
		}
		if (max3 > root.E7.rating) {
			max5 = max3;
			index5 = index3;
		} else {
			max5 = root.E7.rating;
			index5 = root.E7.index;
		}
		if (max5 == 0 && max4 == 0) {
			int randChoice = r.nextInt(7) + 1;

			return randChoice;
		} else if (max4 > max5) {
			return index4;
		} else {
			return index5;
		}
	}

	/**
	 * generate possibilities for each child
	 * 
	 * @param originalboard
	 * @param x
	 * @return
	 */
	public char[][] generate(char[][] originalboard, int x) {
		char[][] newboard = new char[originalboard.length][originalboard[0].length];
		for (int i = 0; i < originalboard.length; i++) {
			for (int j = 0; j < originalboard[0].length; j++) {
				newboard[i][j] = originalboard[i][j];

			}
		}

		for (int i = newboard.length - 1; i >= 0; i--) {

			if (newboard[i][x - 1] == '-') {
				newboard[i][x - 1] = 'o';

				break;
			}
		}

		return newboard;

	}

	/**
	 * 
	 * Evaluate nodes score from patterns and set rating accordingly
	 * 
	 * @param e1
	 */
public void evaluate(Node node) {
		
		char[][] board=node.currentBoard;
		
	node.rating=0;
	
		/**
		 * vertical eval
		 */
		for (int r = 0; r < board.length - 3; r++) {
			for (int c = 0; c < board[1].length ; c++) {
				
				if (	board[r][c] == 'o' 
						&& board[r+1][c] =='\u2022' 
						&& board[r+2][c] =='\u2022'
						&& board[r+3][c] == '\u2022'
						
						) {
					node.rating+=100;
				}
				
				if (	board[r][c] == 'o' 
						&& board[r+1][c] =='o' 
						&& board[r+2][c] =='o'
						&& board[r+3][c] == 'o'
						
						) {
					node.rating+=2000;
				}

				
				 if (	board[r][c] == '-' 
						&& board[r+1][c] =='o' 
						&& board[r+2][c] =='o'
						&& board[r+3][c] == 'o'
						
						) {
					node.rating+=40;

				}
				 if(board[r][c] == '-' 
						&& board[r+1][c] =='-' 
						&& board[r+2][c ] =='o'
						&& board[r+3][c ] == 'o') {
					node.rating+=20;
					}
				 if(board[r][c] == '-' 
							&& board[r+1][c] =='-' 
							&& board[r+2][c ] =='-'
							&& board[r+3][c ] == 'o') {
						node.rating+=5;
						}}	}
	
		
		/**
		 * Horizontal eval
		 */
		for (int r=0;r<board.length;r++) {
			for (int c=0;c<board[0].length-3;c++) {
				// horizontal defense
				if((board[r][c]=='\u2022'
				&& board[r][c+1]=='\u2022'
				&& board[r][c+2]=='\u2022'
				&& board[r][c+3]=='o'
				
				)
						||
				(board[r][c]=='\u2022'
			  && board[r][c+1]=='\u2022'
			  && board[r][c+2]=='o'
			  && board[r][c+3]=='\u2022'
			  
			  )						
						||
			    (board[r][c]=='\u2022'
			  && board[r][c+1]=='o'
			  && board[r][c+2]=='\u2022'
			  && board[r][c+3]=='\u2022'
			  
			  )							
						||
				(board[r][c]=='o'
			  && board[r][c+1]=='\u2022'
			  && board[r][c+2]=='\u2022'
			  && board[r][c+3]=='\u2022'			  
			  )		)
					 {	node.rating+=500;}	
						
				
							if	((	board[r][c]=='o'
								  && board[r][c+1]=='\u2022'
								  && board[r][c+2]=='\u2022'
								  && board[r][c+3]=='-'	
								)
						||
						(
								board[r][c]=='-'
								  && board[r][c+1]=='\u2022'
								  && board[r][c+2]=='\u2022'
								  && board[r][c+3]=='o'	
								)
						) {	node.rating+=30;		}
				
				
				//winning horizontal
				if(board[r][c]=='o'
						&& board[r][c+1]=='o'
						&& board[r][c+2]=='o'
						&& board[r][c+3]=='o'
						) {
							node.rating+=2000;
						}
				
				//horizontal offense
				if((board[r][c]=='-'
						&& board[r][c+1]=='-'
						&& board[r][c+2]=='o'
						&& board[r][c+3]=='o')||

						(board[r][c]=='-'
						&& board[r][c+1]=='o'
						&& board[r][c+2]=='-'
						&& board[r][c+3]=='o')||
						
						(board[r][c]=='-'
						&& board[r][c+1]=='o'
						&& board[r][c+2]=='o'
						&& board[r][c+3]=='-')||
						
						(board[r][c]=='o'
						&& board[r][c+1]=='-'
						&& board[r][c+2]=='-'
						&& board[r][c+3]=='o')||
						
						(board[r][c]=='o'
						&& board[r][c+1]=='-'
						&& board[r][c+2]=='o'
						&& board[r][c+3]=='-')||
						
						(board[r][c]=='o'
						&& board[r][c+1]=='o'
						&& board[r][c+2]=='-'
						&& board[r][c+3]=='-')
							) {
							node.rating+=20;
						}
				
				if((board[r][c]=='-'
						&& board[r][c+1]=='o'
						&& board[r][c+2]=='o'
						&& board[r][c+3]=='o')||

						(board[r][c]=='o'
						&& board[r][c+1]=='-'
						&& board[r][c+2]=='o'
						&& board[r][c+3]=='o')||
						
						(board[r][c]=='o'
						&& board[r][c+1]=='o'
						&& board[r][c+2]=='-'
						&& board[r][c+3]=='o')||
						
						(board[r][c]=='o'
						&& board[r][c+1]=='o'
						&& board[r][c+2]=='o'
						&& board[r][c+3]=='-')
						) {
							node.rating+=60;
						}
				
				if((board[r][c]=='-'
						&& board[r][c+1]=='-'
						&& board[r][c+2]=='-'
						&& board[r][c+3]=='o')||

						(board[r][c]=='-'
						&& board[r][c+1]=='-'
						&& board[r][c+2]=='o'
						&& board[r][c+3]=='-')||
						
						(board[r][c]=='-'
						&& board[r][c+1]=='o'
						&& board[r][c+2]=='-'
						&& board[r][c+3]=='-')||
						
						(board[r][c]=='o'
						&& board[r][c+1]=='-'
						&& board[r][c+2]=='-'
						&& board[r][c+3]=='-')
						
						
						)
								 {
							node.rating+=10;
						}
				}
		}
		//horizontal defense continued
		for(int r1=0;r1<board.length;r1++) {
			for(int c1=1;c1<board[0].length-3;c1++) {

				if 
						((		board[r1][c1-1]=='-'&&
								board[r1][c1]=='o'
								  && board[r1][c1+1]=='\u2022'
								  && board[r1][c1+2]=='\u2022'
								  && board[r1][c1+3]=='-'	
								)
						||
						(		board[r1][c1-1]=='-'&&
								board[r1][c1]=='-'
								  && board[r1][c1+1]=='\u2022'
								  && board[r1][c1+2]=='\u2022'
								  && board[r1][c1+3]=='o'	
								)
						||
						(		board[r1][c1-1]=='-'&&
						board[r1][c1]=='\u2022'
						  && board[r1][c1+1]=='-'
						  && board[r1][c1+2]=='\u2022'
						  && board[r1][c1+3]=='o'	
						)
						||
						(		board[r1][c1-1]=='-'&&
						board[r1][c1]=='\u2022'
						  && board[r1][c1+1]=='o'
						  && board[r1][c1+2]=='\u2022'
						  && board[r1][c1+3]=='-'	
						)
						||
						(		board[r1][c1-1]=='o'&&
						board[r1][c1]=='\u2022'
						  && board[r1][c1+1]=='-'
						  && board[r1][c1+2]=='\u2022'
						  && board[r1][c1+3]=='-'	
						)
						||
						(		board[r1][c1-1]=='o'&&
						board[r1][c1]=='\u2022'
						  && board[r1][c1+1]=='\u2022'
						  && board[r1][c1+2]=='-'
						  && board[r1][c1+3]=='-'	
						)
						||
						(		board[r1][c1-1]=='-'&&
						board[r1][c1]=='\u2022'
						  && board[r1][c1+1]=='\u2022'
						  && board[r1][c1+2]=='-'
						  && board[r1][c1+3]=='o'	
						)
						||(		board[r1][c1-1]=='-'&&
						board[r1][c1]=='\u2022'
						  && board[r1][c1+1]=='\u2022'
						  && board[r1][c1+2]=='o'
						  && board[r1][c1+3]=='-'	
						)
						) {
					
					node.rating+=75;
				}
				
				
				
			}
		}
		
		
		for (int r=0;r<board.length-1;r++) {
			for (int c=0;c<board[0].length-3;c++) {
		//rows not bottom horizontal precautions 
		if((board[r][c]=='\u2022'
				&& board[r][c+1]=='\u2022'
				&& board[r][c+2]=='\u2022'
				&& board[r][c+3]=='-'
				&& board[r+1][c+3]=='o'
				)
						||
				(board[r][c]=='\u2022'
			  && board[r][c+1]=='\u2022'
			  && board[r][c+2]=='-'
			  && board[r][c+3]=='\u2022'
			  && board[r+1][c+2]=='o')						
						||
			    (board[r][c]=='\u2022'
			  && board[r][c+1]=='-'
			  && board[r][c+2]=='\u2022'
			  && board[r][c+3]=='\u2022'
			  && board[r+1][c+1]=='o')							
						||
				(board[r][c]=='-'
			  && board[r][c+1]=='\u2022'
			  && board[r][c+2]=='\u2022'
			  && board[r][c+3]=='\u2022'
			  && board[r+1][c]=='o')					
						) {
					node.rating-=50;
				}}}
		
		
		/**
		 * backward diagonal eval
		 */
		for (int r = 0; r < board.length - 3; r++) {
			for (int c = 0; c < board[1].length - 3; c++) {

				
				//precautions
				if((board[r][c]=='-'
						&&board[r+1][c+1]=='\u2022'
						&&board[r+2][c+2]=='\u2022'
						&&board[r+3][c+3]=='\u2022'
						&&board[r+1][c]!='-')
						||
						(board[r][c]=='\u2022'
						&&board[r+1][c+1]=='-'
						&&board[r+2][c+2]=='\u2022'
						&&board[r+3][c+3]=='\u2022'
						&&board[r+2][c+1]!='-')
						||
						(board[r][c]=='\u2022'
						&&board[r+1][c+1]=='\u2022'
						&&board[r+2][c+2]=='-'
						&&board[r+3][c+3]=='\u2022'
						&&board[r+3][c+2]!='-')
						||
						(board[r][c]=='\u2022'
						&&board[r+1][c+1]=='\u2022'
						&&board[r+2][c+2]=='\u2022'
						&&board[r+3][c+3]=='-'
						)){
					node.rating-=1500;
				}
				
				
				//winning
				if ( board[r][c] == 'o'
						&& board[r + 1][c + 1]=='o'
						&& board[r + 2][c + 2] == 'o'
						&& board[r + 3][c + 3]=='o') {
					node.rating+=2000;

				}
				
				//backward defense
				if ((board[r][c] == 'o'
						&& board[r + 1][c + 1]=='\u2022'
						&& board[r + 2][c + 2] == '\u2022'
						&& board[r + 3][c + 3]=='\u2022') 
						||
						(board[r][c] == '\u2022'
						&& board[r + 1][c + 1]=='o'
						&& board[r + 2][c + 2] == '\u2022'
						&& board[r + 3][c + 3]=='\u2022') 
						||
						(board[r][c] == '\u2022'
						&& board[r + 1][c + 1]=='\u2022'
						&& board[r + 2][c + 2] == 'o'
						&& board[r + 3][c + 3]=='\u2022') 
						||
						(board[r][c] == '\u2022'
						&& board[r + 1][c + 1]=='\u2022'
						&& board[r + 2][c + 2] == '\u2022'
						&& board[r + 3][c + 3]=='o') 
						){
					node.rating+=1000;

				}
				//offense
				if (( board[r][c] == 'o'
						&& board[r + 1][c + 1]=='o'
						&& board[r + 2][c + 2] == 'o'
						&& board[r + 3][c + 3]=='-')
						||
						( board[r][c] == 'o'
						&& board[r + 1][c + 1]=='o'
						&& board[r + 2][c + 2] == '-'
						&& board[r + 3][c + 3]=='o')
						||
						( board[r][c] == 'o'
						&& board[r + 1][c + 1]=='-'
						&& board[r + 2][c + 2] == 'o'
						&& board[r + 3][c + 3]=='o')
				
						||
						( board[r][c] == '-'
						&& board[r + 1][c + 1]=='o'
						&& board[r + 2][c + 2] == 'o'
						&& board[r + 3][c + 3]=='o')
				) {
					node.rating+=35;

				}
				if (( board[r][c] == '-'
						&& board[r + 1][c + 1]=='o'
						&& board[r + 2][c + 2] == 'o'
						&& board[r + 3][c + 3]=='-')
						||
						( board[r][c] == '-'
						&& board[r + 1][c + 1]=='o'
						&& board[r + 2][c + 2] == '-'
						&& board[r + 3][c + 3]=='o')
						||
						( board[r][c] == '-'
						&& board[r + 1][c + 1]=='-'
						&& board[r + 2][c + 2] == 'o'
						&& board[r + 3][c + 3]=='o')
				
						||
						( board[r][c] == 'o'
						&& board[r + 1][c + 1]=='-'
						&& board[r + 2][c + 2] == '-'
						&& board[r + 3][c + 3]=='o')
						||
						( board[r][c] == 'o'
						&& board[r + 1][c + 1]=='-'
						&& board[r + 2][c + 2] == 'o'
						&& board[r + 3][c + 3]=='-')
						||
						( board[r][c] == 'o'
						&& board[r + 1][c + 1]=='o'
						&& board[r + 2][c + 2] == '-'
						&& board[r + 3][c + 3]=='-')
				) {
					node.rating+=10;

				}
				
				
				if (( board[r][c] == 'o'
						&& board[r + 1][c + 1]=='-'
						&& board[r + 2][c + 2] == '-'
						&& board[r + 3][c + 3]=='-')
						||
						( board[r][c] == '-'
						&& board[r + 1][c + 1]=='o'
						&& board[r + 2][c + 2] == '-'
						&& board[r + 3][c + 3]=='-')
						||
						( board[r][c] == '-'
						&& board[r + 1][c + 1]=='-'
						&& board[r + 2][c + 2] == 'o'
						&& board[r + 3][c + 3]=='-')
				
						||
						( board[r][c] == '-'
						&& board[r + 1][c + 1]=='-'
						&& board[r + 2][c + 2] == '-'
						&& board[r + 3][c + 3]=='o')
						
				) {
					node.rating+=2;

				}

			}
		}
		
		
		/**
		 * forward eval
		 * 
		 * 
		 */
		for (int r = 0; r < board.length - 3; r++) {

			for (int c = board[0].length - 1; c > 2; c--) {
				//precautions
				if((board[r][c]=='-'
						&&board[r+1][c-1]=='\u2022'
						&&board[r+2][c-2]=='\u2022'
						&&board[r+3][c-3]=='\u2022'
						&&board[r+1][c]!='-')
						||
						(board[r][c]=='\u2022'
						&&board[r+1][c-1]=='-'
						&&board[r+2][c-2]=='\u2022'
						&&board[r+3][c-3]=='\u2022'
						&&board[r+2][c-1]!='-')
						||
						(board[r][c]=='\u2022'
						&&board[r+1][c-1]=='\u2022'
						&&board[r+2][c-2]=='-'
						&&board[r+3][c-3]=='\u2022'
						&&board[r+3][c-2]!='-')
						||
						(board[r][c]=='\u2022'
						&&board[r+1][c-1]=='\u2022'
						&&board[r+2][c-2]=='\u2022'
						&&board[r+3][c-3]=='-'
						)){
					node.rating-=1500;
				}
				
				
				//winning
				if (board[r][c] =='o'
						&& board[r + 1][c - 1]=='o'
						&& board[r + 2][c - 2]=='o'
						&& board[r + 3][c - 3]=='o') {

						node.rating+=2000;
				}
				
				//defense
				if ((board[r][c] == 'o'
						&& board[r + 1][c - 1]=='\u2022'
						&& board[r + 2][c - 2] == '\u2022'
						&& board[r + 3][c - 3]=='\u2022') 
						||
						(board[r][c] == '\u2022'
						&& board[r + 1][c - 1]=='o'
						&& board[r + 2][c - 2] == '\u2022'
						&& board[r + 3][c - 3]=='\u2022') 
						||
						(board[r][c] == '\u2022'
						&& board[r + 1][c - 1]=='\u2022'
						&& board[r + 2][c - 2] == 'o'
						&& board[r + 3][c - 3]=='\u2022') 
						||
						(board[r][c] == '\u2022'
						&& board[r + 1][c - 1]=='\u2022'
						&& board[r + 2][c - 2] == '\u2022'
						&& board[r + 3][c - 3]=='o') 
						){
					node.rating+=1000;

				}
				//offense
				if (( board[r][c] == 'o'
						&& board[r + 1][c - 1]=='o'
						&& board[r + 2][c - 2] == 'o'
						&& board[r + 3][c - 3]=='-')
						||
						( board[r][c] == 'o'
						&& board[r + 1][c - 1]=='o'
						&& board[r + 2][c - 2] == '-'
						&& board[r + 3][c - 3]=='o')
						||
						( board[r][c] == 'o'
						&& board[r + 1][c - 1]=='-'
						&& board[r + 2][c - 2] == 'o'
						&& board[r + 3][c - 3]=='o')
				
						||
						( board[r][c] == '-'
						&& board[r + 1][c - 1]=='o'
						&& board[r + 2][c - 2] == 'o'
						&& board[r + 3][c - 3]=='o')
				) {
					node.rating+=35;

				}
				if (( board[r][c] == '-'
						&& board[r + 1][c - 1]=='o'
						&& board[r + 2][c - 2] == 'o'
						&& board[r + 3][c - 3]=='-')
						||
						( board[r][c] == '-'
						&& board[r + 1][c - 1]=='o'
						&& board[r + 2][c - 2] == '-'
						&& board[r + 3][c - 3]=='o')
						||
						( board[r][c] == '-'
						&& board[r + 1][c - 1]=='-'
						&& board[r + 2][c - 2] == 'o'
						&& board[r + 3][c - 3]=='o')
				
						||
						( board[r][c] == 'o'
						&& board[r + 1][c - 1]=='-'
						&& board[r + 2][c - 2] == '-'
						&& board[r + 3][c - 3]=='o')
						||
						( board[r][c] == 'o'
						&& board[r + 1][c - 1]=='-'
						&& board[r + 2][c - 2] == 'o'
						&& board[r + 3][c - 3]=='-')
						||
						( board[r][c] == 'o'
						&& board[r + 1][c - 1]=='o'
						&& board[r + 2][c - 2] == '-'
						&& board[r + 3][c - 3]=='-')
				) {
					node.rating+=10;

				}
				
				
				if (( board[r][c] == 'o'
						&& board[r + 1][c - 1]=='-'
						&& board[r + 2][c - 2] == '-'
						&& board[r + 3][c - 3]=='-')
						||
						( board[r][c] == '-'
						&& board[r + 1][c - 1]=='o'
						&& board[r + 2][c - 2] == '-'
						&& board[r + 3][c - 3]=='-')
						||
						( board[r][c] == '-'
						&& board[r + 1][c - 1]=='-'
						&& board[r + 2][c - 2] == 'o'
						&& board[r + 3][c - 3]=='-')
						||
						( board[r][c] == '-'
						&& board[r + 1][c - 1]=='-'
						&& board[r + 2][c - 2] == '-'
						&& board[r + 3][c - 3]=='o')
						
				) {
					node.rating+=2;

				}
			}
		}
		}

	
	
	/**
	 * node inner class
	 * 
	 * @author Erin
	 *
	 * @param <E>
	 */
	public static class Node {
		private char[][] currentBoard = null;
		private Node E1;
		private Node E2;
		private Node E3;
		private Node E4;
		private Node E5;
		private Node E6;
		private Node E7;
		private int index;
		private int rating;

		/**
		 * constructor sets current node with generated possibility
		 * 
		 * @param data
		 */
		public Node(char[][] data) {
			currentBoard = data;
			this.E1 = null;
			this.E2 = null;
			this.E3 = null;
			this.E4 = null;
			this.E5 = null;
			this.E6 = null;
			this.E7 = null;
			this.index = 0;
			this.rating = 0;
		}
	}
}
