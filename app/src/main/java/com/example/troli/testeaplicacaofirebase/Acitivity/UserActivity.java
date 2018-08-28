package com.example.troli.testeaplicacaofirebase.Acitivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.troli.testeaplicacaofirebase.Classes.Usuario;
import com.example.troli.testeaplicacaofirebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;


public class UserActivity extends Activity {

    private ProgressBar progressBarUsuario;
    private EditText txtUsuarioNome;
    private EditText txtDataNascimento;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        progressBarUsuario = findViewById(R.id.progressBarUsuario);
        txtUsuarioNome = findViewById(R.id.txtUsuarioNome);
        txtDataNascimento = findViewById(R.id.txtUsuarioDataNascimento);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("usuario");
    }

    public void salvarUsuario(View view)
    {
        Date dtNascimento = new Date(2018,8,17);
        //SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        //String stringDate = dt.format(dtNascimento);

        Usuario usuario = new Usuario("Tiago", dtNascimento, 1, "PT_BR");
        progressBarUsuario.setVisibility(progressBarUsuario.VISIBLE);
        mRef.setValue(usuario).addOnCompleteListener(UserActivity.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(UserActivity.this, "Carro salvo com sucesso.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UserActivity.this, "Erro ao salvar o carro:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        progressBarUsuario.setVisibility(progressBarUsuario.GONE);
    }
}
