package com.example.organizze.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.organizze.R;
import com.example.organizze.activity.config.ConfiguracaoFirebase;
import com.example.organizze.activity.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivity extends AppCompatActivity {
    private EditText campoEmail, campoSenha;
    private Button botaoEntrar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campoEmail = findViewById(R.id.editAcesseEmail);
        campoSenha = findViewById(R.id.editAcesseSenha);
        botaoEntrar = findViewById(R.id.buttonEntrar);

        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();

                // Verifica se os campos foram preenchidos
                if (!textoEmail.isEmpty()){
                    if (!textoSenha.isEmpty()){
                         usuario = new Usuario();
                         usuario.setEmail(textoEmail);
                         usuario.setSenha(textoSenha);
                         validarLogin();

                    }else {
                        Toast.makeText(LoginActivity.this, "Digite a senha cadastrada!",
                                Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "Digite o nome de cadastrado!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // Método validar login
    public void validarLogin(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            abrirTelaPrinicipal();

                        }else {

                            String excecao = "";
                            try {
                                throw task.getException();
                            }catch (FirebaseAuthInvalidCredentialsException e){
                                excecao = "E-mail ou senha inválido!";
                            }catch (FirebaseAuthInvalidUserException e) {
                                excecao = "Usuário não cadastrado!";
                            }catch (Exception e){
                                excecao = "Erro ao logar!" + e.getMessage();
                                e.getStackTrace();
                            }

                            Toast.makeText(LoginActivity.this, excecao,
                                    Toast.LENGTH_LONG).show();

                        }
                    }
                });

    }

    // Método abrir tela principal
    public void abrirTelaPrinicipal(){
        startActivity(new Intent(this, PrincipalActivity.class));
        finish();
    }
}