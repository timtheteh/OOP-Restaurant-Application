import java.time.LocalDate;
import java.util.HashMap;

/**
 * The SaleRevenueReport class prints the output of the sales revenue report.
 * @author Yong Xuan
 *
 */
public class SaleRevenueReport {

	/**
	 * Total revenue earned by the restaurant in the period.
	 */
	private double totalRevenue;
	
	/**
	 * The items sold by the restaurant, and their corresponding quantity in the period.
	 */
	private HashMap<Item, Integer> saleItems;
	
	/**
	 * The specified period of the report.
	 */
	private String period;

	/**
	 * Creates a new SaleRevenueReport object with the parameters below.
	 * @param totalRevenue the total revenue earned by the restaurant in the period
	 * @param saleItems    the items sold by the restaurant, and their corresponding quantity in the period
	 * @param period       the specified period of the report
	 */
	public SaleRevenueReport(double totalRevenue, HashMap<Item, Integer> saleItems, String period) {
		this.totalRevenue = totalRevenue;
		this.saleItems = saleItems;
		this.period = period;
	}

	/**
	 * Returns total revenue of the report.
	 * @return the total revenue of the report
	 */
	public double getTotalRevenue() {
		return this.totalRevenue;
	}

	/**
	 * Set total revenue of the sales revenue report.
	 * @param totalRevenue the total revenue earned by the restaurant in the period
	 */
	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	
	/**
	 * Returns the items sold by the restaurant, and their corresponding quantity in the period.
	 * @return the items (items and quantities) of the report
	 */
	public HashMap<Item, Integer> getSaleItems() {
		return this.saleItems;
	}

	/**
	 * Set the items sold by the restaurant, and their corresponding quantity in the period.
	 * @param saleItems the sales items (items and quantities) of the report
	 */
	public void setSaleItems(HashMap<Item, Integer> saleItems) {
		this.saleItems = saleItems;
	}

	/**
	 * Returns the specified period of the report.
	 * @return the specified period of the report
	 */
	public String getPeriod() {
		return this.period;
	}

	/**
	 * Sets the specified period of the report.
	 * @param period the period of the report
	 */
	public void setPeriod(LocalDate startPeriod, LocalDate endPeriod) {
		if (startPeriod.isAfter(endPeriod)) {
			System.out.println("End period must be after start period.");
			return;
		}
		String period = startPeriod.toString() + " to " + endPeriod.toString();
		this.period = period;
	}

	/**
	 * Prints out the report onto the user interface.
	 */
	public void printReport() {
		System.out.println("  Sales Revenue Report  ");
		System.out.println("------------------------");
		System.out.println(period);
		System.out.println("------------------------");
		int count = 1;
		for (Item item : saleItems.keySet()) {
			System.out.printf("%d) %-20s (%.2f) \t Qty: %d\n", count, item.getName(),item.getPrice(),saleItems.get(item));
			//System.out.println(count + ") " + item.getName() + " (" + item.getPrice() + ")\t\t" + "Qty: " + saleItems.get(item));
			count++;
		}
		System.out.println("Total Revenue for Period: " + totalRevenue);
	}

}