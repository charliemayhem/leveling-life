package levelingLife;
import java.util.Scanner;

public class Player {
	
	//Player Attributes
	private int level = 0;
	private String name;
	
	//Player constructor
	public Player() {
		level = 1;
	}
	
	
	//Player methods
	public void addName() {
		System.out.println("Enter your name, friend! ");
		Scanner in = new Scanner(System.in);
		name = in.nextLine();
		System.out.println("Hello, " + name + "!");
		System.out.println("   ");
	}
	
	public int levelUp() {
		level++;
		System.out.println("Congratulations, you are now level " + level);
		return level;
	}
	
	//Getters and Setters

}
