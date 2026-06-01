package com.example.miplata;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    // Componentes del Dashboard adaptados a camelCase
    private Button btnIngreso, btnGasto, btnRegistrarRecibo, btnVerRecibos, btnPresupuesto, btnMetasAhorro, btnCerrarSesion;
    private TextView txtSaldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // 1. Vincular los componentes con sus IDs del XML
        txtSaldo = findViewById(R.id.tvSaldo);
        btnIngreso = findViewById(R.id.btnIngreso);
        btnGasto = findViewById(R.id.btnGasto);
        btnRegistrarRecibo = findViewById(R.id.btnRegistrarRecibo);
        btnVerRecibos = findViewById(R.id.btnVerRecibos);
        btnPresupuesto = findViewById(R.id.btnPresupuesto);
        btnMetasAhorro = findViewById(R.id.btnMetasAhorro);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        // 2. Configurar la navegación mediante expresiones Lambda (v -> {}) como las tuyas
        btnIngreso.setOnClickListener(v -> {
            startActivity(new Intent(MenuActivity.this, IngresoActivity.class));
        });

        btnGasto.setOnClickListener(v -> {
            startActivity(new Intent(MenuActivity.this, GastoActivity.class));
        });

        btnRegistrarRecibo.setOnClickListener(v -> {
            startActivity(new Intent(MenuActivity.this, ReciboActivity.class));
        });

        btnVerRecibos.setOnClickListener(v -> {
            startActivity(new Intent(MenuActivity.this, verRecibosActivity.class));
        });

        btnPresupuesto.setOnClickListener(v -> {
            startActivity(new Intent(MenuActivity.this, PresupuestoActivity.class));
        });

        btnMetasAhorro.setOnClickListener(v -> {
            Toast.makeText(MenuActivity.this, "Módulo de Metas en desarrollo", Toast.LENGTH_SHORT).show();
        });

        btnCerrarSesion.setOnClickListener(v -> {
            // Regresa a la pantalla de acceso limpiando el historial de navegación
            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Mantiene tu excelente lógica de refresco automático de saldo local
        SharedPreferences prefs = getSharedPreferences("Finanzas", MODE_PRIVATE);
        float saldo = prefs.getFloat("saldo", 0);
        txtSaldo.setText("Saldo: S/ " + saldo);
    }
}
