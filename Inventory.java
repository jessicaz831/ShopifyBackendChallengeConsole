// Jessica Zhu
// Feature chosen: export product data to CSV

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

class Inventory {
	public static void main(String[] args) {
		ArrayList<Item> inventory = new ArrayList<Item>();

		Scanner scanner = new Scanner(System.in);
		System.out.print("INVENTORY\n>");

		while (scanner.hasNextLine()){
			String inputLine = scanner.nextLine();
			if (inputLine == null || inputLine.equals("")) continue;

			Scanner commandLine = new Scanner(inputLine);
			String command = commandLine.next();

			if (command.equalsIgnoreCase("QUIT") || command.equalsIgnoreCase("Q")) {
				return;
			}
			// use command by typing: create itemName itemQuantity
			// ex. create keyboard 10
			// assumes name has no spaces
			else if (command.equalsIgnoreCase("CREATE")) {
				String name = null;
				String quantity = null;
				Boolean found = false;
				
				if (commandLine.hasNext()) {
					name = commandLine.next();
					quantity = commandLine.next();

					Item temp = new Item(name);

					// check for duplicates
					for (int i = 0; i < inventory.size(); i++) {
						if (inventory.get(i).equals(temp)) {
							found = true;
						}
					}
					
					if (found == false) {
						try {
							int value = Integer.parseInt(quantity);
							inventory.add(new Item(name, value));
						}
						catch (NumberFormatException e)  
						{ 
							System.out.println(quantity + " is not an integer"); 
						} 
					}
					else {
						System.out.println(name + " is already in inventory");
					}
				}
			}
			// use command by typing: edit itemName name/quantity newValue
			// ex. edit keyboard name blackwidow
			// ex. edit keyboard quantity 20
			else if (command.equalsIgnoreCase("EDIT")) {
				String name = null;
				String toEdit = null;
				String newValue = null;
				Boolean found = false;

				if (commandLine.hasNext()) {
					name = commandLine.next();
					toEdit = commandLine.next();
					newValue = commandLine.next();

					Item temp = new Item(name);

					for (int i = 0; i < inventory.size(); i++) {
						if (inventory.get(i).equals(temp)) {
							found = true;
							if (toEdit.equals("name")) {
								inventory.get(i).setName(newValue);
							}
							else if (toEdit.equals("quantity")) {
								try {
									int value = Integer.parseInt(newValue);
									inventory.get(i).setQuantity(value);
								}
								catch (NumberFormatException e)  
								{ 
									System.out.println(newValue + " is not an integer"); 
								} 
							}
							System.out.println(inventory.get(i).toString());
						}
					}

					if (found == false) {
						System.out.println(name + " not found");
					}
				}
			}
			// use command by typing: delete itemName
			// ex. delete keyboard
			else if (command.equalsIgnoreCase("DELETE")) {
				String name = null;
				Boolean found = false;

				if (commandLine.hasNext()) {
					name = commandLine.next();

					Item temp = new Item(name);

					for (int i = 0; i < inventory.size(); i++) {
						if (inventory.get(i).equals(temp)) {
							inventory.remove(i);
							found = true;
							System.out.println(name + " removed");
							break;
						}
					}

					if (found == false) {
						System.out.println(name + " not found");
					}
				}
			}
			// use command by typing: list
			else if (command.equalsIgnoreCase("LIST")) {
				for (int i = 0; i < inventory.size(); i++) {
					System.out.println(inventory.get(i).toString());
				}
			}
			// use command by typing: csv
			else if (command.equalsIgnoreCase("CSV")) {
				try {
					FileWriter file = new FileWriter("inventory.txt");
					
					for (int i = 0; i < inventory.size(); i++) {
						file.write(inventory.get(i).getName() + ", " + inventory.get(i).getQuantity() + "\n");
					}

					file.close();
					System.out.println("CSV exported to inventory.txt");
				} 
				catch (IOException e) {
					System.out.println("Error exporting CSV");
				}
			}
			System.out.print("\n>");
		}
	}
}