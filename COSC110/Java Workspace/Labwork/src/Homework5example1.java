
public class Homework5example1 {
	public char firstChar(String word) {
		return word.charAt(0);
		//for int, divide by 10 until it's 1 digit.
	}
	
	public char lastChar(String test) {
		return test.charAt(test.length()-1);
		//for int, modulo by 10? Maybe?
	}
	
	public int stringLength(String word) {
		return word.length();
		//for int, I'm not sure
	}
	
	public static String backwards(String word) {
		String temp = "";
		for (int i = word.length()-1; i >= 0; i--) {
			temp = temp + word.charAt(i);
		}
		return temp;
	}
	
}
