package com.boardgamesbrotherhood.bgb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
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

//        fa.signInWithEmailAndPassword("esdfe@hotmail.com", "contraseña").addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//              @Override
//              public void onSuccess(AuthResult authResult) {
//                  Snackbar.make(getWindow().getDecorView().getRootView(), "bien", Snackbar.LENGTH_LONG).show();
//              }
//          }
//        );
//
//        fa.createUserWithEmailAndPassword("test@test.com", "clavetest");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}