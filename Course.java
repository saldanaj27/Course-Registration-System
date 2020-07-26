import java.util.*;

@SuppressWarnings("serial")
public class Course implements Comparable<Course>, java.io.Serializable {
	private String name;
	private String ID;
	private int maxNumber;
	private int currentNumber;
	private ArrayList<Student> listNames;
	private String instructor;
	private int sectionNumber;
	private String location;
	
	public Course() { }
	
	public Course(String name, String ID, int maxNumber, int currentNumber,
			ArrayList<Student> listNames, String instructor, int sectionNumber, String location) {
		this.setName(name);
		this.setID(ID);
		this.setMaxNumber(maxNumber);
		this.setCurrentNumber(currentNumber);
		this.setListNames(listNames);
		this.setInstructor(instructor);
		this.setSectionNumber(sectionNumber);
		this.setLocation(location);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		this.ID = iD;
	}

	public int getMaxNumber() {
		return maxNumber;
	}

	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}

	public int getCurrentNumber() {
		return currentNumber;
	}

	public void setCurrentNumber(int currentNumber) {
		this.currentNumber = currentNumber;
	}

	public ArrayList<Student> getListNames() {
		return listNames;
	}

	public void setListNames(ArrayList<Student> listNames) {
		this.listNames = listNames;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public int getSectionNumber() {
		return sectionNumber;
	}

	public void setSectionNumber(int sectionNumber) {
		this.sectionNumber = sectionNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public void printInfo() {
		System.out.println("\nCourse Name: " + this.name);
		System.out.println("Course ID: " + this.ID);
		System.out.println("Course Instructor: " + this.instructor);
		System.out.println("Course Max Number of Students: " + this.maxNumber);
		System.out.println("Course Current Number of Students: " + this.currentNumber);
		System.out.println("Section Number: " + this.sectionNumber);
		System.out.println("Course Location: " + this.location);
		
	}
	
	public static class OrderbyStudents implements Comparator<Course> {
		
		@Override
		public int compare(Course c1, Course c2) {
			return c1.currentNumber > c2.currentNumber ? 1 : (c1.currentNumber < c2.currentNumber ? -1 : 0);
		}
	}

	@Override
	public int compareTo(Course o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String toString() {
		return "Course [Name = " + name + ", ID = " + ID + ", Max Number = " + maxNumber + 
				", Current Number = " + currentNumber + ", Instructor = " + instructor + ", Section Number = " + 
				sectionNumber + ", Location = " + location + "]";
	}
} 