package DB;
import java.util.ArrayList;
import java.sql.*;
import java.io.*;


public class MyConnection {
	
	private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String username;
    private String password;
	

	
	public MyConnection(){
		this(DBDetails.username, DBDetails.password);

		//connect(); // test our connection
	}
	
	public MyConnection(String u, String p){
		username = u;
		password = p; 
	}
	
	/**
	 * NOT FOR GENERAL USE. Placed in a separate method to ensure opening and closing of the connection is handled
	 * every time a request is made, rather than having a connection always open and relying 
	 * on students to close it.
	 */
	public void connect(){
		//Initialize the connection
		try{
			Class.forName(DBDetails.JDBC_DRIVER);			
			//Class.forName("com.mysql.jdbc.Driver");
		    conn = DriverManager.getConnection(DBDetails.DB_URL,username,password);
		}
		catch (Exception e){
			System.out.println(e);
		}
    }
    
    /** 
	 * NOT FOR GENERAL USE. Master mutator method for db updates
	 */
    public void dbUpdate(String sql){
        connect();
        try {
            stmt = conn.createStatement();
			stmt.executeUpdate(sql);
        }
		//catch any issues along the way
		catch (Exception e){
			System.out.println(e);
		}
		//close any/all connections
		finally{
			try{
				stmt.close();
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
    }

	
	/**
	 * Returns a list of strings, where each string represents a full course. You will need to parse this string to rebuild the course
	 * 
	 * @return List of Strings representing every available course
	 */
	public ArrayList<String> getAllCourses(){
		ArrayList<String> cList = new ArrayList<String>();
		System.out.println("Querying for all courses...");
		
        String sql = "SELECT * FROM Courses;";
		connect();
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
			while(rs.next()){
				cList.add(rs.getString("code") +","+ rs.getString("credit") +","+ rs.getString("name") +","+ rs.getString("semester") +"," + rs.getString("prereq"));
			}

		}
        
        //catch any issues along the way
		catch (Exception e){
			System.out.println(e);
		}
		//close any/all connections
		finally{
			try{
				stmt.close();
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		return cList;
	}
	
	
	/**
	 * Finds a specific course based only on the course code. Returns all information about that course in one single string.
	 * You will need to parse this string to rebuild the course.
	 * 
	 * @param courseCode A string course code (such as CIS*1500)
	 * @return A string of all the course details (course code, course credit, course name, course prerequisites)
	 */
	public String findCourse(String courseCode){
		System.out.println("Querying for course: " + courseCode);
		String result = null;
		
		String sql = "SELECT * FROM Courses WHERE code = '" + courseCode + "';";		
        connect();
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

			if (rs.next()){
				result = rs.getString("code") +","+ rs.getString("credit") +","+ rs.getString("name") +","+ rs.getString("semester") +","+ rs.getString("prereq");
				return result;
			}

			System.out.println("No matching course code found!");
		}

        //catch any issues along the way
		catch (Exception e){
			System.out.println(e);
		}
		//close any/all connections
		finally{
			try{
				stmt.close();
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		return null;
	}

	public void addCourse(String code, String credit, String name, String semester, String prereq){
		String sql = "INSERT INTO Courses(code,credit,name,semester,prereq) VALUES('" + code + "','" + credit + "','" + name + "','" + semester + "','" + prereq + "');";
		System.out.println("Adding course with values: ('" + code + "','" + credit + "','" + name + "','" + semester + "','" + prereq + "')" );

		dbUpdate(sql);
	}
	
	/**
	 * Saves information to the database. Accepts a DBStudent object only. See the DBStudent class for details
	 * on creating the DBStudent object.
	 * 
	 * @param s A DBStudent object. Its contents will be saved to the database.
	 */
	public void saveStudent(Student s){
		System.out.println("Saving DBStudent object");
		String id = Integer.toString(s.getStudentNumber());
		String name = s.getFullName();
		String degree = s.getDegreeString();
		ArrayList<String> cList = new ArrayList<>();
		cList = s.getAllCourses();
			
		for (String course : cList){
			String sql = "INSERT INTO SavedStudent(id,name,degree,course) VALUES('" + id + "','" + name + "','" + degree + "','" + course + "');";
			dbUpdate(sql);
        }
	}
	
	/**
	 * Loads and returns a DBStudent object, containing any available information.
	 * 
	 * @param id The student's id
	 * @param name The student's name
	 * @return DBStudent object which contains the student id, name, degree and course information.
	 */
	public Student loadStudent(String id, String name){
		System.out.println("Returning saved student object");
		Student s = new Student(); //what will be returned
		ArrayList<String> cList = new ArrayList<String>(); //where we store the courses
        
		String sql = "SELECT * FROM SavedStudent WHERE id = '" + id + "' AND name = '" + name + "';" ;
        connect();
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

			while(rs.next()){
				s.setStudentNumber(Integer.parseInt(rs.getString("id")));
				s.setFullName(rs.getString("name"));
				s.setDegreeString(rs.getString("degree"));
				cList.add(rs.getString("course"));
			}
			s.setAllCourses(cList);

			conn.close();
			stmt.close();
			rs.close();
		}
        
        //catch any issues along the way
		catch (Exception e){
			System.out.println(e);
		}
		//close any/all connections
		finally{
			try{
				stmt.close();
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		return s;
	}
	
	/**
	 * Deletes any courses matching a given course code from the database.
	 * 
	 * @param code The course code as a string (ex. CIS*1500)
	 */
	public void deleteCourse(String code){
		System.out.println("Deleting course: " + code);

		String sql = "DELETE FROM Courses WHERE code = '" + code + "';" ;
		dbUpdate(sql);
	}
	
	/**
	 * Completely deletes all records matching a given saved student ID and Name.
	 * 
	 * @param id The student id
	 * @param name The student name
	 */
	public void deleteSavedStudent(String id, String name){
		System.out.println("Deleting information for student id: " + id);
        
		String sql = "DELETE FROM SavedStudent WHERE id = '" + id + "' AND name = '" + name + "';" ;
	    dbUpdate(sql);
	}

	/**
	 * Completely deletes all saved student information from the database.
	 * 
	 */
	public void deleteAllSavedStudent(){
		System.out.println("Deleting all saved student information...");
        
		String sql = "DELETE FROM SavedStudent;" ;
	    dbUpdate(sql);
	}
	
    /**
	 * NOT FOR GENERAL USE. Completely deletes all Course information from the database.
	 * 
	 */
	public void deleteAllCourses(){
		System.out.println("Deleting all courses...");

		String sql = "DELETE FROM Courses;" ;
		dbUpdate(sql);
	}
	
	/**
	 * Deletes all current course records in the Courses table, and replaces them with the courselistA2 text file contents.
	 Note that the textfile must be in the same directory as the program is run from or this method will
	 crash. There is no error checking.
	 */
	public void repopulateCourses(){
		System.out.println("Repopulating Courses table...");
		//read courselist into stream
		try{
			File file = new File("./courselistA2.txt"); 
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			
			String st;
			ArrayList<String> rawCourses = new ArrayList<String>();
			while ((st = br.readLine()) != null) {
				rawCourses.add(st);
			}
			String[] splitC;
			for (String rawCourse : rawCourses){
				splitC = rawCourse.split(",");
				
				if (splitC.length > 4){
					addCourse(splitC[0],splitC[1],splitC[2],splitC[3],splitC[4]);
				}else{
					addCourse(splitC[0],splitC[1],splitC[2],splitC[3],"");
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * NOT FOR GENERAL USE. Issues a custom UPDATE command to the mysql DB. This must be a mutator command (INSERT, DROP, UPDATE...)
	 * Use at your own risk. TA's not liable for databases broken by use of this command.
	 * 
	 * @param command the string command you wish to execute.
	 */
	public void customUpdate(String command){
		System.out.println("Executing Custom Update Command: " + command);
        
        dbUpdate(command);
    }
	
	/**
	 * NOT FOR GENERAL USE. Issues a custom EXECUTE command to the mysql DB. This must be an accessor command (SELECT .. )
	 * Prints the returned results to the screen.
	 * Use at your own risk. TA's not liable for databases broken by use of this command.
	 * 
	 * @param command the string command you wish to execute.
	 */
    public void customExecute(String command){
		System.out.println("Executing Custom Execute Command: " + command);
        connect();
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(command);

			while (rs.next()){
				System.out.println(rs.toString());
			}
        }

        //catch any issues along the way
		catch (Exception e){
			System.out.println(e);
		}
		//close any/all connections
		finally{
			try{
				stmt.close();
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
