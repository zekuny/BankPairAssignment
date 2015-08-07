public class CourseDB {
	public static Course getCourse(String name){
		Course c = new Course();
		if(name.equalsIgnoreCase("java 101")){
			c.setCourseID(101);
			c.setDescription("java is the best");
			c.setName(name);
			c.setCredits(3);
		}else if(name.equalsIgnoreCase("english 202")){
			c.setCourseID(202);
			c.setDescription("english is the best");
			c.setName(name);	
			c.setCredits(2);
		}else if(name.equalsIgnoreCase("python 103")){
			c.setCourseID(103);
			c.setDescription("phthon is the best");
			c.setName(name);	
			c.setCredits(3);
		}else if(name.equalsIgnoreCase("data structure 410")){
			c.setCourseID(410);
			c.setDescription("data structure is important");
			c.setName(name);	
			c.setCredits(4);
		}else if(name.equalsIgnoreCase("algorithms 550")){
			c.setCourseID(550);
			c.setDescription("algorithms is very very hard");
			c.setName(name);	
			c.setCredits(5);
		}else{
			c.setCourseID(-1);
			c.setDescription("unknow");
			c.setName("unknow");	
			c.setCredits(0);
		}
		return c;
	}
}
