package com.example.ewalletproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class cashInActivity extends AppCompatActivity {

    private Button sendButton;
    private EditText addedAmount;
    private Spinner bankSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cash_in);


        sendButton = findViewById(R.id.to_cash_in);
        addedAmount = findViewById(R.id.added_amount);
        bankSelection = findViewById(R.id.bank_selection);


        String[] banks = {"Select a bank", "BDO Unibank Inc. (Banco de Oro)", "Metrobank", "Philippine National Bank (PNB)", "Bank of the Philippine Islands (BPI)"};


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, banks);
        bankSelection.setAdapter(adapter);


        sendButton.setOnClickListener(v -> {
            String amountStr = addedAmount.getText().toString().trim();

            if (amountStr.isEmpty()) {
                addedAmount.setError("Enter an amount");
                return;
            }

            double cashInAmount = Double.parseDouble(amountStr);

            // Retrieve current balance
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            double currentBalance = Double.parseDouble(sharedPreferences.getString("amount", "0.00"));

            // Add the cash-in amount to the current balance
            double newBalance = currentBalance + cashInAmount;

            // Save the new balance
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("amount", String.format("%.2f", newBalance));
            editor.apply();

            Toast.makeText(this, "Cash In Successful!", Toast.LENGTH_SHORT).show();

            // Close Activity
            finish();
        });
    }
}
