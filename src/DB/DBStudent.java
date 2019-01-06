package DB;
import java.util.ArrayList;

public class DBStudent {

	private String studentName;
	private String studentId;
	private String degree = "";
	private ArrayList<String> courses;
    
	public DBStudent(){
		studentId = null;
		studentName = null;
		courses = new ArrayList<String>();
	}
	
	public DBStudent(String id, String sName){
        studentId = id;
		studentName = sName;
		courses = new ArrayList<String>();
	}
	
	public DBStudent(String id, String sName, ArrayList<String> courseList){
        studentId = id;
		studentName = sName;
		courses = courseList;
	}
    
    /**
     * Suggested constructor for your DBStudent object. Stores student information in the format used by the database.
     * Student id and student name should never be empty!
     * 
     * @param id The students id
     * @param sName The students name
     * @param deg The degree name in which the student is enrolled
     * @param courseList A list of courses which the student has taken (Ex. "CIS*2430,F18,78,IN PROGRESS")
     */
	public DBStudent(String id, String sName, String deg, ArrayList<String> courseList){
        studentId = id;
		studentName = sName;
		degree = deg;
		courses = courseList;
	}
    
    public void setId(String id){
		studentId = id;
	}
	public void setName(String sName){
		studentName = sName;
	}
	public void setDegree(String deg){
		degree = deg;
    }
	public void setCourses(ArrayList<String> courseList){
		courses = courseList;
	}
    
    public String getId(){
		return studentId;
	}
	public String getName(){
		return studentName;
	}
	public String getDegree(){
		return degree;
	}
	public ArrayList<String> getCourses(){
		return courses;
	}
}
