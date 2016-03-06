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
}
