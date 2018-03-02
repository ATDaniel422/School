/** 
 *  COSC 310-001  Project 1
 *  DrewDanielTestCar.java
 *  
 *  Program to define and create Engine, Wheel, Window, and Car
 *  objects then display specified information about those cars.
 *  The objective is to practice using the ideas of inheritance, 
 *  abstract classes, and abstract methods.
 *  
 *  @author Drew Daniel
 *  
 */


public class DrewDanielTestCar {
	
	public static void main(String[] args) {

		// Declare Engine, Wheel, and Window objects for car one
		Engine eng1 = new Engine(2400, 4, 16, 180);
		Wheel whl1 = new Wheel(4, 265, 33);
		Window win1 = new Window(2, 45, 50);
		
		// Declare Engine, Wheel, and Window objects for car two
		Engine eng2 = new Engine();
		Wheel whl2 = new Wheel(4, 225, 35);
		Window win2 = new Window(4, 50, 60);
		
		// Declare car one and print information
		Vehicle car1 = new Car(eng1, whl1, win1, "Mercedes-Benz");
		System.out.println("Information about the first car:");
		System.out.println();
		System.out.println("The car is a " + ((Car)car1).displayMake() + ".");
		System.out.println(((Car)car1).displayWheelPressure());
		System.out.println(((Car)car1).displayWindowArea());
		System.out.println(((Car)car1).displayValveNumber());
		System.out.println(((Car)car1).displayHorsePower());
		System.out.println();
		System.out.println();
		
		// Declare car two and print information
		Vehicle car2 = new Car(eng2, whl2, win2, "Honda");
		System.out.println("Information about the second car:");
		System.out.println();
		System.out.println("The car is a " + ((Car)car2).displayMake() + ".");
		System.out.println(((Car)car2).displayWheelPressure());
		System.out.println(((Car)car2).displayWindowArea());
		System.out.println(((Car)car2).displayValveNumber());
		System.out.println(((Car)car2).displayHorsePower());		
	}
}

// Define class Engine and its attributes
class Engine {
	public int cylinderCapacity;
	public int numCylinders;
	public int numValves;
	public int horsePower;
	
	// Build default constructor for Engine object
	public Engine(){
		cylinderCapacity = 2300;
		numCylinders = 6;
		numValves = 24;
		horsePower = 220;
	}
	
	// Build explicit constructor for Engine object
	public Engine(int cylinderCapacity, int numCylinders, int numValves, int horsePower) {
		this.cylinderCapacity = cylinderCapacity;
		this.numCylinders = numCylinders;
		this.numValves = numValves;
		this.horsePower = horsePower;
	}
	
	/**
	 * Method to display message indicating an engine starting.
	 * @return The message saying that the engine has started.
	 */
	public String start() {
		return "Engine started.";
	}
	
	/**
	 * Method to display message indicating an engine stopping.
	 * @return The message saying that the engine has stopped.
	 */
	public String stop() {
		return "Engine stopped.";
	}
	
	/**
	 * Method to display message indicating an engine's horse power
	 * @return The horse power of the engine
	 */
	public int getHorsePower() {
		return this.horsePower;
	}
	
	/**
	 * Method to set the horse power of an engine.
	 * @param integer of desired horse power
	 */
	public void setHorsePower(int HP) {
		this.horsePower = HP;
	}
	
}

// Define Wheel class and its attributes
class Wheel {
	public int numWheels;
	public int wheelSize;
	public int tirePressure;
	
	// Build constructor for a Wheel object
	public Wheel(int numWheels, int wheelSize, int tirePressure) {
		this.numWheels = numWheels;
		this.wheelSize = wheelSize;
		this.tirePressure = tirePressure;
	}
	
	/**
	 * Method to get the desired tire pressure of a Wheel.
	 * @return tire pressure in PSI.
	 */
	public int getPressure() {
		return this.tirePressure;
	}
	
	/**
	 * Method to set the desired tire pressure for a Wheel.
	 * @param desired PSI
	 */
	public void setPressure(int PSI) {
		this.tirePressure = PSI;
	}
}

// Define Window class and its attributes
class Window {
	public int numWindows;
	public int winHeight;
	public int winWidth;
	
	// Build constructor for Window object
	public Window(int numWindows, int winHeight, int winWidth) {
		this.numWindows = numWindows;
		this.winHeight = winHeight;
		this.winWidth = winWidth;
	}
	
	/**
	 * Method to roll up windows.
	 * @return Message saying that the windows have been rolled up.
	 */
	public String rollUp() {
		return "Windows have been rolled up.";
	}
	
	/**
	 * Method to roll down windows.
	 * @return Message saying that the windows have been rolled down.
	 */
	public String rollDown() {
		return "Windows have been rolled down.";
	}
	
	/**
	 * Mehtod to compute the area of the Window.
	 * @return total area of Window.
	 */
	public int computeWindowArea() {
		return this.winHeight * this.winWidth;
	}
}

// Define Vehicle class and its attributes
abstract class Vehicle {
	public Engine eng;
	public Wheel whl;
	public Window win;
	
	// Build Vehicle object constructor
	public Vehicle (Engine eng, Wheel whl, Window win) {
		this.eng = eng;
		this.whl = whl;
		this.win = win;
	}
	
	// Abstract method to display the pressure of the Wheel
	public abstract String displayWheelPressure();
	
	// Abstract method to display the area of the Window
	public abstract String displayWindowArea();
	
	/**
	 * Method to display the number of valves in the Vehicle's engine.
	 * @return Message describing the Vehicle's Engine's number of valves.
	 */
	public String displayValveNumber() {
		return "This vehicle has " + eng.numValves + "valves";
	}
}

//Define Class Car and its attributes
class Car extends Vehicle {	
	Engine eng;
	Wheel whl;
	Window win;
	String make;
	
	// Build Car object constructor
	public Car(Engine eng, Wheel whl, Window win, String make) {
		super(eng, whl, win);
		this.eng = eng;
		this.whl = whl;
		this.win = win;
		this.make = make;
	}
	
	/**
	 * Method to override displayWheelPressure() method from Vehicle
	 * parent class.
	 * @return message displaying pressure of the Car's Wheel.
	 */
	@Override
	public String displayWheelPressure() {
		return ("The desired wheel pressure of this car is " + 
			whl.getPressure() + " PSI.");
	}
	
	/**
	 * Method to override displayWindowArea() method from Vehicle
	 * parent class.
	 * @return message displaying the area of the Car's Window.
	 */
	@Override
	public String displayWindowArea() {
		return "The window area of this car is " + win.computeWindowArea() +
				" square centimeters.";
	}
	
	/**
	 * Method overloading the displayValveNumber() method from 
	 * Vehicle parent class.
	 * @return Message displaying the Car's Engine's number of valves.
	 */
	public String displayValveNumber() {
		return "This car has " + eng.numValves + " valves.";
	}
	
	/**
	 * Method to get the horse power of a Car's Engine.
	 * @return message displaying the Car's Engine's horse power.
	 */
	public String displayHorsePower() {
		return "This car has " + eng.getHorsePower() +
				" horse power.";
	}
	
	/**
	 * Method to get the make of a Car.
	 * @return The Car's make.
	 */
	public String displayMake() {
		return this.make;
	}
}
