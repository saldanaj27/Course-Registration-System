import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
@SuppressWarnings("serial")
public class Admin extends User implements AdminInterface {
	Scanner input = new Scanner(System.in);
	
	public Admin(String username, String password, String firstname, String lastname) {
		super(username, password, firstname, lastname);
	}
	
	@Override
	public ArrayList<Course> createCourse(ArrayList<Course> listofCourses) {
		// variables to make a new course
		String name;
		String ID;
		int maxNumber;
		int currentNumber = 0;
		String instructor;
		int sectionNumber = 0;
		String location;
		boolean cont = true;
		
		System.out.println("\nName of New Course: ");
		name = input.nextLine();
		System.out.println("ID of New Course: ");
		ID = input.nextLine();
		System.out.println("Instructor for new Course: ");
		instructor = input.nextLine();
		System.out.println("Location for new Course: ");
		location = input.nextLine();
		System.out.println("Max Number of Students for New Course: ");
		maxNumber = Integer.valueOf(input.nextLine());
		// so that there isnt a class with the same ID and section number
		while(cont == true) {
			System.out.println("Section Number for New Course: ");
			sectionNumber = Integer.valueOf(input.nextLine());
					
			for(int i = 0; i < listofCourses.size(); i++) {
				if(listofCourses.get(i).getID().equals(ID) &&
					listofCourses.get(i).getSectionNumber() == sectionNumber) {
					System.out.println("\nSection Number already exists \nTry Again\n");
				
					break;
				}
				cont = false;
			}
		}
	
		// make a new Course with the new info
		Course c = new Course(name, ID, maxNumber, currentNumber, new ArrayList<Student>(), 
				instructor, sectionNumber, location);
		
		// adds the course to the array list of courses
		listofCourses.add(c);
		
		System.out.println("\nAdded");
		
		return listofCourses;	
	}

	@Override
	public ArrayList<Course> deleteCourse(ArrayList<Course> listofCourses) {
		String ID;
		int section;
		boolean found = false;
		
		// tries to find both ID and section # because two classes might have the same ID but different section #s
		System.out.println("\nID of the Course to Delete: ");
		ID = input.nextLine();
		System.out.println("Section Number of the Course to Delete: ");
		section = Integer.valueOf(input.nextLine());
		
		for(int i = 0; i < listofCourses.size(); i++) {
			if(listofCourses.get(i).getID().equals(ID) && 
					listofCourses.get(i).getSectionNumber() == section) {
				// deletes the classes and changes the variable to true
				listofCourses.remove(i);
				found = true;
				System.out.println("\nDone");
			}
		}
		
		if(found == false) {
			System.out.println("\nCourse Not Found");
		}
		
		return listofCourses;
	}

	@Override
	public ArrayList<Course> editCourse(ArrayList<Course> listofCourses) {
		String name;
		String ID;
		int maxNumber;
		String instructor;
		int sectionNumber;
		String location;
		boolean found = false;
		
		// finds the class with ID and section #
		System.out.println("\nID of the Course to Edit: ");
		ID = input.nextLine();
		System.out.println("Section Number of the Course to Edit: ");
		sectionNumber = Integer.valueOf(input.nextLine());
		
		for(int i = 0; i < listofCourses.size(); i++) {
			if(listofCourses.get(i).getID().equals(ID) && 
					listofCourses.get(i).getSectionNumber() == sectionNumber) {
				// changes every part of the Course
				System.out.println("\nFound");
				
				System.out.println("\nName of Course: ");
				name = input.nextLine();
				listofCourses.get(i).setName(name);
				
				System.out.println("ID of Course: ");
				ID = input.nextLine();
				listofCourses.get(i).setID(ID);
				
				System.out.println("Instuctor for Course: ");
				instructor = input.nextLine();
				listofCourses.get(i).setInstructor(instructor);
				
				System.out.println("Location for Course: ");
				location = input.nextLine();
				listofCourses.get(i).setLocation(location);
				
				System.out.println("Section Number for Course: ");
				sectionNumber = Integer.valueOf(input.nextLine());
				listofCourses.get(i).setSectionNumber(sectionNumber);
				
				System.out.println("Max Number of Students for Course: ");
				maxNumber = Integer.valueOf(input.nextLine());
				listofCourses.get(i).setMaxNumber(maxNumber);

				
				found = true;
				
				System.out.println("\nDone");
				
			}
		}
		
		if(found == false) {
			System.out.println("\nCourse Not Found");
		}
		
		return listofCourses;		
	}

	@Override
	public void displayInfo(ArrayList<Course> listofCourses) {
		String ID;
		int section;
		boolean found = false;
		
		// finds the course on ID and section #
		System.out.println("\nID of the Course to Display Information: ");
		ID = input.nextLine();
		System.out.println("Section Number of the Course to Display Information: ");
		section = Integer.valueOf(input.nextLine());
		
		for(int i = 0; i < listofCourses.size(); i++) {
			if(listofCourses.get(i).getID().equals(ID) &&
					listofCourses.get(i).getSectionNumber() == section) {
				// prints the info
				listofCourses.get(i).printInfo();
				found = true;
			}
		}
		
		if(found == false) System.out.println("\nCourse Not Found");
		
	}

