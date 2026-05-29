package com.example.miplata;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    Button btnIngreso, btnGasto;
    TextView txtSaldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnIngreso = findViewById(R.id.btnIngreso);
        btnGasto = findViewById(R.id.btnGasto);
        txtSaldo = findViewById(R.id.txtSaldo);

        btnIngreso.setOnClickListener(v -> {
            startActivity(new Intent(MenuActivity.this, IngresoActivity.class));
        });

        btnGasto.setOnClickListener(v -> {
            // vacío por ahora
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = getSharedPreferences("Finanzas", MODE_PRIVATE);
        float saldo = prefs.getFloat("saldo", 0);

        txtSaldo.setText("Saldo: S/ " + saldo);
    }
}