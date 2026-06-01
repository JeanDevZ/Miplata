
package com.example.miplata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class verRecibosActivity extends AppCompatActivity {

    private RecyclerView rvListaRecibos;
    private List<Recibo> listaDeRecibos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_recibo);

        // Vincular componente usando camelCase
        rvListaRecibos = findViewById(R.id.rvListaRecibos);
        rvListaRecibos.setLayoutManager(new LinearLayoutManager(this));

        // Cargar datos simulados tal como pide tu flujo
        listaDeRecibos = new ArrayList<>();
        listaDeRecibos.add(new Recibo("Recibo de Luz (Enel)", "S/ 120.50", "15/06/2026"));
        listaDeRecibos.add(new Recibo("Recibo de Agua (Sedapal)", "S/ 45.20", "18/06/2026"));
        listaDeRecibos.add(new Recibo("Plan de Internet", "S/ 89.90", "22/06/2026"));

        // Asignar el adaptador a la lista
        ReciboAdapter adaptador = new ReciboAdapter(listaDeRecibos);
        rvListaRecibos.setAdapter(adaptador);
    }

    // --- CLASE OBJETO PARA MAPEAR LOS CAMPOS SOLICITADOS ---
    public static class Recibo {
        private final String nombre;
        private final String monto;
        private final String fecha;

        public Recibo(String nombre, String monto, String fecha) {
            this.nombre = nombre;
            this.monto = monto;
            this.fecha = fecha;
        }
    }

    // --- ADAPTADOR INTERNO PARA CONTROLAR EL RECYCLERVIEW ---
    private class ReciboAdapter extends RecyclerView.Adapter<ReciboAdapter.ReciboViewHolder> {

        private final List<Recibo> datos;

        public ReciboAdapter(List<Recibo> datos) {
            this.datos = datos;
        }

        @NonNull
        @Override
        public ReciboViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recibo, parent, false);
            return new ReciboViewHolder(vista);
        }

        @Override
        public void onBindViewHolder(@NonNull ReciboViewHolder holder, int position) {
            Recibo reciboActual = datos.get(position);
            holder.tvNombreServicio.setText(reciboActual.nombre);
            holder.tvMontoServicio.setText(reciboActual.monto);
            holder.tvFechaVencimientoServicio.setText("Vence: " + reciboActual.fecha);
        }

        @Override
        public int getItemCount() {
            return datos.size();
        }

        // Contenedor de las vistas del molde (camelCase estricto)
        class ReciboViewHolder extends RecyclerView.ViewHolder {
            TextView tvNombreServicio, tvMontoServicio, tvFechaVencimientoServicio;

            public ReciboViewHolder(@NonNull View itemView) {
                super(itemView);
                tvNombreServicio = itemView.findViewById(R.id.tvNombreServicio);
                tvMontoServicio = itemView.findViewById(R.id.tvMontoServicio);
                tvFechaVencimientoServicio = itemView.findViewById(R.id.tvFechaVencimientoServicio);
            }
        }
    }
}
