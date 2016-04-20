package webb.jerry.elcappandroid;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import webb.jerry.elcappandroid.Model.Course;
import webb.jerry.elcappandroid.Model.CourseSingleton;

/**
 * Created by LJ on 4/18/16.
 */
public class InstructorAdmitActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String PREF_EMAIL = "elc.rollcall.preferences.email";
    private static final String PREF_FILENAME = "webb.jerry.elcappandroid.preferences.app_prefs";
    EditText courseNameEditText;
    EditText beaconNameEditText;
    EditText majorEditText;
    EditText instructorNameEditText;
    EditText daysEditText;
    EditText instructorEmailEditText;
    EditText elcRoomEditText;
    EditText startTimeEditText;
    EditText endTimeEditText;

    Button submitButton;
    Button cancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_admin_page);

        courseNameEditText = (EditText) findViewById(R.id.courseNameEditText);
        beaconNameEditText = (EditText) findViewById(R.id.beaconNameEditText);
        majorEditText = (EditText) findViewById(R.id.majorEditText);
        instructorNameEditText = (EditText) findViewById(R.id.instructorNameEditText);
        daysEditText = (EditText) findViewById(R.id.daysEditText);
        instructorEmailEditText = (EditText) findViewById(R.id.instructorEmailEditText);
        elcRoomEditText = (EditText) findViewById(R.id.elcRoomEditText);
        startTimeEditText = (EditText) findViewById(R.id.startTimeEditText);
        endTimeEditText = (EditText) findViewById(R.id.endTimeEditText);

        submitButton = (Button) findViewById(R.id.submitButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        submitButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submitButton:
                final Course myCourse = new Course();
                myCourse.setClassName(courseNameEditText.getText().toString());
                myCourse.setInstructorName(instructorNameEditText.getText().toString());
                myCourse.setBeaconName(beaconNameEditText.getText().toString());
                myCourse.setDates(daysEditText.getText().toString() +
                        " " + startTimeEditText.getText().toString()
                        + "-" + endTimeEditText.getText().toString());
                CourseSingleton s = CourseSingleton.get(getApplicationContext());
                s.addCourse(myCourse);
                String encodedCourse = courseNameEditText.getText().toString()
                        + "-" + myCourse.getDates();

                final Firebase userLocation = new Firebase(getResources().getString(R.string.Firebase_url))
                        .child("Courses").child(encodedCourse);
                userLocation.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() == null) {
                            userLocation.setValue(myCourse);
                            setResult(Activity.RESULT_OK);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });


                SharedPreferences prefs = getSharedPreferences(
                        PREF_FILENAME, MODE_PRIVATE);
                String encodedEmail = prefs.getString(PREF_EMAIL, "current_user");
                final Firebase userLocation1 = new Firebase(getResources().getString(R.string.Firebase_url))
                        .child("Users").child(encodedEmail).child("courses")
                        .child(myCourse.getClassName() + " " + myCourse.getDates());
                userLocation1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() == null) {
                            userLocation1.setValue(myCourse);
                            setResult(Activity.RESULT_OK);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });


                break;

            case R.id.cancelButton:
                setResult(Activity.RESULT_CANCELED);
                finish();
                break;
        }
    }
}
