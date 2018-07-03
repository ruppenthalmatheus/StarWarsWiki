package com.example.matheus.starwarswiki;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CharacterActivity extends AppCompatActivity {

    CharactersTask pTask;
    Characters c;
    public static String characterName;
    TextView mName;
    TextView mHeight;
    TextView mMass;
    TextView mHairColor;
    TextView mSkinColor;
    TextView mEyeColor;
    TextView mBirthYear;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        characterName = bundle.getString("characterName");

        mName = findViewById(R.id.lblName);
        mHeight = findViewById(R.id.txtHeight);
        mMass = findViewById(R.id.txtMass);
        mHairColor = findViewById(R.id.txtHairColor);
        mSkinColor = findViewById(R.id.txtSkinColor);
        mEyeColor = findViewById(R.id.txtEyeColor);
        mBirthYear = findViewById(R.id.txtBirthYear);
        search();
        mName.setText(c.getnName());
        mHeight.setText(String.valueOf(c.getnHeight()));
        mMass.setText(String.valueOf(c.getnMass()));
        mBirthYear.setText(c.getnBirthYear());
        mHairColor.setText(c.getnHairColor());
        mSkinColor.setText(c.getnSkinColor());
        mEyeColor.setText(c.getnEyeColor());

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

        if (c == null) {
            c = new Characters();
        }

        if (pTask == null) {
            if (CharactersHttp.hasConnected(this)) {
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

            pTask = new CharactersTask();
            pTask.execute();

        }

    }

    //INNER CLASS
    class CharactersTask extends AsyncTask<Void, Void, Characters> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Pronto!", Toast.LENGTH_LONG).show();
        }

        @Override
        protected Characters doInBackground(Void... strings) {
            Characters character = CharactersHttp.loadCharacter(characterName);
            return character;
        }

        @Override
        protected void onPostExecute(Characters newCharacter) {
            super.onPostExecute(c);
            if (c != null) {
                c = newCharacter;
            } else {

                Toast.makeText(getApplicationContext(), "Buscando...", Toast.LENGTH_LONG).show();
            }

        }
    }



}
