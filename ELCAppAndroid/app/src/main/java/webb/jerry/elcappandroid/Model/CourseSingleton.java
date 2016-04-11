package webb.jerry.elcappandroid.Model;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by LJ on 3/5/16.
 */
public class CourseSingleton {

    ArrayList<Course> mCourses;
    private static CourseSingleton sCourseSingleton;
    private Context mAppContext;

    private CourseSingleton(Context c){
        mCourses = new ArrayList<Course>();
        this.mAppContext = c;
    }

    public static CourseSingleton get(Context c) {
        if (sCourseSingleton == null) {
            sCourseSingleton = new CourseSingleton(c.getApplicationContext());
        }
        return sCourseSingleton;
    }

    public ArrayList<Course> getMCourses() {
        return mCourses;
    }

    public void addCourse(Course c){
        mCourses.add(c);
    }

    public void removeCourse(Course c){

        if(!mCourses.isEmpty()){
            for(Course course: mCourses){
                if (c == course){
                    mCourses.remove(c);
                }
            }
        }

    }
}
