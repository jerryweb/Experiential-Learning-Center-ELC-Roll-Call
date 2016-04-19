package webb.jerry.elcappandroid.Model;

/**
 * Created by tiffanyniicole on 4/18/16.
 */
public class User {
    String firstName;
    String lastName;
    String email;
    int universityId;
    boolean student = false;
    String [] myClasses;

    public User() {

    }

    public User(String firstName, int universityId, String lastName, String email, boolean student) {
        this.firstName = firstName;
        this.universityId = universityId;
        this.lastName = lastName;
        this.email = email;
        this.student = student;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public boolean isStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    public String[] getMyClasses() {
        return myClasses;
    }

    public void setMyClasses(String[] myClasses) {
        this.myClasses = myClasses;
    }
}
