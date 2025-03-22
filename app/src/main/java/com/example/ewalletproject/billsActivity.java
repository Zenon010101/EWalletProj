package com.example.ewalletproject;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class billsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bills);

        // Find buttons by ID
        ImageButton electricButton = findViewById(R.id.electric_bill);
        ImageButton waterButton = findViewById(R.id.water_bill);

        // Load animations
        Animation clickAnimation = AnimationUtils.loadAnimation(this, R.anim.button_click);



        // Apply animation on click
        electricButton.setOnClickListener(v -> {
            v.startAnimation(clickAnimation);
            // TODO: Add functionality (like opening a new activity)
        });

        waterButton.setOnClickListener(v -> {
            v.startAnimation(clickAnimation);
        });

    }
}
