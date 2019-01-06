package univ;

/**
 * This class is general degree in the class hierarchy
 *
 * @author Peter Hudel
 * @version Nov. 26, 2018
 */


import java.util.ArrayList;
import java.util.HashMap;
public abstract class GeneralDegree extends Degree
{
    private double totalCredits;
    private double m3000;
    private double maxLowCredits;
    private double scienceCredits;
    private double artsCredits;
    private double cisCredits;
    private String sciencePrefixes;
    private String artPrefixes;
    
    /**
    * This is the constructor for the general degree
    */
    public GeneralDegree()
    {
        super();
        totalCredits = 15.0;
        cisCredits = 5.75;
        m3000 = 4.0;
        maxLowCredits = 0.0;
        scienceCredits = 2.0;
        artsCredits = 2.0;
        sciencePrefixes = "ABIO,BIOC,BIOD,BMPH,BPCH,BIOS,BIOL,BIOM,BTOX,BIOT,BECN,CHPY,CHEM,ECOL,ENVB,EGG,FOOD,GIS,HK,MFB,MSCI,MATH,MICR,MBG,NANO,NEUR,NANS,PSCI,PHYS,PLSC,STAT,THPY,WBC,ZOO";
        artPrefixes = "ANTH,ARTH,ACHM,BUS,BECN,CLAS,CW,CJPP,ECON,ENGL,EGOV,ECC,EURS,FCS,FARE,FREN,GEOG,GERM,HIST,IS,ID,ITAL,MKTG,MAEC,MSCI,MCST,MS,MUSC,PHIL,POLS,PSYC,SOC,PRAH,SART,THST";
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
        HashMap<String, Double> map = new HashMap<>();
        double cis = cisCredits;
        double total = totalCredits;
        double min3000 = m3000;
        double intro = maxLowCredits;
        double science = scienceCredits;
        double arts = artsCredits;
        String[] allSciencePrefixes = sciencePrefixes.split(",");
        String[] allArtPrefixes = artPrefixes.split(",");
        for (Course c : allTheCoursesPlannedAndTaken)
        {
            if (intro <= 6.0)
            {
                String[] course = c.getCourseCode().split("\\*");
                //Adds each prefix into the hashmap to determine how many of each prefix exists
                if (map.containsKey(course[0]))
                {
                    double temp = (double)map.get(course[0]);
                    //Determines if there is a course prefix that has more than 11 credits associated with it
                    if (map.get(course[0]) <= 11.0)
                    {
                        total = total - c.getCourseCredit();
                        map.put(course[0], temp + c.getCourseCredit());
                    }
                }
                else
                {
                    map.put(course[0], c.getCourseCredit());
                }
                
                //Updates the remaining number of credits that are required
                if (findCourse(c.getCourseCode()) != null)
                {
                    cis = cis - c.getCourseCredit();
                }
                else
                {
                    //Determines if the prefix is a science credit
                    for (String s : allSciencePrefixes)
                    {
                        if (s.equals(course[0]))
                        {
                            science = science - c.getCourseCredit();
                            break;
                        }
                    }
                    //Determines if the prefix is an art credit
                    for (String s : allArtPrefixes)
                    {
                        if (s.equals(course[0]))
                        {
                            arts = arts - c.getCourseCredit();
                            break;
                        }
                    }
                }
                //Determines if the level of the course that was completed
                if ((Double.parseDouble(course[1]) < 2000) && (intro <= 6.0))
                {
                    intro = intro + c.getCourseCredit();
                }
                else if (Double.parseDouble(course[1]) >= 3000)
                {
                    m3000 = m3000 - c.getCourseCredit();
                }
            }
        }
        //Determines if the requirements were met
        if ((total <= 0.0) && (min3000 <= 0.0) && (cis <= 0.0))
        {
            if ((science <= 0.0) && (arts <= 0.0))
            {
                return true;
            }
        }
        return false;
    }
}
