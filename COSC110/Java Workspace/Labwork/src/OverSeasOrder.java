
public class OverSeasOrder extends BatchOrder{
	public OverSeasOrder() {
		System.out.println("I am in the Over Seas Order Constructor.");
	}
	
	public OverSeasOrder(String namelast) {
		super(namelast);
		 System.out.println("I am in the OverSeasOrder Constructor that takes in a String");
	}
}
