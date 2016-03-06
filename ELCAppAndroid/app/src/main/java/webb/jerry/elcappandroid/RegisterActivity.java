package webb.jerry.elcappandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.content.Intent;

/**
 * Created by LJ on 3/4/16.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    RadioGroup userSelectionRadioGroup;
    EditText editTextfirstName;
    EditText editTextLastName;
    EditText editTextEmail;
    EditText editTextUniversityId;
    EditText editTextPassword;
    EditText editTextConfirmPass;
    Button buttonCreateAccount;
    Button buttonClear;
    Integer userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userSelectionRadioGroup = (RadioGroup) findViewById(R.id.userSelectionRadioGroup);

        editTextfirstName = (EditText) findViewById(R.id.EditTextfirstName);
        editTextLastName = (EditText) findViewById(R.id.editTextLastName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextUniversityId = (EditText) findViewById(R.id.editTextUniversityId);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPass = (EditText) findViewById(R.id.editTextConfirmPass);

        buttonCreateAccount = (Button) findViewById(R.id.buttonCreateAccount);
        buttonClear = (Button) findViewById(R.id.buttonClear);

        userType = R.id.radioButtonStudent;

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
            case R.id.buttonCreateAccount:
                if(userType == R.id.radioButtonInstructor) {
                    Intent i = new Intent(getApplicationContext(), ProfessorManageCoursesActivitty.class);
                }
                if (userType == R.id.radioButtonStudent) {

                }
                break;
            case R.id.buttonClear:
                editTextfirstName.setText("");
                editTextLastName.setText("");
                editTextEmail.setText("");
                editTextUniversityId.setText("");
                editTextPassword.setText("");
                editTextConfirmPass.setText("");
                break;
        }
    }
}
