package com.example.electricbillestimator;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText etUsage;
    private EditText etRebate;
    private Button btnCalculate;
    private Button btnClear;
    private Button btnAbout;
    private TextView tvFinalCost;
    private TextView tvTotalChargesAmount;
    private TextView tvTotalRebateAmount;
    private TextView tvFinalCostAmount;
    private TableLayout tblResult;
    private RadioGroup rgEnergySource;
    private RadioButton rbStandardElectricity;
    private RadioButton rbGreenEnergy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsage = findViewById(R.id.etUsage);
        etRebate = findViewById(R.id.etRebate);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);
        tvFinalCost = findViewById(R.id.tvFinalCost);
        tvTotalChargesAmount = findViewById(R.id.tvTotalChargesAmount);
        tvTotalRebateAmount = findViewById(R.id.tvTotalRebateAmount);
        tvFinalCostAmount = findViewById(R.id.tvFinalCostAmount);
        tblResult = findViewById(R.id.tblResult);

        
        
        btnCalculate.setOnClickListener(new View.OnClickListener() 
        {
            @Override
            public void onClick(View v) {
                calculateCharges();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInputs();
            }
        });

        btnAbout = findViewById(R.id.btnAbout); // Assuming you have a button with id btnAbout in your activity_main.xml

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start the AboutPage activity
                Intent intent = new Intent(MainActivity.this, AboutPage.class);
                startActivity(intent);
            }
        });


    }




    private void calculateCharges() {
        String usageStr = etUsage.getText().toString();
        String rebateStr = etRebate.getText().toString();

        if (usageStr.isEmpty() || rebateStr.isEmpty()) {
            Toast.makeText(this, "Please enter your electricity usage and rebate percentage", Toast.LENGTH_SHORT).show();
            return;
        }

        double usage;
        double rebate;
        try {
            usage = Double.parseDouble(usageStr);
            rebate = Double.parseDouble(rebateStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        if (rebate < 0 || rebate > 5) {
            Toast.makeText(this, "Rebate percentage should be between 0 and 5", Toast.LENGTH_SHORT).show();
            return;
        }

        double totalCharges = 0;

        if (usage <= 200) {
            totalCharges = usage * 21.8;
        } else if (usage <= 300) {
            totalCharges = (200 * 21.8) + ((usage - 200) * 33.4);
        } else if (usage <= 600) {
            totalCharges = (200 * 21.8) + (100 * 33.4) + ((usage - 300) * 51.6);
        } else if (usage <= 900) {
            totalCharges = (200 * 21.8) + (100 * 33.4) + (300 * 51.6) + ((usage - 600) * 54.6);
        } else {
            totalCharges = (200 * 21.8) + (100 * 33.4) + (300 * 51.6) + (300 * 54.6) + ((usage - 900) * 57.1);
        }

        // Convert total charges from sen to RM
        totalCharges /= 100;

        double totalRebate = totalCharges * rebate / 100;
        double finalCost = totalCharges - totalRebate;
        DecimalFormat df = new DecimalFormat("0.00");

        tvFinalCost.setText("RM " + df.format(finalCost));
        tvTotalChargesAmount.setText(df.format(totalCharges));
        tvTotalRebateAmount.setText(df.format(totalRebate));
        tvFinalCostAmount.setText(df.format(finalCost));

        tblResult.setVisibility(View.VISIBLE);

        // Hide the keyboard after calculation
        hideKeyboard();
    }

    private void clearInputs() {
        etUsage.setText("");
        etRebate.setText("");
        tvFinalCost.setText("");
        tvTotalChargesAmount.setText("");
        tvTotalRebateAmount.setText("");
        tvFinalCostAmount.setText("");
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}