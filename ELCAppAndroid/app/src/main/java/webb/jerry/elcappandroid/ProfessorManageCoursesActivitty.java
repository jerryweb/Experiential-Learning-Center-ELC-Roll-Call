package webb.jerry.elcappandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    ListView listViewCourses;
    CourseAdapter courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_manage_courses);

        buttonCreateCourse = (Button) findViewById(R.id.buttonCreateCourse);
        listViewCourses = (ListView) findViewById(R.id.listViewCourses);
        courseAdapter = new CourseAdapter(this, courses);
        listViewCourses.setAdapter(courseAdapter);

        buttonCreateCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to seperate create course activity
                Intent i = new Intent(getApplicationContext(),InstructorAdmitActivity.class);

            }
        });

    }


}
