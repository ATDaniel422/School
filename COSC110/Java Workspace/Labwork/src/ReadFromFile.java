import java.util.*;
import java.io.*;
public class ReadFromFile {
    public static void main(String[] args) {
        // create a file object that points to the input file
        // use escape sequence \ to keep the slashes in the path to the file
        File inFile = new File("C:\\Users\\Daniel\\Documents\\School\\2015 Spring\\COSC110\\MW5Prob7.txt");

        // Scanner constructor gets its info from the file
        Scanner in = null;
		
        // Writer pushes its info to whatever file you put in the constructor
        PrintWriter out = null;
        String build = "";
        try {
            // connect the scanner to the input file
            in = new Scanner(inFile);
            // connect the writer to the output file
            out = new PrintWriter("C:\\Users\\Daniel\\Documents\\School\\2015 Spring\\COSC110\\data.CSV");

            // use a loop to iterate through the lines of the file
            // use scanner methods to get the type of data you know is in the file
            while (in.hasNextLine()) {
                build = build + in.nextLine().charAt(0) + " ";
            }
        // if a problem occurs, it is dealt with in the catch block of code
        } catch (FileNotFoundException fnfe) {
            System.out.println("A problem occurred");
            fnfe.printStackTrace();
        }
        //write to the output file
        out.println(build);
        // close the Scanner
        in.close();
        // close the writer IMPORTANT actually writes the buffer out to the file
        out.close();
    }
}
