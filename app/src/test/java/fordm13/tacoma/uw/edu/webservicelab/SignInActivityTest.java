package fordm13.tacoma.uw.edu.webservicelab;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import fordm13.tacoma.uw.edu.webservicelab.authenticate.SignInActivity;


public class SignInActivityTest extends
        ActivityInstrumentationTestCase2<SignInActivity>{

    private Solo solo;


    public SignInActivityTest(){
        super(SignInActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        //tearDown() is run after the test case has finished
        //finishedOpenActivities() will finish all the activities that have
        // been opened during the test execution
        solo.finishOpenedActivities();
    }

    public void testSignInFragmentLoads() {
        boolean fragmentLoaded = solo.searchText("Enter your userid");
        assertTrue("Login fragment loaded", fragmentLoaded);
    }


    public void testSignInWorks() {
        solo.enterText(0, "fordm13@uw.edu");
        solo.enterText(1, "husky123");
        solo.clickOnButton("Sign In");
        boolean worked = solo.searchText("Course List");
        assertTrue("Sign in worked!", worked);
    }


}
