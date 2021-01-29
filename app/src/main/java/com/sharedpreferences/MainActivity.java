package com.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Button buttonSalvar;
    private TextInputEditText editNome;
    private TextView textRsultado;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";
    //static = usamos static para definirmos que esse atributo sempre vai ser o mesmo independente da instância
    // final = valor imutável

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSalvar = findViewById(R.id.buttonSalvar);
        editNome = findViewById(R.id.editNome);
        textRsultado = findViewById(R.id.textResultado);

        buttonSalvar.setOnClickListener(v -> {

            //é um arquivo, o getShared recebe dois parametos (o nome do arquivo e o modo de gravação)
            SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
            SharedPreferences.Editor editor = preferences.edit();

            //validar o nome
            if(editNome.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Preencha o nome", Toast.LENGTH_SHORT).show();
            } else {

                String nome = editNome.getText().toString();
                editor.putString("nome", nome);
                editor.apply();// o commit que salva os dados
                textRsultado.setText("Olá " + nome);
                //editor.commit();// o commit que salva os dados
               // editor.apply(); //o commit foi usado na aula para salvar os dados, mas a IDE trocou para apply
            }
        });

        //é um arquivo, o getShared recebe dois parametos (o nome do arquivo e o modo de gravação)
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);

        //valida se temos o nome em preferencias
        if(preferences.contains("nome")){

            String nome = preferences.getString("nome", "usuário não definido");
            textRsultado.setText("Olá " + nome);

        } else {
            textRsultado.setText("Olá, usuário não definido");
        }
    }
}