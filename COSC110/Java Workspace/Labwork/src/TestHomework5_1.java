
public class TestHomework5_1 {
	public static void main(String[] args) {
		Homework5example1 hw5e1 = new Homework5example1();
		//non-static method acll using an object we created
		System.out.println(hw5e1.firstChar("This is a String"));
		System.out.println(hw5e1.lastChar("This is a String"));
		System.out.println(hw5e1.stringLength("This is a String"));
		
		//static call using a class name
		System.out.println(Homework5example1.backwards("This is a String"));
		
		hwk5prob4 Bob = new hwk5prob4();
		Bob.setName(Bob);
	}
}
