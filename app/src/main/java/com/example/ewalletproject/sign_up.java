package com.example.ewalletproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class sign_up extends AppCompatActivity {


    private EditText inputNumber, inputUsername, inputPin;
    private Button buttonSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        inputNumber = findViewById(R.id.input_number);
        inputUsername = findViewById(R.id.input_username);
        inputPin = findViewById(R.id.input_pin);
        buttonSignUp = findViewById(R.id.button_sign_up);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = inputNumber.getText().toString().trim();
                String username = inputUsername.getText().toString().trim();
                String pin = inputPin.getText().toString().trim();

                if (number.isEmpty() || username.isEmpty() || pin.isEmpty()) {
                    Toast.makeText(sign_up.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pin.length() != 4) {
                    Toast.makeText(sign_up.this, "PIN must be 4 digits", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Save PIN using SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("user_pin", pin);
                editor.apply();

                Toast.makeText(sign_up.this, "Sign-up successful! Please log in.", Toast.LENGTH_SHORT).show();

                // Navigate to login screen
                Intent intent = new Intent(sign_up.this, login.class);
                startActivity(intent);
                finish();
            }
        });

    }
}