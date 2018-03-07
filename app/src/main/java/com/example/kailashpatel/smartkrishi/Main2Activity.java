package com.example.kailashpatel.smartkrishi;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    EditText labourname;
    EditText labourphone;
    Spinner labourspinner;
    Button addlabour;
    DatabaseReference mdatabaseReference;
    ListView viewlabour;
    List<Labour> labours;

    public static String labourid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        labourname = (EditText) findViewById(R.id.labourname);
        labourphone = (EditText) findViewById(R.id.labourphone);
        labourspinner = (Spinner) findViewById(R.id.labourspinner);
        addlabour = (Button) findViewById(R.id.addlabour);
        viewlabour = (ListView) findViewById(R.id.viewlabour);
        labours =new ArrayList<Labour>();

        mdatabaseReference= FirebaseDatabase.getInstance().getReference("Labours");

        addlabour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = labourname.getText().toString();
                String phone = labourphone.getText().toString();
                String bf = labourspinner.getSelectedItem().toString();

                if (!TextUtils.isEmpty(name) && phone.length()==10) {
                    String id = mdatabaseReference.push().getKey();
                    Labour labour=new Labour(id, name, phone, bf);

                    mdatabaseReference.child(id).setValue(labour);

                    Toast.makeText(Main2Activity.this, "Labour Added Successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Main2Activity.this, "Please enter name and number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewlabour.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Labour labour = labours.get(i);
                showUpdateDeleteDialog(labour.getId(), labour.getName(), labour.getPhone(), labour.getBf());
                return true;
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        mdatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                labours.clear();

                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Labour labour=postSnapshot.getValue(Labour.class);
                    labours.add(labour);
                }
                LabourList labourAdapter = new LabourList(Main2Activity.this, labours);
                viewlabour.setAdapter(labourAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Main2Activity.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private boolean updateLabour(String id, String name, String phone, String bf) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Labours").child(id);

        //updating artist
        Labour labour = new Labour(id, name, phone, bf);
        dR.setValue(labour);
        Toast.makeText(getApplicationContext(), "Labour Updated", Toast.LENGTH_LONG).show();
        return true;
    }
    private void showUpdateDeleteDialog(final String labourId, String labourName, String labourPhone, final String labourSpinner) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText labourname = (EditText) dialogView.findViewById(R.id.labourname);
        final EditText labourphone = (EditText) dialogView.findViewById(R.id.labourphone);
        final Spinner labourspinner = (Spinner) dialogView.findViewById(R.id.labourspinner);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateLabour);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteLabour);

        dialogBuilder.setTitle(labourName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = labourname.getText().toString().trim();
                String phone = labourphone.getText().toString().trim();
                String bf = labourspinner.getSelectedItem().toString().trim();
                if (!TextUtils.isEmpty(name) && phone.length()==10) {
                    updateLabour(labourId, name, phone, bf);
                    b.dismiss();
                }
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteLabour(labourId);
                b.dismiss();

            }
        });
    }
    private boolean deleteLabour(String id) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Labours").child(id);

        //removing artist
        dR.removeValue();

        Toast.makeText(getApplicationContext(), "Labour Deleted", Toast.LENGTH_LONG).show();

        return true;
    }
}
