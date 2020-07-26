import java.util.*;

@SuppressWarnings("serial")
public class Student extends User implements StudentInterface, java.io.Serializable{

	transient Scanner input = new Scanner(System.in);
	
	// constructor
	public Student(String username, String password, String firstname, String lastname) {
		super(username, password, firstname, lastname);
	}
	
	public void viewAllCourses(ArrayList<Course> listofCourses, Student s) {	
		System.out.println("\nCourses: ");
		// prints all courses 
		
		for(int i = 0; i < listofCourses.size(); i++) {
			listofCourses.get(i).printInfo();
		}
		
		System.out.println("\nDone");
	}

	@Override
	public void viewOpenCourses(ArrayList<Course> listofCourses) {	
		System.out.println("\nCourses: ");
		
		for(int i = 0; i < listofCourses.size(); i++) {
			if(listofCourses.get(i).getCurrentNumber() < 
					listofCourses.get(i).getMaxNumber()) {
				// prints all classes where the current student # is less than the max
				listofCourses.get(i).printInfo();
			}
		}
		
		System.out.println("\nDone");
	}

	@Override
	public void registerCourse(ArrayList<Course> listofCourses, Student s) {
		String ID;
		int section;
		boolean found = false;
		
		System.out.println("\nID of the Course: ");
		ID = input.nextLine();
		System.out.println("Section Number of the Course: ");
		section = input.nextInt();
		
		for(int i = 0; i < listofCourses.size(); i++) {
			if(listofCourses.get(i).getID().equals(ID) &&
					listofCourses.get(i).getSectionNumber() == section) {
				
				// registers a student
				found = true;
				listofCourses.get(i).getListNames().add(s);
				
				int currentNumber = listofCourses.get(i).getCurrentNumber();
				currentNumber += 1;
				
				listofCourses.get(i).setCurrentNumber(currentNumber);
				
				System.out.println("\nAdded");
			}
		}
		
		if(found == false) {
			System.out.println("\nCourse Not Found");
		}
		
	}

	@Override
	public void withdrawCourse(ArrayList<Course> listofCourses, Student s) {
		String ID;
		int section;
		boolean found = false;
		
		System.out.println("\nID of the Course: ");
		input.nextLine();
		ID = input.nextLine();
		System.out.println("Section Number of the Course: ");
		section = Integer.valueOf(input.nextLine());
		
		for(int i = 0; i < listofCourses.size(); i++) {
			if(listofCourses.get(i).getID().equals(ID) && 
					listofCourses.get(i).getSectionNumber() == section) {
				
				// withdraws a student from a course
				found = true;
				listofCourses.get(i).getListNames().remove(s);
				
				System.out.print("\nWithdrawn");
			}
		}
		
		if(found == false) System.out.println("\nCourse Not Found");
		
	}

	@Override
	public void viewRegisteredCourses(ArrayList<Course> listofCourses, Student s) {
			System.out.println("\nCourses: ");
			
			for(int i = 0; i < listofCourses.size(); i++) {
				if(listofCourses.get(i).getListNames().contains(s)) {
					// prints info of all the courses the student is in
					listofCourses.get(i).printInfo();
				}
			}
	}
	
	// getters and setters
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstname;
	}
	
	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastName() {
		return lastname;
	}
	
	public void setLastName(String lastname) {
		this.lastname = lastname;
	}
	
	public void printInfo() {
		System.out.println("  " + firstname + " " + lastname);
	}
}
