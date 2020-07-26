import java.util.ArrayList;

public interface StudentInterface {

	public void viewOpenCourses(ArrayList<Course> c);
	
	public void registerCourse(ArrayList<Course> c, Student s);
	
	public void withdrawCourse(ArrayList<Course> c, Student s);
	
	public void viewRegisteredCourses(ArrayList<Course> c, Student s);
}
