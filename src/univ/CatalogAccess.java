package univ;

/*
* This class stores all the courses in an ArrayList of courses
*
* @author Garnet McLaren
* @version Nov. 26, 2018
*/

public class CatalogAccess {
	private CourseCatalog catalog;
	/**
	    * This is the constructor for the catalog access
	    */
	public CatalogAccess(CourseCatalog catalog)
	{
		this.catalog = catalog;
	}
	  /**
	    * This  allows you to add a course to the catalog
	    * @author Garnet McLaren
	    * @param A course to add
	    */
	public void addCourse(Course toAdd)
	{
		catalog.addCourse(toAdd);
	}
	/**
	    * This  allows you to remove a course to the catalog
	    * @author Garnet McLaren
	    * @param A course to remove
	    */
	public void removeCourse(Course toRemove)
	{
		catalog.removeCourse(toRemove);
	}
}
