package webb.jerry.elcappandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by LJ on 4/18/16.
 */
public class InstructorAdmitActivity extends AppCompatActivity implements View.OnClickListener {
    EditText courseNameEditText;
    EditText beaconAddressEditText;
    EditText beaconNameEditText;
    EditText majorEditText;
    EditText instructorNameEditText;

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
        beaconAddressEditText = (EditText) findViewById(R.id.beaconAddressEditText);
        beaconNameEditText = (EditText) findViewById(R.id.beaconNameEditText);
        majorEditText = (EditText) findViewById(R.id.majorEditText);
        instructorNameEditText = (EditText) findViewById(R.id.instructorNameEditText);

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
                break;

            case R.id.cancelButton:
                break;
        }
    }
}
