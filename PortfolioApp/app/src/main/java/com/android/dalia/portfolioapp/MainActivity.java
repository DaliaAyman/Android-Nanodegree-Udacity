package com.android.dalia.portfolioapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button popularMoviesButton = (Button) findViewById(R.id.popularMoviesButton);
        Button stockHawkButton = (Button) findViewById(R.id.stockHawkButton);
        Button buildItBiggerButton = (Button) findViewById(R.id.buildItBiggerButton);
        Button makeYourAppMaterialButton = (Button) findViewById(R.id.makeYourAppMaterialButton);
        Button goUbiquitousButton = (Button) findViewById(R.id.goUbiquitousButton);
        Button capstoneMyOwnApp = (Button) findViewById(R.id.capstoneMyOwnAppButton);


        popularMoviesButton.setOnClickListener(buttonClickListener);
        stockHawkButton.setOnClickListener(buttonClickListener);
        buildItBiggerButton.setOnClickListener(buttonClickListener);
        makeYourAppMaterialButton.setOnClickListener(buttonClickListener);
        goUbiquitousButton.setOnClickListener(buttonClickListener);
        capstoneMyOwnApp.setOnClickListener(buttonClickListener);

    }

    View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.popularMoviesButton:
                    Toast.makeText(MainActivity.this, "This button will show Popular Movies App", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.stockHawkButton:
                    Toast.makeText(MainActivity.this, "This button will show Stock Hawk App", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.buildItBiggerButton:
                    Toast.makeText(MainActivity.this, "This button will show Build It Bigger App", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.makeYourAppMaterialButton:
                    Toast.makeText(MainActivity.this, "This button will show Make Your App Material App", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.goUbiquitousButton:
                    Toast.makeText(MainActivity.this, "This button will show Go Ubiquitous App", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.capstoneMyOwnAppButton:
                    Toast.makeText(MainActivity.this, "This button will show Capstone: My Own App", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
