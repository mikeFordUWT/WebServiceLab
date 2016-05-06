package fordm13.tacoma.uw.edu.webservicelab.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;


public class Course implements Serializable {
    public String mCourseId;
    public String mShortDescription;
    public String mLongDescription;
    public String mPrereqs;

    public static final String ID = "id", SHORT_DESC = "shortDesc"
            , LONG_DESC = "longDesc", PRE_REQS = "prereqs";

    public Course(String id, String shortDesc, String longDesc, String prereqs){
        this.mCourseId =id;
        this.mShortDescription=shortDesc;
        this.mLongDescription=longDesc;
        this.mPrereqs=prereqs;
    }

    public String getCourseId() {
        return mCourseId;
    }

    public void setCourseId(String courseId) throws IllegalArgumentException{
        if(courseId== null || courseId.length() <5){
            throw new IllegalArgumentException("courseId must be >= 5 long");

        }else{
            this.mCourseId = courseId;
        }

    }

    public String getShortDescription() {
        return mShortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.mShortDescription = shortDescription;
    }

    public String getLongDescription() {
        return mLongDescription;
    }

    public void setLongDescription(String longDescription) {
        this.mLongDescription = longDescription;
    }

    public String getPrereqs() {
        return mPrereqs;
    }

    public void setPrereqs(String prereqs) {
        this.mPrereqs = prereqs;
    }

    public static String parseCourseJSON(String courseJSON, List<Course> courseList){
        String reason = null;
        if(courseJSON != null){
            try{
                JSONArray arr = new JSONArray(courseJSON);
                for(int i = 0; i< arr.length(); i++){
                    JSONObject obj = arr.getJSONObject(i);
                    Course  course  = new Course(obj.getString(Course.ID),
                            obj.getString(Course.SHORT_DESC), obj.getString(Course.LONG_DESC),
                            obj.getString(Course.PRE_REQS));
                    courseList.add(course);

                }
            }catch (JSONException e){
                reason = "Unable to parse data, Reason:" + e.getMessage();
            }
        }
        return reason;
    }
}
