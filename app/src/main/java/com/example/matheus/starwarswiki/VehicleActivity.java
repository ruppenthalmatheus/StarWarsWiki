package com.example.matheus.starwarswiki;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class VehicleActivity extends AppCompatActivity {

    private static String vehicleName;
    TextView mName;
    TextView mModel;
    TextView mManufacter;
    TextView mLength;
    TextView mMaxSpeed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        vehicleName = bundle.getString("vehicleName");

        mName = findViewById(R.id.txtVehicle);
        mModel = findViewById(R.id.txtModel);
        mManufacter = findViewById(R.id.txtManufacter);
        mLength = findViewById(R.id.txtLength);
        mMaxSpeed = findViewById(R.id.txtMaxSpeed);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                finishAffinity();
                break;
            default:break;
        }
        return true;
    }
}
