package com.example.matheus.starwarswiki;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String characterName;
    String vehicleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton searchCharacter = findViewById(R.id.searchCharacter);
        ImageButton searchVehicle = findViewById(R.id.searchVehicle);

        searchCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText txtCharacter = (EditText) findViewById(R.id.txtCharacter);
                characterName = txtCharacter.getText().toString();

                if (characterName.equals("")) {
                    Toast.makeText(getApplicationContext(), "Informe o nome de um personagem!", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent (getApplicationContext(), CharacterActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("characterName", characterName);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }
        });

        searchVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText txtVehicle = (EditText) findViewById(R.id.txtVehicle);
                vehicleName = txtVehicle.getText().toString();

                if (vehicleName.equals("")) {
                    Toast.makeText(getApplicationContext(), "Informe o nome de um veículo!", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent (getApplicationContext(), VehicleActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("vehicleName", characterName);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }
        });

    }
}
