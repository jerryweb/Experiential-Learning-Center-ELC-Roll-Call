package webb.jerry.elcappandroid.Model;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import webb.jerry.elcappandroid.R;

/**
 * Created by LJ on 4/18/16.
 */
public class BluetoothSingleton {
    public static final String PREF_EMAIL = "elc.rollcall.preferences.email";
    private static final String PREF_FILENAME = "webb.jerry.elcappandroid.preferences.app_prefs";
    public static BluetoothSingleton sBluetoothSingleton;
    //    private static final int DISCOVERY_REQUEST = 1;
    private Context mAppContext;
    public BluetoothAdapter mBluetoothAdapter;
    IntentFilter filter;
    Boolean scanning;

    private BluetoothSingleton(Context c){
        this.mAppContext = c;
        initBluetooth();
    }

    public static BluetoothSingleton get(Context c) {
        if(sBluetoothSingleton == null){
            sBluetoothSingleton = new BluetoothSingleton(c.getApplicationContext());
        }
        return sBluetoothSingleton;
    }

    public boolean initBluetooth(){
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        scanning = false;

        if(mBluetoothAdapter == null){
            Toast.makeText(mAppContext, "Bluetooth is not enabled on your device", Toast.LENGTH_SHORT).show();
        }
        else {
            if (!mBluetoothAdapter.isEnabled()) {
                return false;
            } else {
                // This handles finding new bluetooth devices within range
                // Filter just devices that are newly found
                IntentFilter filter = new IntentFilter();

                filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
                filter.addAction(BluetoothDevice.ACTION_FOUND);
                filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
                filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

                mAppContext.registerReceiver(mReceiver, filter);
            }
        }
        return true;
    }

    // Look for other devices within range to see if it matches the beacons requested
    public void toggleBeaconSearch(){
        if (mBluetoothAdapter.isDiscovering()) {
            mBluetoothAdapter.cancelDiscovery();
            scanning = false;
        }
        else if (!scanning){
            mBluetoothAdapter.startDiscovery();
            scanning= true;
        }
    }


    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);

                if (state == BluetoothAdapter.STATE_ON) {
                    Toast.makeText(mAppContext,"Enabled",Toast.LENGTH_SHORT).show();
                }
            }
            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                //discovery starts, we can show progress dialog or perform other tasks
                Toast.makeText(mAppContext,"started",Toast.LENGTH_SHORT).show();

            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                //discovery finishes, dismis progress dialog
                Toast.makeText(mAppContext,"reseting search",Toast.LENGTH_SHORT).show();
                mBluetoothAdapter.startDiscovery();

            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //bluetooth device found
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String name = device.getName();
//                Toast.makeText(getApplicationContext(),name,Toast.LENGTH_SHORT).show();
                boolean foundBeacon = BeaconSingleton.get(mAppContext).searchBeacon(device.getName(),device.getAddress());
                if(foundBeacon){
                    Toast.makeText(mAppContext,"we can now log in!",Toast.LENGTH_SHORT).show();
                    //Tell firebase to mark student as here
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
                    final String formattedDate = df.format(calendar.getTime());

                    SharedPreferences prefs = mAppContext.getSharedPreferences(
                            PREF_FILENAME, mAppContext.MODE_PRIVATE);
                    final String encodedEmail = prefs.getString(PREF_EMAIL, "current_user");
                   final BeaconSingleton b = new BeaconSingleton(mAppContext.getApplicationContext());

                        Firebase userLocation = new Firebase(mAppContext.getResources().
                                getString(R.string.Firebase_url));
                        userLocation.child("Users").child(encodedEmail)
                                .child("courses").addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                                final Map<String, Object> newCourse = (Map<String, Object>) dataSnapshot.getValue();
                                Log.d("TAG", "I'm HERE");
                                Log.d("TAG", Integer.toString(newCourse.size()));
                                if (newCourse.get("beaconName").toString().equals(b.getBeaconName())) {
                                   final Firebase userLocation1 = new Firebase(mAppContext.getResources().getString(R.string.Firebase_url))
                                            .child("Courses").child(newCourse.get("className")
                                            + " " + newCourse.get("dates")).child(formattedDate);
                                    userLocation1.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.getValue() == null) {
                                                userLocation1.setValue(newCourse.get("firstName")
                                                + " " + newCourse.get("lastName"));
                                            }
                                        }

                                        @Override
                                        public void onCancelled(FirebaseError firebaseError) {

                                        }
                                    });
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

            }
        }
    };

    public void unregisterReceiver(){
        mAppContext.unregisterReceiver(this.mReceiver);
    }

    public void stopDiscovery(){
        if (mBluetoothAdapter != null) {
            if (mBluetoothAdapter.isDiscovering()) {
                mBluetoothAdapter.cancelDiscovery();
            }
        }
    }
}
