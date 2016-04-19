package webb.jerry.elcappandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import webb.jerry.elcappandroid.Model.Course;
import webb.jerry.elcappandroid.Model.CourseSingleton;
import webb.jerry.elcappandroid.View.CourseAdapter;

/**
 * Created by tiffanyniicole on 4/18/16.
 */
public class CourseSelectionActivity extends AppCompatActivity {

    ArrayList<Course> mCourses;
    ListView mListView;
    CourseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_course_selection);

        CourseSingleton s = CourseSingleton.get(getApplicationContext());
        mListView = (ListView) findViewById(R.id.listViewStudentCourses);
        mCourses = s.getMCourses();
        mAdapter = new CourseAdapter(
                getApplicationContext(),
                mCourses);
        mListView.setAdapter(mAdapter);

    }
}
