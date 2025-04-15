package com.example.poobdn1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.poobdn1.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class ListagemContatos extends AppCompatActivity {
    private ListView lista;
    private Button btnCadastrarContato;
    private static final List<Contato> rs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_listagem_contatos);

        ArrayAdapter<Contato> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rs);

        lista = findViewById(R.id.lista);
        btnCadastrarContato = findViewById(R.id.btnCadastrarContato);

        lista.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        btnCadastrarContato.setOnClickListener(v -> {
            Intent intent = new Intent(this, CadastroContato.class);
            finish();
            startActivity(intent);
        });

        lista.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, EditarContato.class);
            AlertDialog alert = new AlertDialog.Builder(this).setTitle("Deseja editar o contato?")
                    .setMessage("Selecione uma opção")
                    .setPositiveButton("Sim", (dialog, which) -> {
                        intent.putExtra("index", position);
                        finish();
                        startActivity(intent);
                    })
                    .setNegativeButton("Não", null).create();
            alert.show();
        });
    }

    public static List<Contato> getRS() {
        return rs;
    }
}
