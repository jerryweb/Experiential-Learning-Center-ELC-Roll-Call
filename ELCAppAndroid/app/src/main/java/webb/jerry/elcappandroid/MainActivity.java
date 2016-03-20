package webb.jerry.elcappandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import webb.jerry.elcappandroid.Constants;


import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

import static webb.jerry.elcappandroid.R.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "elc.webb.jerry.tag";

    View viewLogin;
    EditText textEmailAddress;
    EditText textPassword;
    Button forgotPasswordButton;
    Button loginButton;
    Button registerButton;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        Firebase.setAndroidContext(this);

        viewLogin = (View) findViewById(id.viewLogin);
        viewLogin.setVisibility(View.VISIBLE);
        textEmailAddress = (EditText) findViewById(R.id.editTextEmail);
        textPassword = (EditText) findViewById(R.id.passwordEditText);
        forgotPasswordButton = (Button) findViewById(R.id.forgotPasswordButton);
        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton =(Button) findViewById(R.id.registerButton);

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

        userSelectionRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                userType = checkedId;
            }
        });



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.loginButton:
                Log.d(TAG, "Register login Clicked");
//                if(textEmailAddress.getText()uals(" ")) {
//                    Toast.makeText(getApplicationContext(),"Please enter a valid email address", Toast.LENGTH_SHORT).show();
//                }
//                else{
                Intent intentLogin;
                if(true) {
                    intentLogin = new Intent(getApplicationContext(), ClassManagementActivity.class);
                }
                else {
                    intentLogin = new Intent(getApplicationContext(), ProfessorManageCoursesActivitty.class);

                }
                intentLogin.putExtra(ClassManagementActivity.EXTRA_EMAIL_ADDRESS, textEmailAddress.getText());
                intentLogin.putExtra(ClassManagementActivity.EXTRA_PASSWORD, textPassword.getText());

                startActivity(intentLogin);
//                }
                break;

            case R.id.registerButton:
                Log.d(TAG, "Register button Clicked");
                viewLogin.setVisibility(View.GONE);
                viewRegister.setVisibility(View.VISIBLE);
                break;

            case R.id.forgotPasswordButton:
                Log.d(TAG, "Register button Clicked");
                break;

            case R.id.buttonClear:
                editTextfirstName.setText(null);
                editTextLastName.setText(null);
                editTextNewEmail.setText(null);
                editTextUniversityId.setText(null);
                editTextNewPassword.setText(null);
                editTextNewConfirmPass.setText(null);
                break;

            case R.id.buttonCreateAccount:
                Intent intentRegister;
                if(true) {
                    intentRegister = new Intent(getApplicationContext(), ClassManagementActivity.class);
                }
                else {
                    intentRegister = new Intent(getApplicationContext(), ProfessorManageCoursesActivitty.class);
                }
                intentRegister.putExtra(ClassManagementActivity.EXTRA_EMAIL_ADDRESS, editTextNewEmail.getText());
                intentRegister.putExtra(ClassManagementActivity.EXTRA_PASSWORD, editTextNewPassword.getText());
                intentRegister.putExtra(ClassManagementActivity.EXTRA_CONFIRM_PASSWORD, editTextNewConfirmPass.getText());
                intentRegister.putExtra(ClassManagementActivity.EXTRA_FIRST_NAME, editTextfirstName.getText());
                intentRegister.putExtra(ClassManagementActivity.EXTRA_LAST_NAME, editTextLastName.getText());
                intentRegister.putExtra(ClassManagementActivity.EXTRA_UNIVERSITY_ID, editTextUniversityId.getText());
                intentRegister.putExtra(ClassManagementActivity.EXTRA_USER_TYPE, userType);
                startActivity(intentRegister);

                Log.d(TAG, "I'm here");
                Firebase ref = new Firebase(getResources().getString(R.string.Firebase_url));
                Log.d(TAG, getResources().getString(R.string.Firebase_url));
                Log.d(TAG, "I'm here again");
                ref.createUser(editTextNewEmail.getText().toString(),
                        editTextNewPassword.getText().toString(),
                        new Firebase.ValueResultHandler<Map<String, Object>>() {
                            @Override
                            public void onSuccess(Map<String, Object> stringObjectMap) {
                                Log.d(TAG, "I'm here again");
                                Toast.makeText(getApplicationContext(),
                                        "User created!", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(FirebaseError firebaseError) {
                                Toast.makeText(getApplicationContext(),
                                        firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                break;

            case id.buttonBack:
                viewLogin.setVisibility(View.VISIBLE);
                viewRegister.setVisibility(View.GONE);
                break;
        }
    }
}
