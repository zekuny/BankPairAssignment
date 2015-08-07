import java.util.List;
import java.util.ArrayList;

public class Transcript {
	private List<CourseEnrollment> courseList;
	private double gpa; 
	private int totalCredits; 
	
	public Transcript(){
		courseList = new ArrayList<CourseEnrollment>();
		gpa = 0.0;
		totalCredits = 0;
	}
	
	public List<CourseEnrollment> getCourseList(){
		return courseList;
	}
	
	public void addCourse(CourseEnrollment courseenrollment){
		courseList.add(courseenrollment);
	}
	
	public double getOverallGPA(){
		gpa = 0.0;
		totalCredits = 0;
		for(CourseEnrollment ce : courseList){
			totalCredits += ce.getCourse().getCredits();
			gpa += (double)ce.getCourse().getCredits() * ce.getGPA();
		}
		
		double result = 0.0;
		try{
			result = gpa / (double)totalCredits;
		}catch(ArithmeticException e){
			e.printStackTrace();
		}catch(Exception e){
			System.err.println(e.toString());
		}finally{
			System.out.println("This is the message from finally block.");
		}
		return result;
	}
	
	public String getFormattedGPA(){
		getOverallGPA();
		return String.format("%-46s%-5s%f\n", "", "GPA:", gpa / (double)totalCredits);
	}
}
