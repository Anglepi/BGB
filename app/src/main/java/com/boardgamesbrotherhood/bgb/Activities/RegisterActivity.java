package com.boardgamesbrotherhood.bgb.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.boardgamesbrotherhood.bgb.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FirebaseAuth fa = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.register);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = findViewById(R.id.textFieldRegisterUsername);
                EditText password = findViewById(R.id.textFieldRegisterPassword);
                EditText repeatedPassword = findViewById(R.id.textFieldRegisterRepeatPassword);

                String user = username.getText().toString();
                String pwd = password.getText().toString();
                String rptPwd = repeatedPassword.getText().toString();

                register(user, pwd, rptPwd);
            }
        });

    }

    public void register(String username, String password, String repeatedPassword){
        if(password.equals(repeatedPassword)){
            fa.createUserWithEmailAndPassword(username, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        succesfullyRegistered(username);
                    } else {
                        Snackbar.make(getWindow().getDecorView().getRootView(), "Ha ocurrido un error al hacer el registro", Snackbar.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            Snackbar.make(getWindow().getDecorView().getRootView(), "Las claves no coinciden", Snackbar.LENGTH_LONG).show();
        }

    }

    private void succesfullyRegistered(String username){
        Intent toLoginActivity = new Intent(this, LoginActivity.class);
        toLoginActivity.putExtra("user", username);
        toLoginActivity.setFlags(toLoginActivity.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(toLoginActivity);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}