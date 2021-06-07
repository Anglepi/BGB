package com.boardgamesbrotherhood.bgb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FirebaseAuth fa = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.login);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String registeredUser = getIntent().getExtras().getString("user");
        if(!registeredUser.isEmpty()){
            Snackbar.make(getWindow().getDecorView().getRootView(), "¡Gracias por unirte a BGB! Ya puedes iniciar sesión", Snackbar.LENGTH_LONG).show();
            EditText username = findViewById(R.id.textFieldUsername);
            username.setText(registeredUser);
        }

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = findViewById(R.id.textFieldUsername);
                EditText password = findViewById(R.id.textFieldPassword);

                login(username.getText().toString(),password.getText().toString());
            }
        });
    }

    private void login(String username, String password){
        fa.signInWithEmailAndPassword(username, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    authenticationSuccess();
                } else {
                    Snackbar.make(getWindow().getDecorView().getRootView(), "Usuario o clave incorrecta", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private void authenticationSuccess(){
        FirebaseUser fu = fa.getCurrentUser();
        Intent toMainActivity = new Intent(this, MainActivity.class);
        toMainActivity.setFlags(toMainActivity.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(toMainActivity);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}