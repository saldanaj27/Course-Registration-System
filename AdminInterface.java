import java.util.*;

// Admin Interface
public interface AdminInterface {
	
	// signatures of Admin methods
	public ArrayList<Course> createCourse(ArrayList<Course> c);
	
	public ArrayList<Course> deleteCourse(ArrayList<Course> c);
	
	public ArrayList<Course> editCourse(ArrayList<Course> c);
	
	public void displayInfo(ArrayList<Course> c);
	
	public ArrayList<Course> registerStudent(ArrayList<Course> c, ArrayList<Student> s);
	
	public void viewStudentCourses(ArrayList<Course> c);
}
