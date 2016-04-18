package webb.jerry.elcappandroid.Model;

import java.io.Serializable;

/**
 * Created by LJ on 3/5/16.
 * This class holds all the information for the 'course' object including name, instructor name,
 * days of the week the class is held, and if the user (student) was present
 */
public class Course implements Serializable{
    public static final String TAG = "ELCApp.webb.jerry.elc_roll_call.tag";

    private String className, instructorName, dates, beaconAddress;
    private boolean studentPresent;

    public Course(){
        super();
    }

    public Course(String cN, String iN, String date, String bA){
        super();
        this.className = cN;
        this.instructorName = iN;
        this.dates = date;
        this.studentPresent = false;
        this.beaconAddress = bA;
    }

    public boolean isStudentPresent() {
        return studentPresent;
    }

    public void setStudentPresent(boolean studentPresent) {
        this.studentPresent = studentPresent;
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


}
