package webb.jerry.elcappandroid.Model;

import java.io.Serializable;

/**
 * Created by LJ on 3/5/16.
 */
public class Course implements Serializable{
    public static final String TAG = "ELCApp.webb.jerry.elc_roll_call.tag";

    private String className, instructorName, dates;
    private boolean studentPresent;

    public Course(){
        super();
    }

    public Course(String cN, String iN, String date){
        super();
        this.className = cN;
        this.instructorName = iN;
        this.dates = date;
        this.studentPresent = false;
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
