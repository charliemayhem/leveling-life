package levelingLife;

import java.util.InputMismatchException;
import java.lang.IndexOutOfBoundsException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Menu {
	List<Attribute> attributesList = new LinkedList<Attribute>();
	Player player = new Player();
	Scanner in = new Scanner(System.in);
	
	// Constructor
	public Menu() {

	}
	
	//Methods
	public void greeting() {
		player.addName();
	}
	
	public void displayMenu() {
		
		int menuChoice = 0;
		
		while (menuChoice != 8) {
			System.out.println("**************************************************");
			System.out.println("***************    Main Menu    ******************");
			System.out.println("**************************************************");
			System.out.println("What would you like to do? Please type the number: ");
			System.out.println("1. Add Attribute");
			System.out.println("2. Show Attributes List");
			System.out.println("3. Remove Attribute");
			System.out.println("4. Show All Quests");
			System.out.println("5. Add Quest");
			System.out.println("6. Complete Quest");
			System.out.println("7. Remove Quest");
			System.out.println("8. Quit");
			
			try {
			menuChoice = in.nextInt();
			switch (menuChoice) {
			case 1:
				// Add Attribute
				addAttribute();
				System.out.println("This attribute has been added.");
				System.out.println("  ");
				break;
			case 2: 
				// Show attributes list
				printList();
				break;
			case 3: 
				//Remove Attribute from list
				System.out.println("WARNING: Removing an attribute will also remove all associated quests and cannot be undone. "
						+ "\nContinue? y/n");
				String answer = in.next();
				if (answer.equals("n")) {
					break;
				} else {
					System.out.println("Which attribute would you like to remove? Type 0 (zero) to exit.");
					printList();
					int item = in.nextInt();
					if (item - 1 == -1) {
						break;
					}
					attributesList.remove(item - 1);
				}
				break;
			case 4:
				// View all quests
				for (Attribute att : attributesList) {
					att.viewQuests();
				}
				break;
			case 5:
				// add quest
				int i = chooseAttribute();
				attributesList.get(i - 1).addQuest();
				break;
			case 6:
				// Complete quest
				//chooseAttribute returns the listed number on the attributes list
				i = chooseAttribute();
				//i-1 is the actual attribute index; completeQuest uses chooseQuest 
				attributesList.get(i-1).completeQuest();
				// check for leveling up condition - xp on attribute equal or greater than current threshold
				while (attributesList.get(i-1).getXP() >= attributesList.get(i-1).getXPThreshold()) {
					attributesList.get(i-1).increaseThreshold();
					player.levelUp();
					System.out.println("Threshold is: " + attributesList.get(i-1).getXPThreshold());
				}
				break;
			case 7:
				// Remove quest from list
				i = chooseAttribute();
				attributesList.get(i-1).removeQuest();
				break;
			}
				
		}
		
			catch (InputMismatchException e) {
				System.out.println(" ");
				System.out.println("Please type only the number for selection. For example: type 1 for adding an attribute.");
				System.out.println(" ");
				in.nextLine();
				continue;
			}
		}
	} 
	
	
	public void printList() {
		int i = 0;
		System.out.println("       ");
		System.out.println("Your Attributes: ");
		for (Attribute att : attributesList) {
			i++;
			System.out.println(i + ". " + att.getName() + " XP: " + att.getXP());
		}
		System.out.println("       ");
	}
	
	public int chooseAttribute() {
		System.out.println("Which attribute would you like to do this for?");
		printList();
		int i = 0;
		boolean retry = false;
		while (!retry) {
		try {
			i = in.nextInt();
			retry = true;
			} catch (InputMismatchException e) {
				System.out.println("Please type only the number for selection.");
				in.nextLine();
				continue;
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Please type only the number for selection.");
				in.nextLine();
				continue;
			}
		}		
		return i;
	}
	
	public void addAttribute() {
		attributesList.add(new Attribute());
	}

}
