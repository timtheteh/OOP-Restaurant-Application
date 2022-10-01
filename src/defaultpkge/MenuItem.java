
/**
 * This MenuItem class provides the detail for each MenuItem
 * @author Jared Tan
 *
 */
public class MenuItem implements Item {

	/**
   	* food type of normal item
	*/
	private MenuItemType foodType;

	/**
   	* whether item is promo
	*/
	private IsPromo menuType;

	/**
   	* name of food
	*/
	private String name;

	/**
   	* desc of normal item
	*/
	private String description;

	/**
   	* price of normal item
	*/
	private double price;

	
	/**
	 * The type of food: MainCourse/Drinks/Dessert
	 */
	public MenuItemType getFoodType() {
		return foodType;
	}

	/**
	 * Set the Food tpe
	 * @param foodType
	 */
	public void setFoodType(MenuItemType foodType) {
		this.foodType = foodType;
	}

	
	/**
	 * Constructs the Menu Item
	 * @param foodType
	 * @param menuType
	 * @param name
	 * @param description
	 * @param price
	 */
	public MenuItem(MenuItemType foodType, IsPromo MenuType, String name, String description, double price) {
		this.foodType = foodType;
		this.menuType = MenuType;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	/**
	 * Get the name of the MenuItem
	 * @return	the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the menu item
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the description of the menu item
	 * @return	desc of item
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the description of the menu item
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the price of the menu item
	 * @return	price of item
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Set the price of the menu item
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Get the menu type of the Item
	 * @return	whether item is promo
	 */
	public IsPromo getIsPromo(){
		return this.menuType;
	}

	/**
	 * Set the menu type of the item
	 * @param menuType
	 */
	public void setIsPromo(IsPromo menuType){
		this.menuType = menuType;
	}


}