package webb.jerry.elcappandroid.Model;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by LJ on 4/10/16.
 * This holds the list of beacons that associate with the student's class
 * This class and information is not directly accessible to the user
 */
public class BeaconSingleton {
    ArrayList<Beacon> mBeacons;
    private static BeaconSingleton sBeacon;
    private Context mAppContext;

    public BeaconSingleton(Context c){
        this.mAppContext = c;
        mBeacons = new ArrayList<Beacon>();
        Beacon beacon = new Beacon("XY-Beacon", "", "", "","00:EB:19:00:4E:A2");
        mBeacons.add(beacon);
    }

    public static BeaconSingleton get(Context c) {
        if (sBeacon == null) {
            sBeacon = new BeaconSingleton(c.getApplicationContext());
        }
        return sBeacon;
    }

    public ArrayList<Beacon> getMBeacons() {
        return mBeacons;
    }

    public void addBeacon(Beacon b) {
        mBeacons.add(b);
    }

    public boolean searchBeacon(String name, String address){
        for(Beacon b: mBeacons) {
            if (Objects.equals(b.getBeaconName(), name) || Objects.equals(b.getAddress(), address)) {
                Toast.makeText(mAppContext, "found!", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
            return false;
    }

    public void removeBeacon(Beacon b){

        if(!mBeacons.isEmpty()){
            for(Beacon beacon: mBeacons){
                if(b == beacon) {
                    mBeacons.remove(b);
                }
            }
        }

    }

    public String getBeaconName(){
        return "XY-Beacon";
    }
}
