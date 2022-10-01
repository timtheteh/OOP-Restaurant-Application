import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.*;
/**
 * The Menu class provides the methods used to edit the list of food items.
 * @author Bryan
 *
 */
public class Menu {

	/**
   	* listoffood that is avaliable throughout the restaurant
    */
	private ArrayList<Item> listOfFood;

	/**
   	* constructs a menu object which takes menuitems.txt as an input
   	*/
	public Menu() {
		listOfFood = new ArrayList<Item>();
		try{
			File file = new File("MenuItems.txt");
			Scanner scan = new Scanner(file);
			
			double price;
			String name;
			String description;
			MenuItemType FoodType;
			IsPromo MenuType;

			while(scan.hasNextLine()) {
				name = scan.nextLine();
				FoodType = MenuItemType.valueOf(scan.nextLine());
				MenuType = IsPromo.valueOf(scan.nextLine());
				description = scan.nextLine();
				price = scan.nextDouble();
				if(!scan.hasNextLine()){
					Item newMenuItem = new MenuItem(FoodType, MenuType, name, description, price);
					addFood(newMenuItem);
					scan.close();
					break;
				}
				scan.nextLine();
				Item newMenuItem = new MenuItem(FoodType, MenuType, name, description, price);
				addFood(newMenuItem);
			}
			scan.close();

		}
		catch(IOException exception){
			System.out.println("IO exception" + exception);
		}
	}

	
	/** Returns a list of food
	 * @return  this gives back a list of food
	 */
	public ArrayList<Item> getListOfFood() {
		return listOfFood;
	}

	/**
	 * Sets and changes the list of food items
	 * @param listOfFood
	 * 
	 */
	public void setListOfFood(ArrayList<Item> listOfFood) {
		this.listOfFood = listOfFood;
	}

	/**
	 * Adds a particular food item into the list
	 * @param Item
	 */
	public void addFood(Item item) {
		listOfFood.add(item);
	}

	/**
	 * Removes a food item from the list of food
	 * @param Item
	 */
	public void removeFood(Item item) {
	 listOfFood.remove(item);
	}

	/**
	 * A loop that requests the user input to create a menu item that they want
	 * @return boolean	whether function is a success
	 */
	public boolean createMenuItem() {
		Scanner scC = new Scanner(System.in);
		String name = "";
		while (true) {
			try {
				System.out.println("What is the name of your food: ");
				name = scC.nextLine();
				break;
			} catch (Exception e) {
				System.out.println("Error, please try again: ");
				scC.nextLine();
			}
		}

		if(name.equals("break")){
			System.out.println("Exiting...");
			return false;
		}

		//Description
		String desc = "";
		while (true) {
			try {
				System.out.printf("What is the description of %s: \n", name);
				desc = scC.nextLine();
				break;
			} catch (Exception e) {
				System.out.println("Error, please try again: ");
				scC.nextLine();
			}
		}

		if(desc.equals("break")){
			System.out.println("Exiting...");
			return false;
		}

		//Price
		Double price = 0.0;
		while (true) {
			try {
				System.out.printf("What is the price of %s: \n", name);
				price = scC.nextDouble();
				scC.nextLine();
				break;
			} catch (Exception e) {
				System.out.println("Error, please enter a number: ");
				scC.nextLine();
			}
		}

		//Foodtype
		String foodType = "";
		while (!foodType.equals("drink") && !foodType.equals("main course") && !foodType.equals("dessert")) {
			try {
				System.out.println("Food Types: Drink || Main Course || Dessert");
				System.out.printf("What is the foodtype of %s: \n", name);
				foodType = scC.nextLine();
				foodType = foodType.toLowerCase();
			} catch (Exception e) {
				System.out.print(e);
				System.out.println("Error, please enter a valid foodtype: ");
				scC.nextLine();
			}
		}
		
		MenuItemType foodTypeMIT = MenuItemType.MainCourse;
		switch(foodType){
			case "drink":
				foodTypeMIT = MenuItemType.Drink;
				break;
			case "dessert":
				foodTypeMIT = MenuItemType.Dessert;
				break;
			case "main course":
				break;
		}		
		
		Item newMenuItem = new MenuItem(foodTypeMIT, IsPromo.Normal, name, desc, price);
		//scC.close();
		
		try{
			addFood(newMenuItem);
			System.out.printf("Menu Item added: %s (%s | $%.2f) \n", name, desc, price);
			return true;
		} catch(Exception exception){
			System.out.println("An error has occured");
			return false;
		}

	}

