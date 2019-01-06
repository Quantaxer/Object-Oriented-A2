package DB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import univ.*;

/**
* This is the class for the student
*
* @author Peter Hudel
* @version Nov. 26, 2018
*/

public class Student
{

    private String firstName;
    private String lastName;
    private String fullName;
    private Degree deg;
    private String degree;
    private Integer studentNumber;
    private ArrayList<String> allCourses;
    private ArrayList<Course> allCoursesPlannedAndTaken;
    private ArrayList<Attempt> transcript;
    private HashMap<Course, String> plannedCourses;
    
    
    /**
     * Constructor for objects of class Student
     */
    public Student()
    {
        this.firstName = "";
        this.lastName = "";
        this.fullName = "";
        this.studentNumber = 0;
        this.degree = "";
        this.allCourses = new ArrayList<>();
        this.allCoursesPlannedAndTaken = new ArrayList<>();
        this.transcript = new ArrayList<>();
        this.plannedCourses = new HashMap<>();
    }
	/**
	 * Parameterized constructor for the Student class
	 * @param snum the student number of the student
	 * @param name the name of the student
	 * @param deg a string representation of a degree
	 * @param c a list of al lthe courses the student has
	 */
    public Student(Integer snum, String name, String deg, ArrayList<String> c)
    {
        this.firstName = "";
        this.lastName = "";
        this.fullName = name;
        this.studentNumber = snum;
        this.degree = deg;
        this.allCoursesPlannedAndTaken = new ArrayList<>();
        this.allCourses = c;
        this.transcript = new ArrayList<>();
        this.plannedCourses = new HashMap<>();
    }
    
    /**
     * This  method provides a string representation of the object
     * @author Peter Hudel
     * @return A string representing the object
     */
    public String toString()
    {
        return firstName + " " + lastName + " : " + studentNumber;
    }
    
    /**
    * This method determines if a the object being passed in is the same as this object
    * @author Peter Hudel
    * @param  x    The object you are comparing
    * @return    whether or not the two objects are equal
    */
    public boolean equals(Student x)
    {
        if (x == this)
        {
            return true;
        }
        
        if (!(x instanceof Student))
        {
            return false;
        }
        return firstName.equals(x.firstName) && lastName.equals(x.lastName) && studentNumber.equals(x.studentNumber) && transcript.equals(x.transcript) && plannedCourses.equals(x.plannedCourses);
    }
    
	/**
	 * A method which provides the full name of the student
	 * @author Peter Hudel
	 * @return A string which represents the full name of a student
	 */
    public String getFullName()
    {
        return fullName;
    }
    
	/**
	 * A method which set the full name of a student
	 * @author Peter Hudel
	 * @param name The full name of a student
	 */
    public void setFullName(String name) {
    	fullName = name;
    }
    
	/**
	 * This function sets the first name of the student
	 * @author Peter Hudel
	 * @param first A string of the first name of a student
	 */
    public void setFirstName(String first)
    {
        firstName = first;
    }
    
	/**
	 * Sets the last name of a student
	 * @author Peter Hudel
	 * @param last A string of the last name of the student
	 */
    public void setLastName(String last)
    {
        lastName = last;
    }
    
	/**
	 * Returns the first name of a student
	 * 
	 * @return returns the first name of a student
	 */
    String getFirstName()
    {
        return firstName;
    }
    
    /**
	 * Returns the last name of a student
	 * @author Peter Hudel
	 * @return returns the last name of a student
	 */
    String getLastName()
    {
        return lastName;
    }
    
    /**
	 * Sets the student number
	 * @author Peter Hudel
	 * @param studentNum an Integer which is the student number
	 */
    public void setStudentNumber(Integer studentNum)
    {
        studentNumber = studentNum;
    }
    
    /**
	 * Returns the student number
	 * @author Peter Hudel
	 * @return returns the integer of the student number
	 */
    public Integer getStudentNumber()
    {
        return studentNumber;
    }
    
    /**
	 * Saves the string representation of a degree to a temp variable
	 * @author Peter Hudel
	 * @param d the string representation of a degree
	 */
    public void setDegreeString(String d)
    {
    	degree = d;
    }
    
