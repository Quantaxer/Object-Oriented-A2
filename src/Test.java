/**
* This is the Planner class, which can fulfill the user stories as defined by A1.
*
* @author Peter Hudel
* @version Oct. 25, 2018
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;

import univ.*;
import DB.*;

public class Test
{
    public static void main(String args[])
    {
        CourseCatalog catalog = new CourseCatalog();
        Student student = new Student();
        
		String pwd = DBDetails.password;
		String user = DBDetails.username;
		
        MyConnection c = new MyConnection(user, pwd);
        boolean fullyResetTables = false; //Set this to true if you wish to rebuild/reset your tables!
        PrepStudentScript initTables = new PrepStudentScript(fullyResetTables); //called to initialize our tables

        
        Scanner input = new Scanner(System.in);
        Scanner choice = new Scanner(System.in);
        
        String userChoice = "";
        String degType = "";
        String majorType = "";
        
        //Initialize the catalog
        catalog.initializeCatalog(c.getAllCourses());
        System.out.println(catalog);

        while (true)
        {
        	System.out.println("go");
            userChoice = choice.nextLine();
            //Load a student
            if (userChoice.equals("1"))
            {
            	student = c.loadStudent("1234567", "beep");
            	System.out.println(student.getStudentNumber());
        		System.out.println(student.getFullName());
        		System.out.println(student.getDegreeString());
        		
            	student.moveCourses(catalog);
            	student.setDegree(catalog, student.getDegreeString());
                System.out.println(student.getPlannedCourses());
                System.out.println(student.getTranscript());
            }
            //Enter degree
            else if (userChoice.equals("2"))
            {
            	System.out.println("Please enter your degree (BCH, BCG, BTA)");
                degType = input.nextLine();
            }
            //Enter major
            else if (userChoice.equals("3"))
            {
            	//Checks the degree entered by the user and determines which degree to make
                if (degType.equals("BCH"))
                {
                    System.out.println("Please select your major: (CS, Seng)");
                    majorType = input.nextLine();
                }
                else if (degType.equals("BTA")) {
                	majorType = "BTA";
                }
                else 
                {
                	majorType = "BCG";
                }
                student.setDegree(catalog, majorType);
                System.out.println(student.getDegree().getDegreeTitle());
            }
            //list of required courses not in plan or attempt
            else if (userChoice.equals("4")) {
            	try {
            		System.out.println(student.getDegree().remainingRequiredCourses(student.getAllCoursesPlannedAndTaken()));
            	}
            	catch(Exception e) {
            		System.out.println(e);
            	}
            }
            //list of required courses not in transcript
            else if (userChoice.equals("5")) {
            	System.out.println(student.getDegree().remainingRequiredCourses(student.convertAttemptToCourse()));
            }
            //List of all prereqs for any course in degree
            else if (userChoice.equals("6")) {
	            ArrayList<Course> courses = student.getDegree().getRequiredCourses();
	            for (Course x: courses)
	            {
	                if (x.getCourseCode().equals("CIS*2430"))
	                {
	                    System.out.println(x.getPrerequisites());
	                }
	            }
            }
            //List of prereq courses you must take to take the courses in plan
            else if (userChoice.equals("7")) {
            	student.addToPlan(catalog, "CIS*2750", "W16");
            	System.out.println(student.viewPrereqsRequired());
            }
            //Prints number of credits completed
            else if (userChoice.equals("8")) {
            	ArrayList<Course> c1 = new ArrayList<>();
            	for (Attempt a : student.getTranscript()) {
            		if (a.getStatus().equals("Completed")) {
	            		if ((Double.parseDouble(a.getAttemptGrade()) >= 50.0) || (a.getAttemptGrade().equals("P"))) {
	            			c1.add(a.getCourseAttempted());
	            		}
            		}
            	}
            	System.out.println(student.getDegree().numberOfCreditsCompleted(c1));
            }
            //Prints number of credits remaining
            else if (userChoice.equals("9")) {
            	ArrayList<Course> c1 = new ArrayList<>();
            	for (Attempt a : student.getTranscript()) {
            		if (a.getStatus().equals("Completed")) {
	            		if ((Double.parseDouble(a.getAttemptGrade()) >= 50.0) || (a.getAttemptGrade().equals("P"))) {
	            			c1.add(a.getCourseAttempted());
	            		}
            		}
            	}
            	System.out.println(student.getDegree().numberOfCreditsRemaining(c1));
            }
            //Checks if the user meets requirements to graduate
            else if (userChoice.equals("10")) {
            	ArrayList<Course> c1 = new ArrayList<>();
            	for (Attempt a : student.getTranscript()) {
            		if (a.getStatus().equals("Completed")) {
            			c1.add(a.getCourseAttempted());
            		}
            	}
            	System.out.println(student.getDegree().meetsRequirements(c1));
            }
            //Sorting the transcript and getting 10 most recent courses
            else if (userChoice.equals("11")) {
            	student.addToTranscript(catalog, "CIS*2750", "W11", "50", "Completed");
            	student.addToTranscript(catalog, "CIS*1910", "F11", "80", "Completed");
            	student.addToTranscript(catalog, "CIS*2910", "W11", "51", "Completed");
            	student.addToTranscript(catalog, "CIS*2520", "W18", "90", "Completed");
            	student.addToTranscript(catalog, "CIS*2460", "F18", "90", "Completed");
            	System.out.println(student.getGPARecent(student.getTranscript()));
            }
            //number of credits to complete degree
            else if (userChoice.equals("12")) {
            	ArrayList<Course> temp = new ArrayList<>();
            	student.addToPlan(catalog, "CIS*2750", "W16");
            	for (Map.Entry<Course, String> map : student.getPlannedCourses().entrySet()) {
            		temp.add(map.getKey());
            	}
            	System.out.println(student.getDegree().numberOfCreditsToCompleteDegree(temp, student.getTranscript()));
            }
            
            /**
             * USE BOTH 13 AND 14 FOR USER STORY "view my course plan, organized by the semester I have, or plan to, take the courses"
             * 
             */
            //View courses in transcript sorted
            else if (userChoice.equals("13")) {
            	System.out.println(student.sortTranscript(student.getTranscript()));
            }
            //View courses in planned sorted
            else if (userChoice.equals("14")) {
            	ArrayList<Attempt> temp = new ArrayList<>();
            	for (Map.Entry<Course, String> map : student.getPlannedCourses().entrySet()) {
            		Attempt a = new Attempt(map.getKey(), "", map.getValue(), "Planned");
            		temp.add(a);
            	}
            	System.out.println(student.sortTranscript(temp));
            }
            //save student
            else if (userChoice.equals("15")) {
            	student.addToTranscript(catalog, "CIS*2750", "W11", "50", "Completed");
            	student.addToTranscript(catalog, "CIS*1910", "F11", "80", "Completed");
            	Student temp = new Student(student.getStudentNumber(), student.getFullName(), student.getDegreeString(), student.convertCourseToString());
            	c.deleteSavedStudent(Integer.toString(student.getStudentNumber()), student.getFullName());
            	c.saveStudent(temp);
            }
            else if (userChoice.equals("0"))
            {
                break;
            }
            else
            {
                System.out.println("ERROR: not a valid selection");
            }
        }
        input.close();
        choice.close();
        
    }
}
   
