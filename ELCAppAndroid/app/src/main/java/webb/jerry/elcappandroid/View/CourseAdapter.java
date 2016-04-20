package webb.jerry.elcappandroid.View;

import java.util.ArrayList;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import webb.jerry.elcappandroid.Model.Course;
import webb.jerry.elcappandroid.R;

/**
 * Created by LJ on 3/5/16.
 */
public class CourseAdapter extends ArrayAdapter<Course>  {

    public boolean noCheckBox = false;
    public CourseAdapter(Context context, ArrayList<Course> courses){
        super(context, 0, courses);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Course course = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_course, parent
            , false);
        }


        CheckBox checkBoxStudentPresent = (CheckBox) convertView.findViewById(R.id.selectClassCheckBox);
        Button attendanceButton = (Button)  convertView.findViewById(R.id.attendanceButton);
        TextView textViewCourseName = (TextView) convertView.findViewById(R.id.textViewCourseName);
        TextView textViewInstructorName = (TextView) convertView.findViewById(R.id.textViewInstructorName);
        TextView textViewDate = (TextView) convertView.findViewById(R.id.textViewDate);


        textViewCourseName.setText(course.getClassName());
        textViewInstructorName.setText(course.getInstructorName());
        textViewDate.setText(course.getDates());

        if (noCheckBox) {
            Log.d("TAG", "I dont want that check box");
            attendanceButton.setVisibility(View.VISIBLE);
            checkBoxStudentPresent.setVisibility(View.INVISIBLE);
        }
        else if (!noCheckBox){
            Log.d("TAG", "I want my check box");
            checkBoxStudentPresent.setVisibility(View.VISIBLE);
            attendanceButton.setVisibility(View.INVISIBLE);
        }

        checkBoxStudentPresent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                course.setChecked(!course.isChecked());
            }
        });


        return convertView;
    }
}
