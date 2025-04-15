package com.example.poobdn1;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.poobdn1.model.Usuario;

public class RecuperarLogin extends AppCompatActivity {
    private EditText email;
    private Button btnRecuperar, btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_recuperacao);

        email = findViewById(R.id.email);
        btnRecuperar = findViewById(R.id.btnRecuperar);
        btnVoltar = findViewById(R.id.btnVoltar);

        btnRecuperar.setOnClickListener(v -> {
            if (validarCampo()) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this)
                        .setMessage("E-mail de recuperação enviado com sucesso!")
                        .setTitle("E-MAIL ENVIADO");
                AlertDialog alertDialog = alertBuilder.create();
                alertDialog.show();
            }
        });

        btnVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            finish();
            startActivity(intent);
        });
    }

    private boolean validarCampo() {
        boolean valido = true;
        if (email.getText().length() == 0) {
            email.setError("Campo obrigatório!");
            valido = false;
        }
        if (Usuario.getUsuarios().stream().noneMatch(u -> u.getEmail().equals(email.getText().toString()))) {
            Toast.makeText(this, "E-mail não encontrado.", Toast.LENGTH_SHORT).show();
            valido = false;
        }
        return valido;
    }
}
