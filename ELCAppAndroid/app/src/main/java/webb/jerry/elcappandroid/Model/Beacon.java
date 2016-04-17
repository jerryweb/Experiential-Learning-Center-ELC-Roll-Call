package webb.jerry.elcappandroid.Model;

/**
 * Created by LJ on 4/10/16.
 */
public class Beacon {
    public static final String TAG = "ELCApp.webb.jerry.elc_roll_call.tag";

    private String beaconName, beaconId, courseId, instructorName, address;

    public Beacon (){
        super();
    }

    public Beacon (String bN, String bId, String cId, String iN, String a){
        super();
        this.beaconName = bN;
        this.beaconId = bId;
        this.courseId = cId;
        this.instructorName = iN;
        this.address = a;
    }

    public String getBeaconName() {
        return beaconName;
    }

    public void setBeaconName(String beaconName) {
        this.beaconName = beaconName;
    }

    public String getBeaconId() {
        return beaconId;
    }

    public void setBeaconId(String beaconId) {
        this.beaconId = beaconId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }
    //    String beaconName = "XY-8EC4-64";
//    String beaconId = "00.EA:23:33:8E:C4";

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
