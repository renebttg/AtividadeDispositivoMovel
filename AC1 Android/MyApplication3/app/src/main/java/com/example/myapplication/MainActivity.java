package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] op = {
            "Qual desses países é o maior produtor de soja do mundo?",
            "Qual desses países possui a maior extensão territorial do planeta?",
            "Qual desses países tem a maior população mundial?",
            "Qual desses países tem o maior PIB nominal do mundo?",
            "Qual desses países é conhecido pela alta tecnologia e inovação no setor automotivo?"
    };

    String[] respostasCorretas = {
            "Brasil",
            "Russia",
            "China",
            "EUA",
            "Alemanha"
    };

    TextView txtQuestion;
    Spinner spinner;
    Button btnResp;
    Button btnReset;

    int contadorErros = 0;
    int contadorAcertos = 0;
    int perguntaIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buscarComponentes();


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.paises,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        atualizarPergunta();

        btnResp.setOnClickListener(view -> {
            String selectedOption = spinner.getSelectedItem().toString();
            String respostaCorreta = respostasCorretas[perguntaIndex];

            if (selectedOption.equals(respostaCorreta)) {
                Toast.makeText(this, "Acertou!", Toast.LENGTH_SHORT).show();
                contadorAcertos++;
            } else {
                Toast.makeText(this, "Errou! A resposta correta é: " + respostaCorreta, Toast.LENGTH_LONG).show();
                contadorErros++;
            }

            proximaPergunta();
        });

        btnReset.setOnClickListener(view -> {
            contadorAcertos = 0;
            contadorErros = 0;
            perguntaIndex = 0;
            atualizarPergunta();
            btnResp.setEnabled(true); // Habilita o botão de resposta novamente
        });
    }

    private void buscarComponentes() {
        txtQuestion = findViewById(R.id.txtPergunta);
        spinner = findViewById(R.id.spinner);
        btnResp = findViewById(R.id.btnResponder);
        btnReset = findViewById(R.id.btnReiniciar);
    }

    private void atualizarPergunta() {
        if (perguntaIndex < op.length) {
            txtQuestion.setText(op[perguntaIndex]);
        } else {
            Toast.makeText(this, "Quiz terminado! Você acertou " + contadorAcertos + " pergunta(s) e errou " + contadorErros + " pergunta(s).", Toast.LENGTH_LONG).show();
            btnResp.setEnabled(false);
        }
    }

    private void proximaPergunta() {
        perguntaIndex = (perguntaIndex + 1) % op.length;
        atualizarPergunta();
    }
}
