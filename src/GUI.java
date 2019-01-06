import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.DefaultListModel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.CardLayout;

import DB.DBDetails;
import DB.MyConnection;
import DB.Student;

import java.util.ArrayList;
import java.util.Map;

import univ.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.JSeparator;
import javax.swing.JScrollPane;

public class GUI {

	private JFrame frame;
	private Student student;
	private JTextField studentNumber;
	private JPanel adminView;
	private JPanel userView;
	private JTextField studentNumberField;
	private JTextField studentNameField;
	private JPanel loadStudent;
	private JTextField gpaTextField;
	private JTextField cisGpaTextField;
	private JTextField tenGPATextField;
	private JTextField creditsCompletedField;
	private JTextField enterStudentName;
	private JTextField enterStudentNumber;
	private JTextField attemptCode;
	private JTextField attemptSemester;
	private JTextField attemptGrade;
	private JTextField attemptProgress;
	private JTextField creditsRemainingField;
	private JTextField requiermentsMetField;
	private JTextField creditsToAddField;
	private JTextField studentName;
	private DefaultListModel<Attempt> planListModel;
	private DefaultListModel<Attempt> transcriptListModel;
	private DefaultListModel<Course> allCoursesListModel;
	private DefaultListModel<Degree> allDegreesListModel;
	private CourseCatalog catalog;
	private CatalogAccess accessCatalog;
	private JTextField major;
	private JTextField gradeField;
	private JTextField courseCodeField;
	private JTextField courseSemesterField;
	private JTextField majorCourseField;
	private JTextField name;
	private JTextField code;
	private JTextField credits;
	private JTextField semester;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public GUI() {
		adminView = new JPanel();      //initialize the admin view
		userView = new JPanel();
		loadStudent = new JPanel();

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		catalog = new CourseCatalog();
        //student = new Student();
        
		String pwd = DBDetails.password;
		String user = DBDetails.username;
		
        MyConnection c = new MyConnection(user, pwd);
        //PrepStudentScript initTables = new PrepStudentScript(); //called to initialize our tables
        catalog.initializeCatalog(c.getAllCourses());
        accessCatalog = new CatalogAccess(catalog);

		setFrame(new JFrame());
		getFrame().setBounds(0, 0, 1000, 700);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(new CardLayout(0, 0));
		
		userView.setBackground(Color.WHITE);
		getFrame().getContentPane().add(userView, "name_87990588261613");
		userView.setLayout(new BorderLayout(0, 0));        //boarder layout
		
		JMenuBar menuBar = new JMenuBar();
		userView.add(menuBar, BorderLayout.NORTH);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSave = new JMenuItem("Save");
	
		mnFile.add(mntmSave);
		
		JMenuItem mntmLoadStudent = new JMenuItem("Load Student");
		mnFile.add(mntmLoadStudent);
		
		JMenuItem mntmSelectDegree = new JMenuItem("Select Degree");
		mnFile.add(mntmSelectDegree);
		
		JMenuItem mntmNewStudent = new JMenuItem("New Student");
		mnFile.add(mntmNewStudent);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenuItem mntmRequiredCoursesNot = new JMenuItem("Required Courses not in plan nor transcript");
		mnView.add(mntmRequiredCoursesNot);
		
		JMenuItem mntmRequiredCoursesNot_1 = new JMenuItem("Required Courses not in Transcript");
		mnView.add(mntmRequiredCoursesNot_1);
		
		JMenuItem mntmPrereqCoursesFor = new JMenuItem("Prereq Courses for course in major");
		
		mnView.add(mntmPrereqCoursesFor);
		
		JMenuItem mntmPrereqsNeededFor = new JMenuItem("Prereqs needed for plan");
		
		mnView.add(mntmPrereqsNeededFor);
		
		JMenu mnAdmin = new JMenu("Admin");
		menuBar.add(mnAdmin);;
		
		JMenuItem mntmSwitchToAdmin = new JMenuItem("Switch to Admin Mode");
		mnAdmin.add(mntmSwitchToAdmin);
		
		JPanel center = new JPanel();
		center.setBackground(SystemColor.menu);
		userView.add(center, BorderLayout.CENTER);
	    
		center.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		center.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(20, 1, 0, 0));   //Grid Layout
		
