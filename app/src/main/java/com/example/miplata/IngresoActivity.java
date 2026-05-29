package com.example.miplata;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class IngresoActivity extends AppCompatActivity {

    EditText etMonto;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);

        etMonto = findViewById(R.id.etMonto);
        btnGuardar = findViewById(R.id.btnGuardarIngreso);

        btnGuardar.setOnClickListener(v -> {

            String montoTexto = etMonto.getText().toString();

            if (montoTexto.isEmpty()) {
                Toast.makeText(this, "Ingrese un monto", Toast.LENGTH_SHORT).show();
                return;
            }

            float monto = Float.parseFloat(montoTexto);

            // 🔥 SUMAR AL SALDO
            SharedPreferences prefs = getSharedPreferences("Finanzas", MODE_PRIVATE);
            float saldoActual = prefs.getFloat("saldo", 0);

            float nuevoSaldo = saldoActual + monto;

            prefs.edit().putFloat("saldo", nuevoSaldo).apply();

            Toast.makeText(this, "Ingreso agregado", Toast.LENGTH_SHORT).show();

            finish(); // regresar al menú
        });
    }
}