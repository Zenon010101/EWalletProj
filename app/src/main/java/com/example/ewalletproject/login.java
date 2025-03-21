package com.example.ewalletproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.startAnimation(clickAnimation);
                    pinInput.append(button.getText().toString()); // Append number
                    editText.setText(pinInput.toString()); // Update EditText
                }
            });
        }

        // Clear button
        Button clearButton = findViewById(R.id.clear_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinInput.setLength(0); // Clear input
                editText.setText("");  // Clear EditText field
            }
        });

        // OK button (for further action like authentication)
        Button okButton = findViewById(R.id.ok_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredPIN = pinInput.toString();
                if(enteredPIN.isEmpty()){
                    Toast.makeText(login.this, "Please enter your PIN", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }
}