		JLabel lblTranscript = new JLabel("Transcript");
		lblTranscript.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblTranscript);
		
		JButton button = new JButton("Add");
	
		panel.add(button);
		
		JButton button_1 = new JButton("Remove");
		
		panel.add(button_1);
		
		JButton btnChangeGrade = new JButton("Change Grade");
		
		panel.add(btnChangeGrade);
		
		gradeField = new JTextField();
		panel.add(gradeField);
		gradeField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		panel.add(separator);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		panel.add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setOrientation(SwingConstants.VERTICAL);
		panel.add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		panel.add(separator_6);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setOrientation(SwingConstants.VERTICAL);
		panel.add(separator_7);
		
		JLabel lblPlan = new JLabel("Plan");
		lblPlan.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblPlan);
		
		JButton btnAddCourse = new JButton("Add");
		
		panel.add(btnAddCourse);
		
		JButton btnRemoveCourse = new JButton("Remove");
		
		panel.add(btnRemoveCourse);
		
		JPanel panel_1 = new JPanel();
		center.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		planListModel = new DefaultListModel<Attempt>();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(15, 322, 412, 263);
		panel_1.add(scrollPane_1);
		    
		JList<Attempt> planList = new JList<Attempt>(planListModel);
		scrollPane_1.setViewportView(planList);
		planList.setBackground(Color.WHITE);
		
		transcriptListModel = new DefaultListModel<Attempt>();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 16, 412, 290);
		panel_1.add(scrollPane);
		
		JList<Attempt> transcriptList = new JList<Attempt>(transcriptListModel);
		scrollPane.setViewportView(transcriptList);
		transcriptList.setBackground(Color.WHITE);
		
		DefaultListModel<Course> genericListModel = new DefaultListModel<Course>();   
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(442, 16, 242, 569);
		panel_1.add(scrollPane_2);

		JList<Course> genericList = new JList<Course>(genericListModel);
		scrollPane_2.setViewportView(genericList);
		
		JPanel west = new JPanel();
		west.setBackground(Color.WHITE);
		userView.add(west, BorderLayout.WEST);
		west.setLayout(new GridLayout(22, 1, 0, 0));
		
		JLabel lblName = new JLabel("Name:");
		west.add(lblName);
		
		studentName = new JTextField();
		studentName.setEditable(false);
		west.add(studentName);
		
		JLabel lblStudentNumber = new JLabel("Student Number:");
		west.add(lblStudentNumber);
		
		studentNumber = new JTextField();
		studentNumber.setEditable(false);
		west.add(studentNumber);
		studentNumber.setColumns(10);
		
		JLabel lblDegree = new JLabel("Major: ");
		west.add(lblDegree);
		
		major = new JTextField();
		major.setEditable(false);
		west.add(major);
		major.setColumns(10);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		west.add(separator_2);
		
		JLabel lblGpa = new JLabel("GPA:");
		west.add(lblGpa);
		
		gpaTextField = new JTextField();
		gpaTextField.setEditable(false);
		west.add(gpaTextField);
		gpaTextField.setColumns(10);
		
		JLabel lblCisGpa = new JLabel("CIS GPA:");
		west.add(lblCisGpa);
		
		cisGpaTextField = new JTextField();
		cisGpaTextField.setEditable(false);
		west.add(cisGpaTextField);
		cisGpaTextField.setColumns(10);
		
		JLabel lblLastCourse = new JLabel("Last 10 Course GPA:");
		west.add(lblLastCourse);
		
		tenGPATextField = new JTextField();
		tenGPATextField.setEditable(false);
		west.add(tenGPATextField);
		tenGPATextField.setColumns(10);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setOrientation(SwingConstants.VERTICAL);
		west.add(separator_8);
		
		JLabel lblCreditsToAdd = new JLabel("Credits to Add:");
		west.add(lblCreditsToAdd);
		
		creditsToAddField = new JTextField();
		creditsToAddField.setEditable(false);
		west.add(creditsToAddField);
		creditsToAddField.setColumns(10);
		
		JLabel lblCreditsCompleted = new JLabel("Credits Completed:");
		west.add(lblCreditsCompleted);
		
		creditsCompletedField = new JTextField();
		creditsCompletedField.setEditable(false);
		west.add(creditsCompletedField);
		creditsCompletedField.setColumns(10);
		
		JLabel lblCreditsRemaining = new JLabel("Credits Remaining:");
		west.add(lblCreditsRemaining);
		
		creditsRemainingField = new JTextField();
		creditsRemainingField.setEditable(false);
		west.add(creditsRemainingField);
		creditsRemainingField.setColumns(10);
		
		JLabel lblRequiermentsMet = new JLabel("Requierments Met:");
		west.add(lblRequiermentsMet);
		
		requiermentsMetField = new JTextField();
		requiermentsMetField.setEditable(false);
		west.add(requiermentsMetField);
		requiermentsMetField.setColumns(10);
		
		getFrame().getContentPane().add(adminView, "name_88005212754758");   //add adminview to content pane
		adminView.setLayout(null);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setBounds(0, 0, 978, 31);
		adminView.add(menuBar_1);
		
		JMenu mnFile_1 = new JMenu("File");
		menuBar_1.add(mnFile_1);
		
		JMenu mnView_1 = new JMenu("View");
		menuBar_1.add(mnView_1);
		
		JMenu mnAdmin_1 = new JMenu("Admin");
		menuBar_1.add(mnAdmin_1);
		
		JMenuItem mntmSwitchToUser = new JMenuItem("Switch to User Mode");
		mntmSwitchToUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userView.setVisible(true);
				adminView.setVisible(false);
			}
		});
		mnAdmin_1.add(mntmSwitchToUser);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(15, 65, 465, 497);
		adminView.add(scrollPane_3);
		allCoursesListModel = new DefaultListModel<Course>();   
				
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(495, 65, 468, 497);
		adminView.add(scrollPane_4);
		JList<Course> allCoursesList = new JList<Course>(allCoursesListModel);
		scrollPane_3.setViewportView(allCoursesList);

		allDegreesListModel = new DefaultListModel<Degree>();   
		
		JList<Degree> allDegreesList = new JList<Degree>(allDegreesListModel);
		scrollPane_4.setViewportView(allDegreesList);
		
		JButton btnAdd_2 = new JButton("Add");
		
		btnAdd_2.setBounds(25, 578, 115, 29);
		adminView.add(btnAdd_2);
		
		JLabel lblCourses = new JLabel("Courses");
		lblCourses.setBounds(15, 39, 69, 20);
		adminView.add(lblCourses);
		
		JLabel lblDegrees = new JLabel("Degrees");
		lblDegrees.setBounds(495, 39, 69, 20);
		adminView.add(lblDegrees);
		
		JButton btnRemove = new JButton("Remove");
		
		btnRemove.setBounds(155, 578, 115, 29);
		adminView.add(btnRemove);
		
		JButton btnChange = new JButton("Change");
		
		btnChange.setBounds(285, 578, 115, 29);
		adminView.add(btnChange);
		
		JButton btnAdd_3 = new JButton("Add");
		
		btnAdd_3.setBounds(505, 578, 115, 29);
		adminView.add(btnAdd_3);
		
		JButton btnRemove_1 = new JButton("Remove");
		
		btnRemove_1.setBounds(635, 578, 115, 29);
		adminView.add(btnRemove_1);
		
		JButton btnChange_1 = new JButton("Change");
		
		btnChange_1.setBounds(765, 578, 115, 29);
		adminView.add(btnChange_1);
		
		getFrame().getContentPane().add(loadStudent, "name_3480792906100");
		loadStudent.setLayout(null);
		
		studentNameField = new JTextField();
		studentNameField.setBounds(340, 210, 196, 26);
		loadStudent.add(studentNameField);
		studentNameField.setColumns(10);
		
		studentNumberField = new JTextField();
		studentNumberField.setBounds(340, 271, 196, 26);
		loadStudent.add(studentNumberField);
		studentNumberField.setColumns(10);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setBounds(196, 452, 79, 29);
		loadStudent.add(btnReturn);
		
		JButton btnAddStudent = new JButton("Add Student");
		
		btnAddStudent.setBounds(571, 452, 121, 29);
		loadStudent.add(btnAddStudent);
		
		JLabel lblEnterStudentName = new JLabel("Enter Student Name:");
		lblEnterStudentName.setBounds(340, 189, 196, 20);
		loadStudent.add(lblEnterStudentName);
		
		JLabel lblEnterStudentNumber_1 = new JLabel("Enter Student Number:");
		lblEnterStudentNumber_1.setBounds(340, 252, 196, 20);
		loadStudent.add(lblEnterStudentNumber_1);
		
		JPanel newStudent = new JPanel();
		getFrame().getContentPane().add(newStudent, "name_54558154026500");
		newStudent.setLayout(null);
		
		enterStudentName = new JTextField();
		enterStudentName.setBounds(516, 169, 146, 26);
		newStudent.add(enterStudentName);
		enterStudentName.setColumns(10);
		
		JLabel lblEnterName = new JLabel("Enter name:");
		lblEnterName.setBounds(312, 172, 167, 20);
		newStudent.add(lblEnterName);
		
		JLabel lblEnterStudentNumber = new JLabel("Enter student number:");
		lblEnterStudentNumber.setBounds(312, 208, 167, 20);
		newStudent.add(lblEnterStudentNumber);
		
		enterStudentNumber = new JTextField();
		enterStudentNumber.setBounds(516, 211, 146, 26);
		newStudent.add(enterStudentNumber);
		enterStudentNumber.setColumns(10);
		
		JButton btnBack = new JButton("Back");
	
		btnBack.setBounds(250, 340, 115, 29);
		newStudent.add(btnBack);
		
		JButton btnAdd = new JButton("Add");

		btnAdd.setBounds(592, 340, 115, 29);
		newStudent.add(btnAdd);
		
		JPanel addAttempt = new JPanel();
		getFrame().getContentPane().add(addAttempt, "name_66655944454100");
		addAttempt.setLayout(null);
		
		attemptCode = new JTextField();
		attemptCode.setBounds(430, 185, 146, 26);
		addAttempt.add(attemptCode);
		attemptCode.setColumns(10);
		
		attemptSemester = new JTextField();
		attemptSemester.setBounds(430, 233, 146, 26);
		addAttempt.add(attemptSemester);
		attemptSemester.setColumns(10);
		
		attemptGrade = new JTextField();
		attemptGrade.setBounds(430, 285, 146, 26);
		addAttempt.add(attemptGrade);
		attemptGrade.setColumns(10);
		
		attemptProgress = new JTextField();
		attemptProgress.setBounds(430, 334, 146, 26);
		addAttempt.add(attemptProgress);
		attemptProgress.setColumns(10);
		
		JButton btnBack_1 = new JButton("back");
		
		btnBack_1.setBounds(298, 410, 115, 29);
		addAttempt.add(btnBack_1);
		
		JButton btnAdd_1 = new JButton("add");
		
		btnAdd_1.setBounds(588, 410, 115, 29);
		addAttempt.add(btnAdd_1);
		
		JLabel lblEnterCourseCode = new JLabel("Enter Course Code: ");
		lblEnterCourseCode.setBounds(430, 159, 146, 20);
		addAttempt.add(lblEnterCourseCode);
		
		JLabel lblEnterSemester = new JLabel("Enter Semester: ");
		lblEnterSemester.setBounds(430, 213, 146, 20);
		addAttempt.add(lblEnterSemester);
		
		JLabel lblEnterGrade = new JLabel("Enter grade: ");
		lblEnterGrade.setBounds(430, 262, 146, 20);
		addAttempt.add(lblEnterGrade);
		
		JLabel lblEnterProgress = new JLabel("Enter Progress: ");
		lblEnterProgress.setBounds(430, 312, 146, 20);
		addAttempt.add(lblEnterProgress);
		
		JPanel addPlanned = new JPanel();
		getFrame().getContentPane().add(addPlanned, "name_66693715528300");
		addPlanned.setLayout(null);
		
		JLabel lblCourseCode = new JLabel("Course Code:");
		lblCourseCode.setBounds(400, 187, 116, 20);
		addPlanned.add(lblCourseCode);
		
		courseCodeField = new JTextField();
		courseCodeField.setBounds(400, 217, 146, 26);
		addPlanned.add(courseCodeField);
		courseCodeField.setColumns(10);
		
		courseSemesterField = new JTextField();
		courseSemesterField.setBounds(400, 279, 146, 26);
		addPlanned.add(courseSemesterField);
		courseSemesterField.setColumns(10);
		
		JLabel lblSemesterPlanned = new JLabel("Semester Planned: ");
		lblSemesterPlanned.setBounds(400, 256, 146, 20);
		addPlanned.add(lblSemesterPlanned);
		
		JButton btnAddToPlan = new JButton("Add to Plan");
		
		btnAddToPlan.setBounds(512, 419, 115, 29);
		addPlanned.add(btnAddToPlan);
		
		JButton btnBack_6 = new JButton("back");
		
		btnBack_6.setBounds(319, 419, 115, 29);
		addPlanned.add(btnBack_6);
		
		JPanel degreeSelect = new JPanel();
		getFrame().getContentPane().add(degreeSelect, "name_159219646599600");
		degreeSelect.setLayout(null);
		
		JButton degreeBCH = new JButton("BComp Honours");
		
		degreeBCH.setBounds(382, 185, 179, 29);
		degreeSelect.add(degreeBCH);
		
		JButton degreeBCG = new JButton("BComp General");
		
		degreeBCG.setBounds(382, 230, 179, 29);
		degreeSelect.add(degreeBCG);
		
		JButton degreeBA = new JButton("Bachelor of Arts");
	
		degreeBA.setBounds(382, 275, 179, 29);
		degreeSelect.add(degreeBA);
		
		JButton btnBack_2 = new JButton("back");
		
		btnBack_2.setBounds(416, 370, 115, 29);
		degreeSelect.add(btnBack_2);
		
		JPanel BCH = new JPanel();
		getFrame().getContentPane().add(BCH, "name_164641924631000");
		BCH.setLayout(null);
		
		JButton btnComputerScience = new JButton("Computer Science");
		
		btnComputerScience.setBounds(377, 206, 223, 29);
		BCH.add(btnComputerScience);
		
		JButton btnSoftwareEngeneering = new JButton("Software Engineering");
		
		btnSoftwareEngeneering.setBounds(377, 251, 222, 29);
		BCH.add(btnSoftwareEngeneering);
		
		JButton btnBack_3 = new JButton("back");

		btnBack_3.setBounds(437, 333, 115, 29);
		BCH.add(btnBack_3);
		
		JPanel BCG = new JPanel();
		getFrame().getContentPane().add(BCG, "name_164665785800000");
		BCG.setLayout(null);
		
		JButton btnBcompGeneral = new JButton("BComp General");
		
		btnBcompGeneral.setBounds(398, 230, 157, 29);
		BCG.add(btnBcompGeneral);
		
		JButton btnBack_4 = new JButton("back");
		
		btnBack_4.setBounds(417, 309, 115, 29);
		BCG.add(btnBack_4);
		
		JPanel BA = new JPanel();
		getFrame().getContentPane().add(BA, "name_164694911894500");
		BA.setLayout(null);
		
		JButton btnNewButton = new JButton("Theatre Studies");
		
		btnNewButton.setBounds(423, 224, 148, 29);
		BA.add(btnNewButton);
		
		JButton btnBack_5 = new JButton("back");
		
		btnBack_5.setBounds(440, 287, 115, 29);
		BA.add(btnBack_5);
		
		JPanel majorCourse = new JPanel();
		frame.getContentPane().add(majorCourse, "name_217292578343900");
		majorCourse.setLayout(null);
		
		majorCourseField = new JTextField();
		majorCourseField.setBounds(431, 274, 146, 26);
		majorCourse.add(majorCourseField);
		majorCourseField.setColumns(10);
		
		JLabel lblEnterTheCourse = new JLabel("Enter the course in your major to see prereqs for: ");
		lblEnterTheCourse.setBounds(330, 238, 358, 20);
		majorCourse.add(lblEnterTheCourse);
		
		JButton btnSeePrereqs = new JButton("See Prereqs");
		
		btnSeePrereqs.setBounds(573, 385, 115, 29);
		majorCourse.add(btnSeePrereqs);
		
		JButton btnBack_7 = new JButton("back");
		
		btnBack_7.setBounds(341, 385, 115, 29);
		majorCourse.add(btnBack_7);
		
		JPanel addCourse = new JPanel();
		frame.getContentPane().add(addCourse, "name_235375225561900");
		addCourse.setLayout(null);
		
		JLabel lblEnterCourseName = new JLabel("Enter Course Name: ");
		lblEnterCourseName.setBounds(395, 68, 187, 20);
		addCourse.add(lblEnterCourseName);
		
		name = new JTextField();
		name.setBounds(395, 93, 146, 26);
		addCourse.add(name);
		name.setColumns(10);
		
		JLabel lblEnterCourseCode_1 = new JLabel("Enter Course Code: ");
		lblEnterCourseCode_1.setBounds(395, 202, 165, 20);
		addCourse.add(lblEnterCourseCode_1);
		
		code = new JTextField();
		code.setBounds(395, 229, 146, 26);
		addCourse.add(code);
		code.setColumns(10);
		
		JLabel lblEnterCourseCredits = new JLabel("Enter Course Credits: ");
		lblEnterCourseCredits.setBounds(395, 271, 165, 20);
		addCourse.add(lblEnterCourseCredits);
		
		credits = new JTextField();
		credits.setBounds(395, 299, 146, 26);
		addCourse.add(credits);
		credits.setColumns(10);
		
		DefaultListModel<String> preReqListModel = new DefaultListModel<String>();
		
		JButton btnAddCourse_1 = new JButton("Add Course");
		
		btnAddCourse_1.setBounds(556, 550, 115, 29);
		addCourse.add(btnAddCourse_1);
		
		JButton btnBack_8 = new JButton("Back");
		
		btnBack_8.setBounds(265, 550, 115, 29);
		addCourse.add(btnBack_8);
		
		JLabel lblEnterSemesterOffered = new JLabel("Enter Semester Offered:");
		lblEnterSemesterOffered.setBounds(395, 135, 177, 20);
		addCourse.add(lblEnterSemesterOffered);
		
		semester = new JTextField();
		semester.setBounds(395, 160, 146, 26);
		addCourse.add(semester);
		semester.setColumns(10);
		
		JPanel addDegree = new JPanel();
		frame.getContentPane().add(addDegree, "name_256588998692300");
		addDegree.setLayout(null);
		
		JLabel lblEnterDegreeCode = new JLabel("Enter Degree Code: ");
		lblEnterDegreeCode.setBounds(434, 227, 151, 20);
		addDegree.add(lblEnterDegreeCode);
		
		textField = new JTextField();
		textField.setBounds(434, 263, 146, 26);
		addDegree.add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterCoursesRequired = new JLabel("Enter Courses required:");
		lblEnterCoursesRequired.setBounds(434, 305, 219, 20);
		addDegree.add(lblEnterCoursesRequired);
		
		textField_1 = new JTextField();
		textField_1.setBounds(146, 341, 736, 26);
		addDegree.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnAdd_4 = new JButton("Add");
		
		btnAdd_4.setBounds(619, 444, 115, 29);
		addDegree.add(btnAdd_4);
		
		JButton btnBack_9 = new JButton("Back");
		
		btnBack_9.setBounds(339, 444, 115, 29);
		addDegree.add(btnBack_9);
		
		Degree deg1 = new CS();      //deg list
        deg1.setDegreeTitle("CS");
        deg1.setRequiredCourses(catalog, "CIS*1500:MATH*1200:CIS*1910:CIS*2500:CIS*2030:CIS*2430:CIS*2520:CIS*2750:CIS*3110:CIS*3490:CIS*3150:CIS*3750:CIS*2460:CIS*3760:CIS*4650");                                                   
    	allDegreesListModel.addElement(deg1);  
    	
    	Degree deg2 = new Seng();
        deg2.setDegreeTitle("SEng");
        deg2.setRequiredCourses(catalog, "CIS*1250:CIS*1500:CIS*1910:CIS*2250:CIS*2500:CIS*2030:CIS*2430:CIS*2520:CIS*3250:CIS*2750:CIS*3110:CIS*3260:CIS*3750:CIS*2460:CIS*3760:CIS*4150:CIS*4250:CIS*4300");
        allDegreesListModel.addElement(deg2);
        
        Degree deg3 = new BCG();
        deg3.setDegreeTitle("BCG");
        deg3.setRequiredCourses(catalog, "CIS*1500:CIS*1910:CIS*2500:CIS*2910:CIS*2430:CIS*2520:CIS*2750:CIS*3530");
        allDegreesListModel.addElement(deg3);  
        
        Degree deg4 = new BTA();
        deg4.setDegreeTitle("BTA");
        deg4.setRequiredCourses(catalog, "THST*1040,THST*1190:THST*1270:THST*2050:THST*2190:THST*2270:THST*3170:THST*4270:THST*4280");
    	allDegreesListModel.addElement(deg4);  
		
		btnReturn.addActionListener((ActionEvent e)-> {         //this is an example of a lambda expression
				loadStudent.setVisible(false);
				userView.setVisible(true);
			}
		);
		
		mntmLoadStudent.addActionListener((ActionEvent arg0)-> {    //this is an example of a lambda expression
				userView.setVisible(false);
				loadStudent.setVisible(true);
			}
		);
		
		mntmSwitchToAdmin.addActionListener((ActionEvent arg0)-> {   //this is an example of a lambda expression
				userView.setVisible(false);
				adminView.setVisible(true);
				reset();
			}
		);
		
		btnAddStudent.addActionListener(new ActionListener() {     //load student
			public void actionPerformed(ActionEvent e) {
				student = c.loadStudent(studentNumberField.getText(), studentNameField.getText());
				student.moveCourses(catalog);
				student.setDegree(catalog, student.getDegreeString());
				
				if (student.getFullName() != "") {
					loadStudent.setVisible(false);
					userView.setVisible(true);
					reset();
				}
				else {
					studentNameField.setText("student not found");
				}
			}
		});
		
		mntmSave.addActionListener(new ActionListener() {    //save student
			public void actionPerformed(ActionEvent e) {
				Student temp = new Student(student.getStudentNumber(), student.getFullName(), student.getDegreeString(), student.convertCourseToString());
				c.deleteSavedStudent(Integer.toString(student.getStudentNumber()), student.getFullName());
            	c.saveStudent(temp);
            	reset();
			}
		});
		
		mntmNewStudent.addActionListener(new ActionListener() {   //adding a new student
			public void actionPerformed(ActionEvent arg0) {
				userView.setVisible(false);
				newStudent.setVisible(true);
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newStudent.setVisible(false);
				userView.setVisible(true);
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<String> list = new ArrayList<String>();
				
				Student tempStudent = new Student(Integer.parseInt(enterStudentNumber.getText()), enterStudentName.getText(), "CS", list);
            	c.deleteSavedStudent(enterStudentNumber.getText(), enterStudentName.getText());
            	c.saveStudent(tempStudent);	
            	
            	newStudent.setVisible(false);
				userView.setVisible(true);
			}
		});
		
		button.addActionListener(new ActionListener() {     //add course to transcript
			public void actionPerformed(ActionEvent e) {
				userView.setVisible(false);
				addAttempt.setVisible(true);
			}
		});
		
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addAttempt.setVisible(false);
				userView.setVisible(true);
			}
		});
		
		btnAdd_1.addActionListener(new ActionListener() {    
			public void actionPerformed(ActionEvent e) {
				student.addToTranscript(catalog, attemptCode.getText(), attemptSemester.getText(), attemptGrade.getText(), attemptProgress.getText());
				addAttempt.setVisible(false);
				userView.setVisible(true);
				reset();
				
			}
		});
		
		btnChangeGrade.addActionListener(new ActionListener() {    //change grade in transcript
			public void actionPerformed(ActionEvent e) {
				if (!transcriptList.isSelectionEmpty()){
					student.changeGrade(catalog, transcriptListModel.getElementAt(transcriptList.getSelectedIndex()).getCourseAttempted().getCourseCode(), gradeField.getText()); 
				}
				reset();
			}
		});
		
		button_1.addActionListener(new ActionListener() {   //remove from transcript
			public void actionPerformed(ActionEvent arg0) {
				if (!transcriptList.isSelectionEmpty()){
					student.removeFromTranscript(catalog, transcriptListModel.getElementAt(transcriptList.getSelectedIndex()).getCourseAttempted().getCourseCode());
					reset();
				}
			}
		});
		
		btnAddCourse.addActionListener(new ActionListener() {  //add course to plan
			public void actionPerformed(ActionEvent e) {
				userView.setVisible(false);
				addPlanned.setVisible(true);
			}
		});
		
		btnBack_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPlanned.setVisible(false);
				userView.setVisible(true);
			}
		});
		
		btnAddToPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPlanned.setVisible(false);
				userView.setVisible(true);
				student.addToPlan(catalog, courseCodeField.getText(), courseSemesterField.getText());
				reset();
			}
		});
		
		btnRemoveCourse.addActionListener(new ActionListener() {  //remove from plan of study
			public void actionPerformed(ActionEvent e) {
				if (!planList.isSelectionEmpty()){
					student.removeFromPlan(catalog, planListModel.getElementAt(planList.getSelectedIndex()).getCourseAttempted().getCourseCode(), planListModel.getElementAt(planList.getSelectedIndex()).getSemesterTaken());
					reset();
				}
			}
		});

		mntmRequiredCoursesNot.addActionListener(new ActionListener() {   //view courses in degree, not in plan nor transcript
			public void actionPerformed(ActionEvent arg0) {
        		genericListModel.removeAllElements();
            	for (Course course: (student.getDegree().remainingRequiredCourses(student.getAllCoursesPlannedAndTaken())))
            	{
            		genericListModel.addElement(course);
            		
            	}
			}
		});
		
		mntmRequiredCoursesNot_1.addActionListener(new ActionListener() {    //view courses in degree, not in transcript
			public void actionPerformed(ActionEvent arg0) {
        		genericListModel.removeAllElements();
            	for (Course course: student.getDegree().remainingRequiredCourses(student.convertAttemptToCourse()))
            	{
            		genericListModel.addElement(course);
            		
            	}
			}
		});
		
		mntmPrereqCoursesFor.addActionListener(new ActionListener() {    //view prereqs for course in plan
			public void actionPerformed(ActionEvent e) {
				userView.setVisible(false);
				majorCourse.setVisible(true);
			}
		});
		
		btnBack_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				majorCourse.setVisible(false);
				userView.setVisible(true);
			}
		});
		
		btnSeePrereqs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		genericListModel.removeAllElements();
				ArrayList<Course> courses = student.getDegree().getRequiredCourses();
	            for (Course x: courses)
	            {
	                if (x.getCourseCode().equals(majorCourseField.getText()))
	                {
	                    for(Course course : x.getPrerequisites()) {
	                    	genericListModel.addElement(course);
	                    }
	                }
	            }
	            majorCourse.setVisible(false);
                userView.setVisible(true);
                reset();
			}
		});
		
		mntmPrereqsNeededFor.addActionListener(new ActionListener() {    //view courses not in plan
			public void actionPerformed(ActionEvent arg0) {
        		genericListModel.removeAllElements();
				for (Course course : student.viewPrereqsRequired()) {
					genericListModel.addElement(course);
				}
			}
		});
	
		mntmSelectDegree.addActionListener(new ActionListener() {  //selecting the degree
			public void actionPerformed(ActionEvent e) {
				if (student != null)
				{
					userView.setVisible(false);
					degreeSelect.setVisible(true);
				}
			}
		});
		
		btnBack_2.addActionListener(new ActionListener() {   
			public void actionPerformed(ActionEvent e) {
				degreeSelect.setVisible(false);
				userView.setVisible(true);
			}
		});
		
		degreeBCH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				degreeSelect.setVisible(false);
				BCH.setVisible(true);
			}
		});
		
		degreeBCG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				degreeSelect.setVisible(false);
				BCG.setVisible(true);
			}
		});
		
		degreeBA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				degreeSelect.setVisible(false);
				BA.setVisible(true);
			}
		});
		
		btnBack_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BCH.setVisible(false);
				degreeSelect.setVisible(true);
			}
		});
	
		btnBack_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BCG.setVisible(false);
				degreeSelect.setVisible(true);
			}
		});
		
		btnBack_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BA.setVisible(false);
				degreeSelect.setVisible(true);
			}
		});
		
		btnComputerScience.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				student.setDegree(catalog, "CS");
				BCH.setVisible(false);
				userView.setVisible(true);
				reset();
			}
		});
		
		btnSoftwareEngeneering.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				student.setDegree(catalog, "Seng");
				BCH.setVisible(false);
				userView.setVisible(true);
				reset();
			}
		});
		
		btnBcompGeneral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				student.setDegree(catalog, "BCG");
				BCG.setVisible(false);
				userView.setVisible(true);
				reset();
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				student.setDegree(catalog, "BTA");
				BA.setVisible(false);
				userView.setVisible(true);
				reset();
			}
		});
		
		btnAdd_2.addActionListener(new ActionListener() {     //admin view
			public void actionPerformed(ActionEvent arg0) {
				addCourse.setVisible(true);
				adminView.setVisible(false);
			}
		});
		
		btnBack_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCourse.setVisible(false);
				adminView.setVisible(true);
			}
		});
		
		btnAddCourse_1.addActionListener(new ActionListener() {   //add a course
			public void actionPerformed(ActionEvent e) {
				ArrayList<Course> tempPre = new ArrayList<Course>();
				for (int i = 0; i<preReqListModel.getSize(); i++) {
					tempPre.add(catalog.findCourse(preReqListModel.getElementAt(i)));
				}
				Course tempCourse = new Course(code.getText(), Double.parseDouble(credits.getText()), name.getText(), semester.getText(), tempPre );
				accessCatalog.addCourse(tempCourse);
				c.addCourse(tempCourse.getCourseCode(), Double.toString(tempCourse.getCourseCredit()), tempCourse.getCourseTitle(), tempCourse.getSemesterOffered(), "");
			    addCourse.setVisible(false);
				adminView.setVisible(true);
			    reset();
			}
		});
		
		btnRemove.addActionListener(new ActionListener() {  //remove a course
			public void actionPerformed(ActionEvent e) {
				c.deleteCourse(allCoursesListModel.getElementAt(allCoursesList.getSelectedIndex()).getCourseCode());
				accessCatalog.removeCourse(allCoursesListModel.getElementAt(allCoursesList.getSelectedIndex()));
				reset();
			}
		});
		
		btnChange.addActionListener(new ActionListener() {  //change a course
			public void actionPerformed(ActionEvent e) {
				c.deleteCourse(allCoursesListModel.getElementAt(allCoursesList.getSelectedIndex()).getCourseCode());
				accessCatalog.removeCourse(allCoursesListModel.getElementAt(allCoursesList.getSelectedIndex()));
				addCourse.setVisible(true);
				adminView.setVisible(false);
			}
		});
		
		btnRemove_1.addActionListener(new ActionListener() {   //remove a degree
			public void actionPerformed(ActionEvent e) {
				allDegreesListModel.removeElementAt(allDegreesList.getSelectedIndex());
			}
		});
		
		btnBack_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDegree.setVisible(false);
				adminView.setVisible(true);
			}
		});
		
		btnAdd_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDegree.setVisible(false);
				adminView.setVisible(true);
			}
		});
		
		btnAdd_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDegree.setVisible(true);
				adminView.setVisible(false);
			}
		});
		
		btnChange_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDegree.setVisible(true);
				adminView.setVisible(false);
			}
		});
	}
	
	private void reset() {

		planListModel.removeAllElements();
		transcriptListModel.removeAllElements();
		allCoursesListModel.removeAllElements();
		
		if (student != null) {
			studentName.setText(student.getFullName());
			studentNumber.setText(student.getStudentNumber().toString());
			major.setText(student.getDegree().getDegreeTitle());
	    	
	    	ArrayList<Attempt> planned = new ArrayList<>();
	    	for (Map.Entry<Course, String> map : student.getPlannedCourses().entrySet()) {
	    		Attempt a = new Attempt(map.getKey(), "", map.getValue(), "Planned");
	    		planned.add(a);
	    	}
	    	planned = student.sortTranscript(planned);
	    	for (Attempt a: planned)
		    { 
	    		planListModel.addElement(a);
		    }
	    	
	    	ArrayList<Attempt> attempted = new ArrayList<>();            //show attempts:
	    	attempted = student.sortTranscript(student.getTranscript());
	    	for (Attempt a: attempted)
		    {     		
	    		transcriptListModel.addElement(a);
		    }
	    	
	    	gpaTextField.setText(String.format("%.2f", (student.getGPA(student.getTranscript()))));
	    	cisGpaTextField.setText(String.format("%.2f", student.getGPACIS(student.getTranscript())));
	    	tenGPATextField.setText(String.format("%.2f", student.getGPARecent(student.getTranscript())));
	    	
	    	ArrayList<Course> c1 = new ArrayList<>();   //number of credits to complete degree
	    	for (Map.Entry<Course, String> map : student.getPlannedCourses().entrySet()) {
	    		c1.add(map.getKey());
	    	}
	    	creditsToAddField.setText(String.valueOf(student.getDegree().numberOfCreditsToCompleteDegree(c1, student.getTranscript())));
	    	
	    	ArrayList<Course> c2 = new ArrayList<>();           //credits completed
	    	for (Attempt a : student.getTranscript()) {
	    		if (a.getStatus().equals("Completed")) {
	        		if ((Double.parseDouble(a.getAttemptGrade()) >= 50.0) || (a.getAttemptGrade().equals("P"))) {
	        			c2.add(a.getCourseAttempted());
	        		}
	    		}
	    	}
	    	creditsCompletedField.setText(String.valueOf(student.getDegree().numberOfCreditsCompleted(c2)));
	    	
	    	ArrayList<Course> c3 = new ArrayList<>();      //credits remaining
	    	for (Attempt a : student.getTranscript()) {
	    		if (a.getStatus().equals("Completed")) {
	        		if ((Double.parseDouble(a.getAttemptGrade()) >= 50.0) || (a.getAttemptGrade().equals("P"))) {
	        			c3.add(a.getCourseAttempted());
	        		}
	    		}
	    	}
	    	creditsRemainingField.setText(String.valueOf(student.getDegree().numberOfCreditsRemaining(c2)));
	
	    	ArrayList<Course> c4 = new ArrayList<>();      //meets requirements text field
	    	for (Attempt a : student.getTranscript()) {
	    		if (a.getStatus().equals("Completed")) {
	    			c4.add(a.getCourseAttempted());
	    		}
	    	}
	    	if (student.getDegree().meetsRequirements(c4) == true) {  //show attempts
	    		requiermentsMetField.setText("yes");
	    	}
	    	else {
	    		requiermentsMetField.setText("no");                
	    	}
		}
    	ArrayList<Course> allCourses = new ArrayList<>();            //update admin view
    	allCourses = catalog.getCatalog();                           //courselist
    	for (Course course : allCourses) {      
    		allCoursesListModel.addElement(course);
    	}
    	
    	
    	
    }

	/**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * @param frame the frame to set
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
