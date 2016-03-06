package webb.jerry.elcappandroid.View;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import webb.jerry.elcappandroid.Model.Course;
import webb.jerry.elcappandroid.R;

/**
 * Created by LJ on 3/5/16.
 */
public class CourseAdapter extends ArrayAdapter<Course>  {

    public CourseAdapter(Context context, ArrayList<Course> courses){
        super(context, 0, courses);
    }
    boolean instructorInterface;


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Course course = getItem(position);
        instructorInterface = true;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_course, parent
            , false);
        }

        CheckBox checkBoxStudentPresent = (CheckBox) convertView.findViewById(R.id.checkBoxStudentPresent);
        TextView textViewCourseName = (TextView) convertView.findViewById(R.id.textViewCourseName);
        TextView textViewInstructorName = (TextView) convertView.findViewById(R.id.textViewInstructorName);
        TextView textViewDate = (TextView) convertView.findViewById(R.id.textViewDate);

//        if(instructorInterface == false){
//            checkBoxStudentPresent.setVisibility();
//        }

        textViewCourseName.setText(course.getClassName());
        textViewInstructorName.setText(course.getInstructorName());
        textViewDate.setText(course.getDates());


        return convertView;
    }
}