	/**
	 * Asks the user for an item to remove from the menu
	 * @return whether function was a success
	 */
	public boolean removeMenuItem() {
		Scanner scR = new Scanner(System.in);
		
		System.out.println("Items in Menu: ");
		for (int j = 0; j < listOfFood.size(); j++){
			if(listOfFood.get(j).getIsPromo() == IsPromo.Normal){
				System.out.printf("|  %s  |", listOfFood.get(j).getName());
			}
		}
		System.out.println("");


		String removeName = "";
		int idxRM = findItem(removeName);
		while ( idxRM == -1 && !removeName.equals("break")) { // cannot find food
			try {
				System.out.println("What is the name of your food you want to remove: ");
				removeName = scR.nextLine();
				idxRM = findItem(removeName);
				if (idxRM != -1){
					if (listOfFood.get(idxRM).getIsPromo() != IsPromo.Normal){
						idxRM = -1;
					}
				} else {
					System.out.println("Invalid Food Item");
				}
			} catch (Exception e) {
				System.out.println("Error, please enter a valid food item: ");
				scR.nextLine();
			}
		}
		
		if (removeName == "break"){
			System.out.println("Exiting...");
			//scR.close();
			return false;
		}

		int idxR = findItem(removeName);

		//scR.close();
		try{
			System.out.printf("Item removed: %s\n", listOfFood.get(idxR).getName());
			listOfFood.remove(idxR);
			return true;
		} catch (Exception e){
			System.out.println("Removal failed");
			return false;
		}
	}


	/**
	 * allows user to update menu item
	 * @return whether funciton was a success
	 */
	public boolean updateMenuItem() {
		Scanner scU = new Scanner(System.in);

		System.out.println("Items in Menu: ");
		for (int j = 0; j < listOfFood.size(); j++){
			if(listOfFood.get(j).getIsPromo() == IsPromo.Normal){
				System.out.printf("|  %s  |", listOfFood.get(j).getName());
			}
		}
		System.out.println("");

		// Name
		String updateName = "";
		int idxUM = findItem(updateName);
		while ( idxUM == -1 && !updateName.equals("break") ) { // cannot find food
			try {
				System.out.println("What is the name of your food you want to update: ");
				updateName = scU.nextLine();
				idxUM = findItem(updateName);
				if (idxUM != -1){
					if (listOfFood.get(idxUM).getIsPromo() != IsPromo.Normal){
						System.out.println("Invalid Menu Item, is a promo menu item, not a normal item.");
						idxUM = -1;
					}
				} else {
					System.out.println("Invalid Menu Item. Enter 'break' to exit.");
				}
			} catch (Exception e) {
				System.out.println("Error, please enter a valid food item: ");
				scU.nextLine();
			}
		}
		
		if (updateName.equals("break")){
			System.out.println("Exiting...");
			//scU.close();
			return false;
		}

		int idx = findItem(updateName);
	
		//Description
		String descUpdate = "";
		while (true) {
			try {
				System.out.printf("What is the description of %s: \n", updateName);
				descUpdate = scU.nextLine();
				break;
			} catch (Exception e) {
				System.out.println("Error, please try again: ");
				scU.nextLine();
			}
		}
	

		//Price
		Double priceUpdate = 0.0;
		while (true) {
			try {
				System.out.printf("What is the price of %s: \n", updateName);
				priceUpdate = scU.nextDouble();
				scU.nextLine();
				break;
			} catch (Exception e) {
				System.out.println("Error, please enter a number: ");
				scU.nextLine();
			}
		}
	
		//Foodtype
		String foodTypeUpdate = "";
		while (!foodTypeUpdate.equals("drink") && !foodTypeUpdate.equals("main course") && !foodTypeUpdate.equals("dessert")) {
			try {
				System.out.println("Food Types: Drink || Main Course || Dessert");
				System.out.printf("What is the foodtype of %s: \n", updateName);
				foodTypeUpdate = scU.nextLine().toLowerCase();
				break;
			} catch (Exception e) {
				System.out.println("Error, please enter a valid foodtype: ");
				scU.nextLine();
			}
		}
		
		MenuItemType foodTypeMITUpdate = MenuItemType.MainCourse;
		switch(foodTypeUpdate){
			case "drink":
			foodTypeMITUpdate = MenuItemType.Drink;
				break;
			case "dessert":
			foodTypeMITUpdate = MenuItemType.Dessert;
				break;
			case "main course":
				break;
		}

		//scU.close();
		Item newMenuItem = new MenuItem(foodTypeMITUpdate, IsPromo.Normal, updateName, descUpdate, priceUpdate);
		try {
			listOfFood.remove(idx);
			listOfFood.add(newMenuItem);
			System.out.printf("Menu Item updated: %s (%s | $%.2f) \n", updateName, descUpdate, priceUpdate);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Failed to update");
			return false;
		}
		
	}

