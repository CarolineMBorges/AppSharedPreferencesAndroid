package sharedpreferences.cursoandroid.com.sharedpreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText textNome;
    private TextView textoExibicao;
    private Button botaoSalvar;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textNome = findViewById(R.id.textoNomeId);
        textoExibicao = findViewById(R.id.textoExibicaoId);
        botaoSalvar = findViewById(R.id.botaoSalvarId);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                * Shared Preferences nos permite armazenar valores primitivos, como por exemplo, booleans, floats, ints,
                * longs e Strings a partir de uma chave.
                *
                * getSharedPreferences("user_preferences", MODE_PRIVATE);
                * 1º parâmetro: nome da shared preferences que queremos buscar.
                * 2º parâmetro: modo de acesso (Por defautl utilizamos o MODE_PRIVATE pois ele restringe o acesso apenas
                *  para a App que está chamando ou para todas as Apps que tiverem o mesmo user ID)
                */
                SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);

                /*
                * A partir do editor podemos adicionar um valor
                * */
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if(textNome.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Por favor preencher o nome", Toast.LENGTH_SHORT).show();
                }else{
                    editor.putString("nome", textNome.getText().toString());

                    /*
                    * O método commit() do editor salva a operação
                    * */
                    editor.commit();
                    textoExibicao.setText("Ola, " + textNome.getText().toString());
                }
            }
        });

        //recuperar os dados salvos
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
        if(sharedPreferences.contains("nome")){
            String nomeUsuario = sharedPreferences.getString("nome", "usuario não definido");
            textoExibicao.setText("Olá, "+ nomeUsuario);

        }else{
            textoExibicao.setText("Olá, usuário não definido");
        }
    }
}
