package webb.jerry.elcappandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import webb.jerry.elcappandroid.Model.Course;
import webb.jerry.elcappandroid.Model.CourseSingleton;
import webb.jerry.elcappandroid.View.CourseAdapter;

/**
 * Created by tiffanyniicole on 4/18/16.
 */
public class CourseSelectionActivity extends AppCompatActivity {
    public static final String PREF_EMAIL = "elc.rollcall.preferences.email";
    private static final String PREF_FILENAME = "webb.jerry.elcappandroid.preferences.app_prefs";

    ArrayList<Course> mCourses;
    ListView mListView;
    CourseAdapter mAdapter;
    Button registerButton;
    Button cancelButton;
    Context context;
    ArrayList<Course> myCourses = new ArrayList<Course>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_course_selection);
        context = CourseSelectionActivity.this;
        CourseSingleton s = CourseSingleton.get(getApplicationContext());
        mListView = (ListView) findViewById(R.id.listViewStudentCourses);
        registerButton = (Button) findViewById(R.id.registerButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        mCourses = s.getMCourses();
        mAdapter = new CourseAdapter(
                getApplicationContext(),
                mCourses);
        mAdapter.noCheckBox = false;
        mListView.setAdapter(mAdapter);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < mAdapter.getCount(); i++) {
                    Course course = mAdapter.getItem(i);
                    if (course.isChecked()) {
                        Toast.makeText(context,
                                course.getClassName() + " is Checked!!",
                                Toast.LENGTH_SHORT).show();
                        myCourses.add(course);
                    }
                }

                SharedPreferences prefs = getSharedPreferences(
                        PREF_FILENAME, MODE_PRIVATE);
                String encodedEmail = prefs.getString(PREF_EMAIL, "current_user");


                for (int i = 0; i < myCourses.size(); i++) {
                    final Firebase userLocation = new Firebase(getResources().getString(R.string.Firebase_url))
                            .child("Users").child(encodedEmail).child("courses")
                            .child(myCourses.get(i).getClassName() + " " + myCourses.get(i).getDates());
                    final Course c = myCourses.get(i);
                    userLocation.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue() == null) {
                                userLocation.setValue(c);
                                mAdapter = new CourseAdapter(
                                        getApplicationContext(),
                                        mCourses);
                                mAdapter.noCheckBox = false;
                                mListView.setAdapter(mAdapter);
                                setResult(Activity.RESULT_OK);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

    }
}
