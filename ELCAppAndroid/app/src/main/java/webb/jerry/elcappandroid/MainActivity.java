package webb.jerry.elcappandroid;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;


import webb.jerry.elcappandroid.Model.BluetoothSingleton;
import webb.jerry.elcappandroid.Model.Course;
import webb.jerry.elcappandroid.Model.CourseSingleton;
import webb.jerry.elcappandroid.Model.User;


import static webb.jerry.elcappandroid.R.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "elc.webb.jerry.tag";
    public static final String PREF_EMAIL = "elc.rollcall.preferences.email";
    public static final String PREF_IS_STUDENT = "elc.rollcall.preferences.isStudent";
    private static final String PREF_FILENAME = "webb.jerry.elcappandroid.preferences.app_prefs";
    private static final int DISCOVERY_REQUEST = 1;
    private static final int LOGOUT = 2;

    View viewLogin;
    EditText textEmailAddress;
    EditText textPassword;
    Button forgotPasswordButton;
    Button loginButton;
    Button registerButton;
    private BluetoothAdapter mBluetoothAdapter;
    Set<BluetoothDevice> deviceSet;
    ArrayList<String> deviceNames;

    // This is for registering a new user
    View viewRegister;
    RadioGroup userSelectionRadioGroup;
    EditText editTextfirstName;
    EditText editTextLastName;
    EditText editTextNewEmail;
    EditText editTextUniversityId;
    EditText editTextNewPassword;
    EditText editTextNewConfirmPass;
    Button buttonCreateAccount;
    Button buttonClear;
    Button buttonBack;
    Integer userType;
    BluetoothLeScanner mBluetoothLeScanner;
//    IntentFilter filter;

    // Create the bluetooth receiver at the beginning of the activity


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        Firebase.setAndroidContext(this);
//        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//        mBluetoothLeScanner = mBluetoothAdapter.getBluetoothLeScanner();

        init();
//
//        if(!BluetoothSingleton.get(this).initBluetooth()){
//            turnOnBluetooth();
//        }
//
//        if(BluetoothSingleton.get(this).mBluetoothAdapter == null){
//            Toast.makeText(getApplicationContext(),"Bluetooth is not enabled on your device", Toast.LENGTH_SHORT).show();
//            finish();
//        }
//        else{
//            if(!BluetoothSingleton.get(this).mBluetoothAdapter.isEnabled()){
//                turnOnBluetooth();
//            }
////            else{
////              BluetoothSingleton.get(this).toggleBeaconSearch();
////            }
//        }


    }


    private void init() {
        viewLogin = (View) findViewById(id.viewLogin);
        viewLogin.setVisibility(View.VISIBLE);
        textEmailAddress = (EditText) findViewById(R.id.editTextEmail);
        textPassword = (EditText) findViewById(R.id.passwordEditText);
        forgotPasswordButton = (Button) findViewById(R.id.forgotPasswordButton);
        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.registerButton);

        forgotPasswordButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);

        // Initialize vars for new user
        viewRegister = (View) findViewById(id.viewRegister);
        viewRegister.setVisibility(View.GONE);
        userSelectionRadioGroup = (RadioGroup) findViewById(R.id.userSelectionRadioGroup);

        editTextfirstName = (EditText) findViewById(R.id.EditTextfirstName);
        editTextLastName = (EditText) findViewById(R.id.editTextLastName);
        editTextNewEmail = (EditText) findViewById(R.id.editTextNewEmail);
        editTextUniversityId = (EditText) findViewById(R.id.editTextUniversityId);
        editTextNewPassword = (EditText) findViewById(R.id.editTextNewPassword);
        editTextNewConfirmPass = (EditText) findViewById(R.id.editTextConfirmNewPass);

        buttonCreateAccount = (Button) findViewById(R.id.buttonCreateAccount);
        buttonClear = (Button) findViewById(R.id.buttonClear);
        buttonBack = (Button) findViewById(id.buttonBack);

        buttonClear.setOnClickListener(this);
        buttonCreateAccount.setOnClickListener(this);
        buttonBack.setOnClickListener(this);
        userType = 0;

        deviceNames = new ArrayList<>();
        loadCourses();

        userSelectionRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                userType = checkedId;
            }
        });
    }

