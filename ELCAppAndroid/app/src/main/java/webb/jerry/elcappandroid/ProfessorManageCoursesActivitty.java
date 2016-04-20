package webb.jerry.elcappandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import webb.jerry.elcappandroid.Model.Course;
import webb.jerry.elcappandroid.View.CourseAdapter;

/**
 * Created by LJ on 3/5/16.
 */
public class ProfessorManageCoursesActivitty extends AppCompatActivity  {

    ArrayList<Course> courses;
    Button buttonCreateCourse;
    Button buttonInstructorLogout;
    ListView listViewCourses;
    CourseAdapter courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_manage_courses);

        buttonCreateCourse = (Button) findViewById(R.id.buttonCreateCourse);
        buttonInstructorLogout = (Button) findViewById(R.id.buttonInstructorLogout);

        listViewCourses = (ListView) findViewById(R.id.listViewCourses);
        courseAdapter = new CourseAdapter(this, courses);
        listViewCourses.setAdapter(courseAdapter);

        buttonCreateCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to seperate create course activity
                Log.d("TAG", "I'm here");
                Intent i = new Intent(getApplicationContext(), InstructorAdmitActivity.class);
                startActivityForResult(i, 0);

            }
        });

        buttonInstructorLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                setResult(Activity.RESULT_OK);
                finish();
            }
        });

    }


}
