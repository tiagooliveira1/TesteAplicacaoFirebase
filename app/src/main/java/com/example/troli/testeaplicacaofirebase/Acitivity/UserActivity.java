package com.example.troli.testeaplicacaofirebase.Acitivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.troli.testeaplicacaofirebase.Classes.Idioma;
import com.example.troli.testeaplicacaofirebase.Classes.NivelGraduacao;
import com.example.troli.testeaplicacaofirebase.Classes.Usuario;
import com.example.troli.testeaplicacaofirebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;


public class UserActivity extends Activity {

    private ProgressBar progressBarUsuario;
    private EditText txtUsuarioNome;
    private EditText txtDataNascimento;
    private Spinner spnNivelGraduacao;
    private Spinner spnIdioma;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        progressBarUsuario = findViewById(R.id.progressBarUsuario);
        txtUsuarioNome = findViewById(R.id.txtUsuarioNome);
        txtDataNascimento = findViewById(R.id.txtUsuarioDataNascimento);
        spnNivelGraduacao = findViewById(R.id.spnNivelGraduacao);
        spnIdioma = findViewById(R.id.spnIdioma);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("usuario");

        spnNivelGraduacao.setAdapter(new ArrayAdapter<NivelGraduacao>(this, android.R.layout.simple_list_item_1, NivelGraduacao.values()));
        spnIdioma.setAdapter(new ArrayAdapter<Idioma>(this, android.R.layout.simple_list_item_1, Idioma.values()));


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                System.out.println(dataSnapshot.getValue());
                //Usuario usuario = dataSnapshot.getValue(Usuario.class);
                txtUsuarioNome.setText(usuario.getNome());
                /*SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");

                String stringdate = dt.format(usuario.getDataNascimento());

                txtUsuarioNome.setText(stringdate); */
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void salvarUsuario(View view)
    {
        Date dtNascimento = new Date(2018,8,17);

        //SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        //String stringDate = dt.format(dtNascimento);

        NivelGraduacao nivelSelecionado =
                (NivelGraduacao) spnNivelGraduacao.getAdapter().getItem(spnNivelGraduacao.getSelectedItemPosition()
                );

        Idioma idiomaSelecionado =
                (Idioma) spnIdioma.getAdapter().getItem(spnIdioma.getSelectedItemPosition()
                );

        Usuario usuario = new Usuario("Tiago", dtNascimento, nivelSelecionado.ordinal(), idiomaSelecionado.getSigla());

        progressBarUsuario.setVisibility(progressBarUsuario.VISIBLE);
        mRef.setValue(usuario).addOnCompleteListener(UserActivity.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(UserActivity.this, "Dados de usuário salvo com sucesso.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UserActivity.this, "Erro ao salvar o usuário:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        progressBarUsuario.setVisibility(progressBarUsuario.GONE);
    }
}
