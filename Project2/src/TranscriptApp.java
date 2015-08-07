import java.util.Scanner;

public class TranscriptApp {
	public static void main(String[] args) {
		Transcript t = new Transcript();
		System.out.println("Welcome to the transcript application.");
		Scanner sc = new Scanner(System.in);
		
		String next = "";
		while(!next.equalsIgnoreCase("n")){
			String name = Validator.getString(sc, "Enter couse: ");
			// int credits = Validator.getInt(sc, "Enter credits: ");
			int credits = 0;
			try{
				System.out.print("Enter credits: ");
				credits = Integer.parseInt(sc.next());
			}catch(NumberFormatException e){
				e.printStackTrace();
			}finally{
				sc.nextLine();
			}
						
			String grade = Validator.getString(sc, "Enter grade: ");
			String check = "ABCDabcd";
			while(!check.contains(grade) || grade.length() != 1){
				grade = Validator.getString(sc, "Please enter a valid grade: (A, B, C, D)");
			}
			
			char g = grade.charAt(0);
			Course c = CourseDB.getCourse(name);
			//System.out.println(name);
			c.setCredits(credits);
			t.getCourseList().add(new CourseEnrollment(c, g));
			next = Validator.getString(sc, "Another course? (y/n): ");
		}
		
		System.out.println();
		System.out.println("Course                Credits      Grade      Quality Points");
		System.out.println("------                -------      -----      --------------");
		double gpa = 0.0;
		int totalCredits = 0;
		for(CourseEnrollment ce : t.getCourseList()){
			System.out.printf("%-25s%-12d%-12c%f\n", ce.getCourse().getName(), ce.getCourse().getCredits(), ce.getGrade(), ce.getGPA());
			totalCredits += ce.getCourse().getCredits();
			gpa += (double)ce.getCourse().getCredits() * ce.getGPA();
		}
		System.out.println();
		System.out.printf("%-46s%-5s%f\n", "", "GPA:", gpa / (double)totalCredits);
	}

}
