package webb.jerry.elcappandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.ArrayList;

import webb.jerry.elcappandroid.Model.Course;
import webb.jerry.elcappandroid.View.CourseAdapter;

/**
 * Created by LJ on 3/5/16.
 */
public class ClassManagementActivity extends AppCompatActivity {
    ArrayList <Course> studentCourses;
    RadioGroup radioGroupContentSelection;
    ListView listViewStudntClassAttendance;
    CourseAdapter courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_management);

        radioGroupContentSelection = (RadioGroup) findViewById(R.id.radioGroupContentSelection);
        listViewStudntClassAttendance = (ListView) findViewById(R.id.listViewStudntClassAttendance);
        courseAdapter = new CourseAdapter(this, studentCourses);
        listViewStudntClassAttendance.setAdapter(courseAdapter);

    }

}
