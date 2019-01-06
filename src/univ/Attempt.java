package univ;
import java.util.Comparator;
/**
* This is the class for the attempt of a course
*
* @author Peter Hudel
* @version Nov. 26, 2018
*/
public class Attempt
{
	private Course courseAttempted;
	private String grade;
	private String semesterTaken;
	private String status;
	
   /**
    * Constructor for objects of class Attempt
    */
	public Attempt() 
	{
		this.courseAttempted = null;
		this.grade = "";
		this.semesterTaken = "";
		this.status = "";
	}
	
	/**
	 * Parameterized constructor for the Attempt class
	 * @author Peter Hudel
	 * @param courseCode the course code
	 * @param grade the grade the student got
	 * @param semester the semester the course was taken
	 * @param stat whether the course is in progress or completed
	 */
	public Attempt(Course courseCode, String grade, String semester, String stat) 
	{
		this.courseAttempted = courseCode;
		this.grade = grade;
		this.semesterTaken = semester;
		this.status = stat;
	}
	
   /**
    * This  method provides a string representation of the object
    * @author Peter Hudel
    * @return A string representing the object
    */
	public String toString()
	{
		return courseAttempted.getCourseTitle() + ": " + courseAttempted.getCourseCode() + "," + semesterTaken + "," + grade + "," + status;
	}
	
    /**
    * This method determines if a the object being passed in is the same as this object
    * @author Peter Hudel
    * @param  x    The object you are comparing
    * @return    whether or not the two objects are equal
    */
	public boolean equals(Attempt x)
	{
		if (x == this)
        {
            return true;
        }
        
        if (!(x instanceof Attempt))
        {
            return false;
        }
        
		return courseAttempted.equals(x.courseAttempted) && grade.equals(x.grade) && semesterTaken.equals(x.semesterTaken);
	}

	/**
	 * An anonymous object which can compare the year portion of the semester taken
	 * @author Peter Hudel
	 * @return the lexicographic order of 2 attempts
	 */
	public static Comparator<Attempt> year = new Comparator<Attempt>() {
		public int compare(Attempt val1, Attempt val2) {
			return -val1.getSemesterTaken().substring(1).compareTo(val2.getSemesterTaken().substring(1));
		}
	};
	
	/**
	 * An anonymous object which can compare the semester portion of the semester taken
	 * @author Peter Hudel
	 * @return the lexicographic order of 2 attempts
	 */
	public static Comparator<Attempt> semester = new Comparator<Attempt>() {
		public int compare(Attempt val1, Attempt val2) {
			return -val1.getSemesterTaken().substring(0, 1).compareTo(val2.getSemesterTaken().substring(0, 1));
		}
	};
	
	/**
	 * Setter for the grade
	 * @author Peter Hudel
	 * @param grade a student's grade in the course
	 */
	public void setAttemptGrade(String grade) 
	{
		this.grade = grade;
	}
	
	/**
	 * getter for the grade
	 * @author Peter Hudel
	 * @return the student's grade
	 */
	public String getAttemptGrade() 
	{
		return grade;
	}
	
	/**
	 * Setter for the semester taken
	 * @author Peter Hudel
	 * @param semester the semester the course was taken
	 */
	protected void setSemesterTaken(String semester)
    {
        this.semesterTaken = semester;
    }
    
	/**
	 * getter for the semester taken
	 * @author Peter Hudel
	 * @return the semester taken
	 */
	public String getSemesterTaken()
    {
        return semesterTaken;
    }

	/**
	 * Setter for the course attempted
	 * @author Peter Hudel
	 * @param theCourse the course that was attempted
	 */
    protected void setCourseAttempted(Course theCourse)
    {
    	this.courseAttempted = theCourse;
    }
    
	/**
	 * getter for the course attempted
	 * @author Peter Hudel
	 * @return the course attempted
	 */
    public Course getCourseAttempted() 
    {
    	return courseAttempted;
    }
    
	/**
	 * Setter for the status
	 * @author Peter Hudel
	 * @param status a student's status in the course
	 */
    protected void setStatus(String status) {
    	this.status = status;
    }
    
	/**
	 * getter for the status
	 * @author Peter Hudel
	 * @return the status of the course taken
	 */
    public String getStatus() {
    	return this.status;
    }
}