package levelingLife;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Attribute {
	
	//Variables for Attributes class
	
	private List<Quest> quests = new LinkedList<Quest>();
	private int xpTotal = 0;
	private int xpThreshold = 100;
	private String name;
	Scanner in = new Scanner(System.in);
	
	
	
	
	//constructor prompts player to name a new attribute
	
	public Attribute() {
		System.out.println("What would you like to call this attribute?");
		name = in.nextLine();
	}
	
	
	
	
	//Getters and Setters
	
	public String getName() {
		return name;
	}
	public int getXP() {
		return xpTotal;
	}
	public int getXPThreshold() {
		return xpThreshold;
	}
	
	
	
	
	//Methods
	
	public void addQuest() {
		quests.add(new Quest());
		addAnotherQuest();
		System.out.println("Very well. Good luck on your journey.");
	}
	
	public void addAnotherQuest() {
		System.out.println("Would you like to add another one for this attribute? y or n");
		String answer = in.nextLine();
		if (answer.equals("y")) {
			quests.add(new Quest());
			addAnotherQuest();
		} else if (answer.equals("n")) {
			return;
		} else {
			System.out.println("Please type y or n.");
			addAnotherQuest();
		}
		
	}
	
	public void viewQuests() {
		int i = 0;
		System.out.println("Your quests for " + name + " are as follows: ");
		for (Quest quest : quests) {
			i++;
			System.out.println(i + ". " + quest.getQuestName() + " (" + quest.getQuestXPInfo() + " xp)");
		}
		System.out.println("  ");
	}
	
	public int chooseQuest() {
		System.out.println("Which quest would you like to select?");
		viewQuests();
		boolean retry = false;
		int i = 0;
		while (!retry) {
			try {
			i = in.nextInt();
			retry = true;
			} 
			catch (InputMismatchException e) {
				System.out.println(" ");
				System.out.println("Please type just the number to select.");
				System.out.println(" ");
				System.out.println("Which quest would you like to select?");
				in.nextLine();
			}
		}
		return i;
	}
	
	public void completeQuest() {
		int i = 0; 
		i = chooseQuest();
		xpTotal += quests.get(i-1).xpWorth;
		System.out.println("Congratulations--you have completed this journey intact!");
		quests.remove(i - 1);
	}
	
	public void removeQuest() {
		int i = chooseQuest();
		quests.remove(i-1);
	}
	
	/* 
	 * increasing threshold simulates mastery over basics; quests that were difficult to player 
	 * previously are no longer as difficult at higher levels, though task is worth same amount of XP 
	 */
	public void increaseThreshold() {
		xpThreshold *= 1.5;
	}
	
//**************************************************************************************	
	//Inner class for quests within the quests list
	
	public class Quest {
		
		//Variables
		
		private String questName;
		protected int xpWorth;
		
		
		
		//Constructor
		
		public Quest() {
			Scanner in = new Scanner(System.in);
			System.out.println("What would you like to call this quest?");
			this.questName = in.nextLine();
			System.out.println("How many experience points (XP) would you like to assign to this quest?");
			boolean retry = false;
			while(!retry) {
				try {
				this.xpWorth = in.nextInt();
				retry = true;
				}
				catch (InputMismatchException e) {
					System.out.println("Please type the numeric value for XP.");
					System.out.println("How many experience points (XP) would you like to assign to this quest?");
					in.nextLine();
				}
			}
		}
		
		
		
		//Methods
		
		public String getQuestName() {
			return questName;
		}
		
		public int getQuestXPInfo() {
			return xpWorth;
		}
		
		
			
	}

}
