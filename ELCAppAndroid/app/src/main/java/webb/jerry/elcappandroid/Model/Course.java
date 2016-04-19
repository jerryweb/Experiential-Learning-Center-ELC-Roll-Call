package webb.jerry.elcappandroid.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


/**
 * Created by LJ on 3/5/16.
 * This class holds all the information for the 'course' object including name, instructor name,
 * days of the week the class is held, and if the user (student) was present
 */
public class Course implements Serializable{
    public static final String TAG = "ELCApp.webb.jerry.elc_roll_call.tag";

    private String className, instructorName, dates, beaconName;
//    private ArrayList<Boolean> daysPresent;
    private Map<Date, Boolean> daysPresent;
    public Course(){
        super();
    }

    public Course(String cN, String iN, String date, String bN){
        super();
        this.className = cN;
        this.instructorName = iN;
        this.dates = date;
        this.beaconName = bN;
    }

    public Map<Date, Boolean> getDaysPresent() {
        return daysPresent;
    }

    public void setDaysPresent(Map<Date, Boolean> daysPresent) {
        this.daysPresent = daysPresent;
    }

    public void addCurrentDayPresent(Date d, boolean present){
        daysPresent.put(d,present);
    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getBeaconName() {
        return beaconName;
    }

    public void setBeaconName(String beaconName) {
        this.beaconName = beaconName;
    }

}
