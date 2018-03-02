import java.util.GregorianCalendar;

public class Order {
	private String id = "default";
	private GregorianCalendar currentTime = new GregorianCalendar();

	public Order() {
		System.out.println("I am in the Order Constructor");
		id = "default";
	}

	public Order(String lastName) {
		System.out
				.println("I am in the Order Constructor that takes a string as a parameter");
		id = createOrderID(lastName);
		System.out.println("Your Order ID is: " + id);
	}

	public String createOrderID(String lastName) {
		return lastName + currentTime.getTimeInMillis();

	}
	
	public void processOrder() {
		System.out.println("I am in Order running the processORder() method.");
	}
	
	public void processOrder(String orderid) {
		String newID = null;
		 if (id.equals("default")) {
		 newID = createOrderID(id);
		 System.out.println("processing order in Order using id: " + newID);
		 } 
		 else {
		 System.out.println("processing order in Order using id " + orderid);
		 }
		 
	}
}
