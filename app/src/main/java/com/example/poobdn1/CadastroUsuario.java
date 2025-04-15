package com.example.poobdn1;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.poobdn1.model.Usuario;

public class CadastroUsuario extends AppCompatActivity {
    private EditText email, usuario, senha, confirmarSenha;
    private Button btnCadastrar, btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro_usuario);

        email = findViewById(R.id.email);
        usuario = findViewById(R.id.usuario);
        senha = findViewById(R.id.senha);
        confirmarSenha = findViewById(R.id.confirmarSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnVoltar = findViewById(R.id.btnVoltar);

        btnCadastrar.setOnClickListener(v -> {
            if (validarCampos()) {
                Usuario u = new Usuario(
                        email.getText().toString(),
                        usuario.getText().toString(),
                        senha.getText().toString()
                );
                AlertDialog alertDialog = new AlertDialog.Builder(this)
                        .setMessage("Usuário cadastrado com sucesso!")
                        .setTitle("CADASTRO COMPLETO!")
                        .setNeutralButton("OK", null).create();
                alertDialog.show();
                alertDialog.setOnDismissListener(dialog -> {
                    finalizarAtividadeEVoltarParaAnterior();
                });
            }
        });

        btnVoltar.setOnClickListener(v -> {
            finalizarAtividadeEVoltarParaAnterior();
        });
    }

    private void finalizarAtividadeEVoltarParaAnterior() {
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    private boolean validarCampos() {
        boolean valido = false;
        if (email.getText().length() == 0 ||
                usuario.getText().length() == 0 ||
                senha.getText().length() == 0 ||
                confirmarSenha.getText().length() == 0) {
            email.setError("Campo obrigatório!");
            usuario.setError("Campo obrigatório!");
            senha.setError("Campo obrigatório!");
            confirmarSenha.setError("Campo obrigatório!");
        } else {
            if (senha.getText().toString().equals(confirmarSenha.getText().toString())) {
                valido = true;
            } else {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this)
                        .setMessage("Senhas não são iguais!")
                        .setTitle("ERRO!");
                AlertDialog alertDialog = alertBuilder.create();
                alertDialog.show();
            }
        }
        return valido;
    }
}
