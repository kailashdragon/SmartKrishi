package com.example.kailashpatel.smartkrishi;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

/**
 * Created by Kailash Patel on 09-02-2018.
 */
public class LabourList extends ArrayAdapter<Labour> {
    DatabaseReference mdatabaseReference;
    EditText labourname;
    EditText labourphone;
    Spinner labourspinner;
    private Activity context;
    List<Labour> labours;

    public LabourList(Activity context, List<Labour> labours) {
        super(context, R.layout.layout_labour_list, labours);
        this.context = context;
        this.labours = labours;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_labour_list, null, true);

        TextView viewlabourname = (TextView) listViewItem.findViewById(R.id.viewlabourname);
        TextView viewlabourphone = (TextView) listViewItem.findViewById(R.id.viewlabourphone);
        TextView viewlabourspinner = (TextView) listViewItem.findViewById(R.id.viewlabourspinner);

        final Labour labour = labours.get(position);
        viewlabourname.setText(labour.getName());
        viewlabourphone.setText(labour.getPhone());
        viewlabourspinner.setText(labour.getBf());

        return listViewItem;
    }
}