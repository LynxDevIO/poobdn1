package com.example.poobdn1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TelaErro extends AppCompatActivity {
    private TextView mensagemErro;
    private TextView detalhesErro;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_erro);

        mensagemErro = findViewById(R.id.mensagemErro);
        detalhesErro = findViewById(R.id.detalhesErro);
        btnVoltar = findViewById(R.id.btnVoltar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String mensagem = extras.getString("mensagem", "Ocorreu um erro inesperado");
            String detalhes = extras.getString("detalhes", "Detalhes do erro não disponíveis");
            
            mensagemErro.setText(mensagem);
            detalhesErro.setText(detalhes);
        }

        btnVoltar.setOnClickListener(v -> {
            finish();
        });
    }
}
