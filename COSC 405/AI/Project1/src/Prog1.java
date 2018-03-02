import java.util.*;

/**
 * 
 * TODO: limit height of board(cannot add token if column is filled to top) if
 * board is filled with no winners 
 * if number outside array selected
 * input mismatch exception
 * bug in horizontal and vertical check when 3 consecutive skip one, then fourth
 * try catch with array index exception out of bound and for loop to fix?
 * 
 * 
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

class ConnectFour {

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

class gameBoard {
	Scanner in = new Scanner(System.in);
	int moves = 0;
	char gameboard[][] = new char[6][7];

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

	private void chooseCol() {
		System.out.println("Choose Column");
		int col = 0;
		try{
           col = in.nextInt();}
        catch (InputMismatchException e){
            System.out.println("You may only enter integers 0-6. Try again.");
            in.next();
        chooseCol();
    }
		
		boolean moved = false;
		if (col < 0 || col > 6) {
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

	private void PCMove() {
		boolean moved = false;
		Random r = new Random();
		int col = r.nextInt(7);

		for (int i = gameboard.length - 1; i >= 0; i--) {
			if (gameboard[i][col] == '-') {
				gameboard[i][col] = 'o';
				moved = true;
				moves++;
				break;
			}
		}

		if (!moved) {

			chooseCol();
		}

		printboard();
		checkWinner();
		chooseCol();
	}

	public void printboard() {

		System.out.println();

		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(gameboard[i][j] + " | ");

			}

			System.out.println();

		}
		System.out.println();
	}

	private void checkWinner() {

		boolean winner = false;
		String winType = "";
		int col = 0;
		int row = 0;
		int counter = 0;
		int current = 0;
		int next = 0;
		if (moves == 49) {
			draw();
		}
		/**
		 * horizontal win
		 */
		while (row < 7 && !winner) {
			while (col < 6 && !winner) {
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
		while (col < 7 && !winner) {
			while (row < 6 && !winner) {
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
		 */
		for (int r = 0; r < gameboard.length - 3; r++) {

			for (int c = gameboard[0].length - 2; c > 2; c--) {

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

	private void draw() {
		System.out.println("Board filled with no winner");
		ConnectFour n = new ConnectFour();
		n.start();
	}

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
