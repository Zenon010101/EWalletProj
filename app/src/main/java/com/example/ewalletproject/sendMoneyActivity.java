package com.example.ewalletproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class sendMoneyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_send_money);

        Button sendButton = findViewById(R.id.to_send);
        EditText amount_Input = findViewById(R.id.amount_input);
        EditText inputNumber = findViewById(R.id.number_input); // Change ID if needed

        inputNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});

        sendButton.setOnClickListener(v -> {

            String amount = amount_Input.getText().toString().trim();
            if (amount.isEmpty()) {
                amount_Input.setError("Enter an amount");
                return;
            }
            String number = inputNumber.getText().toString().trim();
            if (number.isEmpty()) {
                inputNumber.setError("Enter a number");
                return;
            }
            double sendAmount = Double.parseDouble(amount);

            // Retrieve current balance
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            double currentBalance = Double.parseDouble(sharedPreferences.getString("amount", "0.00"));

            if (sendAmount > currentBalance) {
                Toast.makeText(this, "Insufficient Balance", Toast.LENGTH_SHORT).show();
                return;
            }

            // Show confirmation dialog
            new MaterialAlertDialogBuilder(this, R.style.MyDialogTheme)
                    .setTitle("Confirm Number")
                    .setMessage("Are you sure you want to send money to: " + number + "?")
                    .setPositiveButton("Confirm", (dialog, which) -> {
                        double newBalance = currentBalance - sendAmount;
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("amount", String.format("%.2f", newBalance)); // Save new balance
                        editor.apply(); // Apply changes


                        Toast.makeText(this, "Money Sent", Toast.LENGTH_SHORT).show();

// Notify HomeFragment to update balance
                        Intent intent = new Intent();
                        intent.putExtra("updatedBalance", String.format("%.2f", newBalance));
                        setResult(RESULT_OK, intent);
                        finish(); // Close sendMoneyActivity
                    })
                    .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                    .show();
        });


    }

}