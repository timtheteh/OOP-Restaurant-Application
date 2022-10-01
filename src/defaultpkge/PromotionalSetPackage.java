import java.util.*;
/**
 * This PromotinalSetPackage class provides the details for PromotionalSetPackage
 * @author Jared Tan
 *
 */
public class PromotionalSetPackage implements Item {

	
	private ArrayList<MenuItem> listOfMenuItems;
	private double price;
	private String description;
	private String name;
	private IsPromo menuType;


	/**
	 * Constructor for the PromotionalSet package
	 * @param listOfMenuItems
	 * @param price
	 * @param description
	 * @param name
	 * @param menuType
	 */

	public PromotionalSetPackage(ArrayList<MenuItem> listOfMenuItems, double price, String description, String name,
			IsPromo menuType) {
		this.listOfMenuItems = listOfMenuItems;
		this.price = price;
		this.description = description;
		this.name = name;
		this.menuType = menuType;
	}

	/**
	 * Returns the list of menu item in the promotional set package
	 */
	public ArrayList<MenuItem> getListOfMenuItems() {
		return listOfMenuItems;
	}

	/**
	 * 
	 * @param listOfMenuItems
	 */
	public void setListOfMenuItems(ArrayList<MenuItem> listOfMenuItems) {
		this.listOfMenuItems = listOfMenuItems;
	}

	/**
	 * Get the price of the promotional set package
	 * @return
	 */
	public double getPrice() {
		return this.price;
	}

	/**
	 * Set the price of the promotional set package
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Get the description of the promotional set package
	 * @return
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Set the description of the promotional set package
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the name of the promotional set package
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set the name of the promotional set package
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the menu type of the Item
	 * @return
	 */
	public IsPromo getIsPromo(){
		return menuType;
	}

	/**
	 * Set the menu type of the item
	 * @param menuType
	 */
	public void setIsPromo(IsPromo menuType){
		this.menuType = menuType;
	}


}