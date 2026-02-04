package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    EditText cityInput;
    Button addButton, confirmButton, deleteButton;

    int selectedPosition = -1; // keeps track of selected city

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI references
        cityList = findViewById(R.id.city_list);
        cityInput = findViewById(R.id.cityInput);
        addButton = findViewById(R.id.addButton);
        confirmButton = findViewById(R.id.confirmButton);
        deleteButton = findViewById(R.id.deleteButton);

        // Initial data
        String[] cities = {
                "Lahore",
                "Karachi",
                "Islamabad",
                "Toronto",
                "New York"
        };

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(
                this,
                R.layout.content,
                R.id.city_name,
                dataList
        );

        cityList.setAdapter(cityAdapter);

        // Select a city
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
        });

        // Show input when Add is clicked
        addButton.setOnClickListener(v -> {
            cityInput.setVisibility(View.VISIBLE);
            confirmButton.setVisibility(View.VISIBLE);
        });

        // Confirm adding city
        confirmButton.setOnClickListener(v -> {
            String city = cityInput.getText().toString().trim();

            if (!city.isEmpty()) {
                dataList.add(city);
                cityAdapter.notifyDataSetChanged();
                cityInput.setText("");
            }

            cityInput.setVisibility(View.GONE);
            confirmButton.setVisibility(View.GONE);
        });

        // Delete selected city
        deleteButton.setOnClickListener(v -> {
            if (selectedPosition != -1) {
                dataList.remove(selectedPosition);
                cityAdapter.notifyDataSetChanged();
                selectedPosition = -1;
            }
        });
    }
}
