package webb.jerry.elcappandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText textEmailAddress;
    EditText textPassword;
    Button forgotPasswordButton;
    Button loginButton;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textEmailAddress = (EditText) findViewById(R.id.editTextEmail);
        textPassword = (EditText) findViewById(R.id.passwordEditText);
        forgotPasswordButton = (Button) findViewById(R.id.forgotPasswordButton);
        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton =(Button) findViewById(R.id.loginButton);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginButton:
                break;
            case R.id.registerButton:
                break;
            case R.id.forgotPasswordButton:
                break;
        }
    }
}
