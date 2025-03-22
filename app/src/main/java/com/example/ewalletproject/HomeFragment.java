package com.example.ewalletproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomeFragment extends Fragment {

    private TextView amountTextView;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ImageButton sendButton = view.findViewById(R.id.send_money_button);
        ImageButton cashInButton = view.findViewById(R.id.cash_in_button);
        ImageButton billsButton = view.findViewById(R.id.bills);
        ImageButton transferButton = view.findViewById(R.id.transfer_money);
        ImageButton QrCodeButton = view.findViewById(R.id.Qr_Code);
        amountTextView = view.findViewById(R.id.amount);

        // Apply animation
        applyClickAnimation(sendButton);
        applyClickAnimation(cashInButton);
        applyClickAnimation(billsButton);
        applyClickAnimation(transferButton);
        applyClickAnimation(QrCodeButton);

        // Button Click Listeners
        sendButton.setOnClickListener(v -> startActivity(new Intent(getActivity(), sendMoneyActivity.class)));
        billsButton.setOnClickListener(v -> startActivity(new Intent(getActivity(), billsActivity.class)));
        cashInButton.setOnClickListener(v -> startActivity(new Intent(getActivity(), cashInActivity.class)));
        transferButton.setOnClickListener(v -> startActivity(new Intent(getActivity(), transferActivity.class)));
        QrCodeButton.setOnClickListener(v -> startActivity(new Intent(getActivity(), qr_code_activity.class)));

        // Load saved amount when fragment is created
        loadAmount();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadAmount();  // Refresh amount when user returns to HomeFragment
    }

    private void loadAmount() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String amount = sharedPreferences.getString("amount", "0.00");
        amountTextView.setText("₱" + amount); // Update balance display
    }


    private void applyClickAnimation(ImageButton button) {
        button.setOnClickListener(v -> {
            v.animate().scaleX(0.9f).scaleY(0.9f).setDuration(100)
                    .withEndAction(() -> v.animate().scaleX(1f).scaleY(1f).setDuration(100));
        });
    }
}
