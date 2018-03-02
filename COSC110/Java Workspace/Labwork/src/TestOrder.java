import java.util.Scanner;

public class TestOrder {
	public TestOrder() {
		System.out.println("I am in the TestOrder Constructor");
 }

	public static void main(String[] args) {
		String lastName = null;
		String lastName2 = null;
		String lastName3 = null;
		Scanner input = new Scanner(System.in);
		
		Order order1 = new Order();
		BatchOrder batch1 = new BatchOrder();
		OverSeasOrder sea1 = new OverSeasOrder();
		
		System.out.println("Please Enter last name");
		lastName = input.next();
		Order order2 = new Order(lastName);
		
		System.out.println("Please Enter another last name");
		lastName2 = input.next();
		BatchOrder batch2 = new BatchOrder(lastName2);
		
		System.out.println("Please Enter another last name.");
		lastName3 = input.next();
		OverSeasOrder sea2 = new OverSeasOrder(lastName3);
		
		order1.processOrder();
		batch1.processOrder();
		sea1.processOrder();
		order2.processOrder();
		batch2.processOrder();
		sea2.processOrder();
		
		OverSeasOrder sea3 = new OverSeasOrder();
	}
}