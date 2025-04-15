package com.example.poobdn1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.poobdn1.model.Contato;

import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class CadastroContato extends AppCompatActivity {
    private EditText nome, email, dataNasc, CPF, telefone1, telefone2,
            rua, quadra, lote, bairro, cidade, estado, CEP;
    private Button btnCadastrar, btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro_contato);

        // info
        nome = findViewById(R.id.nome);
        email = findViewById(R.id.email);
        dataNasc = findViewById(R.id.dataNasc);
        CPF = findViewById(R.id.CPF);
        telefone1 = findViewById(R.id.telefone1);
        telefone2 = findViewById(R.id.telefone2);

        // endereço
        rua = findViewById(R.id.rua);
        quadra = findViewById(R.id.quadra);
        lote = findViewById(R.id.lote);
        bairro = findViewById(R.id.bairro);
        cidade = findViewById(R.id.cidade);
        estado = findViewById(R.id.estado);
        CEP = findViewById(R.id.CEP);

        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnVoltar = findViewById(R.id.btnVoltar);

        dataNasc.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            AtomicInteger dia = new AtomicInteger();
            AtomicInteger mes = new AtomicInteger();
            AtomicInteger ano = new AtomicInteger();
            dia.set(calendar.get(Calendar.DAY_OF_MONTH));
            mes.set(calendar.get(Calendar.MONTH));
            ano.set(calendar.get(Calendar.YEAR));

            DatePickerDialog data = new DatePickerDialog(this);
            data.setOnDateSetListener((view, year, month, day) -> {
                dia.set(day);
                mes.set(month);
                ano.set(year);
                dataNasc.setText(String.format(Locale.getDefault(), "%02d/%02d/%d", dia.get(), mes.get() + 1, ano.get()));
            });
            data.show();
        });

        btnCadastrar.setOnClickListener(v -> {
            if (validarCampos()) {
                String endereco = rua.getText().toString() + ", " +
                                  quadra.getText().toString() + ", " +
                                  lote.getText().toString() + ", " +
                                  bairro.getText().toString() + ", " +
                                  cidade.getText().toString() + ", " +
                                  estado.getText().toString() + ", " +
                                  CEP.getText().toString();

                Contato contato = new Contato(
                    nome.getText().toString(),
                    dataNasc.getText().toString(),
                    CPF.getText().toString(),
                    email.getText().toString(),
                    telefone1.getText().toString(),
                    telefone2.getText().toString(),
                    endereco
                );

                ListagemContatos.getRS().add(contato);

                Toast.makeText(this, "Contato adicionado com sucesso!", Toast.LENGTH_SHORT).show();

                finalizarAtividadeEVoltarParaAnterior();
            }
        });

        btnVoltar.setOnClickListener(v -> {
            finalizarAtividadeEVoltarParaAnterior();
        });
    }

    private void finalizarAtividadeEVoltarParaAnterior() {
        Intent intent = new Intent(this, ListagemContatos.class);
        finish();
        startActivity(intent);
    }

    private boolean validarCampos() {
        boolean isValid = true;
        if (rua.getText().toString().isEmpty() ||
            quadra.getText().toString().isEmpty() ||
            lote.getText().toString().isEmpty() ||
            bairro.getText().toString().isEmpty() ||
            cidade.getText().toString().isEmpty() ||
            estado.getText().toString().isEmpty() ||
            CEP.getText().toString().isEmpty()) {

            isValid = false;

            rua.setError("Campo obrigatório");
            quadra.setError("Campo obrigatório");
            lote.setError("Campo obrigatório");
            bairro.setError("Campo obrigatório");
            cidade.setError("Campo obrigatório");
            estado.setError("Campo obrigatório");
            CEP.setError("Campo obrigatório");

            Toast.makeText(this, "Preencha todos os campos de endereço para prosseguir.", Toast.LENGTH_SHORT).show();
            return isValid;
        }
        if (nome.getText().toString().isEmpty() ||
            telefone1.getText().toString().isEmpty()) {

            isValid = false;

            nome.setError("Campo obrigatório");
            telefone1.setError("Campo obrigatório");

            Toast.makeText(this, "Preencha todos os campos obrigatórios para prosseguir.", Toast.LENGTH_SHORT).show();
        }
        return isValid;
    }
}