	/**
	 * allows user to create promo item
	 * @return whether function was a success
	 */
	public boolean createPromoItem() {
		Scanner scCP = new Scanner(System.in);
		String nameP = "";
		while (true) {
			try {
				System.out.println("What is the name of your promo package: ");
				nameP = scCP.nextLine();
				break;
			} catch (Exception e) {
				System.out.println("Error, please try again: ");
				scCP.nextLine();
			}
		}

		System.out.println("Items in Menu: ");
		for (int j = 0; j < listOfFood.size(); j++){
			if(listOfFood.get(j).getIsPromo() == IsPromo.Normal){
				System.out.printf("|  %s  |", listOfFood.get(j).getName());
			}
		}
		System.out.println("");

		//ListofItem
		ArrayList<MenuItem> listOfMenuItems = new ArrayList<MenuItem>();
		String currentSearch = "";
		int idxP = findItem(currentSearch);
		while ( idxP == -1 && !currentSearch.equals("break")) { // cannot find food
			try {
				System.out.println("\nType 'break' to continue");
				System.out.printf("What is the name of a menu item inside %s: \n", nameP);
				currentSearch = scCP.nextLine();
				idxP = findItem(currentSearch);
				if (idxP != -1){
					if(listOfFood.get(idxP).getIsPromo() == IsPromo.Normal){
						listOfMenuItems.add((MenuItem) listOfFood.get(idxP));
					}
					idxP = -1;
				} else if (currentSearch.equals("break")) {
					break;
				} 
				else {
					System.out.println("Menu item not found, choose from these valid menu items: ");
					System.out.println("Items in Menu: ");
					for (int j = 0; j < listOfFood.size(); j++){
						if(listOfFood.get(j).getIsPromo() == IsPromo.Normal){
							System.out.printf("|  %s  |", listOfFood.get(j).getName());
						}
					}
				}
			} catch (Exception e) {
				System.out.println("Error, please enter a valid food item.");
				scCP.nextLine();
			}
		}

		//Description
		String descP = "";
		while (true) {
			try {
				System.out.printf("What is the description of %s: \n", nameP);
				descP = scCP.nextLine();
				break;
			} catch (Exception e) {
				System.out.println("Error, please try again: ");
				scCP.nextLine();
			}
		}

		//Price
		Double priceP = 0.0;
		while (true) {
			try {
				System.out.printf("What is the price of %s: \n", nameP);
				priceP = scCP.nextDouble();
				scCP.nextLine();
				break;
			} catch (Exception e) {
				System.out.println("Error, please enter a number: ");
				scCP.nextLine();
			}
		}
		
		Item newPromoItem = new PromotionalSetPackage(listOfMenuItems, priceP, descP, nameP, IsPromo.Promo);
		//scCP.close();
		
		try{
			listOfFood.add(newPromoItem);
			System.out.printf("Promo Item added: %s (%s | $%.2f) \n", nameP, descP, priceP);
			return true;
		} catch(Exception exception){
			System.out.println("Failed to add promo item");
			return false;
		}
	}

	/**
	 * allows user to remove promo item
	 * @return whether function was a success
	 */
	public boolean removePromoItem() {
		Scanner scRP = new Scanner(System.in);

		System.out.println("Items in Promo Menu: ");
		for (int j = 0; j < listOfFood.size(); j++){
			if(listOfFood.get(j).getIsPromo() == IsPromo.Promo){
				System.out.printf("|  %s  |", listOfFood.get(j).getName());
			}
		}
		System.out.println("");

		String removeNameP = "";
		int idxRP = findItem(removeNameP);
		while ( idxRP == -1 && !removeNameP.equals("break")) { // cannot find food
			try {
				System.out.println("What is the name of your food you want to remove: ");
				removeNameP = scRP.nextLine();
				idxRP = findItem(removeNameP);
				if (idxRP != -1){
					if(listOfFood.get(idxRP).getIsPromo() != IsPromo.Promo){
						idxRP = -1;
					}
				} else {
					System.out.println("Invalid Promo Food Item");
				}
			} catch (Exception e) {
				System.out.println("Error, please enter a valid food item: ");
				scRP.nextLine();
			}
		}
		
		if (removeNameP == "break"){
			System.out.println("Exiting...");
			//scRP.close();
			return false;
		}

		idxRP = findItem(removeNameP);

		//scRP.close();
		try{
			System.out.printf("Removed: %s \n", listOfFood.get(idxRP).getName() );
			listOfFood.remove(idxRP);
			return true;
		} catch (Exception e){
			System.out.println(e);
			return false;
		}
	}