//    private void turnOnBluetooth(){
//        Intent btIntent = new Intent(BluetoothSingleton.get(this).mBluetoothAdapter.ACTION_REQUEST_ENABLE);
//        startActivityForResult(btIntent, 1);
//    }


    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED){
            Toast.makeText(getApplicationContext(),"Bluetooth must be enabled to continue", Toast.LENGTH_SHORT).show();
            finish();
        }
        else if(resultCode == DISCOVERY_REQUEST){
            Toast.makeText(getApplicationContext(), "Discovery in progress", Toast.LENGTH_SHORT).show();
        }
        else if(resultCode == LOGOUT){
            Toast.makeText(getApplicationContext(), "Logging out", Toast.LENGTH_SHORT).show();
            textEmailAddress.setText(null);
            textPassword.setText(null);
        }
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.loginButton:
                Log.d(TAG, "Register login Clicked");

                final User user = new User();
                Firebase ref = new Firebase(getResources().getString(R.string.Firebase_url));
                ref.authWithPassword(textEmailAddress.getText().toString(),
                        textPassword.getText().toString(),
                        new Firebase.AuthResultHandler() {
                            @Override
                            public void onAuthenticated(AuthData authData) {
                                Toast.makeText(getApplicationContext(),
                                        "User logged in!", Toast.LENGTH_LONG).show();

                                // TODO check if student or professor


                                final String encodedEmail = textEmailAddress.getText().toString()
                                        .replace(".", ",");
                                SharedPreferences prefs = getSharedPreferences(
                                        PREF_FILENAME, MODE_PRIVATE);


                                boolean student = prefs.getBoolean(PREF_IS_STUDENT, true);
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putString(PREF_EMAIL, encodedEmail);
                                editor.putBoolean(PREF_IS_STUDENT, student);

                                editor.commit();


                                Firebase userLocation = new Firebase(getResources().getString(R.string.Firebase_url));
                                userLocation.child("Users").child(encodedEmail).child("User info")
                                        .addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                                boolean b;
                                                Log.d("TAG", dataSnapshot.toString());
                                                //Log.d("TAG", "I'm HERE");
                                                if (dataSnapshot.getKey().equals("student")) {
                                                    Log.d("TAG", dataSnapshot.getValue().toString());
                                                    if (dataSnapshot.getValue().toString()
                                                            .equals("true")) {
                                                        user.setStudent(true);
                                                        b = true;
                                                        Log.d("TAG", "SETTING TO TRUE");
                                                        Intent intentLogin;
                                                        if (user.isStudent()) {
                                                            Log.d("TAG", "This is a student");

                                                            intentLogin = new Intent(getApplicationContext(), StudentClassManagementActivity.class);
                                                        } else {
                                                            intentLogin = new Intent(getApplicationContext(), ProfessorManageCoursesActivitty.class);

                                                        }

                                                        intentLogin.putExtra(StudentClassManagementActivity.EXTRA_EMAIL_ADDRESS, textEmailAddress.getText());
                                                        intentLogin.putExtra(StudentClassManagementActivity.EXTRA_PASSWORD, textPassword.getText());
                                                        Log.d("TAG", "HEY " + Boolean.toString(user.isStudent()));

                                                        startActivityForResult(intentLogin, 2);
                                                    } else if (dataSnapshot.getValue().toString()
                                                            .equals("false")) {
                                                        user.setStudent(false);
                                                        Log.d("TAG", "SETTING TO FALSE");

                                                        Intent intentLogin;
                                                        if (user.isStudent()) {
                                                            Log.d("TAG", "This is a student");

                                                            intentLogin = new Intent(getApplicationContext(), StudentClassManagementActivity.class);
                                                        } else {
                                                            intentLogin = new Intent(getApplicationContext(), ProfessorManageCoursesActivitty.class);

                                                        }

                                                        intentLogin.putExtra(StudentClassManagementActivity.EXTRA_EMAIL_ADDRESS, textEmailAddress.getText());
                                                        intentLogin.putExtra(StudentClassManagementActivity.EXTRA_PASSWORD, textPassword.getText());
                                                        Log.d("TAG", "HEY " + Boolean.toString(user.isStudent()));

                                                        startActivityForResult(intentLogin, 2);
                                                    }
                                                }


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
                            public void onAuthenticationError(FirebaseError firebaseError) {
                                Toast.makeText(getApplicationContext(),
                                        firebaseError.getMessage(), Toast.LENGTH_LONG).show();
                            }


                        });



                break;

            case R.id.registerButton:
                Log.d(TAG, "Register button Clicked");
                viewLogin.setVisibility(View.GONE);
                viewRegister.setVisibility(View.VISIBLE);
                clearRegisterText();
                break;

            case R.id.forgotPasswordButton:
                Log.d(TAG, "Register button Clicked");
                break;

            case R.id.buttonClear:
                clearRegisterText();
                break;

            case R.id.buttonCreateAccount:
                Intent intentRegister;
                final String checkedButton = ((RadioButton) findViewById(userSelectionRadioGroup.getCheckedRadioButtonId())).
                        getText().toString();
//                if(checkedButton.equals("Student")) {
//                    intentRegister = new Intent(getApplicationContext(), StudentClassManagementActivity.class);
//                }
//                else {
//                    intentRegister = new Intent(getApplicationContext(), ProfessorManageCoursesActivitty.class);
//                }
//                intentRegister.putExtra(StudentClassManagementActivity.EXTRA_EMAIL_ADDRESS, editTextNewEmail.getText());
//                intentRegister.putExtra(StudentClassManagementActivity.EXTRA_PASSWORD, editTextNewPassword.getText());
//                intentRegister.putExtra(StudentClassManagementActivity.EXTRA_CONFIRM_PASSWORD, editTextNewConfirmPass.getText());
//                intentRegister.putExtra(StudentClassManagementActivity.EXTRA_FIRST_NAME, editTextfirstName.getText());
//                intentRegister.putExtra(StudentClassManagementActivity.EXTRA_LAST_NAME, editTextLastName.getText());
//                intentRegister.putExtra(StudentClassManagementActivity.EXTRA_UNIVERSITY_ID, editTextUniversityId.getText());
//                intentRegister.putExtra(StudentClassManagementActivity.EXTRA_USER_TYPE, userType);
//                startActivity(intentRegister);

                Firebase ref2 = new Firebase(getResources().getString(R.string.Firebase_url));
                Log.d(TAG, "I'm here");
                Log.d(TAG, getResources().getString(R.string.Firebase_url));
                Log.d(TAG, "I'm here again");
                ref2.createUser(editTextNewEmail.getText().toString(),
                        editTextNewPassword.getText().toString(),
                        new Firebase.ValueResultHandler<Map<String, Object>>() {
                            @Override
                            public void onSuccess(Map<String, Object> stringObjectMap) {
                                Log.d(TAG, "I'm here again");
                                Log.d(TAG, editTextNewEmail.getText().toString());
                                Log.d(TAG, editTextNewPassword.getText().toString());
                                Toast.makeText(getApplicationContext(),
                                        "User created!", Toast.LENGTH_LONG).show();
                                final User user = new User();
                                user.setEmail(editTextNewEmail.getText().toString());
                                user.setFirstName(editTextfirstName.getText().toString());
                                user.setLastName(editTextLastName.getText().toString());
                                user.setUniversityId(Integer.parseInt(editTextUniversityId.getText().toString()));

                                if (userSelectionRadioGroup.getCheckedRadioButtonId() != -1) {

                                    if (checkedButton.equals("Student"))
                                        user.setStudent(true);
                                }

                                String encodedEmail = editTextNewEmail.getText().toString()
                                        .replace(".", ",");
                                final Firebase userLocation = new Firebase(getResources().getString(string.Firebase_url))
                                        .child("Users").child(encodedEmail).child("User info");
                                userLocation.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.getValue() == null) {
                                            userLocation.setValue(user);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(FirebaseError firebaseError) {

                                    }
                                });

                                SharedPreferences prefs = getSharedPreferences(
                                        PREF_FILENAME, MODE_PRIVATE);

                                SharedPreferences.Editor editor = prefs.edit();

                                editor.putString(PREF_EMAIL, encodedEmail);
                                if (user.isStudent())
                                    editor.putBoolean(PREF_IS_STUDENT, true);
                                else
                                    editor.putBoolean(PREF_IS_STUDENT, false);

                                editor.commit();
                            }

                            @Override
                            public void onError(FirebaseError firebaseError) {
                                Toast.makeText(getApplicationContext(),
                                        firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                viewRegister.setVisibility(View.GONE);
                viewLogin.setVisibility(View.VISIBLE);
//                clearRegisterText();
                break;

            case id.buttonBack:
                viewLogin.setVisibility(View.VISIBLE);
                viewRegister.setVisibility(View.GONE);
                clearRegisterText();
                break;
        }
    }

    private void loadCourses() {
        Firebase userLocation = new Firebase(getResources().getString(string.Firebase_url));
        userLocation.child("Courses").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Map<String, Object> newCourse = (Map<String, Object>) dataSnapshot.getValue();
                Log.d("TAG", "I'm Here");
                Log.d("TAG", Integer.toString(newCourse.size()));
                Course myCourse = new Course();
                myCourse.setClassName(newCourse.get("className").toString());
                myCourse.setBeaconName(newCourse.get("beaconName").toString());
                myCourse.setInstructorName(newCourse.get("instructorName").toString());
                myCourse.setDates(newCourse.get("dates").toString());

                CourseSingleton c = CourseSingleton.get(getApplicationContext());
                c.addCourse(myCourse);
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


    public void clearRegisterText(){
        editTextfirstName.setText(null);
        editTextLastName.setText(null);
        editTextNewEmail.setText(null);
        editTextUniversityId.setText(null);
        editTextNewPassword.setText(null);
        editTextNewConfirmPass.setText(null);
        userSelectionRadioGroup.clearCheck();
    }
}
