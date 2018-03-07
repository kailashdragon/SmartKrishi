package com.example.kailashpatel.smartkrishi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayFarmer extends AppCompatActivity {

    ImageView mIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_farmer);


        mIcon = (ImageView) findViewById(R.id.ivProfile);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.Farmer);
        RoundedBitmapDrawable mDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        mDrawable.setCircular(true);
        mDrawable.setColorFilter(ContextCompat.getColor(DisplayFarmer.this, R.color.colorAccent), PorterDuff.Mode.DST_OVER);
        mIcon.setImageDrawable(mDrawable)

        Bundle b = getIntent().getExtras();
        TextView name = (TextView) findViewById(R.id.nameValue);
        TextView dob = (TextView) findViewById(R.id.dobValue);
        TextView address = (TextView) findViewById(R.id.addressValue);
        TextView  radiogroup = (TextView) findViewById(R.id.rgValue);

        name.setText(b.getCharSequence("name"));
        dob.setText(b.getCharSequence("DateOfBirth"));
        address.setText(b.getCharSequence("Address"));
        radiogroup.setText(b.getCharSequence("Farmer/Contractor"));
    }
}
