package univ;

/*
* This class stores all the courses in an ArrayList of courses
*
* @author Peter Hudel
* @version Nov. 26, 2018
*/

import java.util.ArrayList;
public class CourseCatalog
{
   private ArrayList<Course> catalog;
    /**
    * This is the constructor for the course catalog
    */
   public CourseCatalog()
   {
       this.catalog = new ArrayList<>();
   }
   
   /**
    * This  method provides a string representation of the object
    * @author Peter Hudel
    * @return A string representing the object
    */
   public String toString()
   {
       String catalogNames = "";
       for (Course courses: catalog)
       {
           catalogNames = catalogNames + courses.getCourseCode() + ",";
       }
       return catalogNames;
   }
   
    /**
    * This method determines if a the object being passed in is the same as this object
    * @author Peter Hudel
    * @param  x    The object you are comparing
    * @return    whether or not the two objects are equal
    */
   public boolean equals(CourseCatalog x)
   {
       if (x == this)
       {
           return true;
       }
       
       if (!(x instanceof CourseCatalog))
       {
           return false;
       }
       return catalog.equals(x.catalog);
   }
  
   /**
    * This method initializes a list of courses from the database
    * @author Peter Hudel
    * @param  allCourses   a list of every course in the database
    */
   
   public void initializeCatalog(ArrayList<String> allCourses) {
        for (String course : allCourses) {
    	    String[] toAdd = course.split(",");
    	    Course c = new Course(toAdd[0], Double.parseDouble(toAdd[1]), toAdd[2], toAdd[3], null);
    	    catalog.add(c);
        }
		for (String course : allCourses) {  
	    	if (course.split(",").length >= 5) {
	    		ArrayList<Course> preReqList = new ArrayList<>();
	    	    for (String c : course.split(",")[4].split(":")) {
	    	    	if (findCourse(c) != null) {
	    	    		Course preReqCourse = new Course(findCourse(c));
	    	    		if (preReqCourse != null) {
	    	    			preReqList.add(preReqCourse);
	    	    		}
	    	    	}
	    	    }
	    	    findCourse(course.split(",")[0]).setPrerequisites(preReqList);
	        }
		}
    }
   
   /**
    * This method adds a course to the catalog
    * @author Peter Hudel
    * @param  toAdd   a course to add to the DB
    */
   protected void addCourse(Course toAdd)
   {
       if (toAdd != null) 
       {
           catalog.add(toAdd);
       }
   }
   
    /**
    * This method removes a course from the catalog
    * @author Peter Hudel
    * @param  toRemove     A course which you want to remove from the arraylist
    */
   protected void removeCourse(Course toRemove)
   {
       if (toRemove != null) 
       {
           catalog.remove(toRemove);
       }
   }
   
   /**
    * This method determines if a course is in the catalog
    *
    * @param  courseCode   The code of the course you are trying to find
    * @author Peter Hudel
    * @return    the course you are trying to locate if it exists. If it doesn't, return null instead.
    */
   public Course findCourse(String courseCode)
   {
       for (Course courses: catalog)
       {
           if (courses.getCourseCode().equals(courseCode))
           {
               return courses;
           }
       }
       return null;
   }
   
   /**
    * This method prints the catalog
    *
    * @author Peter Hudel
    */
	public void printCatalog() {
		for (Course courses: catalog)
        {
			System.out.println(courses.toString());
        }
	}
	 /**
	    * This method returns the catalog
	    *
	    * @author Garnet McLaren
	    * @return An arraylist of all of the courses in the catalog
	    */
	public ArrayList<Course> getCatalog(){
		return catalog;
	}
}
