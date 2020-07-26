import java.util.*;
import java.io.*;

public class Main {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		// created an ArrayList for both Courses and Students
		// will hold all courses with their information
		ArrayList<Course> listofCourses = new ArrayList<>();
		// will hold all students with their information
		ArrayList<Student> listofStudents = new ArrayList<>();
		
		// this doesnt create a file, just gets a name of a supposed file
		File file = new File("CourseList.ser");
		File file2 = new File("StudentList.ser");
		
		// made an Admin Object to call the methods for Admin
		// only one admin log in
		Admin admin = new Admin("Admin01", "Admin001", "Javier", "Saldana");
		
		
		
		// TESTING PURPOSES
		// made a Student Object to call the methods for Student
		Student student = new Student("Student01", "Student001", "Javier", "Saldana");
		listofStudents.add(student);
		
		// used later
		Student s = new Student("", "", "", "");
		
		
		
		
		
		
		
		if(file.exists()) { // multiple times using this program
			 try{
				 
			      FileInputStream fis = new FileInputStream("CourseList.ser");
			      ObjectInputStream ois = new ObjectInputStream(fis);
			      
			      // Cast as ArrayList<Course> (casts from object to ArrayList<Course>)
			      // gets a "Type Safety; Unchecked cast" error so i put a suppresswarnings (ask why)
			      listofCourses = (ArrayList<Course>) ois.readObject();
			      ois.close();
			      fis.close();
			 }
			 catch(IOException ioe) {
			      ioe.printStackTrace();
			      return;
			 }
			 catch(ClassNotFoundException cnfe) {
			      cnfe.printStackTrace();
			      return;
			 }
		} else { // USED ONLY FOR THE VERY FIRST TIME RUNNING THE PROGRAM
			
			// reading the file "MyUniversityCourses.csv"
			String filename = "MyUniversityCourses.csv";
			String line = null;
			
			// FIRST TIME READING THE COURSE LIST
			try {
				FileReader fileReader = new FileReader(filename);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
				// reads the first line (the line of titles because its irrelevant and makes code easier) 
				bufferedReader.readLine();
				
				// goes through each line that is not empty
				while((line = bufferedReader.readLine()) != null) {
					
					// split the lines whenever there was a comma leaving each line with only the necessary material
					String[] lines = line.split(",");
					
					//made a Course with the lines (I repeat this process until there are no more lines left
					Course c = new Course(lines[0], lines[1], Integer.parseInt(lines[2]), 
							Integer.parseInt(lines[3]), new ArrayList<Student>(), lines[5], 
							Integer.parseInt(lines[6]), lines[7]);
					
					// I add the course into the ArrayList of Courses
					listofCourses.add(c);
				}
				bufferedReader.close();
			} catch(FileNotFoundException ex) {
				System.out.println("Unable to open file '" + filename + "'");
				
				ex.printStackTrace();
			} catch(IOException ex) {
				System.out.println("Error reading file '" + filename + "'");
				ex.printStackTrace();
			}
		}
		
		
		// deserializing the list of students with username and passwords (FOR STUDENT LOG IN)
		if(file2.exists()) { // multiple times using this program
			 try{
				 
			      FileInputStream fis = new FileInputStream("StudentList.ser");
			      ObjectInputStream ois = new ObjectInputStream(fis);
			      
			      // Cast as ArrayList<Course> (casts from object to ArrayList<Course>)
			      // gets a "Type Safety; Unchecked cast" error so i put a suppresswarnings (ask why)
			      listofStudents = (ArrayList<Student>) ois.readObject();
			      ois.close();
			      fis.close();
			 }
			 catch(IOException ioe) {
			      ioe.printStackTrace();
			      return;
			 }
			 catch(ClassNotFoundException cnfe) {
			      cnfe.printStackTrace();
			      return;
			 }
		}
		
		

		
		
		
		
		
		
		// beginning of the Log In process
		String user;
		String pass;	
		int option;
			
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		System.out.println("Select your Log-In");
		System.out.println("1. Admin \n2. Student");
		option = input.nextInt();
		
