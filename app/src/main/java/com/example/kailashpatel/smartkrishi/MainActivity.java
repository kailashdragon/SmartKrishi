package com.example.kailashpatel.smartkrishi;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

import Helper.LocaleHelper;
import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button registerbtn=(Button)findViewById(R.id.registerbtn);
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(MainActivity.this, Register.class);
                startActivity(i1);
            }
        });
        Button loginbtn=(Button) findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(MainActivity.this, SignInPhone.class);
                startActivity(i2);
            }
        });
        Button labourinfo=(Button) findViewById(R.id.labourinfo);
        labourinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i3);
            }
        });
        Button fetchdata=(Button) findViewById(R.id.fetchdata);
        fetchdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4=new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(i4);
            }
        });

    }
}
