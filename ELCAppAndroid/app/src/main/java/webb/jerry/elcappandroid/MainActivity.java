package webb.jerry.elcappandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText textEmailAddress;
    EditText textPassword;
    Button forgotPasswordButton;
    Button loginButton;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textEmailAddress = (EditText) findViewById(R.id.emailEditText);
        textPassword = (EditText) findViewById(R.id.passwordEditText);
    }
}
