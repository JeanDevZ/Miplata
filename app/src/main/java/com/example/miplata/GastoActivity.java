package com.example.miplata;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class GastoActivity extends AppCompatActivity {

    private EditText etMontoGasto, etFechaGasto;
    private Spinner spinnerCategoriaGasto;
    private Button btnGuardarGasto;

    // Saldo simulado actual en la cuenta (control estricto)
    private double saldoDisponibleActivo = 200.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasto);

        // Vinculación estricta usando camelCase
        etMontoGasto = findViewById(R.id.etMontoGasto);
        spinnerCategoriaGasto = findViewById(R.id.spinnerCategoriaGasto);
        etFechaGasto = findViewById(R.id.etFechaGasto);
        btnGuardarGasto = findViewById(R.id.btnGuardarGasto);

        String[] categorias = {"Comida", "Transporte", "Ocio", "Otros"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categorias);
        spinnerCategoriaGasto.setAdapter(adapter);

        etFechaGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(GastoActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                etFechaGasto.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        btnGuardarGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String montoStr = etMontoGasto.getText().toString().trim();
                String fechaStr = etFechaGasto.getText().toString().trim();

                if (montoStr.isEmpty() || fechaStr.isEmpty()) {
                    Toast.makeText(GastoActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                double montoGastoIngresado = Double.parseDouble(montoStr);

                // !!!!!!! VALIDACIÓN MATEMÁTICA DE EXCESO DE SALDO !!!!!!!
                if (montoGastoIngresado > saldoDisponibleActivo) {
                    dispararAlertaExceso(montoGastoIngresado);
                } else {
                    saldoDisponibleActivo -= montoGastoIngresado;
                    Toast.makeText(GastoActivity.this, "Gasto exitoso. Saldo restante: S/ " + saldoDisponibleActivo, Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }

    private void dispararAlertaExceso(double montoFaltante) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("🚨 ALERTA DE EXCESO");
        builder.setMessage("Operación cancelada.\n\nEl gasto de S/ " + montoFaltante + " supera tu saldo actual de S/ " + saldoDisponibleActivo + ".");
        builder.setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}