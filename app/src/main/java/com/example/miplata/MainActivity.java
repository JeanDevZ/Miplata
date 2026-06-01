package com.example.miplata;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.graphics.Color;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etCorreo, etPassword;
    Button btnLogin;
    TextView txtRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCorreo = findViewById(R.id.etCorreo);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtRegister = findViewById(R.id.txtRegister);

        String texto = "¿No tienes cuenta? Regístrate";
        SpannableString spannable = new SpannableString(texto);

        int inicio = texto.indexOf("Regístrate");
        int fin = inicio + "Regístrate".length();

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        };

        spannable.setSpan(clickableSpan, inicio, fin, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(Color.GREEN), inicio, fin, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        txtRegister.setText(spannable);
        txtRegister.setMovementMethod(LinkMovementMethod.getInstance());

        /* 🔐 BOTÓN LOGIN
        btnLogin.setOnClickListener(v -> {

            String correo = etCorreo.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (correo.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences prefs = getSharedPreferences("DatosUsuario", MODE_PRIVATE);

            String correoGuardado = prefs.getString("correo_guardado", "");
            String passGuardado = prefs.getString("pass_guardada", "");

            if (correo.equals(correoGuardado) && password.equals(passGuardado)) {

                Toast.makeText(this, "Login correcto", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
        });*/
        btnLogin.setOnClickListener(v -> {
            String correo = etCorreo.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (correo.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Datos fijos de prueba temporales para poder entrar
            if (correo.equals("admin") && password.equals("123456")) {
                Toast.makeText(this, "Login correcto", Toast.LENGTH_SHORT).show();

                // El salto al menú SOLO ocurre si los datos son correctos
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
                finish(); // Cierra el login para que no puedan regresar con el botón atrás
            } else {
                Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });

    }
}