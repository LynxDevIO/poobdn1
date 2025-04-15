package com.example.poobdn1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView recuperar, cadastrar;
    private EditText usuario, senha;
    private Button btnAcessar;
    private final String SENHA_HARDCODED = "123123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_login);

        recuperar = findViewById(R.id.recuperar);
        usuario = findViewById(R.id.usuario);
        senha = findViewById(R.id.senha);
        btnAcessar = findViewById(R.id.btnAcessar);
        cadastrar = findViewById(R.id.cadastrar);

        cadastrar.setOnClickListener(v -> {
            Intent intent = new Intent(this, CadastroUsuario.class);
            finish();
            startActivity(intent);
        });

        btnAcessar.setOnClickListener(v -> {
            if (validarLogin()) {
                Intent intent = new Intent(this, ListagemContatos.class);
                finish();
                startActivity(intent);
            }
        });

        recuperar.setOnClickListener(v -> {
            Intent intent = new Intent(this, RecuperarLogin.class);
            finish();
            startActivity(intent);
        });
    }

    private boolean validarLogin() {
        if (usuario.getText().length() == 0 || senha.getText().length() == 0) {
            usuario.setError("Campo obrigatório!");
            senha.setError("Campo obrigatório!");
            return false;
        }
        if (!senha.getText().toString().equals(SENHA_HARDCODED)) { // mas do jeito que eu fiz (com a classe Usuario, não precisaria testar com essa senha hardcoded
            senha.setError("Senha errada!");
            return false;
        }
        return true;
    }
}