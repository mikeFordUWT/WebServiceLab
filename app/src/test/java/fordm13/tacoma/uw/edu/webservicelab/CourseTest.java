package fordm13.tacoma.uw.edu.webservicelab;

import junit.framework.TestCase;

import org.junit.Test;

import fordm13.tacoma.uw.edu.webservicelab.model.Course;

/**
 * Created by Mike on 5/4/16.
 */
public class CourseTest extends TestCase{

    @Test
    public void testConstructor(){
        Course course = new Course("TCSS450", "Mobile Application Programming"
                , "Android Programming", "TCSS360");
        assertNotNull(course);
    }
}
