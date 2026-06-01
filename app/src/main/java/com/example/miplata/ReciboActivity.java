
package com.example.miplata;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class ReciboActivity extends AppCompatActivity {

    private EditText etNombreRecibo, etMontoRecibo, etFechaVencimiento;
    private Button btnGuardarRecibo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo);

        // Vinculación estricta usando camelCase
        etNombreRecibo = findViewById(R.id.etNombreRecibo);
        etMontoRecibo = findViewById(R.id.etMontoRecibo);
        etFechaVencimiento = findViewById(R.id.etFechaVencimiento);
        btnGuardarRecibo = findViewById(R.id.btnGuardarRecibo);

        // Desplegar calendario nativo
        etFechaVencimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(ReciboActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                etFechaVencimiento.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        btnGuardarRecibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombreRecibo.getText().toString().trim();
                String monto = etMontoRecibo.getText().toString().trim();
                String fecha = etFechaVencimiento.getText().toString().trim();

                if (nombre.isEmpty() || monto.isEmpty() || fecha.isEmpty()) {
                    Toast.makeText(ReciboActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ReciboActivity.this, "Recibo guardado correctamente", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}

