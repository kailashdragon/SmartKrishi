package com.example.kailashpatel.smartkrishi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.kailashpatel.smartkrishi.R.id.radioGroup;

public class Register extends AppCompatActivity {
    private EditText name;

    private EditText address;

    private EditText password;

    private EditText phone;
    private EditText dob;

    private Button submit;

    private RadioGroup radioGroup1;

    private RadioButton radioButton;

    private AwesomeValidation awesomeValidation;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private FirebaseDatabase firebaseDatabase;

    private DatabaseReference mdbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.etName);
        address = (EditText) findViewById(R.id.etAddress);
        phone = (EditText) findViewById(R.id.etPhoneNumber);
        password = (EditText) findViewById(R.id.etPassword);
        dob = (EditText) findViewById(R.id.etDOB);

        radioGroup1 = (RadioGroup) findViewById(radioGroup);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.etName, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.invalid_name);
        awesomeValidation.addValidation(this, R.id.etAddress, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}", R.string.invalid_address);
        awesomeValidation.addValidation(this, R.id.etPassword, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.invalid_password);

        firebaseDatabase=FirebaseDatabase.getInstance();
        mdbr = firebaseDatabase.getInstance().getReference();
        submit = (Button) findViewById(R.id.btnReg);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sid = radioGroup1.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(sid);
                if (awesomeValidation.validate()) {
                    Intent intent = new Intent(getApplicationContext(), DisplayFarmer.class);
                    Bundle b = new Bundle();
                    //process the data further
                    Farmer farmer = new Farmer(name.getText().toString(), address.getText().toString(), dob.getText().toString(), phone.getText().toString());
                    mdbr.child("Farmer").push().setValue(farmer);

                    b.putString("name", name.getText().toString());
                    b.putString("address", address.getText().toString());
                    b.putString("DateOfBirth", dob.getText().toString());
                    int id = radioGroup1.getCheckedRadioButtonId();
                    RadioButton radioButton = (RadioButton) findViewById(id);
                    b.putString("radiogroup", radioButton.getText().toString());

                    //Add the bundle to the intent.
                    intent.putExtras(b);

                    //start the DisplayActivity
                    startActivity(intent);
                }
            }

        });


    }
}
