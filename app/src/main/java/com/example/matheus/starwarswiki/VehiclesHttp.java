package com.example.matheus.starwarswiki;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class VehiclesHttp {

    //Connect
    private static HttpURLConnection connect(String pName) {

        String newName = pName.replace(" ", "%20");

        final int SECONDS = 10000;

        try {
            java.net.URL url = new URL ("https://swapi.co/api/vehicles/?search="+newName);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10 * SECONDS);
            connection.setConnectTimeout(15 * SECONDS);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(false);
            connection.connect();
            return connection;
        } catch (IOException e) {
            Log.d("ERROR", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    //Check if it's connected
    public static boolean hasConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return (info != null && info.isConnected());

    }

    public static Vehicles readJSONVehicle (JSONObject json) {

        Vehicles v = new Vehicles();

        try {
            JSONArray results = json.getJSONArray("results");

            for (int i = 0; i < 1; i++) {
                JSONObject jsonVehicle = results.getJSONObject(i);
                Vehicles vehicleObj = new Vehicles (
                        jsonVehicle.getString("name"),
                        jsonVehicle.getString("model"),
                        jsonVehicle.getString("manufacturer"),
                        jsonVehicle.getString("length"),
                        jsonVehicle.getInt("max_atmosphering_speed"));
                v = vehicleObj;
            }

            return v;

        } catch (JSONException ex) {
            Log.d("JSON", ex.getMessage());
        }

        return null;

    }

    public static Vehicles loadVehicle(String pName) {

        try {
            HttpURLConnection connection = connect(pName);
            int response = connection.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                JSONObject json = new JSONObject(bytesToString(inputStream));
                Vehicles v = readJSONVehicle(json);
                return v;
            }
        } catch (Exception ex) {
            Log.d("ERROR", ex.getMessage());
        }

        return null;
    }

    private static String bytesToString(InputStream inputStream) {
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream bufferzao = new ByteArrayOutputStream();
        int byteslidos;
        try {
            while ((byteslidos = inputStream.read(buffer)) != -1) {
                bufferzao.write(buffer, 0, byteslidos);

            }
            return new String(bufferzao.toByteArray(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
