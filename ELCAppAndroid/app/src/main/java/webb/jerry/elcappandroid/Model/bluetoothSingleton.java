package webb.jerry.elcappandroid.Model;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

/**
 * Created by LJ on 4/18/16.
 * This allows for the application to scan for bluetooth across all activities
 * with one bluetooth instantiation. The main scanning and discovery methods
 * are located here.
 */
public class bluetoothSingleton {
    private static bluetoothSingleton sBluetoothSingleton;
    private static final int DISCOVERY_REQUEST = 1;
    private Context mAppContext;
    public BluetoothAdapter mBluetoothAdapter;
    IntentFilter filter;

    private bluetoothSingleton(Context c){
        this.mAppContext = c;
        initBluetooth();
    }

    public static bluetoothSingleton get(Context c) {
        if(sBluetoothSingleton == null){
            sBluetoothSingleton = new bluetoothSingleton(c.getApplicationContext());
        }
        return sBluetoothSingleton;
    }

    public boolean initBluetooth(){
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


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
    public void searchForBeacon(){
        mBluetoothAdapter.cancelDiscovery();
        mBluetoothAdapter.startDiscovery();
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
                Toast.makeText(mAppContext,"done",Toast.LENGTH_SHORT).show();

            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //bluetooth device found
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String name = device.getName();
//                Toast.makeText(getApplicationContext(),name,Toast.LENGTH_SHORT).show();
                boolean foundBeacon = BeaconSingleton.get(mAppContext).searchBeacon(device.getName(),device.getAddress());
                if(foundBeacon){
                    Toast.makeText(mAppContext,"we can now log in!",Toast.LENGTH_SHORT).show();
                }

            }
        }
    };

    public void toggleDiscovery(){
        if (mBluetoothAdapter != null) {
            if (mBluetoothAdapter.isDiscovering()) {
                mBluetoothAdapter.cancelDiscovery();
            }
        }
    }

    public void unregisterReceiver(){
        mAppContext.unregisterReceiver(this.mReceiver);
    }
}
