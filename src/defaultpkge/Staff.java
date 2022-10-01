/**
 * The Staff class is for staff member objects who work in the restaurant.
 * @author Yong Xuan
 *
 */
public class Staff {

	/**
	 * The staff member's name.
	 */
	private String name;
	
	/**
	 * The staff member's gender.
	 */
	private String gender;
	
	/**
	 * The staff member's employee ID.
	 */
	private int employeeID;
	
	/**
	 * The staff member's job title.
	 */
	private String jobTitle;
	
	/**
	 * Creates a new Staff object with parameters name, gender, employeeID, jobTitle.
	 * @param name              the staff's name
	 * @param gender            the staff's gender
	 * @param employeeID        the staff's ID
	 * @param jobTitle          the staff's job title
	 */
	public Staff(String name, String gender, int employeeID, String jobTitle) {
		this.name = name;
		this.gender = gender;
		this.employeeID = employeeID;
		this.jobTitle = jobTitle;
	}
	
	/**
	 * Returns the staff's name.
	 * @return the staff's name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the staff of name.
	 * @param name the staff's name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the staf's gender.
	 * @return the staff's gender
	 */
	public String getGender() {
		return this.gender;
	}

	/**
	 * Sets the staff's gender.
	 * @param gender the staff's gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Returns the staff's employee ID.
	 * @return the staff's employeeID
	 */
	public int getEmployeeID() {
		return this.employeeID;
	}

	/**
	 * Sets the staff's employee ID.
	 * @param employeeID the staff's employee ID
	 */
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	/**
	 * Returns the staff's job title.
	 * @return the staff's job title
	 */
	public String getJobTitle() {
		return this.jobTitle;
	}

	/**
	 * Sets the staff's job title.
	 * @param jobTitle  the staff's job title
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}


}