    /**
	 * Determines which degree is the string representation, and initializes the degree based off of it
	 * @author Peter Hudel
	 * @param catalog the course catalog to set the required courses
	 * @param d The string representation of a degree
	 */
    public void setDegree(CourseCatalog catalog, String d) {
    	if (d.equals("CS"))
        {
            deg = new CS();
            deg.setDegreeTitle(d);
            deg.setRequiredCourses(catalog, "CIS*1500:MATH*1200:CIS*1910:CIS*2500:CIS*2030:CIS*2430:CIS*2520:CIS*2750:CIS*3110:CIS*3490:CIS*3150:CIS*3750:CIS*2460:CIS*3760:CIS*4650");
        }
        else if (d.equals("Seng"))
        {
            deg = new Seng();
            deg.setDegreeTitle(d);
            deg.setRequiredCourses(catalog, "CIS*1250:CIS*1500:CIS*1910:CIS*2250:CIS*2500:CIS*2030:CIS*2430:CIS*2520:CIS*3250:CIS*2750:CIS*3110:CIS*3260:CIS*3750:CIS*2460:CIS*3760:CIS*4150:CIS*4250:CIS*4300");
        }
        else if (d.equals("BCG"))
        {
            deg = new BCG();
            deg.setDegreeTitle(d);
            deg.setRequiredCourses(catalog, "CIS*1500:CIS*1910:CIS*2500:CIS*2910:CIS*2430:CIS*2520:CIS*2750:CIS*3530");
        }
        else if (d.equals("BTA"))
        {
            deg = new BTA();
            deg.setDegreeTitle(d);
            deg.setRequiredCourses(catalog, "THST*1040:THST*1190:THST*1270:THST*2050:THST*2190:THST*2270:THST*3170:THST*4270:THST*4280");
        }
    }
    
    /**
	 * getter for the string representation of a degree
	 * @author Peter Hudel
	 * @return the string representation of a degree
	 */
    public String getDegreeString() {
    	return degree;
    }
    
    /**
	 * Getter for the actual degree of the student
	 * @author Peter Hudel
	 * @return
	 */
    public Degree getDegree() {
    	try {
    		return deg;
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}
    	return null;
    }
    
    /**
	 * Sets a temp list of all courses
	 * @author Peter Hudel
	 * @param all an array of strings to set a temp variable
	 */
    void setAllCourses(ArrayList<String> all) {
    	allCourses = all;
    }
    
    /**
	 * Gets a temp list of all courses
	 * @author Peter Hudel
	 * @return a temp list of all courses in strings
	 */
    public ArrayList<String> getAllCourses()
    {
    	return allCourses;
    }
    
    /**
	 * moves courses from a string representation to their correct list, transcript, or planned courses
	 * @author Peter Hudel
	 * @param catalog To be used to find the course to add to the respective list
	 */
    public void moveCourses(CourseCatalog catalog) {
    	for (String course : allCourses) {
    		if (catalog.findCourse(course.split(",")[0]) != null) {
    			allCoursesPlannedAndTaken.add(catalog.findCourse(course.split(",")[0]));
    		}
    		
    		if (course.split(",")[3].equals("Completed") || course.split(",")[3].equals("InProgress")) {
    			Attempt a = new Attempt(catalog.findCourse(course.split(",")[0]), course.split(",")[2], course.split(",")[1], course.split(",")[3]);
    			transcript.add(a);
    		}
    		else if (course.split(",")[3].equals("Planned") ) {
    			plannedCourses.put(catalog.findCourse(course.split(",")[0]), course.split(",")[1]);
    		}
    	}
    }
    
    /**
	 * gets a list of all courses a student is taking
	 * @author Peter Hudel
	 * @return a list of every course the student is taking
	 */
    public ArrayList<Course> getAllCoursesPlannedAndTaken() {
    	try {
    		return allCoursesPlannedAndTaken;
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}
    	return null;
    }
    
    /**
	 * returns converts a course list to strings
	 * @author Peter Hudel
	 * @return a list of the string representation of all courses the student has
	 */
    public ArrayList<String> convertCourseToString() {
    	ArrayList<String> c = new ArrayList<>();
    	for (Attempt course : transcript) {
    		c.add(course.toString());
    	}
    	
    	for (Map.Entry<Course, String> map : plannedCourses.entrySet()) {
    		Attempt a = new Attempt(map.getKey(), "", map.getValue(), "Planned");
    		c.add(a.toString());
    	}
    	
    	return c;
    }
    
    /**
	 * returns converts all attempts to a list of their respective courses
	 * @author Peter Hudel
	 * @return a list of the courses a student has in their transcript
	 */
    public ArrayList<Course> convertAttemptToCourse() {
    	ArrayList<Course> c = new ArrayList<>();
    	for (Attempt a : transcript) {
    		c.add(a.getCourseAttempted());
    	}
    	return c;
    }
    
    /**
	 * setter for the transcript
	 * @author Peter Hudel
	 * @param attempts sets the transcript of a user
	 */
    void setTranscript(ArrayList<Attempt> attempts)
    {
    	transcript = attempts;
    }
    
