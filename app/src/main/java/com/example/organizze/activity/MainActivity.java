package com.example.organizze.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.organizze.R;
import com.example.organizze.activity.CadastroActivity;
import com.example.organizze.activity.LoginActivity;
import com.example.organizze.activity.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class MainActivity extends IntroActivity {

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        // Remove botão voltar
        setButtonBackVisible(false);

        // Remove botão adiantar
        setButtonNextVisible(false);

        // Slide 1
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_1)
                .build());
        // Slide 2
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_2)
                .build());

        // Slide 3
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_3)
                .build());

        // Slide 4
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_4)
                .build());

        // Slide Cadastro
        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_cadastro)
                .canGoForward(false)
                .build());
    }

    // Chama método para verificar se usuario esta logado
    @Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioLogado();
    }

    // Entrar no aplicativo
    public void btEntrar(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    // Cadastrar
    public void btCadastrar(View view){
        startActivity(new Intent(this, CadastroActivity.class));
    }

    // Verificar se usuário está logado
    public void verificarUsuarioLogado(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        if (autenticacao.getCurrentUser() != null){
            abrirTelaPrincipal();
        }
    }

    // Método para abrir tela principal
    public void abrirTelaPrincipal(){
        startActivity(new Intent(this, PrincipalActivity.class));
    }
}