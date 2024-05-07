package com.example.day10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGroupCarType;
    private RadioGroup radioGroupSewaType;
    private EditText editTextDayOfRent;
    private Button btnRent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroupCarType = findViewById(R.id.rgCarType);
        radioGroupSewaType = findViewById(R.id.rgOutsideCity);
        editTextDayOfRent = findViewById(R.id.etJumlahHari);
        btnRent = findViewById(R.id.btnRent);

        btnRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedCarTypeId = radioGroupCarType.getCheckedRadioButtonId();
                int selectedSewaTypeId = radioGroupSewaType.getCheckedRadioButtonId();
                if (selectedCarTypeId == -1 || selectedSewaTypeId == -1) {
                    return;
                }

                RadioButton selectedCarTypeRadioButton = findViewById(selectedCarTypeId);
                RadioButton selectedSewaTypeRadioButton = findViewById(selectedSewaTypeId);

                String carType = selectedCarTypeRadioButton.getText().toString();
                String sewaType = selectedSewaTypeRadioButton.getText().toString();

                String dayOfRentStr = editTextDayOfRent.getText().toString();
                int dayOfRent = 0;
                if (!dayOfRentStr.isEmpty()) {
                    dayOfRent = Integer.parseInt(dayOfRentStr);
                }

                double hargaSewaPerHari = 0;
                switch (carType) {
                    case "Pajero":
                        hargaSewaPerHari = 2400000;
                        break;
                    case "Alphard":
                        hargaSewaPerHari = 1550000;
                        break;
                    case "Innova":
                        hargaSewaPerHari = 850000;
                        break;
                    case "Brio":
                        hargaSewaPerHari = 550000;
                        break;
                }

                double biayaTambahan = 0;
                switch (sewaType) {
                    case "Outside":
                        biayaTambahan = 250000;
                        break;
                    case "Inside":
                        biayaTambahan = 0;
                        break;
                }

                double totalBiayaSewa = (hargaSewaPerHari * dayOfRent) + biayaTambahan;

                double diskon = 0;
                if (totalBiayaSewa > 10000000) {
                    diskon = 0.07 * totalBiayaSewa;
                } else if (totalBiayaSewa > 5000000) {
                    diskon = 0.05 * totalBiayaSewa;
                }

                totalBiayaSewa -= diskon;

                Intent intent = new Intent(MainActivity.this, DetailRentActivity.class);
                intent.putExtra("carType", carType);
                intent.putExtra("dayOfRent", dayOfRent);
                intent.putExtra("hargaSewa", hargaSewaPerHari);
                intent.putExtra("biayaTambahan", biayaTambahan);
                intent.putExtra("totalBiayaSewa", totalBiayaSewa);
                startActivity(intent);
            }
        });
    }
}
