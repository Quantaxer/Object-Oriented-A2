package DB;
import java.util.ArrayList;

public class ConnectionDemo {
	private String pwd;
	private String user;
	
	public ConnectionDemo(){
		pwd = DBDetails.password;
		user = DBDetails.username;
		testConnection();
	}
	
	/**
	 * Here you can see example usage of the MyConnection class. Be sure to fullyResetTables at least once after downloading and 
	 * running this package. This ensures tables are rebuilt fresh and ready to use!
	 */
	public void testConnection(){
		MyConnection c = new MyConnection(user, pwd);

		boolean fullyResetTables = true; //Set this to true if you wish to rebuild/reset your tables!
		PrepStudentScript initTables = new PrepStudentScript(fullyResetTables); //called to initialize our tables
		
		//clear all DBStudent information so we have a fresh db
		c.deleteAllSavedStudent();
		
		//clear all course info and repopulate it with the provided course list
		c.repopulateCourses();

		//test addCourse
		c.addCourse("CIS*9999", "0.75", "Oopsies", "never", "");

		//test deleteCourse
		c.deleteCourse("CIS*9999");

		//test getAllCourses
		ArrayList<String> courses = c.getAllCourses();
		for (String course : courses){
			System.out.println(course);
		}
		
		//test getCourse for CIS*1500
		String course = c.findCourse("CIS*1500");
		System.out.println(course);

		//test save student
		ArrayList<String> cl = new ArrayList<String>();
		cl.add("CIS*1500,F16,87,Completed");
		cl.add("CIS*2500,F18,66,Completed");
		cl.add("CIS*2430,W20,,Planned");
		cl.add("CIS*3190,W16,,Planned");                                // What an attempt might look like (you format these however you need!)
		
		
		ArrayList<String> c2 = new ArrayList<String>();
		c2.add("THST*1040,F18,90,Completed");
		c2.add("THST*1270,F18,99,Completed");
		c2.add("THST*3170,W18,100,Completed");
		c2.add("THST*4270,W18,,Planned");
		c2.add("THST*2050,F20,,Planned");
		Student s = new Student(Integer.parseInt("123123"),"Matt","MSC",cl);
		Student s1 = new Student(Integer.parseInt("1234567"),"beep","CS",cl);
		Student s2 = new Student(Integer.parseInt("9876543"),"dumdum","BTA",c2);
		c.saveStudent(s2);
		c.saveStudent(s);
		c.saveStudent(s1);

		//test load student
		Student loadedS = c.loadStudent("123123","Matt");

		System.out.println(loadedS.getStudentNumber());
		System.out.println(loadedS.getFullName());
		System.out.println(loadedS.getDegreeString());
		cl = loadedS.getAllCourses();
		System.out.println(loadedS.getAllCourses());
		for (String cInfo : cl){
			//lets get each course from our course list!
			System.out.println(c.findCourse(cInfo.split(",")[0]));
		}


		//test delete specific student
		loadedS = c.loadStudent("123123", "Matt");
		System.out.println(loadedS.getStudentNumber());

		courses = c.getAllCourses();
		for (String course2 : courses){
			System.out.println(course2);
		}

	}
	
	public static void main (String[] args){
		ConnectionDemo p = new ConnectionDemo();
	}

}