    /**
	 * getter for the transcript
	 * @author Peter Hudel
	 * @return a lsit of attempts the student has
	 */
    public ArrayList<Attempt> getTranscript()
    {
    	return transcript;
    }
    
    /**
	 * setter for the user's plan of study
	 * @author Peter Hudel
	 * @param planned a hashmap of all courses the user plans to take
	 */
    void setPlannedCourses(HashMap<Course, String> planned)
    {
    	plannedCourses = planned;
    }
    
    /**
	 * getter for the user's plan of study
	 * @author Peter Hudel
	 * @return the user's plan of study as a hashmap
	 */
    public HashMap<Course, String> getPlannedCourses()
    {
    	return plannedCourses;
    }
    
    /**
	 * Adds a course to the user's plan
	 * @author Peter Hudel
	 * @param catalog the catalog of all courses, used to find the course
	 * @param courseCode the code of the course they wish to add
	 * @param semesterTaken a string of the semester they want to take the course
	 */
    public void addToPlan(CourseCatalog catalog, String courseCode, String semesterTaken) {
    	if (catalog.findCourse(courseCode) != null) {
    		plannedCourses.put(catalog.findCourse(courseCode), semesterTaken);
    		allCoursesPlannedAndTaken.add(catalog.findCourse(courseCode));
    	}
    }
    
    /**
	 * Adds a course to the user's plan
	 * @author Peter Hudel
	 * @param catalog the catalog of all courses, used to find the course
	 * @param semesterTaken a string of the semester they want to take the course
	 * @param courseCode the code of the course they wish to add
	 */
    public void removeFromPlan(CourseCatalog catalog, String courseCode, String semesterTaken) {
    	if (catalog.findCourse(courseCode) != null) {
    		plannedCourses.remove(catalog.findCourse(courseCode));
    		allCoursesPlannedAndTaken.remove(catalog.findCourse(courseCode));
    	}
    }
    
    /**
	 * Adds a course to the transcript
	 * @author Peter Hudel
	 * @param catalog the catalog of all courses, used to find the course
	 * @param courseCode the code of the course they wish to add
	 * @param semesterTaken a string of the semester they want to take the course
	 * @param grade the grade of the course attempted
	 * @param string of whether or not they completed the course or are still taking it
	 */
    public void addToTranscript(CourseCatalog catalog, String courseCode, String semesterTaken, String grade, String progress) {
    	if (catalog.findCourse(courseCode) != null) {
    		Attempt a = new Attempt(catalog.findCourse(courseCode), grade, semesterTaken, progress);
    		transcript.add(a);
    		allCoursesPlannedAndTaken.add(catalog.findCourse(courseCode));
    	}
    }
    
    /**
	 * Removes a course from the transcript
	 * @author Peter Hudel
	 * @param catalog the catalog of all courses, used to find the course
	 * @param courseCode the code of the course they wish to add
	 */
    public void removeFromTranscript(CourseCatalog catalog, String courseCode) {
    	ArrayList<Attempt> acopy = new ArrayList<>();
    	acopy.addAll(transcript);
    	for (Attempt a : acopy) {
    		if (a.getCourseAttempted().equals(catalog.findCourse(courseCode))) {
    			transcript.remove(a);
    			allCoursesPlannedAndTaken.remove(catalog.findCourse(courseCode));
    		}
    	}
    }
    
    /**
	 * Changes the grade of a course in the student's transcript
	 * @author Peter Hudel
	 * @param catalog the catalog of all courses, used to find the course
	 * @param courseCode the code of the course they wish to add
	 * @param grade the grade to be changed
	 */
    public void changeGrade(CourseCatalog catalog, String courseCode, String grade) {
    	for (Attempt a : transcript) {
    		if (a.getCourseAttempted().equals(catalog.findCourse(courseCode))) {
    			a.setAttemptGrade(grade);
    		}
    	}
    }
    
    /**
	 * Allows the user to view a list of all prerequisite courses they must take so that they can take the courses in their plan
	 * @author Peter Hudel
	 * @return a list of all prerequisite courses the user must take
	 */
    public ArrayList<Course> viewPrereqsRequired() {
    	ArrayList<Course> clist = new ArrayList<>();
    	ArrayList<Course> temp = new ArrayList<>();
    	boolean isIn;
    	//Create temp list of courses that were attempted
    	for (Attempt a : transcript) {
    		temp.add(a.getCourseAttempted());
    		System.out.println(a.getCourseAttempted().getCourseCode());
    		System.out.println(a.getCourseAttempted().getPrerequisites());
    	}
    	//Loop through plan
    	for (Map.Entry<Course, String> map : plannedCourses.entrySet()) {
    		for (Course c : map.getKey().getPrerequisites()) {
    			//Check if course is already in transcript
    			isIn = false;
    			for(Course t : temp) {
    				if (t.getCourseCode().equals(c.getCourseCode())) {
    					isIn = true;
    				}
    			}
    			//If the course is not in the transcript, add it to the courses required to take
    			if (isIn == false) {
    				clist.add(c);
    			}
    		}
    	}
    	return clist;
    }
    
