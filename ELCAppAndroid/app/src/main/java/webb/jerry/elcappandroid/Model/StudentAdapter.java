package webb.jerry.elcappandroid.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import webb.jerry.elcappandroid.R;

/**
 * Created by tiffanyniicole on 4/18/16.
 */
public class StudentAdapter extends ArrayAdapter<Course> {

    public StudentAdapter(Context context, ArrayList<Course> course) {
        super(context, 0, course);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Course course =  getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_course, parent, false);
        }

        TextView courseTextView = (TextView) convertView.findViewById(R.id.textViewCourseName);
        TextView professorTextView = (TextView) convertView.findViewById(R.id.textViewInstructorName);
        TextView dateTextView = (TextView) convertView.findViewById(R.id.textViewDate);
        courseTextView.setText(course.getClassName());
        professorTextView.setText(course.getInstructorName());
        dateTextView.setText(course.getDates());

        return convertView;
    }
}
