public class CourseEnrollment {
	private Course c;
	private char grade;

	public CourseEnrollment(Course c, char g){
		this.c = c;
		grade = g;
	}
	
	public CourseEnrollment(){
		c = new Course();
		grade = ' ';
	}

	public Course getCourse() {
		return c;
	}

	public void setCourse(Course c) {
		this.c = c;
	}

	public char getGrade() {
		return grade;
	}

	public void setGrade(char grade) {
		this.grade = grade;
	}
	
	public double getGPA(){
		switch(grade){
		case 'a':
		case 'A':
			return 4.0;
		case 'b':
		case 'B':
			return 3.0;
		case 'c':
		case 'C':
			return 2.0;
		case 'd':
		case 'D':
			return 1.0;
		default:
			return 0.0;
		}
	}
}
