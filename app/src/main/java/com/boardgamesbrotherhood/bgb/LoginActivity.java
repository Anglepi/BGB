package com.boardgamesbrotherhood.bgb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

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
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}