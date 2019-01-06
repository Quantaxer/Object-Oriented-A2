package univ;

/**
 * This is the highest version of the degree hierarchy
 *
 * @author Peter Hudel
 * @version Nov. 26, 2018
 */

import java.util.ArrayList;
public abstract class Degree
{
    private String degreeTitle;
    private ArrayList<Course> requiredCourses;
    
    /**
    * This is the constructor for the degree
    */
    public Degree()
    {
        this.degreeTitle = "";
        this.requiredCourses = new ArrayList<>();
    }
    
	/**
	 * Parameterized constructor for the Degree class
	 * @author Peter Hudel
	 * @param title the title of the degree
	 * @param req all the required courses of a degree
	 */
    public Degree(String title, ArrayList<Course> req)
    {
        this.degreeTitle = title;
        this.requiredCourses = req;
    }
    
    /**
     * This  method provides a string representation of the object
     * @author Peter Hudel
     * @return A string representing the object
     */
    public String toString()
    {
        String courseArray = "";
        for (Course c : requiredCourses)
        {
            courseArray = courseArray + c.getCourseCode() + ",";
        }
        return degreeTitle + ", " + courseArray;
    }
    
    /**
    * This method determines if a the object being passed in is the same as this object
    * @author Peter Hudel
    * @param  x    The object you are comparing
    * @return    whether or not the two objects are equal
    */
    public boolean equals(Degree x)
    {
        if (x == this)
        {
            return true;
        }
        
        if (!(x instanceof Degree))
        {
            return false;
        }
        return degreeTitle.equals(x.degreeTitle) && requiredCourses.equals(x.requiredCourses);
    }
    
    /**
    * This method returns a course in the required courses of the degree
    * @author Peter Hudel
    * @param  courseCode the course you are looking for
    * @return the course if it was found
    */
    public Course findCourse(String courseCode)
    {
        for (Course courses: requiredCourses)
        {
            if (courses.getCourseCode().equals(courseCode))
            {
                return courses;
            }
        }
        return null;
    }
    
    /**
    * getter for the title of the degree
    * @author Peter Hudel
    * @return the title of the degree
    */
    public String getDegreeTitle()
    {
    	try {
    		return degreeTitle;
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
    	return null;
    }
    
    /**
    * setter for the title of the degree
    * @author Peter Hudel
    * @param the title of the degree
    */
    public void setDegreeTitle(String title)
    {
        degreeTitle = title;
    }

    /**
    * setter for the required courses in a degree
    * @author Peter Hudel
    * @param catalog the course catalog
    * @param listOfRequiredCourseCodes A string delimited by commas of all the course codes required
    */
    public void setRequiredCourses(CourseCatalog catalog, String listOfRequiredCourseCodes)
    {
        //Resets the required courses in case loaded in twice
        requiredCourses.clear();
        for (String code: listOfRequiredCourseCodes.split(":"))
        {
            if (catalog.findCourse(code) != null)
            {
                requiredCourses.add(catalog.findCourse(code));
            }
        }
    }
    
    /**
    * getter for the required courses in a degree
    * @author Peter Hudel
    * @return the list of all courses that are required
    */
    public ArrayList<Course> getRequiredCourses()
    {
    	try {
    		return requiredCourses;
    	}
    	catch (Exception e){
    		System.out.println(e);
    	}
    	return null;
    }
    
    /**
    * determines the total number of credits required to complete the degree
    * @author Peter Hudel
    * @param planned a list of all planned courses
    * @param trans a list of the student's transcript
    * @return a double representing the number of credits needed to complete a degree
    */
    public double numberOfCreditsToCompleteDegree(ArrayList<Course> planned, ArrayList<Attempt> trans) {
    	return 0.0;
    }
    
    /**
    * determines if the user has met the requirements to graduate their degree
    * @author Peter Hudel
    * @param allTheCoursesPlannedAndTaken a list of all the student's courses 
    * @return a boolean representing whether or not they completed the degree
    */
    public boolean meetsRequirements(ArrayList<Course> allTheCoursesPlannedAndTaken)
    {
        return false;
    }
    
    /**
    * determines the total number of credits the user must complete
    * @author Peter Hudel
    * @param allTheCoursesPlannedAndTaken a list of all the student's courses 
    * @return a double representing the number of credits the user must complete
    */
    public double numberOfCreditsRemaining(ArrayList<Course> allTheCoursesPlannedAndTaken)
    {
        return 0.0;
    }
    
    /**
    * determines the total number of credits the user has completed
    * @author Peter Hudel
    * @param allTheCoursesCompleted a list of all the courses a student has completed
    * @return a double representing the number of credits a student has completed
    */
    public double numberOfCreditsCompleted(ArrayList<Course> allCoursesCompleted) {
    	double val = 0.0;
    	for (Course a : allCoursesCompleted) {
    		val += a.getCourseCredit();
    	}
    	return val;
    }
    
    /**
    * returns a list of all the required courses the student must still complete in order to complete the degree
    * @author Peter Hudel
    * @param allTheCoursesPlannedAndTaken a list of all the student's courses 
    * @return a list of all the required courses remaining
    */
    public ArrayList<Course> remainingRequiredCourses(ArrayList<Course> allTheCoursesPlannedAndTaken)
    {
    	try {
	    	ArrayList<Course> c = new ArrayList<>();
	    	c.addAll(requiredCourses);
	    	for (Course course : allTheCoursesPlannedAndTaken) {
	    		if (requiredCourses.contains(course)) {
	    			c.remove(course);
	    		}
	    	}
	        return c;
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
    	return null;
    }
}
