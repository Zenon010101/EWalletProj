package com.example.ewalletproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    private EditText editText;
    private StringBuilder pinInput = new StringBuilder(); // To store the PIN input

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Ensure this matches your XML file name

        editText = findViewById(R.id.PIN);
        final Animation clickAnimation = AnimationUtils.loadAnimation(this, R.anim.button_click);

        // Button IDs for numbers
        int[] buttonIds = {
                R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3,
                R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_7,
                R.id.button_8, R.id.button_9
        };

        // Set click listeners for number buttons
        for (int id : buttonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(v -> {
                v.startAnimation(clickAnimation);
                pinInput.append(button.getText().toString()); // Append number
                editText.setText(pinInput.toString()); // Update EditText
            });
        }

        // Clear button
        Button clearButton = findViewById(R.id.clear_button);
        clearButton.setOnClickListener(v -> {
            pinInput.setLength(0); // Clear input
            editText.setText("");  // Clear EditText field
        });

        // OK button (for authentication)
        Button okButton = findViewById(R.id.ok_button);
        okButton.setOnClickListener(v -> {
            String enteredPIN = pinInput.toString().trim();

            if (enteredPIN.isEmpty()) {
                Toast.makeText(login.this, "Please enter your PIN", Toast.LENGTH_SHORT).show();
                return;
            }

            // Retrieve saved PIN from SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            String savedPin = sharedPreferences.getString("user_pin", "");

            if (enteredPIN.equals(savedPin)) {
                Toast.makeText(login.this, "Login successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(login.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close login screen
            } else {
                Toast.makeText(login.this, "Incorrect PIN", Toast.LENGTH_SHORT).show();
                pinInput.setLength(0); // Reset PIN input
                editText.setText("");  // Clear EditText field
            }
        });

        // Navigate to Sign Up screen
        Button signUpButton = findViewById(R.id.button_sign_up);
        signUpButton.setOnClickListener(v -> {
            Intent intent = new Intent(login.this, sign_up.class);
            startActivity(intent);
        });
    }
}