		if(option == 1) { // ADMIN LOG IN		
			// variable to determine if Log In was successful
			boolean LogIn = false;
			
			
			do { // Username and Password
				System.out.println("\nUsername: ");
				input.nextLine();
				user = input.nextLine();
				System.out.println("Password: ");
				pass = input.nextLine();
				
				// only one set of user and pass
				if(!(user.equals("Admin01")) || !(pass.equals("Admin001"))) {
					System.out.println("\nTry Again");
				} else {
					System.out.println("\n-------------LOG-IN SUCCESSFUL-------------");
					// Log In was successful so the boolean variable is now true
					LogIn = true;
				}
			} while(LogIn == false); // does this process until the Log In is successful
			
			
			if(LogIn == true) { // if they successfully logged in
				System.out.println("\nSelect Task");
				System.out.println("1. Course Management \n2. Reports");
				option = input.nextInt();
				
				if(option == 1) { // Course Management
					do { 
						// prints out the menu
						System.out.println("\nOptions:");
						System.out.println("1. Create a Course");
						System.out.println("2. Delete a Course");
						System.out.println("3. Edit a Course");
						System.out.println("4. Display Information for a Course");
						System.out.println("5. Register a Student");
						System.out.println("6. Exit");
						option = input.nextInt();
						
						
						// calls the methods from the Admin Class
						// each method requires the ArrayList of Courses
						if(option == 1) {
							admin.createCourse(listofCourses);
						} else if(option == 2) {
							admin.deleteCourse(listofCourses);
						} else if(option == 3) {
							admin.editCourse(listofCourses);
						} else if(option == 4) {
							admin.displayInfo(listofCourses);
						} else if(option == 5) {
							admin.registerStudent(listofCourses, listofStudents);
						}
						
					} while(option != 6); // exits out menu
					
					
				} else { // Reports
					do {
						// prints menu
						System.out.println("\nOptions:");
						System.out.println("1. View all Courses");
						System.out.println("2. View all Full Courses");
						System.out.println("3. Write to a File the List of Courses that are Full");
						System.out.println("4. View Names of Students Registered in a Course");
						System.out.println("5. View Courses of a Student");
						System.out.println("6. Sort Courses Based on Students");
						System.out.println("7. Exit");					
						option = input.nextInt();
						
						// again, each method requires the ArrayList of Courses
						if(option == 1) {
							admin.viewAllCourses(listofCourses);
						} else if(option == 2) {
							admin.viewFullCourses(listofCourses);
						} else if(option == 3) {
							admin.writeToFile(listofCourses);
						} else if(option == 4) {
							admin.viewStudentsInCourse(listofCourses);
						} else if(option == 5) {
							admin.viewStudentCourses(listofCourses);
						} else if(option == 6) {
							admin.sortCourseBasedStudents(listofCourses);
						} 
						
					} while(option != 7); // exits out menu
					
					
				} // end of picking tasks
			} // end of Admin
			
			
			
			
			
		} else { // STUDENT LOG IN
			// variable to determin if Log In was successful
			boolean LogIn = false;
			
			
			do { // Username and Password
				System.out.println("\nUsername: ");
				user = input.next();
				System.out.println("Password: ");
				pass = input.next();
				
				// it is going to check the list of Students for a Student who has the same username and password
				for(int i = 0; i < listofStudents.size(); i++) {
					if(listofStudents.get(i).getUsername().equals(user) && 
							listofStudents.get(i).getPassword().equals(pass)) {
						System.out.println("\n-------------LOG-IN SUCCESSFUL-------------");
						
						// grabs the students information for the methods
						s = listofStudents.get(i);
						
						// if found, Log In is considered successful and the loop ends
						LogIn = true;
						break;
					}
				}
			
				if(LogIn == false) System.out.println("\nTry Again");
				
			} while(LogIn == false);
			
			
			if(LogIn == true) { // if they successfully logged in
				do { // Course Management
					// prints out menu
					System.out.println("\nCourse Management");
					System.out.println("1. View all Courses");
					System.out.println("2. View all Courses not Full");
					System.out.println("3. Register in a Course");
					System.out.println("4. Withdraw from a Course");
					System.out.println("5. View all Courses Current Student is Registered in");
					System.out.println("6. Exit");
					option = input.nextInt();
					
					// each method requires the ArrayList of Courses
					if(option == 1) {
						student.viewAllCourses(listofCourses, s);
					} else if(option == 2) {
						student.viewOpenCourses(listofCourses);
					} else if(option == 3) {
						student.registerCourse(listofCourses, s);
					} else if(option == 4) {
						student.withdrawCourse(listofCourses, s);
					} else if(option == 5) {
						student.viewRegisteredCourses(listofCourses, s);
					}
					
				} while(option != 6); // end of menu
			}
		} // end of Student Log In
		
		
		
		
		// takes in the data of the arraylist of courses and puts in into a new file
		try { 
			// creates a file of that name
            FileOutputStream fos = new FileOutputStream("CourseList.ser");
            
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // writes the arraylist of courses to this new file
            oos.writeObject(listofCourses);
            oos.close();
            fos.close();
		} catch (IOException ioe) {
            ioe.printStackTrace();
        }
		
		
		
		// takes in the data of the arraylist of Students and puts in into a new file
		try { 
			// creates a file of that name
		    FileOutputStream fos = new FileOutputStream("StudentList.ser");
		            
            ObjectOutputStream Oos = new ObjectOutputStream(fos);
            // writes the arraylist of courses to this new file
            Oos.writeObject(listofStudents);
            Oos.close();
            fos.close();
		} catch (IOException ioe) {
            ioe.printStackTrace();
        }		

		
		System.out.println("\nClosing...");
	} // end of main

}
