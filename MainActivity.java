package com.example.assignment6;

import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.toolbox.JsonObjectRequest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Import statements...

public class MainActivity extends AppCompatActivity {

    private void fetchData() {
        TextView textView = findViewById(R.id.textView);
        Log.i("Information", "Weather button pressed");

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.open-meteo.com/v1/forecast?latitude=30.28&longitude=-97.76&hourly=temperature_2m&temperature_unit=fahrenheit";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        textView.setText("Response is: " + response.substring(0, 500));
                        // JSON parser interpret and retrieve data

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });
        // Add request to Queue
        queue.add(stringRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fetchData();
            }
        });
    }


    // Go button click handler
    public void goButtonTap(View view) {
        EditText editTextOne = findViewById(R.id.editText1);
        TextView textViewTwo = findViewById(R.id.textView2);
        textViewTwo.setText(editTextOne.getText());
        Log.i("Information", "The go button pressed");
    }
    private double calculateAverage(List<Double> temperatures) {
        if (temperatures.isEmpty()) {
            return 0;
        }

        double sum = 0;
        for (double temperature : temperatures) {
            sum += temperature;
        }

        return sum / temperatures.size();
    }

    private void updateUI(double averageTemperature) {
        TextView averageTemperatureTextView = findViewById(R.id.textView);
        averageTemperatureTextView.setText("Average Temperature: " + averageTemperature + " °C");
    }

    private void handleNetworkError(String errorMessage) {
        Toast.makeText(MainActivity.this, "Network Error: " + errorMessage, Toast.LENGTH_SHORT).show();

        TextView errorTextView = findViewById(R.id.textView);
        errorTextView.setText("Network Error: " + errorMessage);
    }
}
