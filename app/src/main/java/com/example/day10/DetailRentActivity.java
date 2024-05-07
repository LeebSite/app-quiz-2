package com.example.day10;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailRentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_rent);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String carType = extras.getString("carType");
            int dayOfRent = extras.getInt("dayOfRent");
            double hargaMobil = extras.getDouble("hargaMobil");
            double biayaTambahan = extras.getDouble("biayaTambahan");
            double totalBiayaSewa = extras.getDouble("totalBiayaSewa");
            String sewaType = extras.getString("sewaType"); // Mendapatkan nilai sewaType

            String rentalType = "";
            if (biayaTambahan > 0) {
                rentalType = "Outside City: Rp ";
            } else {
                rentalType = "Inside City: Rp ";
            }

            TextView textViewCarType = findViewById(R.id.tvCarType);
            textViewCarType.setText("Car Type: " + carType);

            TextView textViewOutsideCity = findViewById(R.id.tvCity);
            textViewOutsideCity.setText(rentalType + String.format("%.0f", biayaTambahan));

            TextView textViewDayOfRent = findViewById(R.id.tvDayOfRent);
            textViewDayOfRent.setText("Day Of Rent: " + dayOfRent + " days");

            TextView textViewDiscount = findViewById(R.id.tvDiscount);
            double discount = 0;
            if (totalBiayaSewa > 10000000) {
                discount = totalBiayaSewa * 0.07;
            } else if (totalBiayaSewa > 5000000) {
                discount = totalBiayaSewa * 0.05;
            }
            textViewDiscount.setText("Discount: Rp " + String.format("%.0f", discount));

            TextView textViewTotal = findViewById(R.id.tvTotal);
            double totalBiayaSetelahDiskon = totalBiayaSewa - discount;
            textViewTotal.setText("Total: Rp " + String.format("%.0f", totalBiayaSetelahDiskon));

        }
    }
}