	/**
	 * allows user to update promoitem
	 * @return whether function was a success
	 */
	public boolean updatePromoItem() {
		Scanner scUP = new Scanner(System.in);
		
		System.out.println("Items in Promo Menu: ");
		for (int j = 0; j < listOfFood.size(); j++){
			if(listOfFood.get(j).getIsPromo() == IsPromo.Promo){
				System.out.printf("|  %s  |", listOfFood.get(j).getName());
			}
		}
		System.out.println("");

		// Name
		String updateNameP = "";
		int idxPromoUpdate = findItem(updateNameP);
		while ( idxPromoUpdate == -1 && !updateNameP.equals("break")) { // cannot find food
			try {
				System.out.println("What is the name of the promo food you want to update: ");
				updateNameP = scUP.nextLine();
				idxPromoUpdate = findItem(updateNameP);
				if (idxPromoUpdate != -1){
					if (listOfFood.get(idxPromoUpdate).getIsPromo() != IsPromo.Promo){
						idxPromoUpdate = -1;
					}
				} else {
					System.out.println("Invalid Promo Food Item");
				}
			} catch (Exception e) {
				System.out.println("Error, please enter a valid promo item: ");
				scUP.nextLine();
			}
		}
		
		if (updateNameP.equals("break")){
			System.out.println("Exiting...");
			//scUP.close();
			return false;
		}

		idxPromoUpdate = findItem(updateNameP);
		
		//ListofItem
		ArrayList<MenuItem> listOfMenuItemsUpdate = new ArrayList<MenuItem>();
		String currentSearchP = "";
		int idxPUpdate = findItem(currentSearchP);
		while ( idxPUpdate == -1 && currentSearchP.equals("break")) { // cannot find food
			try {
				System.out.println("Enter 'break' to continue");
				System.out.printf("What is the name a menu item inside the updated %s: \n", updateNameP);
				currentSearchP = scUP.nextLine();
				idxPUpdate = findItem(currentSearchP);
				if (idxPUpdate != -1){
					listOfMenuItemsUpdate.add((MenuItem) listOfFood.get(idxPUpdate));
				}
				break;
			} catch (Exception e) {
				System.out.println("Error, please enter a valid food item from this list or enter 'break' to exit. ");
				for (int j = 0; j < listOfFood.size(); j++){
					if(listOfFood.get(j).getIsPromo() == IsPromo.Promo){
						System.out.printf("|  %s  |", listOfFood.get(j).getName());
					}
				}
				scUP.nextLine();
			}
		}


		//Description
		String descUpdateP = "";
		while (true) {
			try {
				System.out.printf("What is the description of %s: \n", updateNameP);
				descUpdateP = scUP.nextLine();
				break;
			} catch (Exception e) {
				System.out.println("Error, please try again: ");
				scUP.nextLine();
			}
		}
	

		//Price
		Double priceUpdateP = 0.0;
		while (true) {
			try {
				System.out.printf("What is the price of %s: \n", updateNameP);
				priceUpdateP = scUP.nextDouble();
				scUP.nextLine();
				break;
			} catch (Exception e) {
				System.out.println("Error, please enter a number: ");
				scUP.nextLine();
			}
		}

		//scUP.close();
		Item PromoItem = new PromotionalSetPackage(listOfMenuItemsUpdate, priceUpdateP, descUpdateP, updateNameP, IsPromo.Promo);
		try {
			listOfFood.remove(idxPromoUpdate);
			listOfFood.add(PromoItem);
			System.out.printf("Promo Item updated: %s (%s | $%.2f) \n", updateNameP, descUpdateP, priceUpdateP);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	
	/** 
	 * finds a given item's index given its name
	 * @param name	name of wanted object
	 * @return index of item in list
	 */
	public int findItem(String name){
		for(int i=0; i<listOfFood.size(); i++){
			if( name.equals(listOfFood.get(i).getName()) ){
				return i;
			}
		}
		return -1;
	}
}
