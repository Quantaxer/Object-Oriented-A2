package univ;

import java.util.ArrayList;

/**
* This is the class for the course a student may take
*
* @author Peter Hudel
* @version Nov. 26, 2018
*/
public class Course
{

   private String code;
   private String title;
   private double courseCredit;
   private ArrayList<Course> prerequisites;
   private String semesterOffered;

   /**
    * Constructor for objects of class Course
    */
   public Course()
   {
       this.code = "";
       this.title = "";
       this.courseCredit = 0.0;
       this.prerequisites = new ArrayList<>();
       this.semesterOffered = "";
   }
   
	/**
	 * Parameterized constructor for the Course class
	 * @author Peter Hudel
	 * @param c the course code
	 * @param cred the total credits of the course
	 * @param t the title of the course
	 * @param sem the semester the course is offered
	 * @param pre a list of all prerequisites of a course
	 */
   public Course(String c, double cred, String t, String sem, ArrayList<Course> pre)
   {
       this.code =c;
       this.title = t;
       this.courseCredit = cred;
       this.prerequisites = pre;
       this.semesterOffered = sem;
   }
   
/**
 * copy constructor for the Course class
 * @author Peter Hudel
 * @param x the course to copy
*/
   public Course(Course x)
   {
       this.code = x.code;
       this.title = x.title;
       this.courseCredit = x.courseCredit;
       this.prerequisites = x.prerequisites;
       this.semesterOffered = x.semesterOffered;

   }
   
   /**
    * This  method provides a string representation of the object
    * @author Peter Hudel
    * @return A string representing the object
    */
    public String toString()
    {
       String preReqStr = "";
       if (prerequisites != null) {
	       for (Course course : prerequisites)
	       {
	           preReqStr = preReqStr + course.getCourseCode() + ":";
	       }
       }
       return code + "," + courseCredit + "," + semesterOffered + "," + preReqStr;
   }
   
   /**
   * This method determines if a the object being passed in is the same as this object
   * @author Peter Hudel
   * @param  x    The object you are comparing
   * @return    whether or not the two objects are equal
   */
   public boolean equals(Course x)
   {
       if (x == this)
       {
           return true;
       }
       
       if (!(x instanceof Course))
       {
           return false;
       }
       return code.equals(x.code) && title.equals(x.title) && courseCredit == x.courseCredit && prerequisites.equals(x.prerequisites) && semesterOffered.equals(x.semesterOffered);
   }
   
	/**
	 * Getter for the course code
	 * @author Peter Hudel
	 * @return a string representing the course code
	*/
   public String getCourseCode()
   {
       return code;
   }

	/**
	 * setter for the course code
	 * @author Peter Hudel
	 * @param courseCode the course code
	*/
   protected void setCourseCode(String courseCode) 
   {
        code = courseCode;
   }

	/**
	 * Getter for the course title
	 * @author Peter Hudel
	 * @return a string representing the course title
	*/
   public String getCourseTitle()
   {
       return title;
   }
   
	/**
	 * setter for the course title
	 * @author Peter Hudel
	 * @param courseTitle the course title
	*/
   protected void setCourseTitle(String courseTitle)
   {
       title = courseTitle;
   }
   
	/**
	 * Getter for the course credit
	 * @author Peter Hudel
	 * @return a double representing the amount of credits a course is worth
	*/
   public double getCourseCredit()
   {
       return courseCredit;
   }
   
	/**
	 * setter for the course credit
	 * @author Peter Hudel
	 * @param credit the course credit
	*/
   protected void setCourseCredit(double credit)
   {
       courseCredit = credit;
   }
   
	/**
	 * Getter for the list of prerequisite courses
	 * @author Peter Hudel
	 * @return a list of courses
	*/
   public ArrayList<Course> getPrerequisites()
   {
       return prerequisites;
   }
   
	/**
	 * setter for the list of prereqs
	 * @author Peter Hudel
	 * @param preReqList a list of prerequisites
	*/
   protected void setPrerequisites(ArrayList<Course>preReqList)
   {
       prerequisites = preReqList;
   }
   
	/**
	 * Getter for the semester the course is offered
	 * @author Peter Hudel
	 * @return a string representing the semester the course is offered
	*/
   public String getSemesterOffered() 
   {
	   return semesterOffered;
   }
   
	/**
	 * setter for the semester offered
	 * @author Peter Hudel
	 * @param sem the semester offered
	*/
   protected void setSemesterOffered(String sem) 
   {
	   semesterOffered = sem;
   }
}