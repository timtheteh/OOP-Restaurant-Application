/**
 * This Item class provides and manages the details of items on the menu
 * @author Jared Tan
 *
 */

 
public interface Item {

	/**
   	* name is the name of the food item.
   	*/
	String name = "";

	/**
   	* description is the description of the food item.
   	*/
	String description = "";

	/**
   	* price is the price of the food item.
   	*/
	double price = 0.0;

	/**
   	* menuType is the menuType of the food item, whether food item is promotional or not.
   	*/
	IsPromo menuType = IsPromo.Normal;
	
	/**
	 * Get the name of the Item
	 * @return
	 */
	String getName();

	/**
	 * Set the name of the Item
	 * @param name
	 */
	void setName(String name);

	/**
	 * Get the description of the item
	 * @return
	 */
	String getDescription();

	/**
	 * Set the description of the item
	 * @param description
	 */
	void setDescription(String description);

	/**
	 * Get the price of the Item
	 * @return
	 */
	double getPrice();

	/**
	 * Set the price of the item
	 * @param price
	 */
	void setPrice(double price);

	/**
	 * Get the menu type of the Item
	 * @return
	 */
	IsPromo getIsPromo();

	/**
	 * Set the menu type of the item
	 * @param menuType
	 */
	void setIsPromo(IsPromo menuType);

}