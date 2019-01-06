package univ;

import java.util.ArrayList;

/**
 * This class is honours degree in the class hierarchy
 *
 * @author Peter Hudel
 * @version Nov. 26, 2018
 */

public abstract class HonoursDegree extends Degree
{
    private double totalCredits;
    private double cisCredits;
    private double minorCredits;
    private double electiveCredits;
    private double maxIntroductoryCredits;
    private double min3000;
    private double min4000;
    private double mina3000;
    
    /**
    * This is the constructor for the honours degree
    */
    public HonoursDegree()
    {
        super();
        totalCredits = 20.00;
        cisCredits = 11.25;
        minorCredits = 4.00;
        electiveCredits = 4.75;
        maxIntroductoryCredits = 0.0;
        min3000 = 6.0;
        min4000 = 2.0;
        mina3000 = 1.0;
    }
    
    /**
    * determines the total number of credits the user must complete
    * @author Peter Hudel
    * @param allTheCoursesPlannedAndTaken a list of all the student's courses 
    * @return a double representing the number of credits the user must complete
    */
    public double numberOfCreditsRemaining(ArrayList<Course> allTheCoursesPlannedAndTaken)
    {
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
    	double val = totalCredits;
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
        double total = totalCredits;
        double cis = cisCredits;
        double minor = minorCredits;
        double electives = electiveCredits;
        double intro = maxIntroductoryCredits;
        double m3000 = min3000;
        double m4000 = min4000;
        double a3000 = mina3000;
        for (Course c : allTheCoursesPlannedAndTaken)
        {
            String[] course = c.getCourseCode().split("\\*");
            //Determines what type of course was completed
            if (findCourse(c.getCourseCode()) != null)
            {
                cis = cis - c.getCourseCredit();
            }
            else if (course[0].equals("CIS"))
            {
                electives = electives - c.getCourseCredit();
            }
            
            if ((course[0].equals("CIS")) || (findCourse(c.getCourseCode()) != null))
            {
                //Determines the course level that was completed
                if ((Double.parseDouble(course[1]) < 4000) && (Double.parseDouble(course[1]) >= 3000))
                {
                    m3000 = m3000 - c.getCourseCredit();
                    total = total - c.getCourseCredit();
                }
                else if (Double.parseDouble(course[1]) >= 4000)
                {
                    m3000 = m3000 - c.getCourseCredit();
                    m4000 = m4000 - c.getCourseCredit();
                    total = total - c.getCourseCredit();
                }

            }
            //Assumes any other course is part of the AoA (Judi mentioned this in a discussion post)
            else
            {
                minor = minor - c.getCourseCredit();
                if ((Double.parseDouble(course[1]) < 4000) && (Double.parseDouble(course[1]) >= 3000))
                {
                    a3000 = a3000 - c.getCourseCredit();
                    total = total - c.getCourseCredit();
                }
            }
            if (Double.parseDouble(course[1]) < 2000)
            {
                intro = intro + c.getCourseCredit();
            }
            
            if ((Double.parseDouble(course[1]) < 2000) && (intro <= 6.0))
            {
                total = total - c.getCourseCredit();
            }
        }
        //Determines if the requirements were met
        if ((total <= 0.0) && (cis <= 0.0) && (minor <= 0.0) && (electives <= 0.0))
        {
            if ((m3000 <= 0.0) && (m4000 <= 0.0) && (a3000 <= 0.0))
            {
            	return true;
            }
        }
        return false;
    }
} 