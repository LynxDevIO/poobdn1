package com.example.poobdn1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.poobdn1.model.Contato;

import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class EditarContato extends AppCompatActivity {
    private EditText nome, email, dataNasc, CPF, telefone1, telefone2,
            rua, quadra, lote, bairro, cidade, estado, CEP;
    private Button btnSalvar, btnExcluir, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_editar_contato);

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

        int index = getIntent().getIntExtra("index", -1);
        if (index != -1) {
            Contato contato = ListagemContatos.getRS().get(index);
            nome.setText(contato.getNome());
            email.setText(contato.getEmail());
            dataNasc.setText(contato.getDataNasc());
            CPF.setText(contato.getCPF());
            telefone1.setText(contato.getTelefone1());
            telefone2.setText(contato.getTelefone2());
            String[] endereco = contato.getEndereco().split(",");
            rua.setText(endereco[0].trim());
            quadra.setText(endereco[1].trim());
            lote.setText(endereco[2].trim());
            bairro.setText(endereco[3].trim());
            String[] cidadeEstadoCep = endereco[4].trim().split(" - ");
            cidade.setText(cidadeEstadoCep[0].trim());
            String[] estadoCep = endereco[5].trim().split(", ");
            estado.setText(estadoCep[0].trim());
            CEP.setText(estadoCep[1].trim());
        }

        btnSalvar = findViewById(R.id.btnSalvar);
        btnExcluir = findViewById(R.id.btnExcluir);
        btnCancelar = findViewById(R.id.btnCancelar);

        dataNasc.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            AtomicInteger dia = new AtomicInteger();
            AtomicInteger mes = new AtomicInteger();
            AtomicInteger ano = new AtomicInteger();
            dia.set(Calendar.DAY_OF_MONTH);
            mes.set(Calendar.MONTH);
            ano.set(Calendar.YEAR);

            DatePickerDialog data = new DatePickerDialog(this);
            data.setOnDateSetListener((view, year, month, day) -> {
                dia.set(day);
                mes.set(month);
                ano.set(year);
                dataNasc.setText(String.format(Locale.getDefault(), "%02d/%02d/%d", dia.get(), mes.get() + 1, ano.get()));
            });
            data.show();
        });

        btnSalvar.setOnClickListener(v -> {
            if (validarCampos()) {
                AlertDialog alert = new AlertDialog.Builder(this).setTitle("Deseja realmente modificar este contato?")
                        .setMessage("Selecione uma opção")
                        .setPositiveButton("Sim", (dialog, which) -> {
                            String endereco = rua.getText().toString() + ", " +
                                    quadra.getText().toString() + ", " +
                                    lote.getText().toString() + ", " +
                                    bairro.getText().toString() + ", " +
                                    cidade.getText().toString() + " - " +
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

                            ListagemContatos.getRS().set(index, contato);

                            Toast.makeText(this, "Contato alterado com sucesso!", Toast.LENGTH_SHORT).show();
                            finalizarAtividadeEVoltarParaAnterior();
                        })
                        .setNegativeButton("Não", (dialog, which) -> dialog.dismiss())
                        .create();
                alert.show();
            }
        });

        btnExcluir.setOnClickListener(v -> {
            ListagemContatos.getRS().remove(getIntent().getIntExtra("index", -1));
            AlertDialog alert = new AlertDialog.Builder(this).setTitle("Deseja excluir este contato?")
                    .setMessage("Selecione uma opção")
                    .setPositiveButton("Sim", (dialog, which) -> {
                        Toast.makeText(this, "Contato excluído com sucesso!", Toast.LENGTH_SHORT).show();
                        finalizarAtividadeEVoltarParaAnterior();
                    })
                    .setNegativeButton("Não", (dialog, which) -> dialog.dismiss())
                    .create();
            alert.show();
        });

        btnCancelar.setOnClickListener(v -> finalizarAtividadeEVoltarParaAnterior());
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
            telefone1.getText().toString().isEmpty() ||
            telefone2.getText().toString().isEmpty()) {

            isValid = false;

            nome.setError("Campo obrigatório");
            telefone1.setError("Campo obrigatório");

            Toast.makeText(this, "Preencha todos os campos obrigatórios para prosseguir.", Toast.LENGTH_SHORT).show();
        }
        return isValid;
    }
}
