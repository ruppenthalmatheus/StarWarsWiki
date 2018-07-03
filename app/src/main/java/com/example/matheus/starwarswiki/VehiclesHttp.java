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
import java.util.ArrayList;

public class VehiclesHttp {

    //Connect
    private static HttpURLConnection connect(String pName) {

        final int SECONDS = 10000;

        try {
            java.net.URL url = new URL ("https://swapi.co/api/vehicles/?search="+pName);
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

    public static ArrayList<Vehicles> readJSONVehicle (JSONObject json) {

        ArrayList<Vehicles> arrayList = new ArrayList<>();

        try {
            JSONArray results = json.getJSONArray("results");

            for (int i = 0; i < 1; i++) {
                JSONObject jsonVehicle = results.getJSONObject(i);
                Vehicles v = new Vehicles (
                        jsonVehicle.getString("name"),
                        jsonVehicle.getString("model"),
                        jsonVehicle.getString("manufacter"),
                        jsonVehicle.getString("length"),
                        jsonVehicle.getInt("max_atmosphering_speed"));
                arrayList.add(v);
            }

            return arrayList;

        } catch (JSONException ex) {
            Log.d("JSON", ex.getMessage());
        }

        return null;

    }

    public static ArrayList<Vehicles> loadVehicle(String pName) {

        try {
            HttpURLConnection connection = connect(pName);
            int response = connection.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                JSONObject json = new JSONObject(bytesIntoString(inputStream));
                ArrayList<Vehicles> vehicle = readJSONVehicle(json);
                return vehicle;
            }
        } catch (Exception ex) {
            Log.d("ERROR", ex.getMessage());
        }

        return null;
    }

    private static String bytesIntoString(InputStream inputStream) {

        byte[] buffer = new byte[1024];
        ByteArrayOutputStream bufferBoss = new ByteArrayOutputStream();
        int readedBytes;

        try {

            while ((readedBytes = inputStream.read(buffer)) != 1) {
                bufferBoss.write(buffer, 0, readedBytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
