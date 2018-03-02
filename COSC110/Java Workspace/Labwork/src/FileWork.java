import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileWork {
	public static void main(String[] args) {
		File in = new File("C:\\Users\\xpjv\\Documents\\Numbers.txt");
		
		int sum = 0;
		int count = 0;
		
		try {
			Scanner inFile = new Scanner(in);
			PrintWriter out = new PrintWriter("C:\\Users\\xpjv\\Documents\\NumbersResults.txt");
			while (inFile.hasNextInt()) {
				int digit = inFile.nextInt();
				sum = sum + digit;
				count++;
				System.out.println("number" + count + " is " + digit);
			}
			System.out.println("The average is: " + (sum/count));
			out.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found");
			
		}

	}
}
