package com.example.troli.testeaplicacaofirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText editLogin;
    private EditText editSenha;

    private EditText editLoginCadastrar;
    private EditText editSenhaCadastrar;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editLogin = findViewById(R.id.editLogin);
        editSenha = findViewById(R.id.editSenha);

        editLoginCadastrar = findViewById(R.id.editLoginCadastrar);
        editSenhaCadastrar = findViewById(R.id.editSenhaCadastrar);

        mAuth = FirebaseAuth.getInstance();

    }

    public void logar(View view) {
        String login = editLogin.getText().toString();
        String senha = editSenha.getText().toString();

        mAuth.signInWithEmailAndPassword(login, senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Usuário logado!", Toast.LENGTH_SHORT).show();
                    exibirHello();
                } else {
                    Log.e("", "onComplete: Failed=" + task.getException().getMessage());
                    Toast.makeText(MainActivity.this, "Erro ao logar."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void exibirHello()
    {
        Intent intent = new Intent(this, HelloActivity.class);
        startActivity(intent);
    }

    public void cadastrar(View view) {
        String login = editLoginCadastrar.getText().toString();
        String senha = editSenhaCadastrar.getText().toString();

        mAuth.createUserWithEmailAndPassword(login, senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Usuário criado com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("", "onComplete: Failed=" + task.getException().getMessage());
                    Toast.makeText(MainActivity.this, "Erro ao criar o mizerável."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
