package univ;

import java.util.ArrayList;

/**
 * This is the highest version of the degree hierarchy
 *
 * @author Peter Hudel
 * @version Nov. 26, 2018
 */
public abstract class ArtDegree extends Degree {
    /**
    * This is the constructor for the art degree
    */
	public ArtDegree() {
		super();
	}
	
    /**
    * determines the total number of credits the user must complete
    * @author Peter Hudel
    * @param allTheCoursesPlannedAndTaken a list of all the student's courses 
    * @return a double representing the number of credits the user must complete
    */
	public double numberOfCreditsRemaining(ArrayList<Course> allTheCoursesPlannedAndTaken)
    {
		double totalCredits = 8.5;
        double creditsRemaining = totalCredits;
        for (Course c : allTheCoursesPlannedAndTaken)
        {
            if (creditsRemaining - c.getCourseCredit() >= 0.0)
            {
                creditsRemaining = creditsRemaining - c.getCourseCredit();
            }
        }
        return creditsRemaining;
    }
    
    /**
    * determines the total number of credits required to complete the degree
    * @author Peter Hudel
    * @param planned a list of all planned courses
    * @param trans a list of the student's transcript
    * @return a double representing the number of credits needed to complete a degree
    */
    public double numberOfCreditsToCompleteDegree(ArrayList<Course> planned, ArrayList<Attempt> trans) {
    	double val = 8.5;
    	for (Attempt a : trans) {
    		if (a.getStatus().equals("Completed")) {
    			val -= a.getCourseAttempted().getCourseCredit();
    		}
    	}
    	for (Course c : planned) {
    		val -= c.getCourseCredit();
    	}
    	return val;
    }
    
    /**
    * determines if the user has met the requirements to graduate their degree
    * @author Peter Hudel
    * @param allTheCoursesPlannedAndTaken a list of all the student's courses 
    * @return a boolean representing whether or not they completed the degree
    */
    public boolean meetsRequirements(ArrayList<Course> allTheCoursesPlannedAndTaken)
    {
	    double totalCredits = 6.5;
	    double m2000 = 1.0;
	    double m3000 = 2.0;
    	for (Course c : allTheCoursesPlannedAndTaken) {
    		String[] course = c.getCourseCode().split("\\*");
            //Determines what type of course was completed
            if (findCourse(c.getCourseCode()) != null)
            {
                totalCredits = totalCredits - c.getCourseCredit();
            }
            
            if ((course[0].equals("THST") && (findCourse(c.getCourseCode()) == null))) {
            	if ((Double.parseDouble(course[1]) >= 2000) && (Double.parseDouble(course[1]) < 3000)) {
            		m2000 = m2000 - c.getCourseCredit();
            	}
            	else if (Double.parseDouble(course[1]) >= 3000) {
            		m3000 = m3000 - c.getCourseCredit();
            	}
            }
    	}
    	if ((totalCredits <= 0.0) && (m2000 <= 0.0) && (m3000 <= 0.0)) {
    		return true;
    	}
        return false;
    }
	
}
