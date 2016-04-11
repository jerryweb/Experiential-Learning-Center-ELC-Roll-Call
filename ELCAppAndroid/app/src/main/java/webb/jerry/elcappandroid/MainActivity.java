package webb.jerry.elcappandroid;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import webb.jerry.elcappandroid.Model.Beacon;
import webb.jerry.elcappandroid.Model.BeaconSingleton;

import static webb.jerry.elcappandroid.R.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "elc.webb.jerry.tag";

    View viewLogin;
    EditText textEmailAddress;
    EditText textPassword;
    Button forgotPasswordButton;
    Button loginButton;
    Button registerButton;
    BluetoothAdapter bluetoothAdapter;
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
    IntentFilter filter;
    BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        Firebase.setAndroidContext(this);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        init();

        if(bluetoothAdapter == null){
            Toast.makeText(getApplicationContext(),"Bluetooth is not enabled on your device", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            if(!bluetoothAdapter.isEnabled()){
                turnOnBluetooth();
            }
            else{
                startDiscovery();
                getDevices();
            }
        }


    }


    private void startDiscovery(){
        bluetoothAdapter.cancelDiscovery();
        bluetoothAdapter.startDiscovery();
    }

    private void init(){
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

        deviceNames = new ArrayList<>();

        userSelectionRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                userType = checkedId;
            }
        });

        // This handles finding new bluetooth devices within range
        // Filter just devices that are newly found
        deviceSet = new Set<BluetoothDevice>() {
            @Override
            public boolean add(BluetoothDevice object) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends BluetoothDevice> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean contains(Object object) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @NonNull
            @Override
            public Iterator<BluetoothDevice> iterator() {
                return null;
            }

            @Override
            public boolean remove(Object object) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> collection) {
                return false;
            }

            @Override
            public int size() {
                return 0;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(T[] array) {
                return null;
            }
        };


        filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if(BluetoothDevice.ACTION_FOUND.equals(action)){
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//                    for(BluetoothDevice d: deviceSet){
//                        if()
//                    }
                    Log.d("bluetooth", "device added: ");

                    for(String name: deviceNames){
                        if(name != device.getName()){
                            deviceSet.add(device);
                            deviceNames.add(device.getName());
                            Log.d("bluetooth", "device added");
                        }
                    }

//                    deviceSet.add(device);
//                    deviceNames.add(device.getName());
                }
                else if(BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)){

                }
                else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){

                }
                else if(BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)){
                    if(bluetoothAdapter.getState() == bluetoothAdapter.STATE_OFF){
                        turnOnBluetooth();
                    }
                }
            }

        };
        registerReceiver(receiver, filter);
        filter =  new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        registerReceiver(receiver, filter);
        filter =  new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(receiver, filter);
        filter =  new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(receiver, filter);

    }

    private void turnOnBluetooth(){
        Intent btIntent = new Intent(bluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(btIntent, 1);
    }

    private void getDevices(){
//        deviceSet = bluetoothAdapter.getBondedDevices();
//        if(!deviceSet.isEmpty()){
            ArrayList<Beacon> beacons = BeaconSingleton.get(getApplicationContext()).getMBeacons();
//            for(BluetoothDevice device: deviceSet){

                for(String name: deviceNames){
//                Toast.makeText(getApplicationContext(), device.getName() + "\n" + device.getAddress(), Toast.LENGTH_SHORT).show();
//                try {
//                    wait(300);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                    Log.d("device name: ", name);
                    for(Beacon beacon: beacons){
                        if(name == beacon.getBeaconName()){
                            foundBeacon();
                        }
                        break;
                    }
                    Toast.makeText(getApplicationContext(), "Done searching", Toast.LENGTH_SHORT).show();
            }
//        }
//        Toast.makeText(getApplicationContext(), "Done searching", Toast.LENGTH_SHORT).show();
        Log.d("device", "no devices");


    }

    private void foundBeacon(){
        Toast.makeText(getApplicationContext(), "Beacon found", Toast.LENGTH_SHORT).show();

    }

    // This is used for
    private void addClass(){

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED){
            Toast.makeText(getApplicationContext(),"Bluetooth must be enabled to continue", Toast.LENGTH_SHORT).show();
            finish();
        }
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

                Firebase ref = new Firebase(getResources().getString(R.string.Firebase_url));
                ref.authWithPassword(textEmailAddress.getText().toString(),
                        textPassword.getText().toString(),
                        new Firebase.AuthResultHandler() {
                            @Override
                            public void onAuthenticated(AuthData authData) {
                                Toast.makeText(getApplicationContext(),
                                        "User logged in!", Toast.LENGTH_LONG).show();
                                Intent intentLogin;
                                if (true) {
                                    intentLogin = new Intent(getApplicationContext(), StudentClassManagementActivity.class);
                                } else {
                                    intentLogin = new Intent(getApplicationContext(), ProfessorManageCoursesActivitty.class);

                                }
                                intentLogin.putExtra(StudentClassManagementActivity.EXTRA_EMAIL_ADDRESS, textEmailAddress.getText());
                                intentLogin.putExtra(StudentClassManagementActivity.EXTRA_PASSWORD, textPassword.getText());

                                startActivity(intentLogin);
                            }

                            @Override
                            public void onAuthenticationError(FirebaseError firebaseError) {
                                Toast.makeText(getApplicationContext(),
                                       firebaseError.getMessage(), Toast.LENGTH_LONG).show();
                            }


                        });
                getDevices();
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
                    intentRegister = new Intent(getApplicationContext(), StudentClassManagementActivity.class);
                }
                else {
                    intentRegister = new Intent(getApplicationContext(), ProfessorManageCoursesActivitty.class);
                }
                intentRegister.putExtra(StudentClassManagementActivity.EXTRA_EMAIL_ADDRESS, editTextNewEmail.getText());
                intentRegister.putExtra(StudentClassManagementActivity.EXTRA_PASSWORD, editTextNewPassword.getText());
                intentRegister.putExtra(StudentClassManagementActivity.EXTRA_CONFIRM_PASSWORD, editTextNewConfirmPass.getText());
                intentRegister.putExtra(StudentClassManagementActivity.EXTRA_FIRST_NAME, editTextfirstName.getText());
                intentRegister.putExtra(StudentClassManagementActivity.EXTRA_LAST_NAME, editTextLastName.getText());
                intentRegister.putExtra(StudentClassManagementActivity.EXTRA_UNIVERSITY_ID, editTextUniversityId.getText());
                intentRegister.putExtra(StudentClassManagementActivity.EXTRA_USER_TYPE, userType);
                startActivity(intentRegister);

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
