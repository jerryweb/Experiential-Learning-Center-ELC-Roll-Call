package webb.jerry.elcappandroid;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Map;

import webb.jerry.elcappandroid.Model.Course;
import webb.jerry.elcappandroid.View.CourseAdapter;

/**
 * Created by LJ on 3/5/16.
 */
public class ProfessorManageCoursesActivitty extends AppCompatActivity  {
    public static final String PREF_EMAIL = "elc.rollcall.preferences.email";
    private static final String PREF_FILENAME = "webb.jerry.elcappandroid.preferences.app_prefs";

    ArrayList<Course> courses;
    Button buttonCreateCourse;
    Button buttonInstructorLogout;
    ListView listViewCourses;
    CourseAdapter courseAdapter;
    ArrayList <Course> instructorCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_manage_courses);

        buttonCreateCourse = (Button) findViewById(R.id.buttonCreateCourse);
        buttonInstructorLogout = (Button) findViewById(R.id.buttonInstructorLogout);

        listViewCourses = (ListView) findViewById(R.id.listViewCourses);

        loadInstructorCourses();
        courseAdapter = new CourseAdapter(
                getApplicationContext(),
                instructorCourses);
        courseAdapter.noCheckBox = true;
        listViewCourses.setAdapter(courseAdapter);
        //courseAdapter = new CourseAdapter(this, courses);
        //listViewCourses.setAdapter(courseAdapter);
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

    private void loadInstructorCourses() {
        SharedPreferences prefs = getSharedPreferences(
                PREF_FILENAME, MODE_PRIVATE);
        String encodedEmail = prefs.getString(PREF_EMAIL, "current_user");
        Log.d("TAG", encodedEmail);
        instructorCourses = new ArrayList<Course>();
        Firebase userLocation = new Firebase(getResources().getString(R.string.Firebase_url));
        userLocation.child("Users").child(encodedEmail)
                .child("courses").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Map<String, Object> newCourse = (Map<String, Object>) dataSnapshot.getValue();
                //Log.d("TAG", "I'm HERE");
                //Log.d("TAG", Integer.toString(newCourse.size()));
                Course myCourse = new Course();
                myCourse.setClassName(newCourse.get("className").toString());
                myCourse.setBeaconName(newCourse.get("beaconName").toString());
                myCourse.setInstructorName(newCourse.get("instructorName").toString());
                myCourse.setDates(newCourse.get("dates").toString());
                instructorCourses.add(myCourse);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        courseAdapter.notifyDataSetChanged();
    }

}
