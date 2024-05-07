package com.example.day10;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailRentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_rent);

        // Receive data from MainActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String carType = extras.getString("carType");
            int dayOfRent = extras.getInt("dayOfRent");
            double hargaSewa = extras.getDouble("hargaSewa");
            double biayaTambahan = extras.getDouble("biayaTambahan");
            double totalBiayaSewa = extras.getDouble("totalBiayaSewa");

            // Display data in TextViews
            TextView textViewCarType = findViewById(R.id.tvCarType);
            textViewCarType.setText("Car Type: " + carType);

            TextView textViewOutsideCity = findViewById(R.id.tvOutsideCity);
            textViewOutsideCity.setText("Outside City: " + (biayaTambahan > 0 ? "Yes" : "No"));

            TextView textViewDayOfRent = findViewById(R.id.tvDayOfRent);
            textViewDayOfRent.setText("Day Of Rent: " + dayOfRent + " days");

            TextView textViewDiscount = findViewById(R.id.tvDiscount);
            double discount = (hargaSewa * dayOfRent + biayaTambahan) * 0.05;
            textViewDiscount.setText("Discount: Rp " + String.format("%.0f", discount));

            TextView textViewTotal = findViewById(R.id.tvTotal);
            textViewTotal.setText("Total: Rp " + String.format("%.0f", totalBiayaSewa));
        }
    }
}
