package webb.jerry.elcappandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.ArrayList;

import webb.jerry.elcappandroid.Model.Course;
import webb.jerry.elcappandroid.Model.bluetoothSingleton;
import webb.jerry.elcappandroid.View.CourseAdapter;

/**
 * Created by LJ on 3/5/16.
 */
public class StudentClassManagementActivity extends AppCompatActivity {
    public static final String EXTRA_USERNAME = "webb.jerry.elc.username";
    public static final String EXTRA_PASSWORD = "webb.jerry.elc.password";
    public static final String EXTRA_FIRST_NAME = "webb.jerry.elc.firstName";
    public static final String EXTRA_LAST_NAME = "webb.jerry.elc.lastName";
    public static final String EXTRA_EMAIL_ADDRESS = "webb.jerry.elc.email";
    public static final String EXTRA_UNIVERSITY_ID = "webb.jerry.elc.universityId";
    public static final String EXTRA_CONFIRM_PASSWORD = "webb.jerry.elc.confirmPassword";
    public static final String EXTRA_USER_TYPE = "webb.jerry.elc.userType";



    ArrayList <Course> studentCourses;
    RadioGroup radioGroupContentSelection;
    ListView listViewStudntClassAttendance;
    CourseAdapter courseAdapter;
    Button scanForBeaconButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_management);

        radioGroupContentSelection = (RadioGroup) findViewById(R.id.radioGroupContentSelection);
        listViewStudntClassAttendance = (ListView) findViewById(R.id.listViewStudntClassAttendance);
        courseAdapter = new CourseAdapter(this, studentCourses);
//        listViewStudntClassAttendance.setAdapter(courseAdapter);
        scanForBeaconButton = (Button) findViewById(R.id.scanForBeaconButton);

        // this allows the student to manually search for the beacon
        scanForBeaconButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluetoothSingleton.get(getApplicationContext()).searchForBeacon();
            }
        });

    }

}
