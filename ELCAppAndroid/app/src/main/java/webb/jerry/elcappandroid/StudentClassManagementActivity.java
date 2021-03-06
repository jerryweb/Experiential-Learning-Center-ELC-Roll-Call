package webb.jerry.elcappandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Map;

import webb.jerry.elcappandroid.Model.BluetoothSingleton;
import webb.jerry.elcappandroid.Model.Course;
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
    public static final String PREF_EMAIL = "elc.rollcall.preferences.email";
    private static final String PREF_FILENAME = "webb.jerry.elcappandroid.preferences.app_prefs";


    ArrayList <Course> studentCourses;
    RadioGroup radioGroupContentSelection;
    ListView listViewStudntClassAttendance;
    CourseAdapter courseAdapter;
    Button scanForBeaconButton;
    Button courseButton;
    Button buttonStudentLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_management);

        radioGroupContentSelection = (RadioGroup) findViewById(R.id.radioGroupContentSelection);
        listViewStudntClassAttendance = (ListView) findViewById(R.id.listViewStudntClassAttendance);
        scanForBeaconButton = (Button) findViewById(R.id.scanForBeaconButton);
        courseButton = (Button) findViewById(R.id.buttonRegister);
        buttonStudentLogout = (Button) findViewById(R.id.buttonStudentLogout);

        loadStudentCourses();
        courseAdapter = new CourseAdapter(
                getApplicationContext(),
                studentCourses);
        courseAdapter.noCheckBox = true;



        if(BluetoothSingleton.get(this).mBluetoothAdapter == null){
            Toast.makeText(getApplicationContext(), "Bluetooth is not enabled on your device", Toast.LENGTH_SHORT).show();
            finish();
        }
//        if (!BluetoothSingleton.get(this).initBluetooth()){
//            turnOnBluetooth();
//        }
//
        listViewStudntClassAttendance.setAdapter(courseAdapter);
//        BluetoothSingleton.get(getApplicationContext()).initBluetooth();
//        BluetoothSingleton.get(getApplicationContext()).toggleBeaconSearch();

                // this allows the student to manually search for the beacon
        scanForBeaconButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothSingleton.get(getApplicationContext()).toggleBeaconSearch();
            }
        });

        courseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CourseSelectionActivity.class);
                BluetoothSingleton.get(getApplicationContext()).stopDiscovery();
                startActivityForResult(i, 0);
            }
        });

        buttonStudentLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                setResult(2);
                BluetoothSingleton.get(getApplicationContext()).stopDiscovery();
                finish();
            }
        });

    }
    private void loadStudentCourses() {
        SharedPreferences prefs = getSharedPreferences(
                PREF_FILENAME, MODE_PRIVATE);
        String encodedEmail = prefs.getString(PREF_EMAIL, "current_user");
        studentCourses = new ArrayList<Course>();
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
                studentCourses.add(myCourse);
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
        BluetoothSingleton.get(getApplicationContext()).initBluetooth();

    }

    private void turnOnBluetooth(){
        Intent btIntent = new Intent(BluetoothSingleton.get(this).mBluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(btIntent, 1);
    }

    @Override
    public void onPause() {
        super.onPause();
        BluetoothSingleton.get(this).stopDiscovery();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        BluetoothSingleton.get(getApplicationContext()).resumeBluetoothActivity();
    }
//    @Override
//    protected void onStop() {
//        super.onStop();
//        BluetoothSingleton.get(this).stopDiscovery();
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BluetoothSingleton.get(getApplicationContext()).stopDiscovery();
//        BluetoothSingleton.get(getApplicationContext()).unregisterReceiver();
    }
}