    /**
	 * returns the user's GPA of all courses they have completed
	 * @author Peter Hudel
	 * @param attempts the user's transcript
	 * @return a double which is the gpa
	 */
    public double getGPA(ArrayList<Attempt> attempts) {
    	double count = 0;
    	double val = 0;
    	for (Attempt a : attempts) {
    		if (a.getStatus().equals("Completed") && Double.parseDouble(a.getAttemptGrade()) >= 50) {
    			if ((!a.getAttemptGrade().equals("P")) && (!a.getAttemptGrade().equals("F")) && (!a.getAttemptGrade().equals("INC")) && (!a.getAttemptGrade().equals("MNR"))) {
	    			val += Double.parseDouble(a.getAttemptGrade());
	    			count += 1;
    			}
    		}
    	}
    	double average = val / count;
    	return average;
    }
    
    /**
	 * returns the user's GPA of all CIS courses they have completed
	 * @author Peter Hudel
	 * @param attempts the user's transcript
	 * @return a double which is the gpa of all CIS courses
	 */
    public double getGPACIS(ArrayList<Attempt> attempts) {
    	double count = 0;
    	double val = 0;
    	String[] title;
    	for (Attempt a : attempts) {
    		title = a.getCourseAttempted().getCourseCode().split("\\*");
    		if (title[0].equals("CIS")) {
	    		if (a.getStatus().equals("Completed") && Double.parseDouble(a.getAttemptGrade()) >= 50) {
	    			if ((!a.getAttemptGrade().equals("P")) && (!a.getAttemptGrade().equals("F")) && (!a.getAttemptGrade().equals("INC")) && (!a.getAttemptGrade().equals("MNR"))) {		    			
	    				val += Double.parseDouble(a.getAttemptGrade());
		    			count += 1;
	    			}
	    		}
    		}
    	}
    	double average = val / count;
    	return average;
    }
    
    /**
	 * Sorts the user's transcript based off of the semester taken
	 * @author Peter Hudel
	 * @param the user's transcript
	 * @return the sorted version of the transcript
	 */
    public ArrayList<Attempt> sortTranscript(ArrayList<Attempt> attempts) {
    	ArrayList<Attempt> alist = attempts;
    	
    	Collections.sort(alist, Attempt.year);
    	int startIndex = 0;
    	int endIndex = 0;
    	String prev = "";
    	ArrayList<Attempt> temp = alist;
    	for (Attempt a : temp) {
    		if (endIndex == 0) {
    			prev = a.getSemesterTaken().substring(1);
    			endIndex++;
    		}
    		else {
    			if (!prev.equals(a.getSemesterTaken().substring(1))) {
    				if  (endIndex - startIndex > 1) {
	    				Collections.sort(alist.subList(startIndex, endIndex), Attempt.semester);
    				}
    				prev = a.getSemesterTaken().substring(1);
    				startIndex = endIndex;
    				endIndex++;
    			}
    			else {
    				prev = a.getSemesterTaken().substring(1);
    				endIndex++;
    			}
    		}
    	}
    	
    	if  (endIndex - startIndex > 1) {
			Collections.sort(alist.subList(startIndex, endIndex), Attempt.semester);
		}
    	return alist;
    }
    
    /**
	 * returns the user's GPA of the 10 most recent courses they have completed
	 * @author Peter Hudel
	 * @param attempts the user's transcript
	 * @return a double which is the gpa of the 10 most recent courses
	 */
    public double getGPARecent(ArrayList<Attempt> attempts) {
    	double count = 0;
    	double val = 0;
    	int courseCount = 10;
    	ArrayList<Attempt> alist = sortTranscript(attempts);
    	System.out.println(alist);
    	for (Attempt a : attempts) {
    		if (courseCount <= 0) {
    			break;
    		}
    		if (a.getStatus().equals("Completed") && Double.parseDouble(a.getAttemptGrade()) >= 50) {
    			if ((!a.getAttemptGrade().equals("P")) && (!a.getAttemptGrade().equals("F")) && (!a.getAttemptGrade().equals("INC")) && (!a.getAttemptGrade().equals("MNR"))) {	    			
    				val += Double.parseDouble(a.getAttemptGrade());
	    			count += 1;
	    			courseCount -= 1;
    			}
    		}
    	}
    	double average = val / count;
    	return average;
    }
}