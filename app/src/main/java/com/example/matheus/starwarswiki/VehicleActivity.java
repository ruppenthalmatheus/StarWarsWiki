package com.example.matheus.starwarswiki;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class VehicleActivity extends AppCompatActivity {

    VehiclesTask pTask;
    Vehicles v;
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

        mName = findViewById(R.id.lblName);
        mModel = findViewById(R.id.txtModel);
        mManufacter = findViewById(R.id.txtManufacter);
        mLength = findViewById(R.id.txtLength);
        mMaxSpeed = findViewById(R.id.txtMaxSpeed);
        search();

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

    private void search() {

        if (v == null) {
            v = new Vehicles();
        }

        if (pTask == null) {
            if (VehiclesHttp.hasConnected(this)) {
                startDownload();
            } else {
                Toast.makeText(getApplicationContext(), "Sem conexão!", Toast.LENGTH_LONG).show();
            }
        } else if (pTask.getStatus() == AsyncTask.Status.RUNNING){

            Toast.makeText(getApplicationContext(), "......", Toast.LENGTH_LONG).show();

        }

    }

    public void startDownload() {

        if (pTask == null || pTask.getStatus() != AsyncTask.Status.RUNNING) {

            pTask = new VehicleActivity.VehiclesTask();
            pTask.execute();

        }

    }

    //INNER CLASS
    class VehiclesTask extends AsyncTask<Void, Void, Vehicles> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Pronto!", Toast.LENGTH_LONG).show();
        }

        @Override
        protected Vehicles doInBackground(Void... strings) {
            Vehicles vehicle = VehiclesHttp.loadVehicle(vehicleName);
            return vehicle;
        }

        @Override
        protected void onPostExecute(Vehicles newVehicle) {
            super.onPostExecute(v);
            if (v != null) {
                v = newVehicle;
                mName.setText(v.getnName());
                mModel.setText(v.getnModel());
                mManufacter.setText(v.getnManufacter());
                mLength.setText(v.getnLength());
                mMaxSpeed.setText(String.valueOf(v.getnMaxSpeed()));
            } else {

                Toast.makeText(getApplicationContext(), "Buscando...", Toast.LENGTH_LONG).show();
            }

        }
    }

}