	@Override
	public ArrayList<Course> registerStudent(ArrayList<Course> listofCourses, ArrayList<Student> listofStudents) {
		String ID;
		int section;
		String firstname;
		String lastname;
		String username;
		String password;
		boolean found = false;
		
		System.out.println("\nID of the Course to Register this Student: ");
		ID = input.nextLine();
		System.out.println("Section Number of the Course to Register this Student: ");
		section = Integer.valueOf(input.nextLine());
		
		for(int i = 0; i < listofCourses.size(); i++) {
			if(listofCourses.get(i).getID().equals(ID) && 
					listofCourses.get(i).getSectionNumber() == section) {
				found = true;
				
				// initializes a variable for the current number of students in a specific course
				int currentNumber = listofCourses.get(i).getCurrentNumber();
				
				// if the current number is still below the max number then it will register the student
				if(currentNumber < listofCourses.get(i).getMaxNumber()) {
					System.out.println("First Name of the Student");
					firstname = input.nextLine();
					System.out.println("Last Name of the Student: ");
					lastname = input.nextLine();
					System.out.println("Username for the Student: ");
					username = input.nextLine();
					System.out.println("Password for the Student: ");
					password = input.nextLine();
					
					Student s = new Student(username, password, firstname, lastname);
						
					listofCourses.get(i).getListNames().add(s);
					
					if(!(listofStudents.contains(s))) {
						listofStudents.add(s);
					}
					
					currentNumber += 1;
					
					listofCourses.get(i).setCurrentNumber(currentNumber);
					
					System.out.println("\nAdded");
					
				} else System.out.println("\nCourse Full");
			} 
		}
		
		if(found == false) {
			System.out.println("\nCourse Not Found");
		}
		
		return listofCourses;
	}

	public void viewAllCourses(ArrayList<Course> listofCourses) {
		System.out.println("\nAll Courses: ");
		
		// prints all the info
		for(int i = 0; i < listofCourses.size(); i++) {
			listofCourses.get(i).printInfo();
		}
		
		System.out.println("\nDone");
	}

	@Override
	public void viewStudentCourses(ArrayList<Course> listofCourses) {
		String firstname;
		String lastname;
		boolean found = false;
		
		// gets name of student
		System.out.println("\nFirst Name of Student: ");
		input.nextLine();
		firstname = input.nextLine();
		System.out.println("Last Name of Student: ");
		lastname = input.nextLine();
		
		System.out.println("\nStudent Courses: ");
		
		for(int i = 0; i < listofCourses.size(); i++) {
			// gets the array list of students from the arraylist of courses
			ArrayList<Student> s = listofCourses.get(i).getListNames();
			
			for(int j = 0; j < s.size(); j++) {
				if(s.get(j).getFirstName().equalsIgnoreCase(firstname) && 
						s.get(j).getLastName().equalsIgnoreCase(lastname)) {
					found = true;
					// if the student is within the class then it will print the class info
					listofCourses.get(i).printInfo();
				}
			}
		}
		
		if(found == false) {
			System.out.println("\nStudent Not Found");
		}
		
		System.out.println("\nDone");
		
	}

	public void viewFullCourses(ArrayList<Course> listofCourses) {
		System.out.println("\nAll Full Courses: ");
		
		for(int i = 0; i < listofCourses.size(); i++) {
			// if the class is full then it will print the info
			if(listofCourses.get(i).getCurrentNumber() == listofCourses.get(i).getMaxNumber()) {
				listofCourses.get(i).printInfo();
			}
		}
		System.out.println("\nDone");
	}
	
	public void writeToFile(ArrayList<Course> listofCourses) {
		// makes a text file 
		String fileName = "FullCourses.txt";
		
		try {
			// creates a file called text.txt
			FileWriter fileWriter = new FileWriter(fileName);
			// needed to write in the file "filewriter"
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
						
			for(int i = 0; i < listofCourses.size(); i++) {
				// prints the info of the full courses
				if(listofCourses.get(i).getCurrentNumber() == listofCourses.get(i).getMaxNumber()) {
					bufferedWriter.write("Course: " + listofCourses.get(i).getName());
				}
			}

			System.out.println("\nDone");

			bufferedWriter.close();
			
		} catch(IOException exk) {
			System.out.println("Error Writing file '" + fileName + "'");
			exk.printStackTrace();
		}
	}

	public void viewStudentsInCourse(ArrayList<Course> listofCourses) {
		String ID;
		int section;
		boolean found = false;
		
		// finds the Course
		System.out.println("\nID of Course: ");
		ID = input.nextLine();
		System.out.println("Section Number of Course: ");
		section = input.nextInt();
		
		for(int i = 0; i < listofCourses.size(); i++) {
			if(listofCourses.get(i).getID().equals(ID) && 
					listofCourses.get(i).getSectionNumber() == section) {
				// gets the arraylist of students from the arraylist of courses
				ArrayList<Student> s = listofCourses.get(i).getListNames();
				
				System.out.println("\nNAMES OF STUDENTS: ");
				// prints the info of students
				for(int j = 0; j < s.size(); j++) {
					s.get(j).printInfo();
				}
				
				found = true;
				
				System.out.println("\nDone");
			}
		}
		
		if(found == false) {
			System.out.println("\nCourse Not Found");
		}
		
	}
	
	public ArrayList<Course> sortCourseBasedStudents(ArrayList<Course> listofCourses) {
		
		System.out.println("\nDone");
		
		// sorts the Courses by students
		Collections.sort(listofCourses, new Course.OrderbyStudents());
		
		return listofCourses;
	}
  }
