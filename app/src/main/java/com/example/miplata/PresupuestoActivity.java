package com.example.miplata;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PresupuestoActivity extends AppCompatActivity {

    // Componentes de la interfaz en camelCase
    private EditText etLimiteGastoMensual;
    private Button btnGuardarLimitePresupuesto;
    private TextView tvPorcentajeProgresoPresupuesto;
    private ProgressBar pbProgresoGastoMensual;

    private TextView tvTotalIngresosMensual, tvTotalGastosMensual, tvTotalRecibosMensual;

    private TextView tvEstadisticaComida, tvEstadisticaTransporte, tvEstadisticaOcio, tvEstadisticaOtros;
    private ProgressBar pbGraficoComida, pbGraficoTransporte, pbGraficoOcio, pbGraficoOtros;

    // Valores simulados para calcular el resumen de totales y los gráficos
    private double totalIngresos = 2500.00;
    private double totalRecibos = 255.60;

    // Desglose de gastos por categorías
    private double gastoComida = 300.00;
    private double gastoTransporte = 120.00;
    private double gastoOcio = 80.00;
    private double gastoOtros = 50.00;
    private double totalGastosAcumulados = gastoComida + gastoTransporte + gastoOcio + gastoOtros; // S/ 550.00

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presupuesto);

        // 1. Vinculación del módulo de establecimiento de presupuesto
        etLimiteGastoMensual = findViewById(R.id.etLimiteGastoMensual);
        btnGuardarLimitePresupuesto = findViewById(R.id.btnGuardarLimitePresupuesto);
        tvPorcentajeProgresoPresupuesto = findViewById(R.id.tvPorcentajeProgresoPresupuesto);
        pbProgresoGastoMensual = findViewById(R.id.pbProgresoGastoMensual);

        // 2. Vinculación del resumen de totales
        tvTotalIngresosMensual = findViewById(R.id.tvTotalIngresosMensual);
        tvTotalGastosMensual = findViewById(R.id.tvTotalGastosMensual);
        tvTotalRecibosMensual = findViewById(R.id.tvTotalRecibosMensual);

        // 3. Vinculación de los gráficos y etiquetas estadísticas
        tvEstadisticaComida = findViewById(R.id.tvEstadisticaComida);
        tvEstadisticaTransporte = findViewById(R.id.tvEstadisticaTransporte);
        tvEstadisticaOcio = findViewById(R.id.tvEstadisticaOcio);
        tvEstadisticaOtros = findViewById(R.id.tvEstadisticaOtros);

        pbGraficoComida = findViewById(R.id.pbGraficoComida);
        pbGraficoTransporte = findViewById(R.id.pbGraficoTransporte);
        pbGraficoOcio = findViewById(R.id.pbGraficoOcio);
        pbGraficoOtros = findViewById(R.id.pbGraficoOtros);

        // Cargar y mostrar los totales calculados
        mostrarResumenYGraficos();

        // Acción al registrar un nuevo tope de presupuesto
        btnGuardarLimitePresupuesto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularPresupuesto();
            }
        });
    }

    private void mostrarResumenYGraficos() {
        // Imprimir los datos en las etiquetas del resumen
        tvTotalIngresosMensual.setText("Total Ingresos: S/ " + totalIngresos);
        tvTotalGastosMensual.setText("Total Gastos: S/ " + totalGastosAcumulados);
        tvTotalRecibosMensual.setText("Total Recibos: S/ " + totalRecibos);

        // Configurar los nombres y montos en la sección estadística
        tvEstadisticaComida.setText("Comida (S/ " + gastoComida + ")");
        tvEstadisticaTransporte.setText("Transporte (S/ " + gastoTransporte + ")");
        tvEstadisticaOcio.setText("Ocio (S/ " + gastoOcio + ")");
        tvEstadisticaOtros.setText("Otros (S/ " + gastoOtros + ")");

        // Calcular porcentajes individuales para los mini gráficos de barras
        if (totalGastosAcumulados > 0) {
            pbGraficoComida.setProgress((int) ((gastoComida / totalGastosAcumulados) * 100));
            pbGraficoTransporte.setProgress((int) ((gastoTransporte / totalGastosAcumulados) * 100));
            pbGraficoOcio.setProgress((int) ((gastoOcio / totalGastosAcumulados) * 100));
            pbGraficoOtros.setProgress((int) ((gastoOtros / totalGastosAcumulados) * 100));
        }
    }

    private void calcularPresupuesto() {
        String limiteStr = etLimiteGastoMensual.getText().toString().trim();

        if (limiteStr.isEmpty()) {
            Toast.makeText(this, "Por favor, introduce un monto límite", Toast.LENGTH_SHORT).show();
            return;
        }

        double limiteEstablecido = Double.parseDouble(limiteStr);

        if (limiteEstablecido <= 0) {
            Toast.makeText(this, "El límite debe ser mayor a 0", Toast.LENGTH_SHORT).show();
            return;
        }

        // Calcular el porcentaje de dinero consumido del presupuesto total
        int porcentajeUtilizado = (int) ((totalGastosAcumulados / limiteEstablecido) * 100);

        // Evitar desbordamiento visual de la barra si el porcentaje excede el 100%
        if (porcentajeUtilizado > 100) {
            pbProgresoGastoMensual.setProgress(100);
            tvPorcentajeProgresoPresupuesto.setText("Progreso de consumo: " + porcentajeUtilizado + "% ¡Excedido!");
            tvPorcentajeProgresoPresupuesto.setTextColor(android.graphics.Color.RED);
        } else {
            pbProgresoGastoMensual.setProgress(porcentajeUtilizado);
            tvPorcentajeProgresoPresupuesto.setText("Progreso de consumo: " + porcentajeUtilizado + "%");
            tvPorcentajeProgresoPresupuesto.setTextColor(android.graphics.Color.parseColor("#666666"));
        }

        Toast.makeText(this, "Límite mensual actualizado con éxito", Toast.LENGTH_SHORT).show();
    }
}
